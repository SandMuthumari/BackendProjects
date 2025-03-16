package Assignment17;

public class Resources extends Player{

    static int food;
    static int wood;
    static int water;
    static int fire;

    public void resources(){
        System.out.println("1. 🍖 Food");
        System.out.println("2. 🪵 Wood");
        System.out.println("3. 💧 Water");
        System.out.println("4. 🔥 Fire" + "\n");

        System.out.print("What you want to gather ? ");
        int userGather = ScannerObject.getScannerObject().nextInt();
        if (userGather == 1){
            increaseFood();
            displayGatheredMessage("🍖 Food");
            increaseEnergy(15);
            increaseHealth(5);
        }
        if (userGather == 2){
            increaseWood();
            displayGatheredMessage("🪵 Wood");
            reduceEnergy(20);
        }
        if (userGather == 3){
            increaseWater();
            displayGatheredMessage("💧 Water");
            increaseHealth(5);
        }
        if (userGather == 4){
            increaseFire();
            displayGatheredMessage("🔥 Fire");
        }
        System.out.println();
    }

    public void displayGatheredMessage(String resource){
        String gatheredResource = "You gathered " + resource;
        System.out.println(gatheredResource);
        System.out.println();
    }

    public void getGatheredThing() {
        if (food == 0 && wood == 0 && water == 0 && fire == 0){
            System.out.println("You don't have any resources !!!");
            System.out.println();
        }
        else {
            String gatheredThing = "🍖 Food " + food + " | " + "🪵 Wood " + wood + " | " + "💧 Water " + water + " | " + "🔥 Fire " + fire;  // It's a mutable data member
            System.out.println(gatheredThing);
            System.out.println();
        }
    }

    public void increaseFood(){
        food++;
    }

    public void increaseWood(){
        wood++;
    }

    public void increaseWater(){
        water++;
    }

    public void increaseFire(){
        fire++;
    }

}























