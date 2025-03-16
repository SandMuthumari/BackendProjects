package ChatApp;

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
    private PrintWriter printWriter;

    private Thread infoThread;

    private Server server;

    private static ArrayList<Server> clients = new ArrayList<>();


    public Server(Socket socket , String clientName){
        try {
            this.socket = socket;
            this.clientName = clientName;

            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
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

    public String getClientName() {
        return clientName;
    }

    public String toString(){
        return socket + clientName;
    }

    public void connectToClients(){
        try {
            serverSocket = new ServerSocket(PortNumber.getPortNumber());
            System.out.println("Waiting for client to join ...");
            while (true){
                socket = serverSocket.accept();
//                System.out.println("org " + socket);

//                System.out.println(clientName + " is typing...");

//                Server server = new Server(socket);
//                clients.add(server);
//                System.out.println("first "+clients.getFirst());
                System.out.println("New Client joined");

//                askClientName(socket);
                server = new Server(socket,"client");

                Thread clientHandlerThread = new Thread(server);
                clientHandlerThread.start();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addToList(Socket socket , String clientName){
        clients.add(new Server(socket , clientName));
//        System.out.println(clients);
    }

    @Override
    public void run() {
        try {
            CollectClientInfo clientInfo = new CollectClientInfo(socket);
            infoThread = new Thread(clientInfo);
            infoThread.start();
            infoThread.join();
//                clients.add(server);
//                System.out.println("fi : "+clients.getFirst());
            clientName = clientInfo.getClientName();

            while (true) {
//                System.out.println(clientName);
                String clientMessage = bufferedReader.readLine();
                while (clientMessage != null) {
//                    broadcastMessage(clientName + " : " + clientMessage);
                    System.out.println(clientName + " : " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("bye") && !clientMessage.contains("@")) {
//                        System.out.println("break");
//                        System.out.println("who say bye    " + socket);
//                        System.out.println("org size " + clients.size());
                        broadcastMessageToAll("\n" + clientName + " exited ....\n");
                        removeClient(socket);
//                        System.out.println(clients.remove(socket) + "   " + socket);
//                        System.out.println(clients.size());
                        break;
                    }
                    if (!clientMessage.contains("@")){
                        broadcastMessageToAll(clientName + " : " + clientMessage);
                    }
                    if (clientMessage.contains("@")) {
//                        System.out.println("private message : " + clientMessage);
                        String[] arrayOfMessage = clientMessage.split("@");
                        String privateMessageToClient = arrayOfMessage[1];
//                        System.out.println("that client : " + privateMessageToClient);
                        privateBroadcastMessage(privateMessageToClient , arrayOfMessage[0]);
                    }
                    clientMessage = bufferedReader.readLine();
//                    else {
//                        clientMessage = bufferedReader.readLine();
//                    }
                }
//                System.out.println(clients.remove(server) + "   " + this);
//                System.out.println("clients : ");
//                for (Server client : clients) {
//                    System.out.println(client.getSocket());
//                }
//                System.out.println(clients.size());
//                    break;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }

    }

    public void removeClient(Socket socket){
//        System.out.println(socket);
        for (int i = 0; i < clients.size(); i++){
//            System.out.println(clients.get(i).getSocket());
            if (clients.get(i).getSocket().equals(socket)){
                clients.remove(i);
                System.out.println(clientName + " Client exited !!!");
                break;
//                System.out.println(clientName);
            }
        }
    }

    public void privateBroadcastMessage(String sendTo , String message) throws IOException {
        int found = -1;
//        PrintWriter printWriter1 = null;
//        System.out.println("message to : " + sendTo);
        for (Server client : clients){
            if (client.getClientName().equalsIgnoreCase(sendTo)){
                PrintWriter printWriter1 = new PrintWriter(client.getSocket().getOutputStream() , true);
                printWriter1.println("You got a private message from " + clientName + " : " + message);
//                printWriter1.println(message);
                printWriter.println("Send Successfully ...");
                found = 1;
                break;
            }
        }
        if (found == -1){
            printWriter.println("Unknown Client .... Message is not send to that person ...");
        }
    }

    public void broadcastMessageToAll(String clientMessage) throws IOException{
//        System.out.println("ins");
//        System.out.println("first : "+clients.getFirst());
        for (Server client : clients) {
//            System.out.println(client);
//            System.out.println(client.getSocket() + "  " + socket);
            if (client.getSocket() != socket) {
//                System.out.println("ins if");
                PrintWriter printWriter1 = new PrintWriter(client.getSocket().getOutputStream() , true);
                printWriter1.println(clientMessage);
            }
        }
    }

}

