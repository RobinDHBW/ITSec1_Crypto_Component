package cryptoUnit;

import configuration.Configuration;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class CryptoUnit implements ICrypto {
    private String key = Configuration.instance.aesEncryptionKEY;
    private String salt = Configuration.instance.getAesEncryptionSALT;

    @Override
    public String encryptAES256(String plain) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(new PBEKeySpec(this.key.toCharArray(), this.salt.getBytes(), 65536, 256)).getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            return Base64.getEncoder().encodeToString(cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    @Override
    public String decryptAES256(String cipherText) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(factory.generateSecret(new PBEKeySpec(this.key.toCharArray(), this.salt.getBytes(),65536, 256)).getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }
}
