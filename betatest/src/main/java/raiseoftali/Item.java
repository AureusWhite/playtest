package raiseoftali;

import java.util.ArrayList;

public class Item {
    String name;
    String description;
    private String type;
    private String optional;
    private boolean locked;
    private boolean takeable;
    protected Game game;
    public boolean isTakeable() {
        return takeable;
    }
    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    private ArrayList<Item> items = new ArrayList<>();
    private final  int slot;
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public Item(String name, String description, String type, Game game) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.slot = -1;       
        this.items = new ArrayList<>(); 
        this.game = game;
        doTakeable();
    }
    public String getOptional() {
        return optional;
    }
    public void setOptional(String optional) {
        this.optional = optional;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return this.name;
    }
    public void use(Player player){
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"Default Use Method");
    }
    public String getType() {
        return this.type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getSlot() {
        return this.slot;
    }
    void addItem(Item item) {
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        if(this.type.equalsIgnoreCase("container")) 
        {     
        this.items.add(item);
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"this happened2" + item.getName());
            if(this.getName().equalsIgnoreCase("trash can")&&item.getType().equalsIgnoreCase("trash")) {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"this happened2" + item.getName());
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You put the trash in the " + this.getName());
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You gained 10 experience points");
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You now have " + game.player.getExperience() + " experience points");
                item.setTakeable(false);
                game.player.getArrayInventory().add(new Item("Gold Star", "These gold stars can be used for privlages like toys or exchanged for Exp", "reward", game));
            }
    } else {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't put anything in the " + this.getName());
        }
        this.items.add(item); }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public void removeItemByName(String itemName) {
        for(Item item : this.items) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                this.items.remove(item);
                break;
            }
        }
    }
    private void doTakeable() {
        if(this.type.equalsIgnoreCase("container")) {
            this.takeable = false;
        } else if (this.type.equalsIgnoreCase("furniture")) {
            this.takeable = false;
        }
    }
}

