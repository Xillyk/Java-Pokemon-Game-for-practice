import java.util.*;

public class ItemRandomizer {
    public static ArrayList<Item> getItems(int amount) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < amount; i++) {
            int rand = (int) (Math.random() * 2);
            if (rand == 0) {
                items.add(new Berry());
            } else if (rand == 1) { 
                items.add(new Medicine());
            }
        }
        return items;
    }
}