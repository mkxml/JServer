import java.io.OutputStream;

public class RequestHandler {
  public HTTPResponse response = null;

  public RequestHandler(HTTPRequest request, OutputStream os) {
    if (!request.isValid) {
      throw new Error("Invalid request");
    }
    try {
      FileLoader fileLoader = new FileLoader(request.path);
      byte[] data = fileLoader.getData();
      response = new HTTPResponse(os);
      response.setStatus(HTTPStatus.OK);
      response.setContentType(fileLoader.mimeType);
      response.setData(data);
    } catch (Error e) {
      response = new HTTPResponse(os);
      response.setStatus(HTTPStatus.NOT_FOUND);
    }

  }

  public HTTPResponse getResponse() {
    return response;
  }
}
