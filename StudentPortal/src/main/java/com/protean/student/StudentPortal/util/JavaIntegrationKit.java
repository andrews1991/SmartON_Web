package com.protean.student.StudentPortal.util;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.protean.student.StudentPortal.model.PaymentDetails;

//import sun.net.www.http.HttpClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
@Component
public class JavaIntegrationKit {

    private Integer error;

    public boolean empty(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public String hashCal(String type, String str) {
        byte[] hashseq = str.getBytes();
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hexString.toString();
    }

    public Map<String, String> hashCalMethod(PaymentDetails paymentDetails) {
        //response.setContentType("text/html;charset=UTF-8");
		String key = "rMKXzU";
        String salt = "LZDh8iBd";
        String action1 = "";
        //String base_url = "https://sandboxsecure.payu.in";
        String base_url = "https://secure.payu.in";
        error = 0;
        String hashString = "";
        
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> urlParams = new HashMap<String, String>();
         
        String txnid = "";
        if (empty(paymentDetails.getTxnid())) {
            Random rand = new Random();
            String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
            txnid = rndm;
            paymentDetails.setTxnid(txnid);
            txnid = hashCal("SHA-256", rndm).substring(0, 20);
        } else {
            txnid = paymentDetails.getTxnid();
        }
        
        String txn = "abcd";
        String hash = "";
        String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
        String hashSequence = "udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
        if (empty(paymentDetails.getHash())) {
            if (empty(key) || empty(txnid) || paymentDetails.getAmount() == 0 || empty(paymentDetails.getFirstname()) || empty(paymentDetails.getEmail()) || paymentDetails.getPhone() == 0 || empty(paymentDetails.getProductinfo()) || empty(paymentDetails.getSurl()) || empty(paymentDetails.getFurl()) || empty(paymentDetails.getService_provider())) {
                error = 1;
            } else {
            	
            	hashString = key + "|" + txnid + "|" + paymentDetails.getAmount() + "|" + paymentDetails.getProductinfo() + "|" + paymentDetails.getFirstname() + "|" + paymentDetails.getEmail() + "|";
            	urlParams.put("txnid", txnid);
            	urlParams.put("key", key);
                String[] hashVarSeq = hashSequence.split("\\|");
                for (String part : hashVarSeq) {
                    hashString = (empty(params.get(part))) ? hashString.concat("") : hashString.concat(params.get(part).trim());
                    urlParams.put(part, "");
                    hashString = hashString.concat("|");
                }
                hashString = hashString.concat(salt);
                hash = hashCal("SHA-512", hashString);
                action1 = base_url.concat("/_payment");
                String[] otherPostParamVarSeq = otherPostParamSeq.split("\\|");
                for (String part : otherPostParamVarSeq) {
                    urlParams.put(part, empty(params.get(part)) ? "" : params.get(part).trim());
                }

            }
        } else if (!empty(params.get("hash"))) {
            hash = paymentDetails.getHash();
            action1 = base_url.concat("/_payment");
        }

        urlParams.put("hash", hash);
        urlParams.put("action", action1);
        urlParams.put("hashString", hashString);
        return urlParams;
    }

    public static void trustSelfSignedSSL() {
        try {
            final SSLContext ctx = SSLContext.getInstance(
                    "TLS");
            final X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
// do nothing
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] xcs, final String string) throws CertificateException {
// do nothing
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLContext.setDefault(ctx);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
