package fileIO;

import java.io.File;

public interface IFileIO {
    String readFile(File file);
    Boolean writeFile(File file, String base64);
    void renameFile(File file, String appendix);
}
