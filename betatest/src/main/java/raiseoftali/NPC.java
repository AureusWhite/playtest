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
    public Item getQuestItem() {
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
    public String interact(Player player) {
        return "Blargh";  
    }
    public void dialog(Player player) {
        player.getGame().getGUI().printToJTextArea(player.getGame().getGUI().getjTextArea(), "Hello, I am " + this.getName() + ". I am a " + this.getType().replace("_", " ") + ".");
        if(player.hasItem(this.getQuestItem().getName())){
            player.getGame().getGUI().printToJTextArea(player.getGame().getGUI().getjTextArea(), "You have the " + this.getQuestItem().getName() + " I need!");
            player.getArrayInventory().remove(this.getQuestItem());
            giveReward(goldStar, player);
            player.removeItem(this.getQuestItem());
            }
        }
    public void setQuest(Quests quest) {
        this.quest = quest;

    }
    public Quests getQuest() {
        return quest;
    }
    public void returnGoldStars(Player player) {
        player.getArrayInventory().remove(player.getItemByName("Gold Star"));
        player.addExperience(100);
        player.getGame().getGUI().printToJTextArea(player.getGame().getGUI().getjTextArea(), "You have given me a Gold Star!");
        player.getGame().getGUI().printToJTextArea(player.getGame().getGUI().getjTextArea(), "You have gained 100 experience points!");

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

    private void giveReward(Item item, Player player) {
        switch(item.getName()){
            case "Gold Star" -> {
                player.addItem(goldStar);
            }
        }
        player.getArrayInventory().add(item);
        
    }
}
