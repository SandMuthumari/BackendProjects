package Assignment16;

class ColorBoxSimulator{
    // To reset our color...
    String resetColor = "\u001B[0m";
    // 0-black , 1-red , 2-green , 3-yellow , 4-blue , 5-purple , 6-cyan , 7-white
    // These are the text colors ...
    String[] textColors = { "\u001B[30m" , "\u001B[31m" , "\u001B[32m" , "\u001B[33m", "\u001B[34m",  "\u001B[35m" , "\u001B[36m", "\u001B[37m" };
    // These are the background colors ....
    String[] backgroundColors = { "\u001B[40m" , "\u001B[41m" , "\u001B[42m" , "\u001B[43m", "\u001B[44m",  "\u001B[45m" , "\u001B[46m", "\u001B[47m" };

    int row = 8;
    int column = 20;
    int textColorNumber;
    int backgroundColorNumber;
    String captchaInput;

    ColorBoxSimulator(int textColorNumber , int backgroundColorNumber , String captchaInput){  // Using Constructor to set text & background color number...
        this.textColorNumber = textColorNumber;
        this.backgroundColorNumber = backgroundColorNumber;
        this.captchaInput = captchaInput;
    }

    public void printDefaultColorBox(){  // Print a default box...
        int boxSpace = 30;
        int spaceInFront = (column / 2) - 3;
        int spaceInEnd = (column / 2) - 3;
        // Making a box .... using two for loops....
        for (int i = 0; i < row; i++){
            System.out.print(" ".repeat(boxSpace));
            if (i == 3){
                System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " ".repeat(spaceInFront) + captchaInput + " ".repeat(spaceInEnd) + resetColor);
            }
            for (int j = 0; j < column; j++){
                if (i != 3){
                    System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " " + resetColor);
                }
            }
            System.out.println();
        }
    }

    public void printDiagonalText(){  // Print text diagonally in a box ...
        System.out.println();
        int index = 0;
        int startingColumn = 2;
        int startingRow = 1;
        int boxSpace = 30;
        for (int i = 0; i < row; i++){
            System.out.print(" ".repeat(boxSpace));
            for (int j = 0; j < column; j++){
                if (i >= startingRow && j == startingColumn && index < captchaInput.length()){
                    System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + captchaInput.charAt(index) + resetColor);
                    index++;
                    startingColumn += 3;
                    startingRow++;
                }
                else {
                    System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " " + resetColor);
                }
            }
            System.out.println();
        }
    }

    public void printTextOnTop(){
        int spaceInFront = (column / 2) - 3;
        int spaceInEnd = (column / 2) - 3;
        int boxSpace = 30;
        for (int i = 0; i < row; i++){
            System.out.print(" ".repeat(boxSpace));
            if (i == 0){
                System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " ".repeat(spaceInFront) + captchaInput + " ".repeat(spaceInEnd) + resetColor + "\n");
            }
            for (int j = 0; j < column; j++){
                if (i != 0){
                    System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " " + resetColor);
                }
            }
            if (i != 0) {
                System.out.println();
            }
        }
    }

    public void printTextOnBottom(){
        int spaceInFront = (column / 2) - 3;
        int spaceInEnd = (column / 2) - 3;
        int boxSpace = 30;
        for (int i = 0; i < row; i++) {
            System.out.print(" ".repeat(boxSpace));
            if (i == row - 1) {
                System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " ".repeat(spaceInFront) + captchaInput + " ".repeat(spaceInEnd) + resetColor);
            }
            for (int j = 0; j < column; j++) {
                if (i != row - 1) {
                    System.out.print(textColors[textColorNumber] + backgroundColors[backgroundColorNumber] + " " + resetColor);
                }
            }
            System.out.println();
        }
    }

}