
package raiseoftali;
import java.util.ArrayList;

public class NPC {
    private String name;
    private String description;
    private String type;
    private Item questItem;
    private Quests quest;
    private final  ArrayList<Item> inventory;
    private Game game;
    private Item trash;
    public NPC(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.inventory = new ArrayList<>();
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
            }
        }
    public void setQuest(Quests quest) {
        this.quest = quest;

    }
    public Quests getQuest() {
        return quest;
    }
    public Item getSetQuestItem() {
        return questItem;
    }
    void reciveitem(Item item , Player player) { //checks if the item given to the NPC is the quest item. If it is, the NPC will take the item.
        if(item.isQuestItem()&&item.getName().equalsIgnoreCase("trash")){
            this.inventory.add(item);
            player.removeItem(item);
            player.getGame().getGUI().printToJTextPane( "You have given me the " + item.getName() + "!");
        }
    }
}