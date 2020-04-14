import java.util.*;

public class Trainer {
    private int numSelect;
    private int pIndex; // pokemonIndex
    private String str;
    private Scanner in;

    private int levelUpCount; 
    private String name;
    private ArrayList<Pokemon> pokemonBag;
    private ArrayList<Item> itemBag;
    private int numOfPokemonInBag;

    public Trainer() {
        pIndex = 0;
        str = "";
        in = new Scanner(System.in);
        levelUpCount = 1;
        name = "";
        pokemonBag = new ArrayList<Pokemon>();
        itemBag = new ArrayList<Item>();
        numOfPokemonInBag = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public void receiveFirstPokemon() {
        ArrayList<Pokemon> randPokemons = PokemonRandomizer.getPokemons(3);
        System.out.println("Select your first pokemon");
        System.out.println("    [1] " + randPokemons.get(0).getName());
        System.out.println("    [2] " + randPokemons.get(1).getName());
        System.out.println("    [3] " + randPokemons.get(2).getName());
        numSelect = 0;
        str = "";
        while (true) {
            System.out.print("Enter : ");
            numSelect = in.nextInt();
            in.nextLine();
            if (numSelect >= 1 && numSelect <= 3) {
                while (true) {
                    System.out.print("Enter Pokemon's name : ");
                    str = in.nextLine();
                    if (str != null && !str.equals("")) {
                        if (numSelect == 1) {
                            randPokemons.get(0).setNickName(str);
                            pokemonBag.add(randPokemons.get(0));
                            numOfPokemonInBag++;
                        }
                        else if (numSelect == 2) {
                            randPokemons.get(1).setNickName(str);
                            pokemonBag.add(randPokemons.get(1));
                            numOfPokemonInBag++;
                        }   
                        else if (numSelect == 3) {
                            randPokemons.get(2).setNickName(str);
                            pokemonBag.add(randPokemons.get(2));
                            numOfPokemonInBag++;
                        }
                        break;  //break if str is valid                                          
                    }
                    System.out.println("Invalid Input!, Try Again");
                }
                break;  //break if numSelect is valid 
            }
            System.out.println("Invalid Input!, Try Again");
        }

        GameUtility.delay(500);
        System.out.println("So, " + pokemonBag.get(0) + " is your first pokemon.");
        GameUtility.delay(500);
        System.out.println("Let's Adventure !!");
        GameUtility.delay(500);
    }

    private void showActInterface() {
        System.out.println("============================================================");
        System.out.println("Choose Action       :: [1] Go Adventure");
        System.out.println("                    :: [2] Feed pokemon");
        System.out.println("                    :: [3] get your pokemon rest");
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
            GameUtility.delay(500);
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
                    goAdventure();
                    GameUtility.pressEnterToContinue();
                    break;
                } else if (numSelect == 2) {
                    pokemonBag.get(pIndex).eatBerry(20, 10); //##value
                    break;
                } else if (numSelect == 3) {
                    pokemonBag.get(pIndex).sleep();
                    GameUtility.pressEnterToContinue();
                    break;
                } else if (numSelect == 4) {
                    // pokemonCenter();
                    break;
                } else if (numSelect == 5) {
                    pokemonBag.get(pIndex).getStatus();
                    GameUtility.pressEnterToContinue();
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
    private void goAdventure() {
        boolean isTrainerEscape = false;
        boolean firstRoundAttack = true;
        Pokemon wildPokemon;
        if(levelUpCount < 3) {
            wildPokemon = PokemonRandomizer.getOnePokemon(levelUpCount, 1);
        } else {
            wildPokemon = PokemonRandomizer.getOnePokemon(GameUtility.randomInt(levelUpCount - 1, levelUpCount + 1), 1);
        }   
       
        GameUtility.delay(1000);
        System.out.println("Wild Pokemon in your area!!!");

        while (!isTrainerEscape) {
            GameUtility.delay(500);

            if (wildPokemon.isDie() && isTrainerEscape == false) {
                GameUtility.delay(500);
                System.out.println("Hurey!! You beat wild pokemon down!!");
                GameUtility.delay(500);
                System.out.println(pokemonBag.get(pIndex) + " earned " + String.format("%.2f", wildPokemon.getExp()) + " Exp");
                pokemonBag.get(pIndex).earnExp(wildPokemon.getExp());        
                System.out.println("[ " + pokemonBag.get(pIndex).getHP() + "/" + pokemonBag.get(pIndex).getMaxHP() + " HP ]");
                System.out.println("[ " + String.format("%.2f", pokemonBag.get(pIndex).getExp()) + "/"+ String.format("%.2f", pokemonBag.get(pIndex).getMaxExpPerLevel()) + " Exp ]");
                // ################################################################################################################
                // get item
                break;  
                
            } else if (isTrainerEscape == false) {
                numSelect = 0;
                // ## print
                System.out.println("============================================================");
                System.out.println(wildPokemon + "      Name :  " + wildPokemon.getName() + "     type : " + wildPokemon.getType());
                System.out.println("HP : " + wildPokemon.getHP() + "/" + wildPokemon.getMaxHP());
                System.out.println(pokemonBag.get(pIndex) + " HP : " + pokemonBag.get(pIndex).getHP() + "/" + pokemonBag.get(pIndex).getMaxHP());
                System.out.println("============================================================");
                // ## print
                //chang pokemon in first round
                if (firstRoundAttack == true) {
                    changePokemon();
                    firstRoundAttack = false;
                }

                //print action
                System.out.println("Action :: [1] Attack    [2] Regen HP    [3] Change Pokemon");
                System.out.println("          [4] catch     [5] Escape");
                System.out.println("------------------------------------------------------------");
                
                while (true) {
                    System.out.print("Enter : ");
                    numSelect = in.nextInt();
                    if (numSelect == 1) {
                        //attack
                        wildPokemon = pokemonBag.get(pIndex).attack(wildPokemon);

                        if (pokemonBag.get(pIndex).isDie()) {
                            GameUtility.delay(400);
                            double tmpLossExp = 0.0;
                            tmpLossExp = GameUtility.randomDouble(5, 15);
                            pokemonBag.get(pIndex).lossExp(tmpLossExp);
                            // ## print
                            System.out.println("------------------------------------------------------------");
                            System.out.println(pokemonBag.get(pIndex) + " is dead!   Death : " + pokemonBag.get(pIndex).getDeathCount() + " times");
                            System.out.println(pokemonBag.get(pIndex) + " loss " + String.format("%.2f", tmpLossExp) + " Exp.");
                            GameUtility.delay(400);
                            System.out.println("You have to 'Change pokemon' or 'Escape' !!");
                            System.out.println("Select ::   [1] Change Pokemon ");
                            System.out.println("       ::   [2] Escape");
                            // ## print
                            while (true) {
                                numSelect = 0;
                                System.out.print("Enter :");
                                numSelect = in.nextInt();
                                if (numSelect == 1) {
                                    changePokemon();
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
                        changePokemon();
                        break;
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

    private void changePokemon() {
        //########## choose pokemon
        System.out.println("Choose Pokemon");
        //list pokemon in pokemonBag
        for(Pokemon p : pokemonBag) {
            int i = 0;
            System.out.println("[" + (i+1) + "]     " + p + "    Name : " + p.getName() + "    Type : " + p.getType() + "    Status : " + p.getLifeStatus());
            i++;
        }
        //if (numOfPokemonInBag >= 2) { 
            numSelect = 0;
            while (true) {
                System.out.print("Enter : ");
                numSelect = in.nextInt(); 
                if (numSelect >= 1 && numSelect <= numOfPokemonInBag) {
                    pIndex = numSelect - 1;
                    if (!pokemonBag.get(pIndex).isDie()) {
                       break; 
                    } else {
                        System.out.println("You Select Died Pokemon, Select Again !!");
                    }
                } else {
                    System.out.println("Invalid Input!, Try Again");
                }
            }
        // } else {
        //     System.out.println("You have only 1 pokemon in bag");
        // }
    }

    private void catchPokemon() {
        //check before catch pokemon
        //if ()

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
}