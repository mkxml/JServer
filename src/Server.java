import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) throws Exception {
    try {
      ServerSocket ss = new ServerSocket(8080);
      while (true) {
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        HTTPRequest request = new HTTPRequest();

        String linha = null;
        while (request.state != RequestParsingState.DONE) {
          linha = br.readLine();
          request.feed(linha);
        }

        RequestHandler handler = new RequestHandler(request, os);

        HTTPResponse response = handler.getResponse();

        response.send();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
