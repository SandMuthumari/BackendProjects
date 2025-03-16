package ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CollectClientInfo implements Runnable{
    private String clientName;
    private BufferedReader bufferedReader;
    PrintWriter printWriter;
    private Socket socket;

    public CollectClientInfo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream() ,true);
            clientName = bufferedReader.readLine();
            Server.addToList(socket , clientName);
            printWriter.println("\nWELCOME to Talk Talk Chat ...");
            printWriter.println("\nIf you want to talk in private chat with a person then type your message , use '@' and their name you want this message to send... (eg : Annyeong@sand) . Here 'Annyeong' is the place to type your message and after '@' mention their name ....");
            printWriter.println("To exit type bye ...\n");

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getClientName() {
        return clientName;
    }
}