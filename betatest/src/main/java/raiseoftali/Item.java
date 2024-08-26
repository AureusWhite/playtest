package raiseoftali;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Item {
    String name;
    String description;
    private String type;
    private String optional;
    private boolean locked;
    private boolean takeable;
    protected Game game;
    private ArrayList<Item> items = new ArrayList<>();
    private final  int slot;
    private boolean isBroken;
    public boolean isBroken() { //returns true if the item is broken
        return isBroken;
    }
    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
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
    public ArrayList<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
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
    public void use(Player player, Game game) throws FileNotFoundException{
        switch(this.getType()){
            case "toy" ->{
                this.playWith(player);
            }
            case "test" ->{
                this.test(player);
            }
            case "tool" ->{
                this.useTool(player, Player.currentRoom.findBrokenItem(player));
            }
            case "book" ->{
                this.read(player);
            }
            case "money" ->{
                player.getGame().getGUI().printToJTextPane( "These shiny pennies are used to buy things. You have " + player.getMoney() + " shiny pennies.");
            }
            
        }
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
    public int getSlot() { //returns the slot of the item if it is equipment or -1 if it is not
        return this.slot;
    }
    public void removeItem(Item item) { //removes an item from the items list
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
    void addItem(Item item) { //adds an item to the items list
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    game.getGUI().printToJTextPane("You put the " + item.getName() + " in the " + this.getName());
    }
    private void doTakeable() { //sets the takeable variable based on the type of the item
        if(this.type.equalsIgnoreCase("container")) {
            this.takeable = false;
        } else this.takeable = !this.type.equalsIgnoreCase("furniture");
    }

    private void playWith(Player player) { //plays with the toy and gets a random outcome
        int outcome = (int)(Math.random() * 10+1);
        game.getGUI().printToJTextPane( outcome + " ");
        switch(outcome){
            case 1,2,3,4,5,6,7 -> {
                game.getGUI().printToJTextPane( "You play with the " + this.getName() + " but quickly get bored.");
                player.addExperience(5);
                player.addResilience(-5);
                break;
            }
            case 8,9 -> {
                game.getGUI().printToJTextPane( "You play with the " + this.getName() + " and have a good time!");
                player.addExperience(10);
                player.addResilience(20);
                break;
            }
            case 10 -> {
                game.getGUI().printToJTextPane( "You play with the " + this.getName() + " and have a bad time!");
                player.addExperience(5);
                this.breakItem(player);
                player.addResilience(-15);
                break;
            }
            
        }
    }

    private void test(Player player) { //completes the test and gets a random outcome
        int outcome = (int)(Math.random() * 10+1);
        switch(outcome){
            case 1,2,3,4,5 -> {
                game.getGUI().printToJTextPane( "You do the " + this.getName() + " test and it is to difficult.");
                player.addExperience(5);
                player.addResilience(-5);
                break;

            }
            case 6,7,8,9 -> {
                game.getGUI().printToJTextPane( "You test the " + this.getName() + " and it works perfectly!");
                player.addExperience(10);
                break;
            }
            case 10 -> {
                game.getGUI().printToJTextPane( "You test the " + this.getName() + " and it breaks!");
                this.breakItem(player);
                player.addResilience(-25);
                break;
        }
        
    }

}

    private void useTool(Player player, Item brokenItem) { //uses the tool to fix a broken item
        if(brokenItem != null){
           if (player.getItemByName(brokenItem.getName()) != null){
                game.getGUI().printToJTextPane( "You use the " + this.getName() + " to fix the " + brokenItem.getName() + "!");
                player.addExperience(10);
                player.addResilience(10);
                brokenItem.isBroken = false;
              } else {
                game.getGUI().printToJTextPane( "You don't have the " + brokenItem.getName() + " to fix!");
           }

            
        }
    }

    private void read(Player player) throws FileNotFoundException { //reads the book and gives the player experience
        try (FileReader fileReader = new FileReader(this.getName() + ".txt")) {
            fileReader.toString();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.getGUI().printToJTextPane( "You read the " + this.getName() + " and learn something new!");
        player.addExperience(10);

    }


    private void breakItem(Player player) { //breaks the item and removes it from the player's inventory.
        game.getGUI().printToJTextPane( "The " + this.getName() + " breaks!");
        this.isBroken = true;
        player.dropItem(this);
        player.addResilience(-25);
    }

    public void giveItem(Player player, Item item, int cost) {
        if(player.getMoney() < cost){
            game.getGUI().printToJTextPane( "You don't have enough money to buy the " + item.getName() + "!");
            return;
        }
        player.removeMoney(cost);
        player.addItem(item);
    }
}

