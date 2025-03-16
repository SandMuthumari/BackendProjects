package Assignment16;

import java.util.*;

public class CaptchaGenerator {
    Scanner scan = new Scanner(System.in);
    CaptchaColorProvider captchaColorProvider = new CaptchaColorProvider();
    CaptchaStringProvider captchaStringProvider = new CaptchaStringProvider();
    CaptchaTextStyleProvider CaptchaTextStyleProvider = new CaptchaTextStyleProvider();

    public static void main(String[] args) {
        CaptchaGenerator captchaGenerator = new CaptchaGenerator();
        captchaGenerator.startCaptcha();
    }

    public void startCaptcha() {
        boolean isHalt = false;
        while (!isHalt) {
            System.out.println("Verify you're Human !!!");

            System.out.println("If you want a default captcha enter 1...");
            System.out.println("If you want to create your own captcha enter 2...");
            System.out.println("To exit enter 0...");
            System.out.print("Now tell me what you want to do now : ");
            int userDecision = scan.nextInt();

            while (userDecision != 1 && userDecision != 2 && userDecision != 0){
                System.out.println("Invalid Number !!!");
                System.out.println("If you want a default captcha enter 1...");
                System.out.println("If you want to create your own captcha enter 2...");
                System.out.println("To exit enter 0...");
                System.out.print("Now tell me what you want to do now : ");
                userDecision = scan.nextInt();
            }

            if (userDecision == 0){  // If user exit ....
                System.out.println("Thanks for using our CAPTCHA GENERATOR !!!" + "\n" + "Visit us again !!!");
                isHalt = true;
            }
            if (userDecision == 1) {
                captchaColorProvider.reset();
                captchaStringProvider.generateDefaultCaptcha();
                ColorBoxSimulator colorBoxSimulator = new ColorBoxSimulator(CaptchaColorProvider.textColorNumber, CaptchaColorProvider.backgroundColorNumber, CaptchaStringProvider.captchaInput);
                colorBoxSimulator.printDefaultColorBox();
                System.out.println();
            }
            if (userDecision == 2) {
                captchaColorProvider.setUserColor();
                captchaStringProvider.generateUserCaptcha();
                CaptchaTextStyleProvider.chooseTextAligningStyle();
                System.out.println();
            }
        }

    }
    
}   