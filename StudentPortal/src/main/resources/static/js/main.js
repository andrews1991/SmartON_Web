
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
	  $("#refcode").focusout(function(){
			var refcode = $("#refcode").val();
			if( refcode != null && refcode != "" ){
				var referalFlag = true;
				var cityCode = refcode.substring(0,3).match(/^[a-zA-Z]*$/)[0];
				var initial = refcode.substring(9,11).match(/^[a-zA-Z]*$/)[0];
				var dob =  refcode.substring(3,9).match(/^[0-9]*$/)[0];
				if( cityCode == null || cityCode.length != 3 ){
					referalFlag = false;
				}
				if( initial == null || initial.length != 2 ){
					referalFlag = false;
				}
				if( dob == null || dob.length != 6 ){
					referalFlag = false;
				}
				if( !referalFlag ){
					alert("Please enter a valid referal code");
				}
			}
		});
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
    
    function validateRegForm(tagme){
    	var userName = $('#userName').val();
    	var email = $('#email').val();
    	$.ajax({
    		url: './checkValidData',
    		type: 'POST',
    		data: 'userName='+userName+'&email='+email,
    		dataType: 'JSON',
    		success: function(data){
    			if(data.userName == 'valid' && data.email == 'valid'){
    				var formData = new FormData($('#registerForm')[0]);
    				$.ajax({
    		    		url: './registerUser',
    		    		type: 'POST',
    		    		data: formData,
    		    		dataType: 'TEXT',
    		    		processData: false,
    		    		contentType: false,
    		    		success: function(data){
    		    			$('#preloader').hide();
    		    			$('.close').click();
    		    	    	showAlert('success');
    		    	    	setTimeout(function() {
    		    	    		//sendmail();
    		    	    		}, 1000);
    		    		}
    		    	});
    				
    				// $http.get("http://localhost:8080/sendMail/regthetagacademy@gmail.com");
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
    function sendmail(){
    	$.ajax({
    		url: './sendMail',
    		type: 'POST',
    		data: email,
    		dataType: 'TEXT',
    		processData: false,
    		contentType: false,
    		success: function(data){
    			$('#preloader').hide();
    			$('.close').click();
    	    	showAlert('success');
    		}
    	});
    }
    $('#registerBtn').click(function(){
    	$('#preloader').show();
    	$('#registerForm').find('input[type=text],input[type=password],select').each(function(){
    		hideValidate($(this));
    	});
    	$('#userName').parent().attr('data-validate','Username is required');
    	$('#email').parent().attr('data-validate','Valid E-mail is required');
    	if(checkValidation()){
    		validateRegForm("registerBtn");
    	}
    	$('#preloader').hide();
    });
    
    $('.clearForm').click(function(){
    	clearFormData('registerForm');
    	$('#registerForm').find('input[type=text],input[type=password],select').each(function(){
    		hideValidate($(this));
    	});
    });
    
    $('#recover-submit').click(function(){
    	if(validate($('#forgotEmail')) == false){
            showValidate(input[i]);
            return false;
        }else{
        	var email = $('#forgotEmail').val();
        	$.ajax({
        		url: './forgotPassword',
	    		type: 'POST',
	    		data: 'forgotEmail='+email,
	    		dataType: 'JSON',
	    		success: function(data){
	    		}
        	});
        }
    	
    });
    
})(jQuery);

function clearFormData(selector){
	$('#'+selector).find('input[type=text],input[type=password],select').each(function(){
	    $(this).val('');
	});
}
