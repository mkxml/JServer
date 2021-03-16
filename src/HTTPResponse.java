import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HTTPResponse {
  public static String serverName = "JServer";
  public static String httpVersion = "HTTP/1.1";

  private OutputStream os;
  public ArrayList<Header> headers = new ArrayList<>();
  public int httpStatus = HTTPStatus.NONE;
  public String contentType = null;
  public byte[] data = null;

  public HTTPResponse(OutputStream os) {
    headers.add(new Header("Server", serverName));
    headers.add(new Header("Connection", "Close"));
    this.os = os;
  }

  public void setStatus(int httpStatus) {
    this.httpStatus = httpStatus;
  }

  public void setContentType(String type) {
    headers.add(new Header("Content-Type", type));
    this.contentType = type;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String resolveHeaders() {
    ArrayList<String> headerStrings = new ArrayList<>();
    for (Header header : headers) {
      headerStrings.add(header.toString());
    }
    return String.join("\n", headerStrings);
  }

  public void send() {
    String httpMessage = HTTPStatus.getMessage(httpStatus);
    if (httpMessage == null) {
      throw new Error("Invalid response");
    }
    String responseStatus = httpVersion + " " + Integer.toString(httpStatus) + " " + httpMessage + "\n";
    if (data != null) {
      headers.add(new Header("Content-Length", Integer.toString(data.length)));
    } else {
      headers.add(new Header("Content-Length", "0"));
    }
    try {
      PrintWriter pw = new PrintWriter(os);
      pw.write(responseStatus);
      pw.write(resolveHeaders());
      pw.write("\n");
      pw.write("\n");
      pw.flush();
      if (data != null) {
        os.write(data, 0, data.length);
        os.flush();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
