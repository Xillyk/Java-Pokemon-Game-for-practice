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
    private int exp;
    private int maxExpPerLevel;
    private int level;
    private int deathCount;
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
        //attackPoint = GameUtility.randomInt(30*level/3, 50*level/3);   //***************************** */
        sleepPoint = 100; // %
        maxSleepPoint = sleepPoint;
        hungryPoint = 100; // %
        maxHungryPoint = hungryPoint;
        if (typeCreature == 0) {            //trainer's pokemon
            exp = 0;
            maxExpPerLevel = GameUtility.randomInt(70*level/3, 160*level/3);
        } else if (typeCreature == 1) {     //wild pokemon 
            exp = GameUtility.randomInt(50*level/3, 70*level/3);
        }
        level = 1;
        deathCount = 0;
        getCaught = false;
    }

    //#---------------action-----------------

    abstract public void attack(Pokemon p);

    //#---------------status------------

    public int getHPStatusForCheck() {
        if (healthPoint >= maxHealthPoint* 0.6) {
            return 2;
        } else if (healthPoint >= maxHealthPoint * 0.4 && healthPoint <= maxHealthPoint *0.6) {
            return 1;
        } else 
            return 0;
    }

    public int getHGStatusForCheck() {
        if (hungryPoint >= maxHungryPoint* 0.5) {
            return 2;
        } else if (hungryPoint >= maxHungryPoint * 0.3 && hungryPoint <= maxHungryPoint *0.5) {
            return 1;
        } else 
            return 0;
    }

    public int getSPStatusForCheck() {
        if (sleepPoint >= maxSleepPoint* 0.5) {
            return 2;
        } else if (sleepPoint >= maxSleepPoint * 0.3 && sleepPoint <= maxSleepPoint *0.5) {
            return 1;
        } else 
            return 0;
    }

    public String getHPStatus() {
        return healthPoint + " / " + maxHealthPoint;
    }

    public String getHGStatus() {
        return hungryPoint + " / " + maxHungryPoint;
    }

    public String getSPStatus() {
        return sleepPoint + " / " + maxSleepPoint;
    }

    public String getEXPStatus() {
        return exp + " / " + maxExpPerLevel;
    }

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

    //#--------------get caught-----------------  
    // set creature type from wild pokemon to trainer's pokemon (using in chatch pokemon method) + also set new nickname

    public void getCaught (String nickName) {
        changeDetail();
        setNickName(nickName);
        getCaught = true;
    }

    public void changeDetail() {
        healthPoint = maxHealthPoint;   //reset hp to be max
        exp = 0;                      //reset exp to be 0.0
        maxExpPerLevel = GameUtility.randomInt(70*level/3, 160*level/3);     //set new maxExp 
    }

    //#---------------setting----------------

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    //# --------------Exp and Level------------------

    public void earnExp(int monsterExp) {
        if ((exp + monsterExp) >= maxExpPerLevel) {
            exp = (exp + monsterExp) - maxExpPerLevel;
            levelUp();
        } else {
            exp += monsterExp;
        }
    }

    public void levelUp() {
        level += 1;
        maxExpPerLevel = GameUtility.randomInt(70*level/3, 160*level/3);
        maxHealthPoint = GameUtility.randomInt(50*level/2, 70*level/2);
        attackPoint = GameUtility.randomInt(30*level/3, 50*level/3);
        System.out.println(nickName + " level up!!");
    }

    public void lossExp(int value){
        if ((exp - value) > 0 ) {
            exp -= value;
        } else {
            exp = 0;
        }
    }

    public boolean willLevelUp(int monsterExp) { 
        if (exp + monsterExp >= maxExpPerLevel) {
            return true;
        } else 
            return false;
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
        System.out.println(" HP : " + getHP() + "/" + getMaxHP());

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

    public void eatBerry(int valueHgP) {
        if ((hungryPoint + valueHgP) >= maxHungryPoint) {
            hungryPoint = maxHungryPoint;
        } else {
            hungryPoint += valueHgP;
        }
        System.out.println(" HGP : " + getHungryPoint() + "/" + getMaxHungryPoint());

    }

    public void sleep() {
        sleepPoint = maxSleepPoint;
        lossHugryPoint(20);
        System.out.println(nickName + " Sleep Point : " + getSleepPoint() + "/" + getMaxSleepingPoint());
    }

    // #-------------get method----------------

    public String getNickName() {
        return nickName;
    }

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
        attackPoint = GameUtility.randomInt(30*level/3, 50*level/3);
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

    public int getExp() {
        return exp;
    }

    public int getMaxExpPerLevel() {
        return maxExpPerLevel;
    }

    public int getDeathCount() {
        return deathCount;
    }

}