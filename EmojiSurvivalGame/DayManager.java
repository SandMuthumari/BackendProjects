package Assignment17;

import java.util.*;

public class DayManager extends Player{
    private int totalDay;
    private int currentDay = 0;
    private int dayTiming = 0;
    private final String[] dayTimings = {"Morning ☀️" , "Evening 🌄" , "Night 🌙"};


    public void askHowManyDays(){
        System.out.println("\n" + "In a day there will be MORNING ☀️ , EVENING 🌄 , NIGHT 🌙");
        System.out.print("At least you want choose 1 day ...." + "\n" + "Now type how many day you want to play this game : ");
        totalDay = ScannerObject.getScannerObject().nextInt();
    }

    public void startDay(){
        DoThings doThings =new DoThings();
        dayTiming = 0;
        while (dayTiming <= 2) {
            getHealthEnergyInventoryDetails();
            displayDayDetails();
            doThings.thingsToDo();
            increaseDayTiming();
            if (dayTiming == 2){
                eveningChallenge();
            }
        }
    }

    public void eveningChallenge(){
        String challenges[] = {"STORM" , "HEAVY RAIN" , "WILD ANIMAL"};
        String challenge = challenges[new Random().nextInt(challenges.length)];
        if (challenge.equals("STORM")){
            attackedMessage(challenge);
            System.out.println("⛈️");
        }
        if (challenge.equals("HEAVY RAIN")){
            System.out.println(Player.userName + " you're in danger !!!" + "\n" + challenge + " is approaching you ...");
            if (new CraftItems().isHaveShelter()){
                System.out.println("You hid in a SHELTER so you're safe " + Player.userName + " !!");
            }
            else {
                System.out.println("You don't have a SHELTER to hide . So you were sick of that rain 🌧️ ...");
                reduceEnergy(20);
                reduceHealth(30);
            }
        }
        if (challenge.equals("WILD ANIMAL")){
            attackedMessage(challenge);
            System.out.println("🦁");
        }
        System.out.println();
    }

    public void attackedMessage(String attackedBy){
        System.out.println(Player.userName + " you're in danger !!!" + "\n" + attackedBy + " is approaching you ...");
        System.out.println("You were attacked by " + attackedBy + " ...");
        reduceEnergy(25);
        reduceHealth(30);
    }

    public boolean isGameEnd(){
        return currentDay == totalDay;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getDayTiming() {
        return dayTiming;
    }

    public void increaseDayTiming(){
        dayTiming++;
    }

    public void increaseDay(){
        currentDay++;
    }

    public void displayDayDetails(){
        int currentUserDay = getCurrentDay();
        System.out.println("Day " + (++currentUserDay) + " - " + dayTimings[dayTiming]);
    }

}