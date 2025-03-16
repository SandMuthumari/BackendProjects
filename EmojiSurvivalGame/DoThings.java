package Assignment17;

public class DoThings{
    Resources resources = new Resources();
    CraftItems craftItems = new CraftItems();

    public void thingsToDo(){
        System.out.println("what would you like to do ?");
        System.out.println("1. Gather resources");
        System.out.println("2. Buy Craft Items");
        System.out.println("3. End day" + "\n");
        System.out.print("Enter that specific number that you want to do ? ");
        int decidedNumber = ScannerObject.getScannerObject().nextInt();

        if (decidedNumber == 1){
            resources.resources(); // Call listResources in Resources class...
        }
        if (decidedNumber == 2){
            craftItems.displayItems();
        }
        if (decidedNumber == 3){
            System.out.println("You LOSE " + Player.userName);
            System.out.println("PLAY AGAIN " + Player.userName + " !!" + "\n" + "DON'T GIVE UP !!!");
            System.exit(0);
        }

    }

}