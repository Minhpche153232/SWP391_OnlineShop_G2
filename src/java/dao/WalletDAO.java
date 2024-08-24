/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;
import models.*;

/**
 *
 * @author admin
 */
public class WalletDAO extends DBContext {

    public List<Bank> getBanks() {
        List<Bank> list = new ArrayList<>();
        try {
            String query = "select * from Bank";
            conn = new DBContext().conn;
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Bank b = new Bank();
                b.setBankCode(rs.getString("bankCode"));
                b.setBankName(rs.getString("bankName"));
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public BankAccount getBankAccount(int userId){
        BankAccount ba = new BankAccount();
        try {
            String query = """
                           select ba.*, b.bankName
                           from BankAccount ba
                           join Bank b on b.bankCode = ba.bankCode
                           where userId = ? """;
            conn = new DBContext().conn;
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while(rs.next()){
                ba.setBaId(rs.getInt("baId"));
                ba.setUserId(rs.getInt("userId"));
                ba.setCardOwner(rs.getString("cardOwner"));
                ba.setExpertDate(rs.getString("expertDate"));
                ba.setCardNumber(rs.getString("cardNumber"));
                ba.setBankCode(rs.getString("bankCode"));
                ba.setBalance(rs.getInt("balance"));
                ba.setBankName(rs.getString("bankName"));
            }
            return ba;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean linkBankAccount(BankAccount ba){
        try {
            String query = """
                           Insert into BankAccount(userId, cardOwner, expertDate, cardNumber, bankCode, balance)
                           values(?,?,?,?,?,?)
                           """;
            conn = new DBContext().conn;
            ps = conn.prepareStatement(query);
            ps.setInt(1, ba.getUserId());
            ps.setString(2, ba.getCardOwner());
            ps.setString(3, ba.getExpertDate());
            ps.setString(4, ba.getCardNumber());
            ps.setString(5, ba.getBankCode());
            ps.setInt(6, 2000000);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateBankBalance(int balance, int userId){
        try {
            String query = """
                           Update BankAccount set balance = ? where userId = ?
                           """;
            conn = new DBContext().conn;
            ps = conn.prepareStatement(query);
            ps.setInt(1, balance);
            ps.setInt(2, userId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAccountBalance(int balance, int userId){
        try {
            String query = """
                           Update [User] set balance = ? where userId = ?
                           """;
            conn = new DBContext().conn;
            ps = conn.prepareStatement(query);
            ps.setInt(1, balance);
            ps.setInt(2, userId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        WalletDAO dao = new WalletDAO();
        String test = "^(1[0-2]|[1-9])/([12][0-9]|3[01]|[1-9])$";
        String test2 = "[0-9]+";
        String input = "0000";
        if(input.matches(test2)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }
}
