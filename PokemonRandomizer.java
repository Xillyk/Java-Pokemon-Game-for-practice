import java.util.*;

public class PokemonRandomizer {
    public static ArrayList<Pokemon> getPokemons(int amount) {
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        for (int i = 0; i < amount; i++) {
            int type = (int) (Math.random() * 4);
            if (type == 0)
                pokemons.add(new Pikachu("Wild Pikachu", 1, 0));
            else if (type == 1)
                pokemons.add(new Charmander("Wild Charmander", 1, 0));
            else if (type == 2)
                pokemons.add(new Clefairy("Wild Clefairy", 1, 0));
            else if (type == 3)
                pokemons.add(new Squirtle("Wild Squirtle", 1, 0));
        }
        return pokemons;
    }

    public static Pokemon getOnePokemon(int level, int typeCreature) {
        int type = (int) (Math.random() * 4);
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
        }
        return null;
    }
}