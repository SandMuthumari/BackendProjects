package GroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private ServerSocket serverSocket;
    private Socket socket;
    private String clientName;

    private BufferedReader bufferedReader;

    Thread infoThread;

    private Server server;

    private static ArrayList<Server> clients = new ArrayList<>();


    public Server(Socket socket){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public Server(){}


    public static void main(String[] args){
        Server server = new Server();
        server.connectToClients();
    }

    public Socket getSocket(){
        return socket;
    }

    public String toString(){
        return String.valueOf(socket);
    }
    
    public static int getPortNumber() {
    	return 2006;
    }

    public void connectToClients(){
        try {
            serverSocket = new ServerSocket(getPortNumber());
            System.out.println("Waiting for client to join ...");
            while (true){
                socket = serverSocket.accept();
                System.out.println("New Client joined");
                server = new Server(socket);

                Thread clientHandlerThread = new Thread(server);
                clientHandlerThread.start();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addToList(Socket socket){
        clients.add(new Server(socket));
    }

    @Override
    public void run() {
        try {
            CollectClientInfo clientInfo = new CollectClientInfo(socket);
            infoThread = new Thread(clientInfo);
            infoThread.start();
            infoThread.join();
            clientName = clientInfo.getClientName();

            while (true) {
                String clientMessage = bufferedReader.readLine();
                while (clientMessage != null) {
                    broadcastMessage(clientName + " : " + clientMessage);
                    System.out.println(clientName + " : " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("bye")) {
                        removeClient(socket);
                        break;
                    }
                    clientMessage = bufferedReader.readLine();
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }

    public void removeClient(Socket socket){
        for (int i = 0; i < clients.size(); i++){
            if (clients.get(i).getSocket().equals(socket)){
                clients.remove(i);
                System.out.println(clientName + " Client exited !!!");
            }
        }
    }

    public void broadcastMessage(String clientMessage) throws IOException{
        for (Server client : clients) {
            if (client.getSocket() != socket) {
                PrintWriter printWriter1 = new PrintWriter(client.getSocket().getOutputStream() , true);
                printWriter1.println(clientMessage);
            }
        }
    }

}

