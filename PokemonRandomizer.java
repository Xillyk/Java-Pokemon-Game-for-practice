import java.util.*;

public class PokemonRandomizer {
    public static ArrayList<Pokemon> getPokemons(int amount) {
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        for(int i = 0; i < amount; i++){
            int type = (int)(Math.random()*4);
            if(type == 0)
                pokemons.add(new Pikachu("Wild Pikachu"));
            else if(type == 1)
                pokemons.add(new Charmander("Wild Charmander"));
            else if(type == 2)
                pokemons.add(new Clefairy("Wild Clefairy"));
            else if(type == 3)
                pokemons.add(new Squirtle("Wild Squirtle"));
        }
        return pokemons;
    }
}