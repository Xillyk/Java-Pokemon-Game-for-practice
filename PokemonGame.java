import java.util.Scanner;
import java.util.ArrayList; 

public class PokemonGame {
    private static boolean running = true;
    private static int numSelect;
    private static String str;
    private static Scanner in = new Scanner(System.in);
    private static int indexObj;
    private static boolean firstRoundRunning = true;
    private static String tmpName;
    private static ArrayList<Pokemon> myPokemons;

    public static void main(String args[]) {
        myPokemons = new ArrayList<Pokemon>();
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
                    delay(2500);
                    createNewPokemon();
                    firstRoundRunning = false;
                }
                playPokemonWorld();
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
        System.out.println("with monster in dungeon , catch some pokemon , feed it with ");
        System.out.println("'Berry' and whenever your pokemon die , don't be so sorrow.");
        System.out.println("Just go to 'Pokemon Center to treat it. Have fun :) '");
        System.out.println("                          <><><><>");
        System.out.println("============================================================\n");
    }

    private static void createNewPokemon() {
        while (true) {
            str = "";
            System.out.print("Your pokemon's name : ");
            str = in.next();
            if (str != null && !str.equals("")) {
                createNewPokemonUI();
                while (true) {
                    numSelect = 0;
                    System.out.print("Enter : ");
                    numSelect = in.nextInt();
                    if (numSelect >= 1 && numSelect <= 6) {
                        indexObj = 0;
                        myPokemons.add(new Pokemon(str,numSelect));
                        break;
                    } else {
                        System.out.println("Invalid Input!, Try Again");
                    }
                }
                delay(500);
                System.out.println("Finish Created Pokemon");
                delay(500);
                showPokemonStatusUI();
                delay(1500);
                break;
            } else
                System.out.println("Invalid Input!, Try Again");
        }
    }

    private static void createNewPokemonUI() {
        System.out.println("============================================================");
        System.out.println("                     Create your Pokemon");          
        System.out.println("Choose Pokemon Type     :: [1]  Normal");
        System.out.println("                        :: [2]  Fire");
        System.out.println("                        :: [3]  Water");
        System.out.println("                        :: [4]  Grass");
        System.out.println("                        :: [5]  Electric");
        System.out.println("                        :: [6]  Poison");
        System.out.println("------------------------------------------------------------");
    }
    
    private static void playPokemonWorld() {
        // #Later with array
        // System.out.println("Choose Your Pokemon");
        boolean outToMain = false;
        while (!outToMain) {
            delay(500);
            showPlayPokemonWorldUI();
            while (true) {
                System.out.print("Enter : ");
                numSelect = -1;
                numSelect = in.nextInt();
                // roam , attack (or leave) // eat // sleep
                if (numSelect == 1) {
                    /*if(myPokemon.getHP() <= 0 ) {
                        System.out.println("Your pokemon has died , Go to pokemon Center first!!");
                        break;
                    }*/
                    delay(1000);
                    goAdventure();
                    break;
                } else if (numSelect == 2) {
                    myPokemons.get(indexObj).eatBerry(20, 10);
                    System.out.println(myPokemons.get(indexObj).getName() + " HP : " + myPokemons.get(indexObj).getHP() + "/" + myPokemons.get(indexObj).getMaxHP());
                    break;
                } else if (numSelect == 3) {
                    System.out.println(myPokemons.get(indexObj).getName() + " is sleeping . . .");
                    delay(2000);
                    myPokemons.get(indexObj).sleep();
                    System.out.println(myPokemons.get(indexObj).getName() + " Sleep Point : " + myPokemons.get(indexObj).getSleepPoint() + "/" + myPokemons.get(indexObj).getMaxSleepingPoint());
                    break;
                } else if (numSelect == 4) {
                    curePokemonAtPokemonCenter();
                    break;
                } else if (numSelect == 5) {
                    showPokemonStatusUI();
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

    private static void showPlayPokemonWorldUI() {
        System.out.println("============================================================");
        System.out.println("Choose Action       :: [1] Go Adventure");
        System.out.println("                    :: [2] Feed");
        System.out.println("                    :: [3] Sleep");
        System.out.println("                    :: [4] Cure at pokemon center");
        System.out.println("                    :: [5] See Status");
        System.out.println("                    :: [0] Back to Main Menu");
        System.out.println("------------------------------------------------------------");
    }

    private static void showPokemonStatusUI() {
        System.out.println("| " + myPokemons.get(indexObj).getName() + "          Type : " + myPokemons.get(indexObj).getType() + "    Level : "
                + myPokemons.get(indexObj).getLevel() + "    Death : " + myPokemons.get(indexObj).getDeathCount() + " time");
        System.out.println("| HP           : " + myPokemons.get(indexObj).getHP() + "/" + myPokemons.get(indexObj).getMaxHP());
        System.out.println("| Exp          : " + String.format("%.2f", myPokemons.get(indexObj).getExp()) + "/"
                + String.format("%.2f", myPokemons.get(indexObj).getMaxExpPerLevel()));
        System.out.println("| AP           : " + myPokemons.get(indexObj).getAP());
        System.out.println("| Hungry Point : " + myPokemons.get(indexObj).getHungryPoint() + "/" + myPokemons.get(indexObj).getMaxHungryPoint());
        System.out.println("| Sleep Point  : " + myPokemons.get(indexObj).getSleepPoint() + "/" + myPokemons.get(indexObj).getMaxSleepingPoint());
    }

    private static void curePokemonAtPokemonCenter() {
        System.out.println("============================================================");
        System.out.println("                   Welcome to Pokemon Center");
        System.out.println("============================================================");
        listPokemon();
        System.out.println("Select Pokemon to treat / Treat all of your pokemon [Press 0]");
        while (true) {
            numSelect = -1;
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect >= 1 && numSelect <= (myPokemons.size() + 1)) {
                
                System.out.println("Do you want to treat another pokemon [y/n] ?");

            } else if (numSelect == 0) {
               
                break;
            } else {
                System.out.println("Invalid Input!, Try Again");
            }
        }

       // System.out.println(myPokemons.get(indexObj).getName() + " back to Alive!");
        //myPokemons.get(indexObj).curePokemon();
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
                myPokemons.get(indexObj).earnExp(monsterExp);
                System.out.println(myPokemons.get(indexObj).getName() + " earned " + String.format("%.2f", monsterExp) + " Exp");
                System.out.println("[ " + myPokemons.get(indexObj).getHP() + "/" + myPokemons.get(indexObj).getMaxHP() + " HP ]");
                System.out.println("[ " + String.format("%.2f", myPokemons.get(indexObj).getExp()) + "/" + String.format("%.2f", myPokemons.get(indexObj).getMaxExpPerLevel()) + " Exp ]");
                // ################################################################################################################
                // get item
                break;
            } else if (isTrainerEscape == false) {
                numSelect = 0;
                //## print
                System.out.println("============================================================");
                System.out.println("Monster HP : " + monsterHP + "/" + monsterMaxHP);
                System.out.println(myPokemons.get(indexObj).getName() + " HP : " + myPokemons.get(indexObj).getHP() + "/" + myPokemons.get(indexObj).getMaxHP());
                System.out.println("Action :: [1] Attack    [2] Regen HP    [3] Change Pokemon [4] Escape");
                System.out.println(" [5] catch");              
                System.out.println("------------------------------------------------------------");
                //## print
                while (true) {
                    System.out.print("Enter : ");
                    numSelect = in.nextInt();
                    if (numSelect == 1) {
                        monsterHP -= myPokemons.get(indexObj).getAP();
                        myPokemons.get(indexObj).getDamage(monsterAP);
                        myPokemons.get(indexObj).lossHugryPoint(5);
                        myPokemons.get(indexObj).lossSleepPoint(5);
                        if (myPokemons.get(indexObj).isDie()) {
                            delay(400);
                            double tmpLossExp = 0.0;
                            tmpLossExp = randomDouble(5, 15);
                            myPokemons.get(indexObj).lossExp(tmpLossExp);
                            //## print
                            System.out.println("------------------------------------------------------------");
                            System.out.println(myPokemons.get(indexObj).getName() + " is dead!   Death : " + myPokemons.get(indexObj).getDeathCount() + " times");
                            System.out.println(myPokemons.get(indexObj).getName() + " loss " + String.format("%.2f", tmpLossExp) + " Exp.");
                            delay(400);
                            System.out.println("You have to 'Change pokemon' or 'Escape' !!");
                            System.out.println("Select ::   [1] Change Pokemon ");
                            System.out.println("       ::   [2] Escape");
                            //## print
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
                        myPokemons.get(indexObj).regenHealth(20);
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
    private static void listPokemon() {
        int index = 1;
        System.out.println("Your Pokemon : ");
        for (Pokemon mypokemon : myPokemons) {
            System.out.println("            [" + index + "] " + mypokemon.getName());
        }
    }
/*
    private static int findIndexOfArrayList() {
        int index = 0;
        for (Pokemon mypokemon : myPokemons) {
            if()
        }
    }
*/
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