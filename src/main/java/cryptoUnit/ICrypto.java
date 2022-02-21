package cryptoUnit;

public interface ICrypto {
    String encryptAES256(String plain);
    String decryptAES256(String cipher);
}
