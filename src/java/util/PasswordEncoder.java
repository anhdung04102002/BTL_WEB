/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Windows 10 TIMT
 */
public class PasswordEncoder {
    public static String encode(String password) throws NoSuchAlgorithmException{
        String salt = "nhom7";
        String saltedpassword = password + salt;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashed = messageDigest.digest(saltedpassword.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashed) {
//            System.out.println(b);
            stringBuilder.append(String.format("%02x", b));
        }
        return  stringBuilder.toString();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encode("123"));
    
    }
}
