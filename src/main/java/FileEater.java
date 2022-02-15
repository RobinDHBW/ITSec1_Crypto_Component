import java.net.URL;

public class FileEater {
    private static final FileEater instance = new FileEater();
    public Port port;
    private URL path;

    private FileEater() {
        this.port = new Port();
    }


    public static FileEater getInstance() {
        return instance;
    }

    public URL innerSetPath(URL path){
        this.path = path;
        return path;
    }

    public Boolean innerEncrypt(){
        return null;
    }

    public Boolean innerDecypt(){
        return  null;
    }

    public class Port implements IFileEater {
        @Override
        public URL setPath(URL path) {
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
    }
}
