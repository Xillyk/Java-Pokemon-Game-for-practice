public abstract class Item{
    //superclass
    //items in game 
    private String name;
    private String type;
    private int healValue;
    private int feedValue;

    public Item(String name, String type) { 
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }

    public int getHealValue() {
        return healValue;
    }

    public void setFeedValue(int feedValue) {
        this.feedValue = feedValue;
    }

    public int getFeedValue() {
        return feedValue;
    }
}