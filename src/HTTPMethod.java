public class HTTPMethod {
  public static String GET = "GET";
  public static String POST = "POST";
  public static String PUT = "PUT";
  public static String PATCH = "PATCH";
  public static String DELETE = "DELETE";
  public static String OPTIONS = "OPTIONS";

  public static String parseMethod(String line) {
    String[] parts = line.split(" ");
    return parts[0];
  }
}
