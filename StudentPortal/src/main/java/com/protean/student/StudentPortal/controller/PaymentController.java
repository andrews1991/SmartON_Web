package com.protean.student.StudentPortal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.protean.student.StudentPortal.model.PaymentDetails;
import com.protean.student.StudentPortal.model.RegisterUserDetails;
import com.protean.student.StudentPortal.model.TransactionDetails;
import com.protean.student.StudentPortal.repository.PaymentDao;
import com.protean.student.StudentPortal.repository.RegistrationDao;
import com.protean.student.StudentPortal.service.PaymentService;
import com.protean.student.StudentPortal.service.StudentUserDetailsService;
import com.protean.student.StudentPortal.util.JavaIntegrationKit;

@Controller
public class PaymentController {
	
	@Autowired
	JavaIntegrationKit integrationKit;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	StudentUserDetailsService studentService;
	
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping("/securePay")
	@ResponseBody
	public String securePay(PaymentDetails paymentDetails) {
		
		String htmlResponse = "";
		Map<String, String> values = integrationKit.hashCalMethod(paymentDetails);
		/*
		 * JSONObject jsObj = new JSONObject(values); jsObj.put("amount",
		 * paymentDetails.getAmount()); jsObj.put("firstname",
		 * paymentDetails.getFirstname()); jsObj.put("email",
		 * paymentDetails.getEmail()); jsObj.put("phone", paymentDetails.getPhone());
		 * jsObj.put("productinfo", paymentDetails.getProductinfo()); jsObj.put("surl",
		 * paymentDetails.getSurl()); jsObj.put("furl", paymentDetails.getFurl());
		 */
        //PrintWriter writer = response.getWriter();
// build HTML code
		session.setAttribute("transactionId", values.get("txnid"));
        htmlResponse = "<div>"
                + "        <form id=\"payuform\" action=\"" + values.get("action") + "\"  name=\"payuform\" method=POST >\n"
                + "      <input type=\"hidden\" name=\"key\" value=" + values.get("key").trim() + ">"
                + "      <input type=\"hidden\" name=\"hash\" value=" + values.get("hash").trim() + ">"
                + "      <input type=\"hidden\" name=\"txnid\" value=" + values.get("txnid").trim() + ">"
                + "      <table>\n"
                + "        <tr>\n"
                + "          <td><b>Mandatory Parameters</b></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "         <td>Amount: </td>\n"
                + "          <td><input name=\"amount\" value=" + paymentDetails.getAmount() + " /></td>\n"
                + "          <td>First Name: </td>\n"
                + "          <td><input name=\"firstname\" id=\"firstname\" value=" + paymentDetails.getFirstname().trim() + " /></td>\n"
                + "        <tr>\n"
                + "          <td>Email: </td>\n"
                + "          <td><input name=\"email\" id=\"email\" value=" + paymentDetails.getEmail().trim() + " /></td>\n"
                + "          <td>Phone: </td>\n"
                + "          <td><input name=\"phone\" value=" + paymentDetails.getPhone() + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Product Info: </td>\n"
                + "<td><input name=\"productinfo\" value=" + paymentDetails.getProductinfo().trim() + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Success URI: </td>\n"
                + "          <td colspan=\"3\"><input name=\"surl\"  size=\"64\" value=" + paymentDetails.getSurl() + "></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Failure URI: </td>\n"
                + "          <td colspan=\"3\"><input name=\"furl\" value=" + paymentDetails.getFurl() + " size=\"64\" ></td>\n"
                + "        </tr>\n"
                + "\n"
                + "        <tr>\n"
                + "          <td colspan=\"3\"><input type=\"hidden\" name=\"service_provider\" value=\"payu_paisa\" /></td>\n"
                + "        </tr>\n"
                + "             <tr>\n"
                + "          <td><b>Optional Parameters</b></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Last Name: </td>\n"
                + "          <td><input name=\"lastname\" id=\"lastname\" value=" + values.get("lastname") + " ></td>\n"
                + "          <td>Cancel URI: </td>\n"
                + "          <td><input name=\"curl\" value=" + values.get("curl") + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Address1: </td>\n"
                + "          <td><input name=\"address1\" value=" + values.get("address1") + " ></td>\n"
                + "          <td>Address2: </td>\n"
                + "          <td><input name=\"address2\" value=" + values.get("address2") + " ></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>City: </td>\n"
                + "          <td><input name=\"city\" value=" + values.get("city") + "></td>\n"
                + "          <td>State: </td>\n"
                + "          <td><input name=\"state\" value=" + values.get("state") + "></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "          <td>Country: </td>\n"
                + "          <td><input name=\"country\" value=" + values.get("country") + " ></td>\n"
                + "          <td>Zipcode: </td>\n"
                + "          <td><input name=\"zipcode\" value=" + values.get("zipcode") + " ></td>\n"
                + "        </tr>\n"
                + "          <td>UDF1: </td>\n"
                + "          <td><input name=\"udf1\" value=" + values.get("udf1") + "></td>\n"
                + "          <td>UDF2: </td>\n"
                + "          <td><input name=\"udf2\" value=" + values.get("udf2") + "></td>\n"
                + " <td><input name=\"hashString\" value=" + values.get("hashString") + "></td>\n"
                + "          <td>UDF3: </td>\n"
                + "          <td><input name=\"udf3\" value=" + values.get("udf3") + " ></td>\n"
                + "          <td>UDF4: </td>\n"
                + "          <td><input name=\"udf4\" value=" + values.get("udf4") + " ></td>\n"
                + "          <td>UDF5: </td>\n"
               + "          <td><input name=\"udf5\" value=" + values.get("udf5") + " ></td>\n"
                 + "          <td>PG: </td>\n"
               + "          <td><input name=\"pg\" value=" + values.get("pg") + " ></td>\n"
                + "        <td colspan=\"4\"><input type=\"submit\" value=\"Submit\"  /></td>\n"
                + "      \n"
                + "    \n"
                + "      </table>\n"
                + "    </form>\n"
                + " <script> "
                + " document.getElementById(\"payuform\").submit(); "
                + " </script> "
                + "       </div>   ";
                //+ "  \n"
                //+ "  </body>\n"
                //+ "</html>";
// return response
        //writer.println(htmlResponse);
        return htmlResponse;
	}
	
