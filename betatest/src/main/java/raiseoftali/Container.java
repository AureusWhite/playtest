package raiseoftali;

public class Container extends Item{
    
    public Container(String name, String description, String type, Game game) {
        super(name, description, type, game);
        
    }
    @Override
    public void use(Player player, Game game) {
        if(this.isLocked()) {
            game.getGUI().printToJTextPane( "The " + this.getName() + " is locked.");   
            }else {
                this.giveSomeToPlayer(player,"Trash");
                game.getGUI().printToJTextPane( "You found " + this.getItems().size() + " items in the " + this.getName() + ".");
                for(Item item : this.getItems()) {
                    game.getGUI().printToJTextPane( item.getName());
                }
            }
    }
        public void giveAllToPlayer(Player player) {
            this.getItems().forEach((item) -> {
                player.getArrayInventory().add(item);
            });
        }
        public void giveSomeToPlayer(Player player, String itemName) { //gives all items with the same name to the player
            int i = 0;
            for(Item item : this.getItems()) {
                if(item.getName().equalsIgnoreCase(itemName)) {
                    player.getArrayInventory().add(item);
                    i++;
                    game.getGUI().printToJTextPane( "You found "+ i + item.getName() + " in the " + this.getName() + ".");
                    this.getItems().remove(item);
                    return;
                }
            }
            game.getGUI().printToJTextPane( "You didn't find " + itemName + " in the " + this.getName() + ".");
        }
        
    public void close() {   //closes the container, only closed containers can be locked
        if(this.getType().equalsIgnoreCase("container")) {
           game.getGUI().printToJTextPane("You closed the "+ this.getName());
        } else {
           game.getGUI().printToJTextPane("You can't close that");
        }
    
    }
    public void open() {        //opens the container, only unlocked containers can be opened
        if(this.getType().equalsIgnoreCase("container")) {
            game.getGUI().printToJTextPane("You opened the "+ this.getName());
        } else {
            game.getGUI().printToJTextPane("You can't open that");
        }
    }
    public void displayInventory() {        //displays the items in the container
        if(this.getItems().isEmpty()) {
            game.getGUI().printToJTextPane("There is nothing in the " + this.getName());
        } else {
            game.getGUI().printToJTextPane("The " + this.getName() + " contains: ");
            for(Item item : this.getItems()) {
                game.getGUI().printToJTextPane(item.getName());
            }
        }
    }
    public void roomDispenser(Player player) { //dispenses items based on the name of the container
        if(this.getName().equalsIgnoreCase("Snack Dispenser")) {     
        Food juiceBox = new Food("Juice Box", "A juice box", "food", game);
        Food animalCrackers = new Food("Animal Crackers", "A box of animal crackers", "food", game);
        if(this.getItems().isEmpty()){
            game.getGUI().printToJTextPane("You bang on it to make the snacks come out");
            this.getItems().add(juiceBox);
            this.getItems().add(animalCrackers);           
        if(player.getArrayInventory().contains(player.getItemByName("Juice Box"))){
        game.getGUI().printToJTextPane("You already have a juice box");
        } else {
            player.getArrayInventory().add(juiceBox);
            game.getGUI().printToJTextPane("You got a juice box");
        }
        if(player.getArrayInventory().contains(player.getItemByName("Animal Crackers"))){
            game.getGUI().printToJTextPane("You already have animal crackers");
        } else {
            player.getArrayInventory().add(animalCrackers);
            game.getGUI().printToJTextPane("You got animal crackers");
        }
    }
    }else if(this.getName().equalsIgnoreCase("Art Supplies")) {
        Item crayons = new Item("Crayons", "A box of crayons", "toy", game);
        Item coloringBook = new Item("Coloring Book", "A coloring book", "toy", game);
        if(this.getItems().isEmpty()){
            game.getGUI().printToJTextPane("You bang on it to make the art supplies come out");
            this.getItems().add(crayons);
            this.getItems().add(coloringBook);
            if(player.getArrayInventory().contains(player.getItemByName("Crayons"))){
                game.getGUI().printToJTextPane("You already have crayons");
            } else {
                player.getArrayInventory().add(crayons);
                game.getGUI().printToJTextPane("You got crayons");
            }
            if(player.getArrayInventory().contains(player.getItemByName("Coloring Book"))){
                game.getGUI().printToJTextPane("You already have a coloring book");
            } else {
                player.getArrayInventory().add(coloringBook); 
                game.getGUI().printToJTextPane("You got a coloring book");
            }
        }
    }   
    }

    Item getItemByName(String itemName) { //returns the item with the same name as the parameter    
        for(Item item : this.getItems()) {
            if(item.getName().equalsIgnoreCase(itemName)) {
                return item;
            
        }
    }
        return null;

    }
}
