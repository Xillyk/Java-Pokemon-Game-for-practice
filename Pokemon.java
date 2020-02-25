public class Pokemon {
    private String name;
    private String type;
    private int healthPoint;
    private int maxHealthPoint;
    private int attackPoint; // attack point
    private int powerPoint; // power point
    private int sleepPoint;
    private int maxSleepPoint;
    private int hungryPoint;
    private int maxHungryPoint;
    private double exp;
    private double maxExpPerLevel;
    private int level;
    private int deathCount;
    private int tmpHP;
    private double tmpMaxExp;
    private int tmpAP;

    public Pokemon(String _name, int _type) {
        setType(_type);
        //type = "";
        name = _name;
        healthPoint = randomInt(50, 70);
        maxHealthPoint = healthPoint;
        attackPoint = randomInt(20, 40);
        sleepPoint = 100; // %
        maxSleepPoint = sleepPoint;
        hungryPoint = 100; // %
        maxHungryPoint = hungryPoint;
        exp = 0.0;
        maxExpPerLevel = 50.0;
        level = 1;
        deathCount = 0;
        tmpHP = healthPoint;
        tmpMaxExp = maxExpPerLevel;
        tmpAP = attackPoint;
    }
   // # -------------user initial-------------------
    public void setName(String _pokemonName) {
        name = _pokemonName;
    }

    public void setType(int numType) {
        if (numType == 1) {
            type = "Normal";
        } else if (numType == 2) {
            type = "Fire";
        } else if (numType == 3) {
            type = "Water";
        } else if (numType == 4) {
            type = "Grass";
        } else if (numType == 5) {
            type = "Electric";
        } else if (numType == 6) {
            type = "Poison";
        }
    }

    //#---------------initial for monster------------

    //public void setCatchedPokemonN

    //# --------------Exp and Level------------------
    public void earnExp(double monsterExp) {
        if ((exp + monsterExp) >= maxExpPerLevel) {
            exp = (exp + monsterExp) - maxExpPerLevel;
            levelUp();
        } else {
            exp += monsterExp;
        }
    }

    public void levelUp() {
        level += 1;
        maxExpPerLevel = tmpMaxExp * level;
        maxHealthPoint = tmpHP * level;
        attackPoint = tmpAP * level;
    }

    public void lossExp(double value){
        if ((exp - value) > 0.0 ) {
            exp -= value;
        } else {
            exp = 0.0;
        }
    }
    //# --------------Get Damage------------------
    public void getDamage(int monsterAP) {
        if ((healthPoint - monsterAP) > 0) {
            healthPoint -= monsterAP;
        } else {
            healthPoint = 0;
            deathCount++;
        }
    }

    public boolean isDie() {
        if (healthPoint <= 0)
            return true;
        else
            return false;
    }
    //# --------------Healing------------------
    public void getCured() {
        healthPoint = maxHealthPoint;
    }

    public void regenHealth(int value) {
        // add more
        if ((healthPoint + value) >= maxHealthPoint && (value != -1)) {
            healthPoint = maxHealthPoint;
        } else if (value == -1) {       //special
            healthPoint = maxHealthPoint;
        } else {
            healthPoint += value;
        }
    }
    // #------------------decrese------------------
    public void lossSleepPoint(int value) {
        if ((sleepPoint - value) <= 0) {
            sleepPoint = 0;
        }
        else {
            sleepPoint -= value;
        }
    }

    public void lossHugryPoint(int value) {
        if ((hungryPoint - value) <= 0) {
            hungryPoint = 0;
        }
        else {
            hungryPoint -= value;
        }
    }
    //# ----------------increase------------------
    public void eatBerry(int valueHp , int valueHgP) {
        if ((healthPoint + valueHp) >= maxHealthPoint) {
            healthPoint = maxHealthPoint;
        } else {
            healthPoint += valueHp;
        }
        if ((hungryPoint + valueHgP) >= maxHungryPoint) {
            hungryPoint = maxHungryPoint;
        } else {
            hungryPoint += valueHgP;
        }
    }

    public void sleep() {
        sleepPoint = maxSleepPoint;
        lossHugryPoint(20);
        regenHealth(-1);        //set Hp to max
    }

    // #-------------get method----------------
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHP() {
        return healthPoint;
    }

    public int getMaxHP() {
        return maxHealthPoint;
    }

    public int getAP() {
        return attackPoint;
    }

    public int getPP() {
        return powerPoint;
    }

    public int getSleepPoint() {
        return sleepPoint;
    }

    public int getMaxSleepingPoint() {
        return maxSleepPoint;
    }

    public int getHungryPoint() {
        return hungryPoint;
    }

    public int getMaxHungryPoint() {
        return maxHungryPoint;
    }

    public int getLevel() {
        return level;
    }

    public double getExp() {
        return exp;
    }

    public double getMaxExpPerLevel() {
        return maxExpPerLevel;
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