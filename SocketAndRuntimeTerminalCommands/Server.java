package SocketAndRuntime;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// IMPORTANT NOTE : RUN IN INTELLIJ or TERMINAL

public class Server {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;


    public static void main(String[] args){
        try {
            Server server = new Server();
            server.connectClient();
        }
        catch (Exception e){
            System.out.println("Client Exited !!!");
            System.out.println(e);
        }
    }

    public void connectClient(){
        try {
            serverSocket = new ServerSocket(Tcp.getPortNumber());
            System.out.println("Server is listening on port " + Tcp.getPortNumber());
            System.out.println("Waiting for CLIENT !!");
            clientSocket = serverSocket.accept();
            String clientIp = String.valueOf(clientSocket.getInetAddress());
            System.out.println("Connected to client : " + clientIp);

            while (!clientSocket.isClosed()) {
                readClientMessage();
            }
        }
        catch (Exception e){
            System.out.println("Client Exited !!!");
//            System.out.println(e);
        }
        finally {
            try {
                clientSocket.close();
                serverSocket.close();
                System.out.println("Socket is closed Sucessfully!!");
            }
            catch (Exception e){
//                System.out.println(e);
            }
        }
    }

    public void readClientMessage() throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String clientMessage;
        while ((clientMessage = bufferedReader.readLine()) != null) {
            System.out.println("Received: " + clientMessage);
            processClientRequest(clientMessage);
        }
    }

    public void processClientRequest(String clientRequest) throws IOException{
        String  messageToSend = "";
        switch (clientRequest){
            case "0":
                clientSocket.close();
                serverSocket.close();
                break;
            case "1":
                messageToSend = "amixer set Master mute";
                break;
            case "2":
                messageToSend = "amixer set Master 50%+";
                break;
            case "3":
                messageToSend = "amixer set Master 50%-";
                break;
            case "4":
                messageToSend = "ps aux";
                break;
            case "5":
                messageToSend = "nmcli networking off";
                break;
            case "6":
                messageToSend = "nmcli networking on";
                break;
            case "7":
                messageToSend = "espeak";
                break;
            case "8":
                messageToSend = "dpkg --get-selections";
                break;
            default:
                messageToSend = "Invalid choice !!";
        }
        sendResponseToClient(messageToSend);
    }

    public void sendResponseToClient(String messageToSend) throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream), true);
        printWriter.println(messageToSend);
    }

}