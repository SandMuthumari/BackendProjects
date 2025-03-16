package SocketAndRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//IMPORTANT NOTE : RUN IN INTELLIJ or TERMINAL

public class ExecuteTerminalCommand {

    public String runCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                output.append(line).append("\n");
                line = reader.readLine();
            }
            process.waitFor();
        }
        catch (Exception e) {
            output.append("Error executing command: ").append(e.getMessage()).append("\n");
        }
        return output.toString();
    }

    public void runEspeakCommand(String str , String toSpeak) {
        String[] command = {str, toSpeak};
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
