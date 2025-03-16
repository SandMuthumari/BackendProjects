package Assignment16;

import java.util.*;

class CaptchaColorProvider{
    static int textColorNumber;
    static int backgroundColorNumber;
    Scanner scan = new Scanner(System.in);

    public CaptchaColorProvider() {
        reset();
    }

    public void reset(){
        textColorNumber = 0;
        backgroundColorNumber = 7;
    }

    public void setUserColor(){
        System.out.println("0-black" + "\n" + "1-red" + "\n" + "2-green" + "\n" + "3-yellow" + "\n" + "4-blue" + "\n" + "5-purple" + "\n" + "6-cyan" + " \n" + "7-white");
        System.out.println("I have listed my color above !!!");

        // Get text color number ....
        System.out.print("Now tell me what color you want to add in your captcha for TEXT ?" + "\n" + "Enter that specific color number : ");
        int userTextColorNumber = scan.nextInt();
        checkValidNumber(userTextColorNumber , "text");
        textColorNumber = userTextColorNumber;   // set text color number ....

        // Get background color number ...
        System.out.print("Now what color you want to add in your captcha for BACKGROUND ?" + "\n" + "Enter that specific color number : ");
        int userBackgroundColorNumber = scan.nextInt();
        checkValidNumber(userBackgroundColorNumber , "background");
        backgroundColorNumber = userBackgroundColorNumber;   // set background color number ....
    }

    public int checkValidNumber(int userNumber , String colorType){
        while (userNumber < 0 || userNumber > 7){
            System.out.println("You entered a Invalid Number !!!" + "\n" + "Try Again !!!");
            System.out.print("Enter your " + colorType + " color number : ");
            userNumber = scan.nextInt();
        }
        return userNumber;
    }

}