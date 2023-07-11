import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer {
    public static final int SERVER_PORT = 8765;
    public static void main(String[] args) {
        try {

            final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            Socket sock = null;
            EchoThread thread = null;

            while (true) {
                sock = serverSocket.accept();
                thread = new EchoThread(sock);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
