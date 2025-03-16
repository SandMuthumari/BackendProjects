package ChatApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SenderThread implements Runnable{
    Scanner scan = new Scanner(System.in);
    private String clientName;
    private PrintWriter printWriter;


    public SenderThread(Socket socket , String clientName){
        this.clientName = clientName;
        try {
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Start typing ...");

        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        String clientMessage = scan.nextLine();
        while (!clientMessage.equalsIgnoreCase("bye")) {
            printWriter.println(clientMessage);
            clientMessage = scan.nextLine();
        }
        printWriter.println(clientMessage);
        System.out.println(clientName + " exited ...");
        System.exit(0);
    }

}

