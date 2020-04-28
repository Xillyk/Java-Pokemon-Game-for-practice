import java.util.*;

public class Trainer {
    private int pIndex; // pokemonIndex
    private int levelUpCount; 
    private String gender;
    private String name;
    private int money;
    private int killStat;
    private int donateStat;
    private ArrayList<Pokemon> pokemonBag;
    private Bag itemBag;

    public Trainer() {
        pIndex = 0;
        levelUpCount = 1;
        name = "";
        money = 0;
        killStat = 0;
        donateStat = 0;
        pokemonBag = new ArrayList<Pokemon>();
        itemBag = new Bag();
    }

    // TRAINER PROFILE ---------------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void successKill() {
        killStat++;
    }

    public int getKillStat() {
        return killStat;
    }

    public void donatePokemon() {
        donateStat++;
    }

    public int getDonateStat() { 
        return donateStat;
    }

    // MONEY -------------------------------------------------
    public int getAmountMoney() {
        return money;
    }

    public void receiveMoney(int money) {
        this.money += money;
    }

    public void pay(int fee) {
        if (money - fee >= 0) {
            money -= fee;
        }
    }

    public Boolean hasEnoughMoney(int fee) {
        if (money - fee < 0) {
            return false; 
        } else 
            return true;
    }

    // ITEM BAG -------------------------------------------------

    public Bag getBag() {
        return itemBag;
    }

    // POKEMON BAG -------------------------------------------------

    public ArrayList<Pokemon> getPokemonBag() {
        return pokemonBag;
    }

    public int getPokemonIndex() {
        return pIndex;
    }

    public void setPokemonIndex(int pIndex) {
        this.pIndex = pIndex;
    }

    public void receivePokemonInFirstTime(Pokemon p) {
        pokemonBag.add(p);
    }

    public void increaseLevelUpCount() {
        levelUpCount++;
    }

    public int getLevelUpCount() {
        return levelUpCount;
    }

    public void donatePokemon(int index) {
        pokemonBag.remove(index);
        pIndex = 0;
        donateStat++;
    }
}