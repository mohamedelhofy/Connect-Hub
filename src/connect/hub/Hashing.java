/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 *
 * @author rawan
 */
public  class  Hashing {
    /// hashing the password using ("SHA-256")
   public  String hash(String password) throws NoSuchAlgorithmException{
       
       try{
       ///1-create instance of SHA-256 ////////////
       MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
       ///2-hash//array of digested bytes of password/////
       byte [] hash = messageDigest.digest();
       
       // 3- convert the byte array into hexadeciaml String
       StringBuilder hexaString = new StringBuilder();
       
       // looping on each byte in hash array to add it to hexastring
       for (byte b : hash){
           hexaString.append(String.format("%02x" , b));
       }
       
       return hexaString.toString();
       
   }
   catch( NoSuchAlgorithmException e){
    throw new RuntimeException(e);
}
   }
   
   public boolean verifyPassword(String password , String hashedPasswors){
       return password.equals(password);
   }
}
