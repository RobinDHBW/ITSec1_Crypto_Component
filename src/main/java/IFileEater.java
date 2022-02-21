import java.net.URL;

public interface IFileEater {
    String setPath(String path);
    Boolean encrypt();
    Boolean decrypt();
    Boolean rename(String appendix, Boolean shouldBeAdded);
}
