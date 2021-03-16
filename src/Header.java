public class Header {
  String key;
  String value;

  public static Header parseHeader(String headerString) {
    String[] parts = headerString.split(":");
    String key = parts[0].trim();
    String value = parts[1].trim();
    return new Header(key, value);
  }

  public Header() {
  }

  public Header(String key, String value) {
    setKey(key);
    setValue(value);
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return key + ": " + value;
  }
}
