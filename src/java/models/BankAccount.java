/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class BankAccount {
    private int baId;
    private int userId;
    private String cardOwner;
    private String expertDate;
    private String cardNumber;
    private String bankCode;
    private int balance;
    private String bankName;
    
    public BankAccount() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBaId() {
        return baId;
    }

    public void setBaId(int baId) {
        this.baId = baId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getExpertDate() {
        return expertDate;
    }

    public void setExpertDate(String expertDate) {
        this.expertDate = expertDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "baId=" + baId + ", userId=" + userId + ", cardOwner=" + cardOwner + ", expertDate=" + expertDate + ", cardNumber=" + cardNumber + ", bankCode=" + bankCode + ", balance=" + balance + '}';
    }
    
}
