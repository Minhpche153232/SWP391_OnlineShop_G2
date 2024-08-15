/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.util.Random;

/**
 *
 * @author catmi
 */
public class Validate {
    public String RandomCode(){
        String code = "";
        Random rd = new Random();
        String chars = "0123456789";
        for (int i = 0; i < 6; i++) {
            code += chars.charAt(rd.nextInt(chars.length()));
        }
        return code;
    }
    public static void main(String[] args) {
        Validate v = new Validate();
        System.out.println(v.RandomCode());
    }
}
