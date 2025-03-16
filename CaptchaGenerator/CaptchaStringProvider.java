package Assignment16;

import java.util.*;

class CaptchaStringProvider{
    static String captchaInput = "";
    int captchaLength = 6;
    char[] alphabets = new char[26];  // Alphabets a - z
    char[] specialCharacters = {'!' , '@' , '#' , '$' , '&'};
    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    public void generateDefaultCaptcha(){   // Generating Default Captcha String....
        captchaInput = ""; // Re-initializing my captchaInput data member because I'm going to take char randomly and append it to my data member.
        setAlphabets(97); //set alphabets
        int specialCharacterIndex = random.nextInt(specialCharacters.length);  // Generate a random number for special char...
        for (int i = 0; i < captchaLength - 2; i++){
            int alphabetIndex = random.nextInt(alphabets.length);
            captchaInput += alphabets[alphabetIndex]; // Adding random alphabet to captchaInput ...

            if (i == 2){ // Adding a special char in middle of my captchaInput....
                captchaInput += specialCharacters[specialCharacterIndex];
            } // end of if

        } // end of for-loop
        specialCharacterIndex = random.nextInt(specialCharacters.length);  // Again Generate a random number for special char....
        captchaInput += specialCharacters[specialCharacterIndex];  // Adding a special char at last
    }

    public void generateUserCaptcha(){  // Generating User Captcha String ....
        captchaInput = ""; // Re-initializing my captchaInput data member because I'm going to append user input by taking char randomly.
        String userInput = getUserInput();
        for (int i = 0; i < captchaLength; i++){
            int index = random.nextInt(userInput.length());   // Gives me a random number ...
            captchaInput += userInput.charAt(index);
        } // End of for-loop
    }

    public String getUserInput() {   // User Captcha String....
        System.out.println("Your Captcha input length at least should be 10 !!!");
        System.out.print("Enter a captcha input to generate your CAPTCHA : ");
        String userInput = scan.nextLine();

        while (userInput.length() < 10) { // Repeatedly ask for valid input 'til length is 10
            System.out.println("Your Captcha input is invalid !!!");
            System.out.println("At least length should be 10 ...");
            System.out.print("Enter a captcha input to generate your CAPTCHA : ");
            userInput = scan.nextLine();
        }
        return userInput;
    }

    public void setAlphabets(int asciiNumber) { // Set alphabet to 'alphabets' array ...
        for (int i = 0; i < alphabets.length; i++) {
            alphabets[i] = (char) (asciiNumber);
            asciiNumber++;
        }
    }

}