import java.util.*;

public class Bag {
    private ArrayList<Item> itemBag;
    private ArrayList<String> names;
    private HashMap<String, Integer> nameValueHashMap;

    public Bag() {
        itemBag = new ArrayList<Item>();
        names = new ArrayList<String>();
        nameValueHashMap = new HashMap<String, Integer>();
        names.add("Berry");
        names.add("Medicine");
        initHashMap();

    }
    // BAG INFO ------------------------------------------------

    public void initHashMap() {
        for (int i = 0; i < getNumItemNames(); i++) {
            nameValueHashMap.put(names.get(i), 0);
        }
    }
    
    // get all items in bag

    public ArrayList<Item> getAllItem() {
        return itemBag;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public int getNumItemNames() {
        return names.size();
    }

    public int getItemAmount(String name) {
        return nameValueHashMap.get(name);
    }

    public int getNumItemInBag() {
        return itemBag.size();
    }

    public Item getItem(String itemName) {
        for (Item it : itemBag) {
            if (it.getName().equals(itemName)) {
                return it;
            }
        }
        return null;
    }

    public void addItem(int num) {
        ArrayList<Item> tmpItems = ItemRandomizer.getItems(num);

        // increase value in hm (update hm)
        for (Item it : tmpItems) {
            for (int i = 0; i < getNumItemNames(); i++) {
                if (it.getName().equals(names.get(i))) {
                    nameValueHashMap.put(names.get(i), nameValueHashMap.get(names.get(i)) + 1);
                }
            }
        }
        itemBag.addAll(tmpItems);
    }

    public void removeItem(String name) {
        // remove item from itemBag

        for (int i = 0; i < getNumItemInBag(); i++) {
            if (itemBag.get(i).getName() == name) {
                itemBag.remove(i);
                break;
            }
        }
        // decrese value from hm (update hm)
        nameValueHashMap.put(name, nameValueHashMap.get(name) - 1);
    }

    // Collections.frequency(itemBag, new Berry());

}