	@RequestMapping("/paymentSuccess")
	public String paymentSuccess(TransactionDetails transactionDetails) {
		BufferedReader in = null;
		try {
			String txnId = session.getAttribute("transactionId").toString();
			String link = "https://www.payumoney.com/payment/op/getPaymentResponse?merchantKey=rMKXzU&merchantTransactionIds="+txnId;
			URL url = new URL(link);
	        HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
		    postConnection.setRequestMethod("POST");
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setRequestProperty("authorization","sgLy2LRIX0HqKl+CU8ENaVgv9lqhK4uAn5u9MKP9uCI=");
		    postConnection.setDoOutput(true);
		    int responseCode = postConnection.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		    in = new BufferedReader(new InputStreamReader(
		    postConnection.getInputStream()));
		    String inputLine;
		    StringBuffer res = new StringBuffer();
		    while ((inputLine = in .readLine()) != null) {
		          res.append(inputLine);
		    }
		    JSONObject jsObj = new JSONObject(res.toString());
		    JSONObject obj = new JSONObject();
		    obj = jsObj.getJSONArray("result").getJSONObject(0).getJSONObject("postBackParam");
		    transactionDetails.setMerchantTransactionId(obj.getString("txnid"));
		    transactionDetails.setMode(obj.getString("mode"));
		    transactionDetails.setPaymentId(String.valueOf(obj.getLong("paymentId")));
		    transactionDetails.setAmount(Double.parseDouble(obj.getString("amount")));
		    transactionDetails.setProductinfo(obj.getString("productinfo"));
		    transactionDetails.setPayor(obj.getString("firstname"));
		    transactionDetails.setMihpayid(obj.getString("mihpayid"));
		    transactionDetails.setCardNum(obj.getString("cardnum"));
		    transactionDetails.setStatus(obj.getString("status"));
		    transactionDetails.setCreatedOn(new Date());
		    transactionDetails.setUserMail(obj.getString("email"));
		    paymentService.savePaymentData(transactionDetails);
		    
		}catch(IOException io) {
			io.printStackTrace();
		}finally {
			try {
				in .close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.removeAttribute("transactionId");
		}
		return "login.jsp";
	}
	
	@RequestMapping("/paymentFailure")
	public String paymentFailure() {
		BufferedReader in = null;
		try {
			String txnId = session.getAttribute("transactionId").toString();
			String link = "https://www.payumoney.com/payment/op/getPaymentResponse?merchantKey=rMKXzU&merchantTransactionIds="+txnId;
			URL url = new URL(link);
	        HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
		    postConnection.setRequestMethod("POST");
		    postConnection.setRequestProperty("Content-Type", "application/json");
		    postConnection.setRequestProperty("authorization","sgLy2LRIX0HqKl+CU8ENaVgv9lqhK4uAn5u9MKP9uCI=");
		    postConnection.setDoOutput(true);
		    int responseCode = postConnection.getResponseCode();
		    System.out.println("POST Response Code :  " + responseCode);
		    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		    in = new BufferedReader(new InputStreamReader(
		    postConnection.getInputStream()));
		    String inputLine;
		    StringBuffer res = new StringBuffer();
		    while ((inputLine = in .readLine()) != null) {
		          res.append(inputLine);
		    }
		    System.out.println("STRING..."+res);
		    JSONObject jsObj = new JSONObject(res.toString());
		    JSONObject obj = new JSONObject();
		    obj = jsObj.getJSONArray("result").getJSONObject(0).getJSONObject("postBackParam");
		    System.out.println("JSON..."+obj);
		    RegisterUserDetails regDetails = studentService.getUserDetailsByMail(obj.getString("email"));
		    if(regDetails != null) {
		    	regDetails.setIsPremium("guest");
		    	studentService.updateUserDetails(regDetails);
		    }
		    
		}catch(IOException io) {
			io.printStackTrace();
		}finally {
			try {
				in .close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.removeAttribute("transactionId");
		}
		return "login.jsp";
	}

}
