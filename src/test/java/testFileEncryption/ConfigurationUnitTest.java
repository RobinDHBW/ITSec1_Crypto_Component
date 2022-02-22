package testFileEncryption;

public enum ConfigurationUnitTest {
    instance;
    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");

    public final String pathToAttack = userDirectory + fileSeparator + "TestEncryption" + fileSeparator;
}
