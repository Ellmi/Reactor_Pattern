import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        Runtime.getRuntime().addShutdownHook(new ShutDownHook(serverSocket));
        long listenTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - listenTime < 60000) {
            Socket socket = serverSocket.accept();
            int requestID = socket.getInputStream().read();
            if (requestID != -1){
                System.out.println("No. " + requestID + " request has came!");
            }
        }
        serverSocket.close();
    }
}
