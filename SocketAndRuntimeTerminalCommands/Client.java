package SocketAndRuntime;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//IMPORTANT NOTE : RUN IN INTELLIJ or TERMINAL

public class Client {
    ExecuteTerminalCommand executeTerminalCommand = new ExecuteTerminalCommand();
    Scanner scan = new Scanner(System.in);

    private String clientName;
    private Socket socket = null;


    public static void main(String[] args){
        Client client = new Client();
        client.connectToServer();
    }

    public void connectToServer() {
        try {
            socket = new Socket("localhost", Tcp.getPortNumber());
            System.out.println("Connected to server !!!");
            System.out.println("                                             Welcome to MY SYSTEM BUDDY !!" + "\n");
            clientName = getClientName();
            displayCommands();
            boolean isHalt = processUserInput();
            while (!isHalt) {
                readResponseFromServer();
                isHalt = processUserInput();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                socket.close();
                System.out.println("You lose the connection with Server !!");
                System.out.println("EXITED ...");
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    public String getClientName(){
        System.out.print("May I have your name : ");
        String name = scan.nextLine();
        System.out.println("Annyeong " + name + " !!" + "\n" + "Explore all the commands in MY SYSTEM BUDDY ..." + "\n");
        return name;
    }

    public void displayCommands(){
        System.out.println("1) Mute Volume");
        System.out.println("2) Increase Volume");
        System.out.println("3) Decrease Volume");
        System.out.println("4) List Running Processes");
        System.out.println("5) Disable Network");
        System.out.println("6) Enable Network");
        System.out.println("7) Send Text-to-Speech Message");
        System.out.println("8) List All Installed Software" + "\n");
    }

    public boolean processUserInput() throws IOException {
        boolean isHalt = true;
        System.out.println("Type that topic specific number you want to try " + clientName + " (E.g. Type 2)");
        System.out.println("To exit type 0");
        System.out.print("Now enter the number : ");
        String clientInput = scan.nextLine();
        System.out.println();
        if (!clientInput.equals("0")) {
            isHalt = false;
        }
        sendRequestToServer(clientInput);
        return isHalt;
    }

    public void sendRequestToServer(String messageToSend) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream), true);
        printWriter.println(messageToSend);
    }

    public void readResponseFromServer() throws IOException , InterruptedException{
        System.out.println("Loading Response " + clientName + " ...");
        InputStream inputStream = socket.getInputStream();
        BufferedReader messageFromServer = new BufferedReader(new InputStreamReader(inputStream));
        String response = messageFromServer.readLine();
        executeCommand(response);
    }

    public void executeCommand(String messageFromServer) throws InterruptedException {
        System.out.println("\nReceived command : " + messageFromServer);
        if (!messageFromServer.equalsIgnoreCase("espeak")) {
            String commandOutput = executeTerminalCommand.runCommand(messageFromServer);
            if (!commandOutput.isEmpty()){
                System.out.println("\nOutput of command : ");
                Thread.sleep(800);
                System.out.println(commandOutput);
            }
        }
        else {
            executeTerminalCommand.runEspeakCommand(messageFromServer, "Good");
        }
        System.out.println("Command Executed Successfully " + clientName + " !! \n");
    }

}