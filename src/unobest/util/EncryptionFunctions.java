/*
 * Class taken from Security lecture code examples.
 *
 * Provides static methods for simple symmetric (public key)
 * cryptography.
 */
package unobest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionFunctions
{

    public static SecretKey keyForCipher(String cipher)
    {
        try
        {
            KeyGenerator kg = KeyGenerator.getInstance(cipher);
            SecretKey key = kg.generateKey();
            System.out.println("Using algorithm " + key.getAlgorithm());
            System.out.println("Key format " + key.getFormat());
            System.out.println("Key encoded " + key.getEncoded());
            EncryptionFunctions.displayKey(key);
            return key;

        } catch (NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
    
    
    
    public static SecretKey readKey()
    {
        String fileName = "SecretKey";
        
        try{
           FileReader fReader = new FileReader(fileName);
           BufferedReader bufferedReader = new BufferedReader(fReader);
           String key = bufferedReader.readLine();
           //System.out.println(key);
           byte[] keyDecoded = Base64Decode(key);
           SecretKey sKey = new SecretKeySpec(keyDecoded,"AES");
           return sKey;
        }
        catch(IOException ex)
        {
            System.out.println("Error");
            return null;
        }
        
    }

    // This method is provided to show an example of encryption
    // It returns a byte[], but could return an alternative format
    public static byte[] encipher(String cipher, String text, SecretKey key)
    {
        byte[] ciphertext;

        try
        {
            Cipher ecipher = Cipher.getInstance(cipher);
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            ciphertext = ecipher.doFinal(text.getBytes("UTF8"));
        } catch (Exception ex)
        {
            ex.printStackTrace();
            ciphertext = null;
        }

        return ciphertext;
    }

    // This method returns the plaintext or null if an exception occurs
    public static String decipher(String cipher, byte[] ciphertext, SecretKey key)
    {
        byte[] plaintext;

        try
        {
            Cipher ecipher = Cipher.getInstance(cipher);
            ecipher.init(Cipher.DECRYPT_MODE, key);
            plaintext = ecipher.doFinal(ciphertext);
            return new String(plaintext);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            ciphertext = null;
        }
        return null;
    }

    public static void displayKey(SecretKey s)
    {
        System.out.println("Key byte values:");
        byte[] b = s.getEncoded();

        for (byte x : b)
        {
            System.out.print(x + " ");
        }        

        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(b);
        System.out.println("\nBase 64 Encoded Key is " + base64);
    }
    
    //********************************************************************************************
    //The methods below were written by us.
    
        
//    public static void generateKey() throws FileNotFoundException, IOException 
//    {
//        String inputFile = "SecretKey";
//        FileOutputStream out = new FileOutputStream(inputFile);
//        SecretKey key = keyForCipher("AES");
//        byte[] b = key.getEncoded();
//        String keyEncoded = Base64Encode(b);
//        System.out.println("******" + keyEncoded);
//        out.write(keyEncoded.getBytes());
//        out.close();
//        
//    }
    
    public static String Base64Encode(byte[] b)
    {
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(b);
        return base64;
    }
    
    public static byte[] Base64Decode(String s) throws IOException
    {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(s);
        return b;
    }
    
    public static String encryptPassword (String s)
    {
         
          byte[] pWordBytes = EncryptionFunctions.encipher("AES", s, EncryptionFunctions.readKey());
          String passWord64 = EncryptionFunctions.Base64Encode(pWordBytes);
          return passWord64;
    }
      
}

