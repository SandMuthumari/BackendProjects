package ChatApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SandClient {
    private Socket socket;

    public static void main(String[] args){
        SandClient client = new SandClient();
        client.connectToServer();
    }

    public void connectToServer(){
        try {
            System.out.println("Connecting to server...\nPlease wait...");
            Thread.sleep(1000);
            socket = new Socket("localhost" , PortNumber.getPortNumber());
            System.out.println("You're connected to our chat ...\n");

            System.out.print("Enter your name  : ");
            String clientName = new Scanner(System.in).nextLine();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println(clientName);
            printWriter.flush();

            Thread receiveThread = new Thread(new ReceiverThread(socket));
            receiveThread.start();
            Thread senderThread = new Thread(new SenderThread(socket , clientName));
            senderThread.start();

        }
        catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

}