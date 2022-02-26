package fileIO;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;

public class FileIO implements IFileIO {
    @Override
    public String readFile(File file) {
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
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
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void renameFile(File file, String appendix, Boolean shouldBeAdded) {
        Boolean result = false;
        try {
            if (shouldBeAdded) {
                result = file.renameTo(new File(file.getAbsolutePath() + appendix));
            } else {
                String renameTo = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(appendix));
                result = file.renameTo(new File(renameTo));
            }

            if (!result) throw new Exception("Could not rename File: " + file.getAbsolutePath());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
