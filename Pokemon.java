public class Pokemon {
    private String pokemonName;
    private String pokemonType;
    private int pokemonHP;
    private int pokemonMaxHP;
    private int pokemonAP; // attack point
    private int pokemonPP; // power point
    private int pokemonSleepPoint;
    private int pokemonMaxSleepPoint;
    private int pokemonHungryPoint;
    private int pokemonMaxHungryPoint;
    private double pokemonExp;
    private double pokemonMaxExpPerLevel;
    private int pokemonLevel;
    private int deathCount;
    private int tmpHP;
    private double tmpMaxExp;
    private int tmpAP;

    public Pokemon() {
        pokemonName = "";
        pokemonType = "";
        pokemonHP = randomInt(50, 70);
        pokemonMaxHP = pokemonHP;
        pokemonAP = randomInt(20, 40);
        pokemonSleepPoint = 100; // %
        pokemonMaxSleepPoint = pokemonSleepPoint;
        pokemonHungryPoint = 100; // %
        pokemonMaxHungryPoint = pokemonHungryPoint;
        pokemonExp = 0.0;
        pokemonMaxExpPerLevel = 50.0;
        pokemonLevel = 1;
        deathCount = 0;
        tmpHP = pokemonHP;
        tmpMaxExp = pokemonMaxExpPerLevel;
        tmpAP = pokemonAP;
    }
    //# -------------user initial-------------------
    public void setName(String _pokemonName) {
        pokemonName = _pokemonName;
    }

    public void setType(int numType) {
        if (numType == 1) {
            pokemonType = "Normal";
        } else if (numType == 2) {
            pokemonType = "Fire";
        } else if (numType == 3) {
            pokemonType = "Water";
        } else if (numType == 4) {
            pokemonType = "Grass";
        } else if (numType == 5) {
            pokemonType = "Electric";
        } else if (numType == 6) {
            pokemonType = "Poison";
        }
    }
    //# --------------Exp and Level------------------
    public void earnExp(double monsterExp) {
        if ((pokemonExp + monsterExp) >= pokemonMaxExpPerLevel) {
            pokemonExp = (pokemonExp + monsterExp) - pokemonMaxExpPerLevel;
            levelUp();
        } else {
            pokemonExp += monsterExp;
        }
    }

    public void levelUp() {
        pokemonLevel += 1;
        pokemonMaxExpPerLevel = tmpMaxExp * pokemonLevel;
        pokemonMaxHP = tmpHP * pokemonLevel;
        pokemonAP = tmpAP * pokemonLevel;
    }

    public void lossExp(double value){
        if ((pokemonExp - value) > 0.0 ) {
            pokemonExp -= value;
        } else {
            pokemonExp = 0.0;
        }
    }
    //# --------------Get Damage------------------
    public void getDamage(int monsterAP) {
        if ((pokemonHP - monsterAP) > 0) {
            pokemonHP -= monsterAP;
        } else {
            pokemonHP = 0;
            deathCount++;
        }
    }

    public boolean isDie() {
        if (pokemonHP <= 0)
            return true;
        else
            return false;
    }
    //# --------------Healing------------------
    public void curePokemon() {
        pokemonHP = pokemonMaxHP;
    }

    public void regenHealth(int value) {
        // add more
        if ((pokemonHP + value) >= pokemonMaxHP && (value != -1)) {
            pokemonHP = pokemonMaxHP;
        } else if (value == -1) {       //special
            pokemonHP = pokemonMaxHP;
        } else {
            pokemonHP += value;
        }
    }
    // #------------------decrese------------------
    public void lossSleepPoint(int value) {
        if ((pokemonSleepPoint - value) <= 0) {
            pokemonSleepPoint = 0;
        }
        else {
            pokemonSleepPoint -= value;
        }
    }

    public void lossHugryPoint(int value) {
        if ((pokemonHungryPoint - value) <= 0) {
            pokemonHungryPoint = 0;
        }
        else {
            pokemonHungryPoint -= value;
        }
    }
    //# ----------------increase------------------
    public void eatBerry(int valueHp , int valueHgP) {
        if ((pokemonHP + valueHp) >= pokemonMaxHP) {
            pokemonHP = pokemonMaxHP;
        } else {
            pokemonHP += valueHp;
        }
        if ((pokemonHungryPoint + valueHgP) >= pokemonMaxHungryPoint) {
            pokemonHungryPoint = pokemonMaxHungryPoint;
        } else {
            pokemonHungryPoint += valueHgP;
        }
    }

    public void sleep() {
        pokemonSleepPoint = pokemonMaxSleepPoint;
        lossHugryPoint(20);
        regenHealth(-1);        //set Hp to max
    }

    // #-------------get method----------------
    public String getName() {
        return pokemonName;
    }

    public String getType() {
        return pokemonType;
    }

    public int getHP() {
        return pokemonHP;
    }

    public int getMaxHP() {
        return pokemonMaxHP;
    }

    public int getAP() {
        return pokemonAP;
    }

    public int getPP() {
        return pokemonPP;
    }

    public int getSleepPoint() {
        return pokemonSleepPoint;
    }

    public int getMaxSleepingPoint() {
        return pokemonMaxSleepPoint;
    }

    public int getHungryPoint() {
        return pokemonHungryPoint;
    }

    public int getMaxHungryPoint() {
        return pokemonMaxHungryPoint;
    }

    public int getLevel() {
        return pokemonLevel;
    }

    public double getExp() {
        return pokemonExp;
    }

    public double getMaxExpPerLevel() {
        return pokemonMaxExpPerLevel;
    }

    public int getDeathCount() {
        return deathCount;
    }
    // #-------------utility----------------
    private int randomInt(int min, int max) {
        int randNum = 0;
        randNum = (int) (Math.random() * ((max - min) + 1)) + min;
        return randNum;
    }

    private static double randomDouble(int min, int max) {
        double randNum = 0;
        randNum = (double) Math.random() * ((max - min) + 1) + min;
        return randNum;
    }
}