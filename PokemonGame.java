import java.util.Scanner;

public class PokemonGame {
    private static boolean running = true;
    private static boolean firstRoundRunning = true;
    private static int numSelect;
    private static Scanner in = new Scanner(System.in);
    private static GameAction gameAct = new GameAction();
    public static void main(String args[]) {
        showWelcomeUI();
        do {
            gameMenu(); // run game
        } while (running);
        System.out.println("Good bye");
        in.close();
    }

    private static void showWelcomeUI() {
        System.out.println("============================================================");
        System.out.println("                P O K E M O N    W O R L D");
        System.out.println("------------------------------------------------------------");
        System.out.print("               Welcome New Pokemon Trainer!!");
        delay(700);
        System.out.print(". ");
        delay(700);
        System.out.println(".");
        delay(700);
    }

    private static void gameMenu() {
        delay(500);
        if (firstRoundRunning == true) {
           showMenuUIFirstTime();
           selectMenu();
        } else {
            showMenuUIBackToMainMenu();
            selectMenu();
        }
    }
    
    private static void showMenuUIFirstTime() {
        System.out.println("============================================================");
        System.out.println("                         Main Menu");
        System.out.println("Action  :: [1]  Start Pokemon World");
        System.out.println("        :: [0]  Exit Game");
        System.out.println("------------------------------------------------------------");
    }
   
    private static void showMenuUIBackToMainMenu() {
        System.out.println("============================================================");
        System.out.println("                         Main Menu");
        System.out.println("Action  :: [1]  Return to Pokemon World");
        System.out.println("        :: [0]  Exit Game");
        System.out.println("------------------------------------------------------------");
    }
   
    private static void selectMenu() {
        while (true) {
            numSelect = -1;
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect == 1) {
                delay(500);
                if (firstRoundRunning == true) {
                    noticeToPlayerUI();
                    //delay(2500);
                    gameAct.createNewPokemon();
                    firstRoundRunning = false;
                }
                gameAct.playPokemonWorld();
                break;
            } else if (numSelect == 0) {
                stopRunning();
                break;
            } else {
                System.out.println("Invalid Input!, Try Again");
            }
        }
    }
   
    private static void noticeToPlayerUI() {
        System.out.println("============================================================");
        System.out.println("                       > How TO Play <");          
        System.out.println("You will be playing as pokemon trainer. You can go to fight");
        System.out.println("with pokemon in dungeon , catch some pokemon , feed it with ");
        System.out.println("'Berry' and whenever your pokemon die , don't be so sorrow.");
        System.out.println("Just go to 'Pokemon Center to treat it. Have fun :) '");
        System.out.println("                          <><><><>");
        System.out.println("============================================================\n");
    }

    // #-----------------------
    private static void delay(int _delayInMicrosec) {
        try {
            Thread.sleep(_delayInMicrosec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private static void stopRunning() {
        running = false;
    }
}