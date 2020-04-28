import java.util.*;

public class PokemonRandomizer {
    public static ArrayList<Pokemon> getPokemons(int amount) {
        int[] rands = new int[8];
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        do {
            int type = (int) (Math.random() * 8);
            if (type == 0 && rands[type] == 0) {
                pokemons.add(new Pikachu("Wild Pikachu", 1, 0));
                rands[type] = 1;
                amount--;
            } else if (type == 1 && rands[type] == 0) {
                pokemons.add(new Charmander("Wild Charmander", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 2 && rands[type] == 0) {
                pokemons.add(new Clefairy("Wild Clefairy", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 3 && rands[type] == 0) {
                pokemons.add(new Squirtle("Wild Squirtle", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 4 && rands[type] == 0) {
                pokemons.add(new Caterpie("Wild Caterpie", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 5 && rands[type] == 0) {
                pokemons.add(new Pidgey("Wild Pidgey", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 6 && rands[type] == 0) {
                pokemons.add(new Vulpix("Wild Vulpix", 1, 0));
                rands[type] = 1;
                amount--;
            }
            else if (type == 7 && rands[type] == 0) {
                pokemons.add(new Psyduck("Wild Psyduck", 1, 0));
                rands[type] = 1;
                amount--;
            }
            
        } while (amount > 0);
        return pokemons;
    }

    public static Pokemon getOnePokemon(int level, int typeCreature) {
        int type = (int) (Math.random() * 8);
        if (type == 0) {
            Pokemon t0 = new Pikachu("Wild Pikachu", level, typeCreature);
            return t0;
        } else if (type == 1) {
            Pokemon t1 = new Charmander("Wild Charmander", level, typeCreature);
            return t1;
        } else if (type == 2) {
            Pokemon t2 = new Clefairy("Wild Clefairy", level, typeCreature);
            return t2;
        } else if (type == 3) {
            Pokemon t3 = new Squirtle("Wild Squirtle", level, typeCreature);
            return t3;
        } else if (type == 4) {
            Pokemon t4 = new Caterpie("Wild Caterpie", level, typeCreature);
            return t4;
        } else if (type == 5) {
            Pokemon t5 = new Pidgey("Wild Pidgey", level, typeCreature);
            return t5;
        } else if (type == 6) {
            Pokemon t6 = new Vulpix("Wild Vulpix", level, typeCreature);
            return t6;
        } else if (type == 7) {
            Pokemon t7 = new Psyduck("Wild Psyduck", level, typeCreature);
            return t7;
        } 
        return null;
    }
}