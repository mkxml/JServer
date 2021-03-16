import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URLConnection;

public class FileLoader {
  private static String rootPath = "wwwroot";
  public String mimeType = null;
  private BufferedInputStream is = null;

  public FileLoader(String path) {
    try {
      is = new BufferedInputStream(new FileInputStream(rootPath + path));
      mimeType = URLConnection.guessContentTypeFromStream(is);
    } catch (Exception e) {
      throw new Error("File not found");
    }
  }

  public byte[] getData() {
    try {
      return is.readAllBytes();
    } catch (Exception e) {
      throw new Error("Error reading file");
    }

  }

}
