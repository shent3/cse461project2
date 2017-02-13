import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by tongshen on 2/12/17.
 */

public class HTTPProxy {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HTTPProxy <port>");
            System.exit(0);
        }
        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
            if (port < 0 || port > 65536) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.printf("Invalid port number: %s\n", args[0]);
            System.exit(0);
        }
        runProxy(port);
    }

    public static void runProxy(int port) {
        ServerSocket socket = null;
        Thread thread = null;
        try {
            socket = new ServerSocket(port);
            thread = new ClientThread(socket.accept());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("Starting proxy at port: %d\n", port);
        thread.run();
    }
}
