package raiseoftali;



public class Quests {
    private String name;
    private String description;
    private String type;
    public Item questItem;
    private Item trash;
    private Item completedTest;
    private Item shinyPenny;

    public Quests(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
    public Item getQuestItem() {
        return questItem;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean isCompleted(Player player) {
        switch(this.type){
            case "fetch" -> { 
                return player.getArrayInventory().contains(this.questItem);
            }
            case "test" -> {
                return player.getExperience()>100|| player.getArrayInventory().contains(this.questItem);
            }
            case "social" -> {
                return player.getResilience()>80 || player.getArrayInventory().contains(this.questItem);
            }
        }
        return false;
    }
    public void setQuestRequirement() {
        switch(this.type){
            case "fetch" -> this.questItem = trash;
            case "test" -> this.questItem = completedTest;
            case "social" -> this.questItem = shinyPenny;
        }
    }
    public void setQuestItem(Item item) {
        this.questItem = item;
    }
    
}