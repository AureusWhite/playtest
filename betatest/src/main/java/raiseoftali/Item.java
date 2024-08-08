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
    private boolean results;
    private boolean dispenser;

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
    public Game getGame() {
        return game;
    }
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
    public String playedWith() {
        if(this.type.equalsIgnoreCase("toy")) {
            return "You played with the " + this.name;
        }else{
            game.getPlayer().setExperience(game.getPlayer().getExperience() + 1);
            game.getPlayer().setResilience(slot);
            return this.name + "isn't very fun to play with";
            
        }        
    }
    public void use(Player player) {
        switch(this.slot){
            case -1 -> {
                if(this.type.equalsIgnoreCase("toy")) {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You played with the " + this.name);
                } else if(this.type.equalsIgnoreCase("food")){
                   player.getArrayInventory().remove(this);
                     game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You ate the " + this.name);
                     player.eatDrink(this); 

                } else if(this.type.equalsIgnoreCase("container")) {
                    this.displayInventory();
                    if(this.dispenser){
                        this.roomDispenser(player);
                    } else {
                        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"It's locked " + this.getName());
                    }
                }

            }

            case 0,1,2,3,4 -> player.equip((Equipment) this, this.slot);
        }
            }
    public String getType() {
        return this.type;
    }
    public void playedWith(Player player) {
        game = game.getGame();
        if(this.type.equalsIgnoreCase("toy")) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You played with the " + this.name);
        } else {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't play with the " + this.name);
        }
    }
    public void close() {
        if(this.getName().equalsIgnoreCase("container")) {
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You closed the "+ this.getName());
        } else {
           game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't close that");
        }
    
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
    public void displayInventory() {
        if(this.items.isEmpty()) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"There is nothing in the " + this.getName());
        } else {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"The " + this.getName() + " contains: ");
            for(Item item : this.items) {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),item.getName());
            }
        }
    }   
    void addItem(Item item) {
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        if(this.type.equalsIgnoreCase("container")) {           
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
        this.items.add(item);
    }
    private void doTakeable() {
        results =! (this.getName().equalsIgnoreCase("Trash Can")||this.getName().equalsIgnoreCase("Cleaning Set"));
        this.takeable = results;       
    }
    public void roomDispenser(Player player) {
        Item juiceBox = new Item("Juice Box", "A juice box", "food", game);
        Item animalCrackers = new Item("Animal Crackers", "A box of animal crackers", "food", game);
        if(this.getItems().isEmpty()){
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You bang on it to make the snacks come out");
            this.items.add(juiceBox);
            this.items.add(animalCrackers);
        } 
                
        if(player.getArrayInventory().contains(player.getItemByName("Juice Box"))){
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You already have a juice box");
        } else {
            player.getArrayInventory().add(juiceBox);
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You got a juice box");
        }
        if(player.getArrayInventory().contains(player.getItemByName("Animal Crackers"))){
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You already have animal crackers");
        } else {
            player.getArrayInventory().add(animalCrackers);
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You got animal crackers");
        }
    }

    void setDispenser(boolean b) {
        this.dispenser = b;
    }
}
