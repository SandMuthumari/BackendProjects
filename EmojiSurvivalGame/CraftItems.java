package Assignment17;

public class CraftItems {
    private static boolean haveShelter;


    public boolean isHaveShelter() {
        return haveShelter;
    }

    public void displayItems(){
        System.out.println("1. Shelter 🛖 (Requires : Wood x 4 + Water x 1)");
        System.out.println("2. CampFire 🪵🔥 (Requires : Wood x 3 + Fire x 1)");
        System.out.println("3. Cooked Meat 🍖🍗 (Requires : Food x 2 + Fire x 1)" + "\n");

        System.out.print("What do you want from my craft items(ready made things) type that specific number : ");
        int userChoice = ScannerObject.getScannerObject().nextInt();
        buyCraftItems(userChoice);
    }

    public void buyCraftItems(int craftNumber){
        if (craftNumber == 1){  // For Shelter...
            if (Resources.wood >= 4 && Resources.water >= 1){
                Resources.wood -= 4;
                Resources.water--;
                System.out.println("You got this CRAFT ITEMS 🛖 ....");
                haveShelter = true;
            }
            else {
                System.out.println("You don't have enough things to buy this CRAFT ITEM " + Player.userName);
            }
        }
        if (craftNumber == 2){  // For CampFire...
            if (Resources.wood >= 3 && Resources.fire >= 1){
                Resources.wood -= 3;
                Resources.fire--;
                System.out.println("You got this CRAFT ITEMS 🪵🔥 ....");
            }
            else {
                System.out.println("You don't have enough things to buy this CRAFT ITEM " + Player.userName);
            }
        }
        if (craftNumber == 3){  // For Cooked Meat...
            if (Resources.food >= 2 && Resources.fire >= 1){
                Resources.food -= 2;
                Resources.fire--;
                System.out.println("You got this CRAFT ITEMS 🍖🍗....");
            }
            else {
                System.out.println("You don't have enough things to buy this CRAFT ITEM " + Player.userName);
            }
        }
        System.out.println();
    }

}