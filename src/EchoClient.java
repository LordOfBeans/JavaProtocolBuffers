import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


public class EchoClient {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Requires server name or IP as argument");
            System.exit(-1);
        }

        try {
            final Socket sock = new Socket(args[0], EchoServer.SERVER_PORT);
            System.out.println("Connected to " + args[0] + " on port " + EchoServer.SERVER_PORT);

            final InputStream input = sock.getInputStream();
            final OutputStream output = sock.getOutputStream();

            Scanner scan = new Scanner(System.in);

            String text = null;
            Proto.Status response = null;
            while (text == null || !text.equals("EXIT")) {
                text = scan.nextLine();
                System.out.println("Sending message: " + text);
                Proto.Message.Builder message = Proto.Message.newBuilder().setText(text);
                message.build().writeDelimitedTo(output);
                response = Proto.Response.parseDelimitedFrom(input).getStatus();
                switch (response) {
                    case FAIL:
                        System.out.println("Server responded with default value: FAIL");
                        break;
                    case OK:
                        System.out.println("Server responded with OK");
                        break;
                    case DISCONNECT:
                        System.out.println("Server disconnected");
                        break;
                    case UNRECOGNIZED:
                        System.out.println("Client has unrecognized enum value");
                        break;
                }
            }
            scan.close();
            sock.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
