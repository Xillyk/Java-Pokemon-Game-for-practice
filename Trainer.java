import java.util.*;

public class Trainer {
    private int numSelect;
    private int pIndex; // pokemonIndex
    private String str;
    private Scanner in;

    // private int money;
    private String name;
    private ArrayList<Pokemon> pokemonBag;
    private ArrayList<Item> itemBag;

    public Trainer() {
        pIndex = 0;
        str = "";
        in = new Scanner(System.in);
        name = "";
        pokemonBag = new ArrayList<Pokemon>();
        itemBag = new ArrayList<Item>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public void receiveFirstPokemon() {
        System.out.println("Select your first pokemon");
        System.out.println("    [1] Pikachu");
        System.out.println("    [2] Charmander");
        System.out.println("    [3] Squirtle");
        numSelect = 0;
        str = "";
        while (true) {
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            if (numSelect >= 1 && numSelect <= 3) {
                while (true) {
                    System.out.print("Enter Pokemon's name : ");
                    str = in.nextLine();
                    if (str != null && !str.equals("")) {
                        if (numSelect == 1)
                            pokemonBag.add(new Pikachu(str));
                        else if (numSelect == 2)
                            pokemonBag.add(new Charmander(str));
                        else if (numSelect == 3)
                            pokemonBag.add(new Squirtle(str)); 
                        break;  //break if str is valid                                          
                    }
                    System.out.println("Invalid Input!, Try Again");
                }
                break;  //break if numSelect is valid 
            }
            System.out.println("Invalid Input!, Try Again");
        }

        delay(500);
        System.out.println("So, " + pokemonBag.get(0) + " is your first pokemon.");
        delay(500);
        System.out.println("Let's Adventure !!");
        delay(500);
    }

    private void showActInterface() {
        System.out.println("============================================================");
        System.out.println("Choose Action       :: [1] Go Adventure");
        System.out.println("                    :: [2] Feed");
        System.out.println("                    :: [3] Sleep");
        System.out.println("                    :: [4] Cure pokemons at pokemon center");
        System.out.println("                    :: [5] See Status");
        System.out.println("                    :: [0] Back to Main Menu");
        System.out.println("------------------------------------------------------------");
    }

    public void act() {
        // #Later with array
        // System.out.println("Choose Your Pokemon");
        boolean outToMain = false;
        while (!outToMain) {
            delay(500);
            showActInterface();
            while (true) {
                System.out.print("Enter : ");
                numSelect = -1;
                numSelect = in.nextInt();
                // roam , attack (or leave) // eat // sleep
                if (numSelect == 1) {
                    /*
                     * if(myPokemon.getHP() <= 0 ) {
                     * System.out.println("Your pokemon has died , Go to pokemon Center first!!");
                     * break; }
                     */
                    delay(1000);
                    goAdventure();
                    break;
                } else if (numSelect == 2) {
                    pokemonBag.get(pIndex).eatBerry(20, 10);
                    System.out.println(pokemonBag.get(pIndex) + " HP : " + pokemonBag.get(pIndex).getHP()
                            + "/" + pokemonBag.get(pIndex).getMaxHP());
                    break;
                } else if (numSelect == 3) {
                    System.out.println(pokemonBag.get(pIndex) + " is sleeping . . .");
                    delay(2000);
                    pokemonBag.get(pIndex).sleep();
                    System.out.println(pokemonBag.get(pIndex) + " Sleep Point : "
                            + pokemonBag.get(pIndex).getSleepPoint() + "/"
                            + pokemonBag.get(pIndex).getMaxSleepingPoint());
                    break;
                } else if (numSelect == 4) {
                    // pokemonCenter();
                    break;
                } else if (numSelect == 5) {
                    showPokemonStatusInterface();
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

    private void showPokemonStatusInterface() {
        System.out.println("| Nickname     : " + pokemonBag.get(pIndex) + "      Name : " + pokemonBag.get(pIndex).getName());
        System.out.println("| Type         : " + pokemonBag.get(pIndex).getType() + "    Level : "
        + pokemonBag.get(pIndex).getLevel() + "    Death : " + pokemonBag.get(pIndex).getDeathCount() + " time");
        System.out.println("| HP           : " + pokemonBag.get(pIndex).getHP() + "/" + pokemonBag.get(pIndex).getMaxHP());
        System.out.println("| Exp          : " + String.format("%.2f", pokemonBag.get(pIndex).getExp()) + "/"
                + String.format("%.2f", pokemonBag.get(pIndex).getMaxExpPerLevel()));
        System.out.println("| AP           : " + pokemonBag.get(pIndex).getAP());
        System.out.println("| Hungry Point : " + pokemonBag.get(pIndex).getHungryPoint() + "/" + pokemonBag.get(pIndex).getMaxHungryPoint());
        System.out.println("| Sleep Point  : " + pokemonBag.get(pIndex).getSleepPoint() + "/" + pokemonBag.get(pIndex).getMaxSleepingPoint());
    }

    private void goAdventure() {
        int wildPokemonHP = randomInt(35, 60);                              //# change to random pokemon
        int wildPokemonMaxHP = wildPokemonHP;
        int wildPokemonAP;
        double wildPokemonExp = randomDouble(30, 50);
        boolean isTrainerEscape = false;
        System.out.println("Wild Pokemon in your area!!!");
        while (!isTrainerEscape) {
            delay(500);
            wildPokemonAP = randomInt(20, 30);
            if (wildPokemonHP <= 0 && isTrainerEscape == false) {
                delay(500);
                System.out.println("Hurey!! You beat pokemon down!!");
                delay(500);
                pokemonBag.get(pIndex).earnExp(wildPokemonExp);
                System.out.println(pokemonBag.get(pIndex) + " earned "
                        + String.format("%.2f", wildPokemonExp) + " Exp");
                System.out.println(
                        "[ " + pokemonBag.get(pIndex).getHP() + "/" + pokemonBag.get(pIndex).getMaxHP() + " HP ]");
                System.out.println("[ " + String.format("%.2f", pokemonBag.get(pIndex).getExp()) + "/"
                        + String.format("%.2f", pokemonBag.get(pIndex).getMaxExpPerLevel()) + " Exp ]");
                // ################################################################################################################
                // get item
                break;
            } else if (isTrainerEscape == false) {
                numSelect = 0;
                // ## print
                System.out.println("============================================================");
                System.out.println("Wild Pokemon HP : " + wildPokemonHP + "/" + wildPokemonMaxHP);
                System.out.println(pokemonBag.get(pIndex) + " HP : " + pokemonBag.get(pIndex).getHP() + "/"
                        + pokemonBag.get(pIndex).getMaxHP());
                System.out.println("Action :: [1] Attack    [2] Regen HP    [3] Change Pokemon");
                System.out.println("          [4] catch     [5] Escape");
                System.out.println("------------------------------------------------------------");
                // ## print
                while (true) {
                    System.out.print("Enter : ");
                    numSelect = in.nextInt();
                    if (numSelect == 1) {
                        wildPokemonHP -= pokemonBag.get(pIndex).getAP();
                        pokemonBag.get(pIndex).getDamage(wildPokemonAP);
                        pokemonBag.get(pIndex).lossHugryPoint(5);
                        pokemonBag.get(pIndex).lossSleepPoint(5);
                        if (pokemonBag.get(pIndex).isDie()) {
                            delay(400);
                            double tmpLossExp = 0.0;
                            tmpLossExp = randomDouble(5, 15);
                            pokemonBag.get(pIndex).lossExp(tmpLossExp);
                            // ## print
                            System.out.println("------------------------------------------------------------");
                            System.out.println(pokemonBag.get(pIndex) + " is dead!   Death : "
                                    + pokemonBag.get(pIndex).getDeathCount() + " times");
                            System.out.println(pokemonBag.get(pIndex) + " loss "
                                    + String.format("%.2f", tmpLossExp) + " Exp.");
                            delay(400);
                            System.out.println("You have to 'Change pokemon' or 'Escape' !!");
                            System.out.println("Select ::   [1] Change Pokemon ");
                            System.out.println("       ::   [2] Escape");
                            // ## print
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
                        pokemonBag.get(pIndex).regenHealth(20);
                        break;
                    } else if (numSelect == 3) {
                        // chage pokemon
                        // ################################################################################################################
                        // ################################################################################################################
                        System.out.println("Change Pokemon Not awailable Now!");
                    } else if (numSelect == 4) {
                        catchPokemon();
                        break;
                    } else if (numSelect == 5) {
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

    private void catchPokemon() {

    }

    // private void listPokemon() {
    // int index = 1;
    // System.out.println("Your Pokemon : ");
    // for (Pokemon p : pokemonBag) {
    // System.out.println(" [" + index + "] " + p);
    // }
    // }

    // private int findPokemonIndex() {
    // int index = 0;
    // for (Pokemon p :pokemonBag) {
    // if()
    // }
    // }

    private static void delay(int _delayInMicrosec) {
        try {
            Thread.sleep(_delayInMicrosec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    private int randomInt(int min, int max) {
        int randNum = 0;
        randNum = (int) (Math.random() * ((max - min) + 1)) + min;
        return randNum;
    }

    private double randomDouble(int min, int max) {
        double randNum = 0;
        randNum = (double) Math.random() * ((max - min) + 1) + min;
        return randNum;
    }
}