package raiseoftali;

public class Perk {
    private String name;
    private String description;
    private int cost;
    private int level;
    private int maxLevel;
    private boolean active;
    public Perk(String name, String description, int cost, int maxLevel) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.maxLevel = maxLevel;
        this.level = 0;
        this.active = false;
    }
    public void activate() { //activates the perk
        this.active = true;
    }
    public void deactivate() {
        this.active = false;
    }
    public String getDescription() {
        return this.description;
    }
    public int getCost() { //returns the cost of the perk
        return this.cost;
    }
    public int getLevel() {
        return this.level;
    }
    public int getMaxLevel() { //returns the max level of the perk
        return this.maxLevel;
    }
    public boolean isActive() {
        return this.active;
    }
    public String getName() {
        return this.name;
    }
    public Perk setActive(boolean b) {
        this.active = b;
        return this;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCost(int cost) { //sets the cost of the perk to the given value
        this.cost = cost;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
    }