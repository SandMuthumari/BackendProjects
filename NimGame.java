package Assignment12;

import java.util.*;
//Don't try this in ECLIPSE because I use ESPEAK command it will not work in ECLIPSE....
class NimGame{
    String userName;
    String computerName = "SAND";
    String WinnerName;
    Random random = new Random();
    Scanner sc = new Scanner(System.in);
    int[] arr = {3 , 5};
    String[] sweetEmojiArray = {"🍧" , "🍦" , "🍬" , "🍨" , "🍩"};

    NimGame(String userName){
        this.userName = userName;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String userName = sc.nextLine();
        NimGame nimGame = new NimGame(userName);
        nimGame.startGame();
    }

    public void startGame(){
        String message = "";
        System.out.println("Are you Ready to play with me ?" + "\n" + "If yes type '1' else '0' ");
        int choice = sc.nextInt();
        while (choice != 1 && choice != 0){
            System.out.println("Invalid Input !!!");
            System.out.println("Are you Ready to play with me ?" + "\n" + "If yes type '1' else '0' ");
            choice = sc.nextInt();
        }
        if (choice == 1) {  // If user decide to play....
            System.out.println("It's great to see you..." + "\n" + "Annyeong I'M " + computerName + " , your Opponent !!!");
            message = "Let's play MY GAME ..." + "\n" + "Whoever taking last SWEET Win this game ...";
            textToSpeech(message);
            while (!isGameEnds()) {
                displayGame();
                playerTurn();
                if (isGameEnds()) {
                    message = "Hurray! " + userName + " win my Game";
                    textToSpeech(message);
                    break;
                }
                computerTurn();
                if (isGameEnds()) {
                    message = "Oops! " + WinnerName + " Wins .." + "\n" + "Don't give up " + userName + "\n" + "Try Again" + "\n" + "A candy 🍬 for you....";
                    textToSpeech(message);
                    break;
                }
            }
        }
        else {  // If user not decided to play ....
            message = "Ok ... BYE ... Visit me again.... ";
            textToSpeech(message);
        }
    }

    public void displayGame(){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i]; j++) {
                String sweetEmoji = sweetEmojiArray[j];
                if (arr[i] != 0) {
                    System.out.print(sweetEmoji + " ");
                }
            }
            if (arr[i] != 0){
                System.out.println();  // Leave a new line to run outer loop in next line if value in arr[i] != 0 ...
            }
        }
    }

    public void computerTurn(){
        int index = random.nextInt(2);
        int removeFromFirstNumber = random.nextInt(arr[0] + 1); // If array element becomes '0' then random nextInt argument is passed as '0' and it shows bound positive error .So I do +1 in that bound....
        int removeFromSecondNumber = random.nextInt(arr[1] + 1);
        if (removeFromFirstNumber < 1){    // And I do +1 outside random because if arr[0]=0 ,bound=0+1 = 1 then random num (0 , 1) will be '0' , I add +1 to removeFirstNumber & removeSecondNumber ,then I get greater than '0' value...
            removeFromFirstNumber++;
        }
        if (removeFromSecondNumber < 1){
            removeFromSecondNumber++;
        }
        int removeNumber = 0;
        if (index == 0){ //If computer gives '0' (index : 0)
            if (arr[0] < 1){ // check the number becomes '0' or not...
                arr[1] -= removeFromSecondNumber;
                removeNumber = removeFromSecondNumber;
            }
            else {
                arr[0] -= removeFromFirstNumber;
                removeNumber = removeFromFirstNumber;
            }
        } // end of index == 0
        else{ // else index == 1
            if (arr[1] < 1){ // check the number becomes '0' or not...
                arr[0] -= removeFromFirstNumber;
                removeNumber = removeFromFirstNumber;
            }
            else {
                arr[1] -= removeFromSecondNumber;
                removeNumber = removeFromSecondNumber;
            }
        } // end of index == 1
        System.out.println(computerName + " removed " + removeNumber + " element !!!");
        WinnerName = computerName; // change winner name
    }

    public void playerTurn(){
        int removeNumber = 0;
        if ((arr[0] > 0 && arr[1] < 1) || (arr[0] < 1 && arr[1] > 0)) {
            System.out.print("How many sweet you want to remove : ");
            removeNumber = sc.nextInt();
            int arrayHighNumber = (arr[0] < 1) ? arr[1] : arr[0];
            while (removeNumber < 1 || removeNumber > arrayHighNumber) {
                System.out.println("Invalid Input!!!");
                System.out.print("How many sweet you want to remove : ");
                removeNumber = sc.nextInt();
            }
            if (arr[0] > 0 && arr[1] < 1){
                arr[0] -= removeNumber;
            }
            if (arr[0] < 1 && arr[1] > 0){
                arr[1] -= removeNumber;
            }
            System.out.println(userName + " removed " + removeNumber + " element !!!");
            WinnerName = computerName; // change winner name
        }
        if (arr[0] > 0 && arr[1] > 0) {
            System.out.print("In which ROW you want to remove a sweet : ");
            int index = sc.nextInt();
            while (index != 1 && index != 2) {
                System.out.println("Invalid Input!!!");
                System.out.print("In which ROW you want to remove a sweet : ");
                index = sc.nextInt();
            }
            index--;
            System.out.print("How many sweet you want to remove : ");
            removeNumber = sc.nextInt();
            while (removeNumber < 1 || removeNumber > arr[index]){
                System.out.println("Invalid Input!!!");
                System.out.print("How many sweet you want to remove : ");
                removeNumber = sc.nextInt();
            }
            arr[index] -= removeNumber;
            System.out.println(userName + " removed " + removeNumber + " element !!!");
        }
        WinnerName = computerName; // change winner name
    }

    public boolean isGameEnds(){
        boolean isHalt = false;
        if (arr[0] < 1 && arr[1] < 1){
            isHalt = true;
        }
        return isHalt;
    }
    public void textToSpeech(String message) {
        try {
            String[] command = {"espeak", message};
            Runtime.getRuntime().exec(command);
            System.out.println(message);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}