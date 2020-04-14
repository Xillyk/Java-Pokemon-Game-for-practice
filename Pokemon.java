public abstract class Pokemon {
    private String name;
    private String nickName;
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
    private boolean getCaught;

    public Pokemon(String name, String nickName, String type, int level, int typeCreature) {
        //setType(type);
        //type = "";
        this.name = name;
        this.nickName = nickName;
        this.type = type;
        this.level = level;

        healthPoint = GameUtility.randomInt(50*level/2, 70*level/2);   //***************************** */
        maxHealthPoint = healthPoint;
        attackPoint = GameUtility.randomInt(30*level/3, 50*level/3);   //***************************** */
        sleepPoint = 100; // %
        maxSleepPoint = sleepPoint;
        hungryPoint = 100; // %
        maxHungryPoint = hungryPoint;
        if (typeCreature == 0) {            //trainer's pokemon
            exp = 0.0;
            maxExpPerLevel = GameUtility.randomDouble(70*level/3, 160*level/3);
        } else if (typeCreature == 1) {     //wild pokemon 
            exp = GameUtility.randomDouble(50*level/3, 70*level/3);
        }
        level = 1;
        deathCount = 0;
        tmpHP = healthPoint;
        tmpMaxExp = maxExpPerLevel;
        tmpAP = attackPoint;
        getCaught = false;
    }
    //#---------------status------------

    public boolean isWeak() {
        if(healthPoint < (maxHealthPoint * 40 / 100)) {
            return true;
        } else if ((healthPoint >= maxHealthPoint * 40 /100) && (healthPoint <= maxHealthPoint * 60 /100)) {
            int rand = (int) (Math.random() * 2);
            if(rand == 0) 
                return true;
            else if (rand == 1)
                return false;
            else 
                return false;
        } else 
            return false;
    }

    public void getStatus() { 
        System.out.println("| Nickname     : " + nickName + "      Name : " + name);
        System.out.println("| Type         : " + getType() + "    Level : " + getLevel() + "    Death : " + getDeathCount() + " time");
        System.out.println("| HP           : " + getHP() + "/" + getMaxHP());
        System.out.println("| Exp          : " + String.format("%.2f", getExp()) + "/"+ String.format("%.2f", getMaxExpPerLevel()));
        System.out.println("| AP           : " + getAP());
        System.out.println("| Hungry Point : " + getHungryPoint() + "/" + getMaxHungryPoint());
        System.out.println("| Sleep Point  : " + getSleepPoint() + "/" + getMaxSleepingPoint());
    }

    //#--------------get caught-----------------  
    // set creature type from wild pokemon to trainer's pokemon (using in chatch pokemon method) + also set new nickname

    public void getCaught (String nickName) {
        changeDetail();
        setNickName(nickName);
        getCaught = true;
    }

    public void changeDetail() {
        healthPoint = maxHealthPoint;   //reset hp to be max
        exp = 0.0;                      //reset exp to be 0.0
        maxExpPerLevel = GameUtility.randomDouble(70*level/3, 160*level/3);     //set new maxExp 
    }

    public boolean isGetCaught() {
        if(getCaught == true)
            return true;
        else
            return false;
    }
    //#---------------setting----------------

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    //#---------------action-----------------

    public Pokemon attack(Pokemon wildPokemon) {
        //damage wild pokemon
        wildPokemon.getDamage(attackPoint);
        //get damage from wild pokemon 
        getDamage(wildPokemon.getAP());

        lossHugryPoint(5);   
        lossSleepPoint(5);
        return wildPokemon;
    }

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
        System.out.println(nickName + " level up!!");
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
        System.out.println(" HP : " + getHP() + "/" + getMaxHP());

    }

    public void sleep() {
        System.out.println(nickName + " is sleeping . . .");
        GameUtility.delay(2000);
        sleepPoint = maxSleepPoint;
        lossHugryPoint(20);
        regenHealth(-1);        //set Hp to max
        System.out.println(nickName + " Sleep Point : " + getSleepPoint() + "/" + getMaxSleepingPoint());
    }

    // #-------------get method----------------

    public String toString() {
        return nickName;
    }

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

    public String getLifeStatus() {
        if(isDie())
            return "Died";
        else 
            return "Alive";
    }
}