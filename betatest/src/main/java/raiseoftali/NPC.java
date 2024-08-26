package raiseoftali;
import java.util.ArrayList;

public class NPC {
    private String name;
    private String description;
    private String type;
    private Quests quest;
    private Item questItem;
    private final  ArrayList<Item> inventory;
    public Item setQuestItem;
    private Game game;
    private final Item goldStar = new Item("Gold Star", "A shiny gold star", "reward", game);
    public NPC(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.inventory = new ArrayList<>();
        Item trash = new Item("Trash", "A piece of trash", "trash", game);
        this.addItem(trash);
        this.questItem = trash;
    }
    public Item getQuestItem() { //returns the quest item of the NPC 
        return questItem;
    }
    public void setQuestItem(Item questItem) {
        this.questItem = questItem;
    }
    public Game getGame() {
        return game;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String getType() { //returns the type of the NPC. Teacher, Rejuve, etc.
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
    public String interact(Player player) { //returns a string that is printed to the GUI when the player interacts with the NPC.
        return "Blargh";  
    }
    public void dialog(Player player) { //prints the NPC's greeting to the GUI and checks if the player has the quest item. If the player has the quest item, the NPC will take the item and give the player a reward.
        player.getGame().getGUI().printToJTextPane( "Hello, I am " + this.getName().replace("_" , " ") + ". I am a " + this.getType().replace("_", " ") + ".");
        if(player.hasItem(this.getQuestItem().getName())){
            player.getGame().getGUI().printToJTextPane( "You have the " + this.getQuestItem().getName() + " I need!");
            player.removeItem(player.getItemByName(this.getQuestItem().getName()));
            player.getGame().getGUI().printToJTextPane( "You have given me the " + this.getQuestItem().getName() + "!");
            giveReward(goldStar, player);
            }
        }
    public void setQuest(Quests quest) {
        this.quest = quest;

    }
    public Quests getQuest() {
        return quest;
    }
    public void returnGoldStars(Player player) { //takes a gold star from the player and gives the player 100 experience points.
        player.getArrayInventory().remove(player.getItemByName("Gold Star"));
        player.addExperience(100);
        player.getGame().getGUI().printToJTextPane( "You have given me a Gold Star!");
        player.getGame().getGUI().printToJTextPane( "You have gained 100 experience points!");

    }
    private void addItem(Item trash) {
        this.inventory.add(trash);
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public Item getSetQuestItem() {
        return setQuestItem;
    }
    public void setSetQuestItem(Item setQuestItem) {
        this.setQuestItem = setQuestItem;
    }

    private void giveReward(Item item, Player player) { //gives the player a reward based on the item given to the NPC.
        switch(item.getName()){
            case "Gold Star" -> {
                player.addItem(goldStar);
            }
        }
        
    }
}
