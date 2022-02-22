package testFileEncryption;

import cryptoUnit.CryptoUnit;
import fileIO.FileIO;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestComponent {
    private FileIO fileIO = new FileIO();
    private CryptoUnit cryptoUnit = new CryptoUnit();
    private String path = ConfigurationUnitTest.instance.pathToAttack;

    @TestFactory
    public Stream<DynamicTest> testEncryption_Decryption() {
        ArrayList<DynamicTest> tests = new ArrayList<>();
        File folder = new File(this.path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                Boolean check = fileIO.writeFile(file, cryptoUnit.encryptAES256(fileIO.readFile(file)));
                tests.add(DynamicTest.dynamicTest("checkEncryption for " + file.getName(), () -> assertTrue(check)));
            }
        }
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                Boolean check = fileIO.writeFile(file, cryptoUnit.decryptAES256(fileIO.readFile(file)));
                tests.add(DynamicTest.dynamicTest("checkDecryption for " + file.getName(), () -> assertTrue(check)));
            }
        }
        return tests.stream();
    }

}
