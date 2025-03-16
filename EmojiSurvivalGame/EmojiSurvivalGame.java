package Assignment17;

public class EmojiSurvivalGame extends Player{

    public static void main(String[] args){
    	EmojiSurvivalGame game = new EmojiSurvivalGame();
        game.startGame();
    }

    public void startGame(){
        CountDownTimer countDownTimer = new CountDownTimer();
        DayManager dayManager = new DayManager();
        askUserName();
        System.out.println("===== Emoji Survival Game =====");
        System.out.println("Scenario : Sand Island");
        System.out.println("RULES : ");
        System.out.println("1) Decide how many days you want to play !!");
        System.out.println("2) You want to play this game for that days but if you choose 'END DAY' then you will lose the game .. Play till end of that day ...");
        System.out.println("3) You want to survive here for that days ... by not losing your HEALTH and ENERGY point ...");
        dayManager.askHowManyDays();
        System.out.print("\n" + "ARE YOU READY TO PLAY ? " + userName + "\n" + "If yes type 1 else 0 : ");
        String play = ScannerObject.getScannerObject().nextLine();

        if (play.equals("1")) {
            boolean isPlayerAlive = true;
            countDownTimer.startTimer();
            while (!dayManager.isGameEnd() && isPlayerAlive) {
                if (getHealth() <= 0){
                    System.out.println("You lose this GAME " + userName + " because your HEALTH is ZERO !!!" + "\n" + "PLAY AGAIN " + userName);
                    isPlayerAlive = false;
                }
                else if (getEnergy() <= 0) {
                    System.out.println("You lose this GAME " + userName + " because your ENERGY is ZERO !!!" + "\n" + "PLAY AGAIN " + userName);
                    isPlayerAlive = false;
                }
                else {
                    dayManager.startDay();
                    dayManager.increaseDay();
                }
            }
            if (isPlayerAlive && dayManager.isGameEnd()){
                System.out.println("WOW !!! 😱🤯 AMAZING " + userName + " !!!" + "\n" + "I think you're a unbeatable 💪 person because you win my GAME !!!" + "\n" +"Chughahaeyo " + userName + " !!!");
                System.out.println("In END : After surviving in this island you were rescued by my team 👥 ..." + userName);
                System.out.println("Thanks 🤗 for playing this game " + userName + " 👋😊");
            }
        }
        else {
            System.out.println("VISIT US AGAIN !!! " + userName + " 👋😊");
        }
    }

}