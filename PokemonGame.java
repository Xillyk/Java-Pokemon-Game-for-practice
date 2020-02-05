import java.util.Scanner;

public class PokemonGame {
    private static boolean running = true;
    private static int numSelect;
    // private static int numSelect2;
    // private static int numSelect3;
    private static String str;
    private static Scanner in = new Scanner(System.in);
    private static Pokemon myPokemon = new Pokemon();

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
        System.out.println("============================================================");
        System.out.println("                        Choose Action");
        System.out.println("Action  :: [1]  Create New Pokemon");
        System.out.println("        :: [2]  play with your Pokemon");
        System.out.println("        :: [0]  Exit Pokemon World");
        System.out.println("------------------------------------------------------------");

        while (true) {
            numSelect = -1;
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect == 1) {
                createNewPokemon();
                break;
            } else if (numSelect == 2) {
                playWithPokemon();
                break;
            } else if (numSelect == 0) {
                stopRunning();
                break;
            } else {
                System.out.println("Invalid Input!, Try Again");
            }
        }
    }

    private static void createNewPokemon() {
        System.out.println("============================================================");
        System.out.println("                    Create New Pokemon");
        System.out.println("Choose Pokemon Type     :: [1]  Normal");
        System.out.println("                        :: [2]  Fire");
        System.out.println("                        :: [3]  Water");
        System.out.println("                        :: [4]  Grass");
        System.out.println("                        :: [5]  Electric");
        System.out.println("                        :: [6]  Poison");
        System.out.println("------------------------------------------------------------");
        while (true) {
            numSelect = 0;
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect >= 1 && numSelect <= 6) {
                myPokemon.setType(numSelect);
                while (true) {
                    str = "";
                    System.out.print("Give it a name          :: ");
                    str = in.next();
                    if (str != "") {
                        myPokemon.setName(str);
                        System.out.println("Finish Created Pokemon");
                        showPokemonStatus();
                        delay(1000);
                        break;
                    } else
                        System.out.println("Invalid Input!, Try Again");
                }
                break;
            } else {
                System.out.println("Invalid Input!, Try Again");
            }
        }
    }

    private static void playWithPokemon() {
        // #Later with array
        // System.out.println("Choose Your Pokemon");
        boolean outToMain = false;
        while (!outToMain) {
            delay(500);
            System.out.println("============================================================");
            System.out.println("Choose Action       :: [1] Go Adventure");
            System.out.println("                    :: [2] Feed");
            System.out.println("                    :: [3] Sleep");
            System.out.println("                    :: [4] Cure at pokemon center");
            System.out.println("                    :: [5] See Status");
            System.out.println("                    :: [0] Back to Main Menu");
            System.out.println("------------------------------------------------------------");
            while (true) {
                System.out.print("Enter : ");
                numSelect = -1;
                numSelect = in.nextInt();
                // roam , attack (or leave)
                // eat
                // sleep
                if (numSelect == 1) {
                    if(myPokemon.getHP() <= 0 ) {
                        System.out.println("Your pokemon has died , Go to pokemon Center first!!");
                        break;
                    }
                    delay(1000);
                    goAdventure();
                    break;
                } else if (numSelect == 2) {
                    myPokemon.eatBerry(20, 10);
                    System.out.println(myPokemon.getName() + " HP : " + myPokemon.getHP() + "/" + myPokemon.getMaxHP());
                    break;
                } else if (numSelect == 3) {
                    System.out.println(myPokemon.getName() + " is sleeping . . .");
                    delay(2000);
                    myPokemon.sleep();
                    System.out.println(myPokemon.getName() + " Sleep Point : " + myPokemon.getSleepPoint() + "/" + myPokemon.getMaxSleepingPoint());
                    break;
                } else if (numSelect == 4) {
                    curePokemonAtPokemonCenter();
                    break;
                } else if (numSelect == 5) {
                    showPokemonStatus();
                    break;
                } else if (numSelect == 0) {
                    outToMain = true;
                    break;
                } else {
                    System.out.println("Invalid Input!, Try Again");
                }
            }
        }
    }

    private static void showPokemonStatus() {
        System.out.println("| " + myPokemon.getName() + "          Type : " + myPokemon.getType() + "    Level : "
                + myPokemon.getLevel() + "    Death : " + myPokemon.getDeathCount() + " time");
        System.out.println("| HP           : " + myPokemon.getHP() + "/" + myPokemon.getMaxHP());
        System.out.println("| Exp          : " + String.format("%.2f", myPokemon.getExp()) + "/"
                + String.format("%.2f", myPokemon.getMaxExpPerLevel()));
        System.out.println("| AP           : " + myPokemon.getAP());
        System.out.println("| Hungry Point : " + myPokemon.getHungryPoint() + "/" + myPokemon.getMaxHungryPoint());
        System.out.println("| Sleep Point  : " + myPokemon.getSleepPoint() + "/" + myPokemon.getMaxSleepingPoint());
    }

    private static void curePokemonAtPokemonCenter() {
        // System.out.println("============================================================");
        // System.out.println(" Select Pokemon to Cure");
        System.out.println(myPokemon.getName() + " back to Alive!");
        myPokemon.curePokemon();
    }

    private static void goAdventure() {
        int monsterHP = randomInt(35, 60);
        int monsterMaxHP = monsterHP;
        int monsterAP;
        double monsterExp = randomDouble(30, 50);
        boolean isTrainerEscape = false;
        System.out.println("Monster in your area!!!");
        while (!isTrainerEscape) {
            delay(500);
            monsterAP = randomInt(20, 30);
            if (monsterHP <= 0 && isTrainerEscape == false) {
                delay(500);
                System.out.println("Hurey!! You beat monster down!!");
                delay(500);
                myPokemon.earnExp(monsterExp);
                System.out.println(myPokemon.getName() + " earned " + String.format("%.2f", monsterExp) + " Exp");
                System.out.println("[ " + myPokemon.getHP() + "/" + myPokemon.getMaxHP() + " HP ]");
                System.out.println("[ " + String.format("%.2f", myPokemon.getExp()) + "/" + String.format("%.2f", myPokemon.getMaxExpPerLevel()) + " Exp ]");
                // ################################################################################################################
                // get item
                break;
            } else if (isTrainerEscape == false) {
                numSelect = 0;
                System.out.println("============================================================");
                System.out.println("Monster HP : " + monsterHP + "/" + monsterMaxHP);
                System.out.println(myPokemon.getName() + " HP : " + myPokemon.getHP() + "/" + myPokemon.getMaxHP());
                System.out.println("Action :: [1] Attack    [2] Regen HP    [-X-] Change Pokemon [4] Escape");
                System.out.println("------------------------------------------------------------");
                // System.out.println(" [5] catch");
                while (true) {
                    System.out.print("Enter : ");
                    numSelect = in.nextInt();
                    if (numSelect == 1) {
                        monsterHP -= myPokemon.getAP();
                        myPokemon.getDamage(monsterAP);
                        myPokemon.lossHugryPoint(5);
                        myPokemon.lossSleepPoint(5);
                        if (myPokemon.isDie()) {
                            delay(400);
                            double tmpLossExp = 0.0;
                            System.out.println("------------------------------------------------------------");
                            System.out.println(myPokemon.getName() + " is dead!   Death : " + myPokemon.getDeathCount() + " times");
                            tmpLossExp = randomDouble(5, 15);
                            myPokemon.lossExp(tmpLossExp);
                            System.out.println(myPokemon.getName() + " loss " + String.format("%.2f", tmpLossExp) + " Exp.");
                            delay(400);
                            System.out.println("You have to 'Change pokemon' or 'Escape' !!");
                            System.out.println("Select ::   [1] Change Pokemon [!!Dont press not available now]");
                            System.out.println("       ::   [2] Escape");
                            while (true) {
                                numSelect = 0;
                                System.out.print("Enter :");
                                numSelect = in.nextInt();
                                if (numSelect == 1) {
                                    System.out.println("Change Pokemon Not awailable Now!");
                                    // #########################################################
                                    break;
                                } else if (numSelect == 2) {
                                    isTrainerEscape = true;
                                    System.out.println("Runnnnn!!!!!");
                                    break;
                                } else {
                                    System.out.println("Invalid Input!, Try Again");
                                }
                            }
                        }
                        break;
                    } else if (numSelect == 2) {
                        myPokemon.regenHealth(20);
                        break;
                    } else if (numSelect == 3) {
                        // chage pokemon
                        // ################################################################################################################
                        // ################################################################################################################
                        System.out.println("Change Pokemon Not awailable Now!");
                    } else if (numSelect == 4) {
                        isTrainerEscape = true;
                        System.out.println("Runnnnn!!!!!");
                        break;
                    } else {
                        System.out.println("Invalid Input!, Try Again");
                    }
                }
            } 
        }
    }

    // #-----------------------

    private static int randomInt(int min, int max) {
        int randNum = 0;
        randNum = (int) (Math.random() * ((max - min) + 1)) + min;
        return randNum;
    }

    private static double randomDouble(int min, int max) {
        double randNum = 0;
        randNum = (double) Math.random() * ((max - min) + 1) + min;
        return randNum;
    }

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