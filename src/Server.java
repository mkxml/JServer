import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
                PrintWriter pw = new PrintWriter(os);

                HTTPRequest request = new HTTPRequest(null);

                String linha = null;
                while ((linha = br.readLine()).length() != 0) {
                   request.feed(linha);
                }

                // Requisicao HTTP valida
                if (request.isValid) {

                }

                Date date = new Date();

                String body = "<html><body><h1>" + date.toString() + "</h1></body></html>\n";

                int length = body.length();

                String resposta = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html;\n" +
                "Server: FEEVALE\n" +
                "Connection: close\n" +
                "Content-Length:"+ length +"\n\n" +
                body;
    
                pw.print(resposta);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
