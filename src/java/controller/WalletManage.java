/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import models.*;

/**
 *
 * @author admin
 */
public class WalletManage extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("currentUser");
        WalletDAO wdao = new WalletDAO();
        if (user != null) {
            if (action.equals("createAccount")) {
                if (wdao.getBankAccount(user.getUserId()).getBaId() != 0) {
                    response.sendRedirect("home");
                }
                sess.setAttribute("notification", "Please link your card to continue using wallet");
                List<Bank> listBank = wdao.getBanks();
                request.setAttribute("listBank", listBank);
                request.getRequestDispatcher("link-bankaccount.jsp").forward(request, response);
            } else if (action.equals("deposit")) {
                if (wdao.getBankAccount(user.getUserId()).getBaId() == 0) {
                    response.sendRedirect("wallet?action=createAccount");
                } else {
                    BankAccount ba = wdao.getBankAccount(user.getUserId());
                    List<Bank> listBank = wdao.getBanks();
                    request.setAttribute("listBank", listBank);
                    request.setAttribute("ba", ba);
                    request.getRequestDispatcher("deposit.jsp").forward(request, response);
                }
            } else if (action.equals("withdraw")) {
                if (wdao.getBankAccount(user.getUserId()).getBaId() == 0) {
                    response.sendRedirect("wallet?action=createAccount");
                } else {
                    BankAccount ba = wdao.getBankAccount(user.getUserId());
                    List<Bank> listBank = wdao.getBanks();
                    request.setAttribute("listBank", listBank);
                    request.setAttribute("ba", ba);
                    request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                }
            }
        } else {
            response.sendRedirect("home");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession sess = request.getSession();
        User user = (User) sess.getAttribute("currentUser");
        WalletDAO wdao = new WalletDAO();
        List<Bank> listBank = wdao.getBanks();
        if (user != null) {
            //Check format
            String expertDateFormat = "^(1[0-2]|[1-9])/([12][0-9]|3[01]|[1-9])$";
            String cardNumberFormat = "[0-9]+";
            String cardOwnerFormat = "[A-Z ]+";
            if (action.equals("linkAccount")) {
                //Get value
                String bankCode = request.getParameter("bankCode");
                String cardOwner = request.getParameter("cardOwner").replace("\\s+", " ").trim();
                String cardNumber = request.getParameter("cardNumber").trim();
                String expertDate = request.getParameter("expertDate").trim();
                //Set temp object
                BankAccount baTmp = new BankAccount();
                baTmp.setUserId(user.getUserId());
                baTmp.setBankCode(bankCode);
                baTmp.setCardNumber(cardNumber);
                baTmp.setCardOwner(cardOwner);
                baTmp.setExpertDate(expertDate);
                sess.setAttribute("baTmp", baTmp);
                //Check
                if (!cardNumber.matches(cardNumberFormat) || cardNumber.length() < 12) {
                    request.setAttribute("cardNumberErr", "We couldn't found card!");
                    request.setAttribute("listBank", listBank);
                    request.getRequestDispatcher("link-bankaccount.jsp").forward(request, response);
                } else if (!cardOwner.matches(cardOwnerFormat)) {
                    request.setAttribute("cardOwnerErr", "Wrong Card Owner Format!");
                    request.setAttribute("listBank", listBank);
                    request.getRequestDispatcher("link-bankaccount.jsp").forward(request, response);
                } else if (!expertDate.matches(expertDateFormat)) {
                    request.setAttribute("expertDateErr", "Wrong expert date format!");
                    request.setAttribute("listBank", listBank);
                    request.getRequestDispatcher("link-bankaccount.jsp").forward(request, response);
                } else {
                    String code = new Validate().RandomCode();
                    sess.setAttribute("codeLinkBA", code);
                    new Email().sendEmailToLinkAccount(user.getEmail(), code);
                    request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                }

            } else if (action.equals("checkCodeLinkAccount")) {
                String btnSubmit = request.getParameter("btnSubmit");
                if (btnSubmit.equals("resend")) {
                    String code = new Validate().RandomCode();
                    sess.setAttribute("codeLinkBA", code);
                    sess.setMaxInactiveInterval(300);
                    new Email().sendEmailToLinkAccount(user.getEmail(), code);
                    request.setAttribute("notification", "Code had been sent to you!");
                    request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                } else {
                    String code = (String) sess.getAttribute("codeLinkBA");
                    String codeInput = request.getParameter("codeCfmAccount");
                    if (code == null || code.isEmpty()) {
                        request.setAttribute("errorMessage", "Code is empty");
                        request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                    } else if (!code.equals(codeInput)) {
                        request.setAttribute("errorMessage", "Code is not correct");
                        request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                    } else {
                        BankAccount ba = (BankAccount) sess.getAttribute("baTmp");
                        wdao.linkBankAccount(ba);
                        sess.removeAttribute("baTmp");
                        sess.removeAttribute("codeLinkBA");
                        response.sendRedirect("wallet?action=createAccount");
                    }
                }
            } else if (action.equals("deposit")) {
                int amount = Integer.parseInt(request.getParameter("amount"));
                BankAccount ba = wdao.getBankAccount(user.getUserId());
                if (ba.getBalance() - amount < 50000) {
                    request.setAttribute("ErrorMess", "Balance in account isn't enough to make transaction!");
                    request.setAttribute("ba", ba);
                    request.getRequestDispatcher("deposit.jsp").forward(request, response);
                } else {
                    request.setAttribute("ba", ba);
                    sess.setAttribute("amount", amount);
                    sess.setAttribute("trans", "deposit");
                    String code = new Validate().RandomCode();
                    sess.setAttribute("codePay", code);
                    new Email().sendEmailToConfirmPay(user.getEmail(), code);
                    request.getRequestDispatcher("confirm-transaction.jsp").forward(request, response);
                }
            } else if (action.equals("withdraw")) {
                int amount = Integer.parseInt(request.getParameter("amount"));
                BankAccount ba = wdao.getBankAccount(user.getUserId());
                if (user.getBalance() - amount < 0) {
                    request.setAttribute("ErrorMess", "Balance in account isn't enough to make transaction!");
                    request.setAttribute("ba", ba);
                    request.getRequestDispatcher("deposit.jsp").forward(request, response);
                } else {
                    request.setAttribute("ba", ba);
                    sess.setAttribute("amount", amount);
                    sess.setAttribute("trans", "withdraw");
                    String code = new Validate().RandomCode();
                    sess.setAttribute("codePay", code);
                    new Email().sendEmailToConfirmPay(user.getEmail(), code);
                    request.getRequestDispatcher("confirm-transaction.jsp").forward(request, response);
                }
            } else if (action.equals("checkCodePay")) {
                BankAccount ba = wdao.getBankAccount(user.getUserId());
                String btnSubmit = request.getParameter("btnSubmit");
                int amount = (Integer) sess.getAttribute("amount");
                String trans = (String) sess.getAttribute("trans");
                if (btnSubmit.equals("resend")) {
                    String code = new Validate().RandomCode();
                    sess.setAttribute("codePay", code);
                    sess.setMaxInactiveInterval(300);
                    new Email().sendEmailToConfirmPay(user.getEmail(), code);
                    request.setAttribute("notification", "Code had been sent to you!");
                    request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                } else {
                    String code = (String) sess.getAttribute("codePay");
                    String codeInput = request.getParameter("codeCfmAccount");
                    if (code == null || code.isEmpty()) {
                        request.setAttribute("errorMessage", "Code is empty");
                        request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                    } else if (!code.equals(codeInput)) {
                        request.setAttribute("errorMessage", "Code is not correct");
                        request.getRequestDispatcher("confirm-bankaccount.jsp").forward(request, response);
                    } else {
                        if(trans.equals("deposit")){
                            wdao.updateAccountBalance(wdao.getBankAccount(user.getUserId()).getBalance() + amount, user.getUserId());
                            wdao.updateBankBalance(wdao.getBankAccount(user.getUserId()).getBalance() - amount, user.getUserId());
                            user.setBalance(user.getBalance() + amount);
                            sess.setAttribute("currentUser", user);
                            sess.removeAttribute("trans");
                            sess.removeAttribute("amount");
                            response.sendRedirect("home");
                        }else if(trans.equals("withdraw")){
                            wdao.updateAccountBalance(wdao.getBankAccount(user.getUserId()).getBalance() - amount, user.getUserId());
                            wdao.updateBankBalance(wdao.getBankAccount(user.getUserId()).getBalance() + amount, user.getUserId());
                            user.setBalance(user.getBalance() - amount);
                            sess.setAttribute("currentUser", user);
                            sess.removeAttribute("trans");
                            sess.removeAttribute("amount");
                            response.sendRedirect("home");
                        }
                    }
                }
            }
        } else {
            response.sendRedirect("home");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
