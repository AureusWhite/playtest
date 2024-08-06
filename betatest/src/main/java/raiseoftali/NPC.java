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

    public Game getGame() {
        return game;
    }

    public NPC(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.inventory = new ArrayList<>();
        Item trash = new Item("Trash", "A piece of trash", "trash", game);
        this.addItem(trash); 
    }

    private void addItem(Item trash) {
        this.inventory.add(trash);
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
        this.questItem = new Item("Trash", "A piece of trash", "trash", game);
       game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "Hello, " + player.getName() + "!");
       game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "I am " + this.getName() + ".");
       game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), this.getDescription());
        if(this.quest != null) {
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "I have a quest for you!");
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), this.quest.getDescription());
        }
        if(player.hasItem(this.questItem.getName())){
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "You have the item I need!");
            player.getArrayInventory().remove(player.getItemByName(this.questItem.getName()));
            player.addExperience(100);
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "You have completed the quest!");
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "You have gained 100 experience points!");
            this.quest = null;
            this.questItem = null;
        }
        if(this.quest == null) {
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), "I have no quest for you.");
        }
    }

    public void setQuest(Quests quest) {
        this.quest = quest;

    }
    public Quests getQuest() {
        return quest;
    }
}
