public class RequestParsingState {
  public static int START = 0;
  public static int READING_HEADERS = 1;
  public static int READING_BODY = 2;
  public static int DONE = 3;
}
