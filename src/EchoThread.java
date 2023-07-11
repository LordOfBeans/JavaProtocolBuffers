import java.lang.Thread;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class EchoThread extends Thread {
    private final Socket socket;

    public EchoThread(Socket _socket) {
        socket = _socket;
    }

    public void run() {
        try {
            System.out.println("New connection from " + socket.getInetAddress() + ":" + socket.getPort());

            final InputStream input = socket.getInputStream();
            final OutputStream output = socket.getOutputStream();

            String text = null;
            while (true) {
                if (input.available() >  0) {
                    text = Proto.Message.parseDelimitedFrom(input).getText();
                    System.out.println("Recieved message: " + text);
                    Proto.Response.Builder response = Proto.Response.newBuilder();
                    if (text.equals("EXIT")) {
                        response.setStatus(Proto.Status.DISCONNECT);
                        socket.close();
                        break;
                    } else {
                        response.setStatus(Proto.Status.OK);
                    }
                    response.build().writeDelimitedTo(output);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
