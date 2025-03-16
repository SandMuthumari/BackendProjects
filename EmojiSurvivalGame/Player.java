package Assignment17;

public class Player{
     private static int health = 50;
     private static int energy = 40;
     static String userName;


    public static void main(String[] args){
        Player player = new Player();
        player.getHealthEnergyInventoryDetails();
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public void reduceHealth(int amountToReduce){  // To reduce energy / health
        health = (health >= amountToReduce) ? health -= amountToReduce : 0;  // Make sure to check doesn't go to negative values while reducing...
    }

    public void reduceEnergy(int amountToReduce){
        energy = (energy >= amountToReduce) ? energy -= amountToReduce : 0;  // Make sure to check doesn't go to negative values while reducing...
    }

    public void increaseHealth(int amountToIncrease){
        health += amountToIncrease;
    }

    public void increaseEnergy(int amountToIncrease){
        energy += amountToIncrease;
    }

    public void getHealthEnergyInventoryDetails(){
        Resources resources = new Resources();
        System.out.println("Health : " + health + " | " + "Energy : " + energy);
        System.out.print("Inventory : ");
        resources.getGatheredThing();
        System.out.println();
    }

    public void askUserName(){
        System.out.println("\n" + "Welcome to Emoji Survival Game !!!");
        System.out.print("Annyeong Player !!!" + "\n" + "May I have your name ? ");
        userName = ScannerObject.getScannerObject().nextLine();
        System.out.println("Welcome to this Game " + userName);
        System.out.println();
    }

}