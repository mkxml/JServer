import java.util.HashMap;

public class HTTPStatus {
  public static int NONE = -1;
  public static int OK = 200;
  public static int NOT_FOUND = 404;
  public static int SERVER_ERROR = 500;

  public static String getMessage(int code) {
    HashMap<Integer, String> HTTPMessages = new HashMap<>();
    HTTPMessages.put(OK, "OK");
    HTTPMessages.put(NOT_FOUND, "NOT FOUND");
    HTTPMessages.put(SERVER_ERROR, "SERVER ERROR");
    return HTTPMessages.get(code);
  }
}
