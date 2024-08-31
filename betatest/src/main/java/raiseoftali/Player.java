package raiseoftali;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Player {

    public static Room currentRoom;
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    private int thirst, bladder, hunger, age, experience, money, resilience, level, maturity;
    private ArrayList<Item> inventory;
    private ArrayList<Quests> quests;
    private String[] CRAFT;
    private int[] SMILE;
    private Item item;
    private String name, description, optional;
    private ArrayList<Perk> perks;
    private boolean perkpicked, agePicked;
    public Game game;
    public Quests currentQuest;
    private Equipment[] equipment;
    public Player(Game game) {
        this.setStats(); 
        this.generatePerks();
        }
    public void setStats() {
        this.setHunger(100);
        this.setThirst(100);
        this.setBladder(0);
        this.setAge(1);
        this.setExperience(0);
        this.setMoney(20);
        this.setResilience(100);
        this.setLevel(1);
        this.setMaturity(0);
        this.setSMILE(new int[]{0, 0, 0, 0, 0});
        this.setCRAFT(new String[]{"", "", "", "", ""});
        this.setPerks(new ArrayList<>());
        this.setInventory(new ArrayList<>());
        this.setEquipment(new Equipment[5]);
        this.setQuests(new ArrayList<>());
        this.setCurrentQuest(null);
        this.setPerkpicked(false);
        this.setAgePicked(false);
        this.setOptional("none");
        this.setName("Player");
        this.setDescription("A player");
        this.setGame(game);
        this.setCurrentRoom(null);
    }
    public void setQuest(Quests quest) {
        this.quests.add(quest);
    }
    public void removeQuest(Quests quest) {
        this.quests.remove(quest);
    }
    public int[] getSMILE() {
    return SMILE;
}
    public void setSMILE(int[] sMILE) {
    SMILE = sMILE;
}
    public ArrayList<Quests> getQuests() {
    return quests;
}
    public void reciveitem(NPC npc, Item item) {
    if(item.isQuestItem()){
        setQuest(npc.getQuest());
    }
    this.inventory.add(item);
}
    public void setQuests(ArrayList<Quests> quests) {
    this.quests = quests;
}
    public void takeItem(Item item) {
    if(item.isTakeable()){
    inventory.add(item);
    getCurrentRoom().removeItem(item);
}else {
    game.getGUI().printToJTextPane("You can't take that item.");
}
}  
    public void dropItem(Item item) {
        if(item.getName().equals("ID")||item.getName() == null) {
            game.getGUI().printToJTextPane("You can't drop that item.");
        } else {
        inventory.remove(item);
        getCurrentRoom().addItem(item);
    }
    }
    public String[] getInventory() {
        if(inventory.isEmpty()) {
            return new String[]{"Empty"};
        }
        String[] inventoryArray = new String[inventory.size()];
        for(int i = 0; i < inventory.size(); i++) {
            inventoryArray[i] = inventory.get(i).getName();
        }
        return inventoryArray;
    }
    public void displaySMILE() { //displays the SMILE stats of the player
        game.getGUI().printToJTextPane("Your SMILE stats are: ");
        game.getGUI().printToJTextPane("S: " + this.SMILE[0]);
        game.getGUI().printToJTextPane("M: " + this.SMILE[1]);
        game.getGUI().printToJTextPane("I: " + this.SMILE[2]);
        game.getGUI().printToJTextPane("L: " + this.SMILE[3]);
        game.getGUI().printToJTextPane("E: " + this.SMILE[4]);
    }
    public void displayQuests() {
        game.getGUI().printToJTextPane("Your current quests are: ");
        for(Quests quest : quests) {
            game.getGUI().printToJTextPane(quest.getName());
        }
    }
    public void displayInventory() {
        game.getGUI().printToJTextPane("Your inventory contains: ");
        for(Item item2 : inventory) {
            game.getGUI().printToJTextPane(item2.getName());
        }
    }
    public String getName() {
        return this.name;
    }
    public void updateStatus() //updates the status of the player
    {
        game.getGUI().getStatsLabel().setText("Player: " + this.getName() + "    | |    Experience: " + this.getExperience() + "    | |    Shiny Pennies: " + this.getMoney() + "    | |    Resilience: " + this.getResilience() + "    | |    Time: " + game.getClock().getTimeOfDay() + "    | |    Hunger/Thirst: " + this.getHungerThirst(this));
}
    public int getExperience() {
return this.experience;
    }
    public String getDescription() {
        return this.description;
    }
    public String inspect(String selectedItem) {
        return this.getItemByName(selectedItem).getDescription();
    }
    public void setName(String name) {
    this.name = name;
}
    public void setMoney(int money) {
        this.money = money;
    }
    public void setResilience(int resilience) { //sets the resilience of the player and checks if the player has reached the maximum level for their age
            this.resilience = resilience; 
    }
    public void setOptional(String optional) {
        this.optional = optional;
    }
    public synchronized void  levelup() {
        this.setExperience(experience % 100);
        this.level++;
        System.out.println("Level up! Current level: " + this.getLevel());
        for(Perk p : this.getPerks()){
            if(p.isActive()){   
                upgrade(p);
                    }else{

                    }
                }
            }
    public void giveItem(Item item, NPC npc) {
                npc.reciveitem(item, this);
            }
    public void setPerks(ArrayList<Perk> perks) {
            this.perks = perks;
        }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
    public void addItem(Item item) {
        this.inventory.add(item);
    }
    public int getMoney() {
        return this.money;
        }
    public int getResilience() {
        return this.resilience;
    }
    public synchronized void setUp() { //sets up the player by asking for their name, description, and age and then setting their stats and perks accordingly
        game.getGUI().printToJTextPane("What is your name?");
        game.getGUI().waitForInput();
        this.name = game.getGUI().getInput();
        game.getGUI().printToJTextPane("Hello " + this.name + "! Please describe yourself.");
        game.getGUI().waitForInput();
        this.description = game.getGUI().getInput();
        game.getGUI().printToJTextPane("How old are you?");
        while(!agePicked) {
            game.getGUI().waitForInput();
            String input = game.getGUI().getInput();
            if(input != null && input.length() < 3 && !input.isEmpty() && input.matches("[1-9]+") && Integer.parseInt(input) < 12) {
                this.age = Integer.parseInt(input);
                agePicked = true;
            } else {
                game.getGUI().printToJTextPane("Please enter a valid age.");
            }
        }
        game.getGUI().printToJTextPane("Welcome " + this.getName() + "You are " + this.getAge() + " years old. You are a  " + this.getDescription() + ".");
        this.money = 20;
        this.resilience = (this.getAge() * 5);
        this.experience = 0;
        this.level = 1;
        this.listPerks();
        perkpicked = false;
        perkPicker();
        this.equipUniform();
        this.setClass();
     game.getGUI().printToJTextPane ("What is your name?");
      game.getGUI().waitForInput();
         this.name =game.getGUI().getInput();
      game.getGUI().printToJTextPane ("Hello " + this.name + "! Please describe yourself.");
      game.getGUI().getjTextFeild().setText("");
      game.getGUI().waitForInput();
       this.description =game.getGUI().getInput();
           game.getGUI().getjTextFeild().setText("");
        game.getGUI().printToJTextPane ("How old are you?");
        while(!agePicked) { 
        game.getGUI().waitForInput(); 
        String input = game.getGUI().getInput();
        if (input != null&&input.length()<3&& !input.isEmpty()&&input.matches("[1-9]+")&& Integer.parseInt(input) < 12) {
            this.age = Integer.parseInt(input);
            agePicked = true;
        } else {
            game.getGUI().printToJTextPane ("Please enter a valid age.");
        }
        }
        game.getGUI().printToJTextPane ( "Welcome "+ this.getName() +"You are " + this.getAge() + " years old. You are a  " + this.getDescription() + ".");
            this.money = 20;
            this.resilience = (this.getAge()*5);
            this.experience = 0;
            this.level = 1;
            this.listPerks();    
            perkpicked = false;
            perkPicker();
            this.equipUniform();
            this.setClass();   
            }
    public void tantrum() {
        game.getGUI().printToJTextPane ("You threw a tantrum, got tired, and fell asleep.");
        this.nap();
        updateStatus();
    }        
    public void nap() {
        game.getGUI().printToJTextPane ("You took a nap and feel better.");
        game.getGUI().printToJTextPane ("You are now " + this.getResilience() + " resilient.");
        game.getGUI().printToJTextPane ("You are now " + this.getMaturity() + " mature.");
        int time = age+5;
        if (time > 20){
            time = 20;
        }else if(time < 5){
            time = 5;
        }
        game.moveTime(time);
    }
    public Item getItem(Item item2) {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public ArrayList<Perk> getPerks() {
        return perks;
    }   
    public synchronized void perkPicker() { //allows the player to pick a perk from the list of available perks
        this.perkpicked = false;
        while(!perkpicked) {
        game.getGUI().printToJTextPane ("Please pick a perk.");
        game.getGUI().waitForInput();
            Perk perk = getPerkByName(game.getGUI().getInput());
            if(perk != null) {
                perk.setActive(true);
                perkpicked = true;
            } else {
                game.getGUI().printToJTextPane ("Please choose a valid perk.");
            }
        }
   game.getGUI().printToJTextPane ("You have the following perks: ");
    for(Perk p : perks) {
        if(p.isActive()){
           game.getGUI().printToJTextPane (p.getName());
        }
        
    }
    game.getGUI().printToJTextPane("No further perks unlocked.");
}
    public String getOptional() {
        return optional;
    }
    public void setLevel(int level) { //sets the level of the player and checks if the player has reached the maximum level for their age
        this.level = level;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String[] getStatus() { //returns the status of the player as an array of strings
        String[] status = new String[4];
        status[0] = "Name: " + this.name;
        status[1] = "Pennies: " + this.money;
        status[2] = "Resilience: " + this.resilience;
        status[3] = "Experience: " + this.experience;
        return status;
    }
    public boolean hasItem(String itemName) {   //checks if the player has an item in their inventory
        for(Item items : inventory) {
            if(items.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
    public void addExperience(int i) {
        this.experience += i;
        if(this.experience >= 100) {
            this.levelup();
        }
    }
    public void play(Item item) { //plays with an item in the player's inventory and gets a random outcome.
        if("toy".equals(item.getType())) {
           game.getGUI().printToJTextPane ("You played with the " + item.getName());
            this.addResilience(1);
            this.addExperience(2);
        } else {
           game.getGUI().printToJTextPane ( item.getName()+ " isn't very fun to play with");
            this.addResilience(-10);
           this.addExperience(2);
        }
    }
    public void addResilience(int i) {
        if(this.resilience + i > age*25) {
            this.maturity++;
            this.resilience = (this.resilience + i) % (age*25);
            this.checkMaturity();
        } else {
            this.resilience += i;
            if(this.resilience <= 0) {
               this.tantrum();
               this.setResilience(age*10);
            }
        }
    }
    public void equip(Equipment equipment, int slot) {
        this.equipment[slot] = equipment;
    }
    public void unequip(Equipment equipment2, int slot) {
        this.equipment[slot] = null;
    }
    public Equipment[] getEquipment() {
        return this.equipment;
    }
    public void listEquips(){ //lists the equipment the player has equipped 
        for(int i = 0; i < equipment.length; i++) {
            if(equipment[i] != null) {
               game.getGUI().printToJTextPane (slotWord(i) + equipment[i].getName());
            } else {
               game.getGUI().printToJTextPane (slotWord(i) + "Empty");
            }
        }
    }
    public Perk getPerkByName(String input) {
        for(Perk perk : this.perks) {
            if(perk.getName().equalsIgnoreCase(input)) {
                return perk;
            }
        }
        return null;
    }
    public Item getItem() {
        return item;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Quests getCurrentQuest() {
        return currentQuest;
    }
    public void setCurrentQuest(Quests currentQuest) {
        this.currentQuest = currentQuest;
    }
    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }
    public void equipUniform(){ //equips the player with the default uniform
        for (int i = 0; i < equipment.length; i++) {
            switch(i){
                case 0 -> {
                    unequip(equipment[i], i);
                    equipment[i] = new Equipment("Uniform Hat", "A hat that is part of the uniform", 0, description);
                }
                case 1 -> {
                    unequip(equipment[i], i);
                    equipment[i] = new Equipment("Uniform Shirt", "A shirt that is part of the uniform", 1, description);
                }
                case 2 -> {
                    unequip(equipment[i], i);
                    equipment[i] = new Equipment("Training Pants", "Just in case.", 2, description);
                }
                case 3 -> {
                    unequip(equipment[i], i);
                    equipment[i] = new Equipment("Uniform Pants", "Pants that are part of the uniform", 3, description);
                }
                case 4 -> {
                    unequip(equipment[i], i);
                    equipment[i] = new Equipment("Uniform Shoes", "Shoes that are part of the uniform", 4, description);
                }
                default -> {
                }
            }
        }
        this.getEquipment();
        }
    public int getMaturity() {
        return maturity;
    }
    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }
    public void upgrade(Perk perk2) {    //upgrades the player's SMILE stats based on the perk they have chosen
        switch(perk2.getName().toLowerCase()) {
            case "performer" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("performer").setActive(false);
                newPerk = getPerkByName("ring master").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(2)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1; 
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                
                break;
            }
            case "busy beaver" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("busy beaver".toLowerCase()).setActive(false);
                newPerk = getPerkByName("confederate".toLowerCase()).setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "playmate" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("playmate").setActive(false);
                newPerk = getPerkByName("popular").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[4] += 1;

                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "bookworm" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("bookworm").setActive(false);
                newPerk = getPerkByName("storyteller").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "mediator" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("mediator").setActive(false);
                newPerk = getPerkByName("arbitrator").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "tutor" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("Tutor").setActive(false);
                newPerk = getPerkByName("prefect").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "little monkey" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("little monkey").setActive(false);
                newPerk = getPerkByName("playground royalty").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;

                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "visionary" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("visionary").setActive(false); 
                newPerk = getPerkByName("inovater").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(2)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "trial blazer" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("trial blazer").setActive(false);
                newPerk = getPerkByName("leader").setActive(true);
                game.getGUI().printToJTextPane("You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "ring master" -> {  
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(2)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;

            }

            case "confederate" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "popular" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "story teller" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "prefect" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;

                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "arbitrator" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "playground royalty" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "leader" -> {
                game.getGUI().printToJTextPane("You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "arbitator" ->{
                if(!maxLevel(2)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextPane("This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextPane("You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }

            default -> {
            }
            
        }
    doCRAFT();
    }
    public void doCRAFT() { //checks if the player has unlocked a CRAFT and prints a message if they have. and then sets the CRAFT to the player's CRAFT array
        int i=0;
        for(int s : SMILE){
            switch(i){
                case 0 -> {
                    if(s>this.getAge()){
                       CRAFT[i] = "Ring Leader";
                    }
                    break;
                }
                case 1 -> {
                    if(s>this.getAge()){
                        CRAFT[i] = "Care Taker";
                    }
                    break;
                }
                case 2 -> {
                    if(s>this.getAge()){
                        CRAFT[i] = "Innovator";
                    }
                    break;
                }
                case 3 -> {
                    if(s>this.getAge()){
                        CRAFT[i] = "Prefect";
                    }
                    break;
                }

                case 4 -> {
                    if(s>this.getAge()){
                        CRAFT[i] = "Leader";
                    }
                    break;
                }
                    
            }

            i++;
            }          
            for(String s2 : CRAFT) {
                if(s2 != null && !s2.equals("")){ {
                    
                    game.getGUI().printToJTextPane("You have unlocked the " + s2 + " CRAFT.");
                }
            }       }
        }
    public int getNapTime() { //returns the time it takes for the player to nap
        int napTime = age+5;
        return napTime;
	}
    public String getHungerThirst(Player player) { //returns the hunger and thirst status of the player and sets the status to "Hungery" if the player is hungry, "Thirsty" if the player is thirsty, and "Hungery and Thirsty" if the player is both hungry and thirsty
        String HT = "Content";
        if(player.getHunger()<25){
           HT = "Hungery";

        }
        if(player.getThirst()<25){
             HT = "Thirsty";
        }
        if(player.getHunger()<25 && player.getThirst()<25){
            HT = "Hungery and Thirsty";
        }
        return HT;
    }
    public void setHunger(int i) {
        this.hunger = i;
    }
    public void bladderFull(int i) { //checks if the player's bladder is full and if it is, empties the bladder and makes the player potty
        this.bladder += i;
        if(this.bladder >= 100){
            this.bladder = 0;
            this.potty(currentRoom);
            this.game.endTurn();
        }
    }
    public int getBladder() {
        return bladder;
    }
    public void setBladder(int bladder) {
        this.bladder = bladder;
    }
    public String[] getCRAFT() {
        return CRAFT;
    }
    public boolean isPerkpicked() {
        return perkpicked;
    }
    public void setPerkpicked(boolean perkpicked) {
        this.perkpicked = perkpicked;
    }
	public boolean isAgePicked() {
        return agePicked;
    }
    public void setAgePicked(boolean agePicked) {
        this.agePicked = agePicked;
    }
    public void eatDrink(Item aThis) { //checks if the player can eat or drink the item and then eats or drinks the item if they can
        switch(aThis.getType()){
            case "food" -> {
                game.getGUI().printToJTextPane("You eat the " + aThis.getName());
                this.addResilience(5);
                this.addExperience(5);
                this.inventory.remove(aThis);
                game.endTurn();
            }
            case "drink" -> {
                game.getGUI().printToJTextPane("You drink the " + aThis.getName());
                this.addResilience(5);
                this.addExperience(5);
                this.inventory.remove(aThis);
                game.endTurn();
            }
            default -> {
                game.getGUI().printToJTextPane("You can't eat that.");
            }
        }
    }
    public  void interact(Item selectedItem) { //interacts with the item selected by the player from the room's inventory
            if(selectedItem.getType().equalsIgnoreCase("toy")){
                game.getGUI().printToJTextPane("You played with the toy.");
                this.addResilience(1);
                this.addExperience(4);
            } else if(selectedItem.getType().equalsIgnoreCase("food") || selectedItem.getType().equalsIgnoreCase("drink")){
                game.getGUI().printToJTextPane("You ate the " + selectedItem);
                this.addResilience(5);
                this.addExperience(5);
            } else if(selectedItem.getType().equalsIgnoreCase("potty")){ 
                game.getGUI().printToJTextPane("You went potty.");
                this.addResilience(10);
                this.addExperience(2);
            } else {
                game.getGUI().printToJTextPane("You can't play with that.");
                this.addResilience(-10);
                this.addExperience(2);
            }
        }
    public void open(Container aThis) {
            aThis.displayInventory();
            game.getGUI().printToJTextPane("You open the " + aThis.getName());
        }
    public void setClass() { //sets the player's SMILE stats based on the perk they have chosen
        switch(this.getPerkName()){
            case "Performer" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 4;
                this.SMILE[2] = 4;
                this.SMILE[3] = 2;
                this.SMILE[4] = 2;
            }
            case "Busy Beaver" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 4;
                this.SMILE[2] = 2;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "Playmate" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 4;
                this.SMILE[2] = 2;
                this.SMILE[3] = 2;
                this.SMILE[4] = 4;
            }
            case "Bookworm" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "Mediator" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 2;
                this.SMILE[4] = 4;
            }
            case "Tutor" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 2;
                this.SMILE[3] = 4;
                this.SMILE[4] = 4;
            }
            case "Little Monkey" -> {
                this.SMILE[0] = 2;
                this.SMILE[1] = 4;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "Trial Blazer" -> {
                this.SMILE[0] = 2;
                this.SMILE[1] = 4;
                this.SMILE[2] = 4;
                this.SMILE[3] = 2;
                this.SMILE[4] = 4;
            }
            case "Visionary" -> {
                this.SMILE[0] = 2;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 4;
            }
            default ->{
                this.SMILE[0] = 3;
                this.SMILE[1] = 3;
                this.SMILE[2] = 3;
                this.SMILE[3] = 3;
                this.SMILE[4] = 3;
            }

        }
        }
    public String getPerkName() {
        for(Perk perk : getPerks()) {
            if(perk.isActive()) {
                return perk.getName();
            }
        }
        return null;
    }
    public void useSpecial(String string) { //uses the player's special based on the CRAFT they have unlocked
        switch(string){
            case "Ring Leader" -> {
                game.getGUI().printToJTextPane("You used the Ring Leader special.");
                this.addResilience(10);
                this.addExperience(2);
                break;
            }
            case "Care Taker" -> {
                game.getGUI().printToJTextPane("You used the Care Taker special.");
                this.addResilience(20);
                this.addExperience(2);
                break;
            }
            case "Innovator" -> {
                game.getGUI().printToJTextPane("You used the Innovator special.");
                this.addResilience(10);
                this.addExperience(2);
                break;
            }
            case "Prefect" -> {
                game.getGUI().printToJTextPane("You used the Prefect special.");
                this.addResilience(20);
                this.addExperience(2);
                break;
            }
            case "Leader" -> {
                game.getGUI().printToJTextPane("You used the Leader special.");
                this.addResilience(15);
                this.addExperience(2);
                break;
            }
            default -> {
                game.getGUI().printToJTextPane("You can't use that special.");
                break;
            }
        }
    }
    public int getHunger() {
        return this.hunger;
    }
    public int getThirst() {
        return this.thirst;
    }
    public Item getItemByName(String itemName) {
        for(Item items : inventory) {
            if(items.getName().equals(itemName)) {
                return items;
            }
        }
        return null;    
    }
    public ArrayList<Item> getArrayInventory() { 
        return this.inventory;
    }
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
    public void putItem(Item item, Item containerByName) {
        containerByName.addItem(item);
        this.inventory.remove(item);
    }
    public void throwAway(Item item) {
    this.inventory.remove(item);
    currentRoom.getItemByName("Trash Can").addItem(item);
}
public void potty(Room room) {
        if(this.equipment[2] == null) {
            game.getGUI().printToJTextPane("You went potty in "+ getCurrentRoom().getName());
            currentRoom.getArrayInventory().add(makePuddle());
            this.addResilience(-10);
            this.addExperience(2);
            return;
        }
        if(currentRoom.getName().equalsIgnoreCase("Bathroom")) {
            game.endTurn();
            game.getGUI().printToJTextPane("You went potty in the potty.");
            this.addResilience(15);
            this.addExperience(7);
            return;
        }
        switch (getEquipment()[2].getName()) {
            case "Training Pants" -> {
                game.getGUI().printToJTextPane("You went potty in your training pants.");
                getEquipment()[2].setName("Wet Training Pants");
                this.addResilience(0);
                this.addExperience(2);
            }
            case "Wet Training Pants" -> {
                game.getGUI().printToJTextPane("You went potty in your wet training pants.");
                getEquipment()[2].setName("Soggy Training Pants");
                currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(2);
            }
            case "Wet Diaper" -> {
                game.getGUI().printToJTextPane("You went potty in your wet diaper.");
                getEquipment()[2].setName("Soggy Diaper");
                this.addResilience(0);
                this.addExperience(2);
            }
            case "Diaper" -> {
                game.getGUI().printToJTextPane("You went potty in your diaper.");
                getEquipment()[2].setName("Wet Diaper");
                this.addResilience(0);
                this.addExperience(2);
            }
            case "Soggy Diaper" -> {
                game.getGUI().printToJTextPane("You went potty in your soggy diaper.");
                getEquipment()[2].setName("Leaky Diaper");
                currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-5);
                this.addExperience(2);
            }
            case "Underwear" -> {
                game.getGUI().printToJTextPane("You went potty in your undies.");
                currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(2);
            }
            default -> {
                game.getGUI().printToJTextPane("You go potty on the floor in "+ getCurrentRoom().getName());
                currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(2);
            }
        }
    }
    public  void eatDrink() throws FileNotFoundException {
    if(getItemByType("food")==null){
        game.getGUI().printToJTextPane("You have nothing to eat or drink.");
        return;
    }
    game.endTurn();
    getItemByType("food").use(this, game);
    getItemByType("drink").use(this, game);
}
public  void reflect() { //player must reflect on their day to end the day and age up.
        game.getGUI().printToJTextPane("You reflect on your day.");
        this.addResilience(9);
        this.addExperience(5);
        game.moveTime(20);
        game.getGUI().printToJTextPane( this.age + " years old.");
        game.getGUI().printToJTextPane( "You are " + this.getMaturity() + "mature.");
    }
    public  void setThirst(int i) {
        this.thirst = i;
    }
    public  void playedWith(Item aThis) {
        game.getGUI().printToJTextPane("You played with the " + aThis.getName());
        this.addResilience(5);
        this.addExperience(6);
        this.game.endTurn();
        
    }
    public  void removeItem(Item item) {
        this.inventory.remove(item);
    }
    /**
     * @param cost
     */
    public  void removeMoney(int cost) {
        this.money -= cost;
    }
    private void generatePerks() { //generates the perks for the player, some are locked and some are unlocked
        Perk performer = new Perk("Performer", "You are a performer.",0,0);
        Perk busyBeaver = new Perk("Busy Beaver", "You are a busy beaver.",0,0);
        Perk playmate = new Perk("Playmate", "You are a playmate.", 0,0);
        Perk bookworm = new Perk("Bookworm", "You are a bookworm.", 0,0);
        Perk mediator = new Perk("Mediator", "You are a mediator.", 0,0);
        Perk tutor = new Perk("Tutor", "You are a tutor.", 0,0);
        Perk littleMonkey = new Perk("Little Monkey", "You are a little monkey.", 0,0);
        Perk trialBlazer = new Perk("Trial Blazer", "You are a trial blazer.", 0,0);
        Perk visionary = new Perk("Visionary", "You are a visionary.", 0,0);
        Perk ringmaster = new Perk("Ring Master", "You are a ring master.", 1,0);
        Perk confederate = new Perk ("Confederate", "You are a confederate.", 1,0);
        Perk popular = new Perk("Popular", "You are popular.", 1,0);
        Perk storyteller = new Perk("Storyteller", "You are a storyteller.", 1,0);
        Perk prefect = new Perk("Prefect", "You are a prefect.", 1,0);
        Perk arbitrator = new Perk("Arbitrator", "You are an arbitrator.", 1,0);
        Perk playgroundRoyalty = new Perk("Playground Royalty", "You are playground royalty.", 1,0);
        Perk leader = new Perk("Leader", "You are a leader.", 1,0);
        perks.add(performer);
        perks.add(busyBeaver);
        perks.add(playmate);
        perks.add(bookworm);
        perks.add(mediator);
        perks.add(tutor);
        perks.add(littleMonkey);
        perks.add(trialBlazer);
        perks.add(visionary);
        perks.add(ringmaster);
        perks.add(confederate);
        perks.add(popular);
        perks.add(storyteller);
        perks.add(prefect);
        perks.add(arbitrator);
        perks.add(playgroundRoyalty);
        perks.add(leader);

    }
    private int getLevel() {
return this.level;
    }
    private void listPerks() { //lists the perks that that can be chosen by the player at the beginning of the game
        for(Perk perk : perks) {
            if(perk.getCost()==0){
           game.getGUI().printToJTextPane (perk.getName() + ": " + perk.getDescription());
        }
    }
    }
    private String slotWord(int index) { //returns the slot word for the equipment slot based on the index
        return switch (index) {
            case 0 -> "Head: ";
            case 1 -> "Chest: ";
            case 2 -> "Undies: ";
            case 3 -> "Pants: ";
            case 4 -> "Shoes: ";
            default -> "Unknown: ";
        };
    }
    private Item getItemByType(String type) {
        for(Item item1 : inventory) {
            if(item1.getType().equals(type)) {
                return item1;
            }
        }
        return null;
    }
    private Item makePuddle() { //makes a puddle of pee item when the player goes potty in the room
        Item puddles = new Item("Puddle of pee", "A puddle of pee.", "mess", game);
        puddles.setTakeable(false);
        return puddles;
        
    }
	private void setCRAFT(String[] string) {
        this.CRAFT = string;
    }
    private boolean maxLevel(int index ) {
     return this.SMILE[index] > this.getAge();   
    }
    private void checkMaturity() {
        if(this.maturity >= this.age){
            this.maturity = 0;
            this.ageUp();
        }
    }
    private void ageUp() {
        this.age += 1;
    }
}
