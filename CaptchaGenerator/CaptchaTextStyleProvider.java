package Assignment16;

import java.util.*;

class CaptchaTextStyleProvider{
    Scanner scan = new Scanner(System.in);

//    public static void main(String[] args){
//        CaptchaTextStyleProvider captchaTextStyleProvider = new CaptchaTextStyleProvider();
//        captchaTextStyleProvider.chooseTextAligningStyle();
//    }

    public void chooseTextAligningStyle(){  // To choose text alignment.....top/center/bottom/diagonal....
        ColorBoxSimulator colorBoxSimulator = new ColorBoxSimulator(CaptchaColorProvider.textColorNumber, CaptchaColorProvider.backgroundColorNumber, CaptchaStringProvider.captchaInput);
        System.out.println();
        System.out.println("If you want your captcha text to align in top enter 1");
        System.out.println("If you want your captcha text to align in center enter 2");
        System.out.println("If you want your captcha text to align in bottom enter 3");
        System.out.println("If you want your captcha text to align diagonally enter 4");
        System.out.print("In which alignment you want to see ? ");
        int userNumber = scan.nextInt();

        while (userNumber != 1 && userNumber !=2 && userNumber != 3 && userNumber != 4){
            System.out.println();
            System.out.println("Invalid Input !!!");
            System.out.println("If you want your captcha text to align in top enter 1");
            System.out.println("If you want your captcha text to align in center enter 2");
            System.out.println("If you want your captcha text to align in bottom enter 3");
            System.out.println("If you want your captcha text to align diagonally enter 4");
            System.out.print("In which alignment you want to see ? ");
            userNumber = scan.nextInt();
        }
        switch (userNumber){
            case 1:
                colorBoxSimulator.printTextOnTop();
                break;
            case 2:
                colorBoxSimulator.printDefaultColorBox();
                break;
            case 3:
                colorBoxSimulator.printTextOnBottom();
                break;
            case 4:
                colorBoxSimulator.printDiagonalText();
                break;
        }

    }
}