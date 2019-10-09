
(function ($) {
    "use strict";
	
	$(document).ready(function(){
      var date_input=$('.date'); 
      var options={
        format: 'mm/dd/yyyy',
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
	
	function showAlert(type){
		if(type == 'success'){
			$('.alertDiv').html('<div class="alert alert-success" style="text-align:center;"><strong>Success</strong> Data Saved Successfully.</div>');
		}
		$('.alertDiv').slideDown();
		$('.alertDiv').delay(2000).slideUp( "slow", function() {
		});
	}

     /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
    	var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });
    
    function checkValidation(){
    	var check = true;
    	var input = $('.validate-input1');
        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    }


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    function validateRegForm(){
    	var userName = $('#userName').val();
    	var email = $('#email').val();
    	$.ajax({
    		url: '/checkValidData',
    		type: 'POST',
    		data: 'userName='+userName+'&email='+email,
    		dataType: 'JSON',
    		success: function(data){
    			console.log('ok'+JSON.stringify(data));
    			if(data.userName == 'valid' && data.email == 'valid'){
    				var formData = new FormData($('#registerForm')[0]);
    				$.ajax({
    		    		url: '/registerUser',
    		    		type: 'POST',
    		    		data: formData,
    		    		dataType: 'TEXT',
    		    		processData: false,
    		    		contentType: false,
    		    		success: function(data){
    		    			$('.close').click();
    		    	    	showAlert('success');
    		    		}
    		    	});
    			}else{
    				if(data.userName == 'invalid'){
    					$('#userName').parent().attr('data-validate','Username is already taken');
    					showValidate($('#userName'));
    				}
    				if(data.email == 'invalid'){
    					$('#email').parent().attr('data-validate','E-mail is already taken');
    					showValidate($('#email'));
    				}
    			}
    		}
    	});
    }
    
    $('#registerBtn').click(function(){
    	$('#registerForm').find('input[type=text],input[type=password],select').each(function(){
    		hideValidate($(this));
    	});
    	$('#userName').parent().attr('data-validate','Username is required');
    	$('#email').parent().attr('data-validate','Valid E-mail is required');
    	if(checkValidation()){
    		validateRegForm();
    	}
    });
    
    $('.clearForm').click(function(){
    	clearFormData('registerForm');
    	$('#registerForm').find('input[type=text],input[type=password],select').each(function(){
    		hideValidate($(this));
    	});
    });
    
})(jQuery);

function clearFormData(selector){
	$('#'+selector).find('input[type=text],input[type=password],select').each(function(){
	    $(this).val('');
	});
}