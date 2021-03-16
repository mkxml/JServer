import java.util.ArrayList;
import java.util.HashMap;

public class HTTPRequest {
  private HashMap<String, String> headerMap = new HashMap<>();
  private int bytesRead = 0;
  public int contentLength = 0;
  public int state = RequestParsingState.START;
  public String method = null;
  public String path = null;
  public ArrayList<Header> headers = new ArrayList<>();
  public String body = null;
  public boolean isValid = false;

  public HTTPRequest() {
  }

  public HTTPRequest(String request) {
    String[] lines = request.split("\n");
    for (String line : lines) {
      feed(line);
    }
  }

  public HTTPRequest(String[] lines) {
    for (String line : lines) {
      feed(line);
    }
  }

  public String getHeader(String name) {
    return headerMap.get(name);
  }

  public void feed(String line) {
    if (state == RequestParsingState.DONE) {
      return;
    }
    if (state == RequestParsingState.START) {
      method = HTTPMethod.parseMethod(line);
      path = URI.getPathFromHTTP(line);
      state = RequestParsingState.READING_HEADERS;
      return;
    }
    if (state == RequestParsingState.READING_HEADERS && !line.isEmpty()) {
      Header header = Header.parseHeader(line);
      headers.add(header);
      headerMap.put(header.key, header.value);
      return;
    }
    if (state == RequestParsingState.READING_HEADERS && line.isEmpty()) {
      String contentLengthStr = getHeader("Content-Length");
      if (contentLengthStr != null) {
        contentLength = Integer.parseInt(contentLengthStr);
      }
      if (method.equals(HTTPMethod.GET)) {
        isValid = true;
        state = RequestParsingState.DONE;
        return;
      }
      state = RequestParsingState.READING_BODY;
      return;
    }
    // if (state == RequestParsingState.READING_BODY) {
    // if (bytesRead < contentLength) {

    // } else {
    // state = RequestParsingState.DONE;
    // }
    // }
    state = RequestParsingState.DONE;
  }

}
