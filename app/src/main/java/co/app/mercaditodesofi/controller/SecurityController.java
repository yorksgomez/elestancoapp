package co.app.mercaditodesofi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityController {

    public static String encryptToSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
