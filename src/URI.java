public class URI {
  public static String getPathFromHTTP(String line) {
    String[] parts = line.split(" ");
    return parts[1];
  }
}
