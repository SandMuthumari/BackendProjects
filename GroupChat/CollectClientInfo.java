package GroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CollectClientInfo implements Runnable{
    private String clientName;
    private BufferedReader bufferedReader;
    private Socket socket;

    public CollectClientInfo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientName = bufferedReader.readLine();
            Server.addToList(socket);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getClientName() {
        return clientName;
    }
}