<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="config.*"%>
<%@page import="model.*"%>
<%@page import="dao.*"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //Begin process return from VNPAY
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = PaymentConfig.hashAllFields(fields);
            
        %>
        <div class="container">
            <div class="main-wrapper">
                <div class="error-box">
                    <h1 style="font-size: 100px;"><%
                        if (signValue.equals(vnp_SecureHash)) {
                            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                out.print("Transaction success");
                            }else{
                                out.print("Transaction failed");
                            }
                        } else {
                            out.print("Invalid signature");
                        }
                        %></h1>
                    <form action="#" method="">
                        <input type="hidden" name="errorCode" value="<%=request.getParameter("vnp_ResponseCode")%>">
                        <input type="hidden" name="amount" value="<%=Integer.parseInt(request.getParameter("vnp_Amount"))/100%>">
                        <button  class="btn btn-success">Back to Home</button>
                    </form>
                </div>
            </div>
        </div>  
    </body>
</html>
