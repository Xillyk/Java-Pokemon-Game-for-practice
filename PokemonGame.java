import java.util.Scanner;

public class PokemonGame {
    private static boolean running;
    private static boolean firstRoundRunning;
    private static int numSelect;
    private static String str;
    private static Scanner in;
    public static void main(String args[]) {
        running = true;
        firstRoundRunning = true;
        in = new Scanner(System.in);
        PokemonGame g = new PokemonGame();
        Trainer t = new Trainer();
        g.showWelcomeInterface();

        do {
            delay(500);
            if (firstRoundRunning == true) {
                g.showMenuInterfaceFirstTime();
                g.selectMenu(t);
            } else {
                g.showMenuInterfaceBackToMainMenu();
                g.selectMenu(t);
            }

        } while (running);
        System.out.println("Good bye");
        in.close();
    }

    private void showWelcomeInterface() {
        System.out.println("============================================================");
        System.out.println("                P O K E M O N    W O R L D");
        System.out.println("------------------------------------------------------------");
        System.out.println("               Welcome New Pokemon Trainer!!");
        // delay(700);
        // System.out.print(". ");
        // delay(700);
        // System.out.println(".");
        // delay(700);
    }
    
    private void showMenuInterfaceFirstTime() {
        System.out.println("============================================================");
        System.out.println("                         Main Menu");
        System.out.println("Action  :: [1]  Start Pokemon World");
        System.out.println("        :: [0]  Exit Game");
        System.out.println("------------------------------------------------------------");
    }
   
    private void showMenuInterfaceBackToMainMenu() {
        System.out.println("============================================================");
        System.out.println("                         Main Menu");
        System.out.println("Action  :: [1]  Return to Pokemon World");
        System.out.println("        :: [0]  Exit Game");
        System.out.println("------------------------------------------------------------");
    }

    private void noticeToPlayerInterface() {
        System.out.println("============================================================");
        System.out.println("                       > How TO Play <");          
        System.out.println("You will be playing as pokemon trainer. You can go to fight");
        System.out.println("with pokemon in dungeon , catch some pokemon , feed it with ");
        System.out.println("'Berry' and whenever your pokemon die , don't be so sorrow.");
        System.out.println("Just go to 'Pokemon Center to treat it. Have fun :) '");
        System.out.println("                          <><><><>");
        System.out.println("============================================================\n");
    }

    private void selectMenu(Trainer t) {
        while (true) {
            numSelect = -1;
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect == 1) {
                delay(500);
                if (firstRoundRunning == true) {
                    noticeToPlayerInterface();
                    //delay(2500);
                    createTrainer(t);
                    firstRoundRunning = false;
                    t.receiveFirstPokemon();
                    t.act();
                }
                else {
                    t.act();
                }
                break;
            } else if (numSelect == 0) {
                stopRunning();
                break;
            } else {
                System.out.println("Invalid Input!, Try Again");
            }
        }
    }

    public void createTrainer(Trainer t) {
        while (true) {
            str = "";
            System.out.print("Enter your name : ");
            str = in.next();
            if (str != null && !str.equals("")) {
                t.setName(str);
                break;
            }
            System.out.println("Invalid name!, Try Again");
        }
    }

    // #-----------------------


    private void stopRunning() {
        running = false;
    }

    private static void delay(int _delayInMicrosec) {
        try {
            Thread.sleep(_delayInMicrosec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}