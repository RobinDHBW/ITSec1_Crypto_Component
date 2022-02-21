package fileIO;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

public class FileIO implements IFileIO {
    @Override
    public String readFile(File file) {
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
            return null;
        }
    }

    @Override
    public Boolean writeFile(File file, String base64) {
        try {
            Files.write(file.toPath(), Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8)));
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
            return false;
        }
    }

    @Override
    public void renameFile(File file, String appendix) {

    }
}
