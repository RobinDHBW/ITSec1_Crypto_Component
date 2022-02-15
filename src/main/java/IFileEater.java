import java.net.URL;

public interface IFileEater {
    URL setPath(URL path);
    Boolean encrypt();
    Boolean decrypt();
}
