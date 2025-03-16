package ChatApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverThread implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;

    public ReceiverThread(Socket socket){
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}