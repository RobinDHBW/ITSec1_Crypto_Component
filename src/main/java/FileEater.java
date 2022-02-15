public class FileEater {
    private static final FileEater instance = new FileEater();
    public Port port;

    private FileEater() {
        this.port = new Port();
    }

    public static FileEater getInstance() {
        return instance;
    }

    public class Port implements IFileEater {

    }
}
