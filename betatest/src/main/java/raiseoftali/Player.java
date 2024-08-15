package raiseoftali;
import java.util.ArrayList;

public class Player {

    private int thirst, bladder, hunger, age, experience, money, resilience, level, maturity;
    public Room currentRoom;
    private ArrayList<Item> inventory;
    private ArrayList<Quests> quests;
    private String[] CRAFT;
    private int[] SMILE;
    private Item item, animalCrackerBox, puddle;
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
        this.setAge(0);
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
public void setQuests(ArrayList<Quests> quests) {
    this.quests = quests;
}
public void takeItem(Item item) {
    if(item.isTakeable()){
    inventory.add(item);
    this.currentRoom.removeItem(item);
}else {
    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't take that item.");
}
}
    public void dropItem(Item item) {
        if(item.getName().equals("ID")||item.getName() == null) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't drop that item.");
        } else {
        inventory.remove(item);
        this.currentRoom.addItem(item);
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
    public void displaySMILE() {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"Your SMILE stats are: ");
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"S: " + this.SMILE[0]);
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"M: " + this.SMILE[1]);
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"I: " + this.SMILE[2]);
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"L: " + this.SMILE[3]);
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"E: " + this.SMILE[4]);
    }
    public void displayQuests() {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"Your current quests are: ");
        for(Quests quest : quests) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),quest.getName());
        }
    }
    public void displayInventory() {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"Your inventory contains: ");
        for(Item item2 : inventory) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),item2.getName());
        }
    }
    public String getName() {
        return this.name;
    }
    public void updateStatus() 
    {
        game.getGUI().getStatsLabel().setText("Player: " + this.getName() + "    | |    Experience: " + this.getExperience() + "    | |    Shiny Pennies: " + this.getMoney() + "    | |    Resilience: " + this.getResilience() + "    | |    Time: " + game.getClock().getTimeOfDay() + "    | |    Hunger/Thirst: " + this.getHungerThirst(this));
}
    public int getExperience() {
return this.experience;
    }
    public String getDescription() {
        return this.description;
    }
    public void dialog(String npcName) {
    
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
    public void setResilience(int resilience) {
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
    public void setPerk(Perk perk) {
        this.perks.add(perk);
    }
    public void removePerk(Perk perk) {
        this.perks.remove(perk);
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
    public synchronized void setUp() {
     game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"What is your name?");
      game.getGUI().waitForInput();
         this.name =game.getGUI().getInput();
      game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"Hello " + this.name + "! Please describe yourself.");
      game.getGUI().getjTextFeild().setText("");
      game.getGUI().waitForInput();
       this.description =game.getGUI().getInput();
           game.getGUI().getjTextFeild().setText("");
        game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"How old are you?");
        while(!agePicked) { 
        game.getGUI().waitForInput(); 
        String input = game.getGUI().getInput();
        if (input != null&&input.length()<3&& !input.isEmpty()&&input.matches("[1-9]+")&& Integer.parseInt(input) < 12) {
            this.age = Integer.parseInt(input);
            agePicked = true;
        } else {
            game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"Please enter a valid age.");
        }
        }
        game.getGUI().printToJTextArea (game.getGUI().getjTextArea(), "Welcome "+ this.getName() +"You are " + this.getAge() + " years old. You are a  " + this.getDescription() + ".");
            this.money = 20;
            this.resilience = 100;
            this.experience = 0;
            this.level = 1;
            this.listPerks();    
            perkpicked = false;
            perkPicker();
            this.equipUniform();
            this.setClass();   
            }
    public void tantrum() {
        game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"You threw a tantrum, got tired, and fell asleep.");
        this.nap();
        this.resilience = 100;
    }
    public void nap() {
        game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"You took a nap and feel better.");
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
    public synchronized void perkPicker() {
        this.perkpicked = false;
        while(!perkpicked) {
        game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"Please pick a perk.");
        game.getGUI().waitForInput();
            Perk perk = getPerkByName(game.getGUI().getInput());
            if(perk != null) {
                perk.setActive(true);
                perkpicked = true;
            } else {
                game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"Please choose a valid perk.");
            }
        }
   game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"You have the following perks: ");
    for(Perk p : perks) {
        if(p.isActive()){
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),p.getName());
        }
        
    }
    game.getGUI().printToJTextArea(null, "No further perks unlocked.");
}   
    public String getOptional() {
        return optional;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String[] getStatus() {
        String[] status = new String[4];
        status[0] = "Name: " + this.name;
        status[1] = "Pennies: " + this.money;
        status[2] = "Resilience: " + this.resilience;
        status[3] = "Experience: " + this.experience;
        return status;
    }
    public boolean hasItem(String itemName) {
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
    public void play(Item item) {
        if("toy".equals(item.getType())) {
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"You played with the " + item.getName());
            this.addResilience(1);
            this.addExperience(66);
        } else {
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(), item.getName()+ " isn't very fun to play with");
            this.addResilience(-10);
           this.addExperience(1);
        }
    }
    public void addResilience(int i) {
        if(this.resilience + i > 200) {
            this.maturity++;
        } else {
            this.resilience += i;
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
    public void listEquips(){
        for(int i = 0; i < equipment.length; i++) {
            if(equipment[i] != null) {
               game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),slotWord(i) + equipment[i].getName());
            } else {
               game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),slotWord(i) + "Empty");
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
    public void equipUniform(){
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
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    public void upgrade(Perk perk2) {    
        switch(perk2.getName().toLowerCase()) {
            case "performer" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("performer").setActive(false);
                newPerk = getPerkByName("ring master").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(2)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1; 
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                
                break;
            }
            case "busy beaver" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("busy beaver".toLowerCase()).setActive(false);
                newPerk = getPerkByName("confederate".toLowerCase()).setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "playmate" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("playmate").setActive(false);
                newPerk = getPerkByName("popular").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[4] += 1;

                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "bookworm" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("bookworm").setActive(false);
                newPerk = getPerkByName("storyteller").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "mediator" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("mediator").setActive(false);
                newPerk = getPerkByName("arbitrator").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "tutor" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("Tutor").setActive(false);
                newPerk = getPerkByName("prefect").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(0)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "little monkey" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("little monkey").setActive(false);
                newPerk = getPerkByName("playground royalty").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;

                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "visionary" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("visionary").setActive(false); 
                newPerk = getPerkByName("inovater").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(2)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "trial blazer" -> {
                Perk oldPerk;
                Perk newPerk;
                oldPerk = getPerkByName("trial blazer").setActive(false);
                newPerk = getPerkByName("leader").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from " + oldPerk.getName() + " to " + newPerk.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "ring master" -> {  
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(2)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;

            }

            case "confederate" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "popular" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(1)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[1] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "story teller" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "prefect" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;

                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "arbitrator" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(0)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[0] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "playground royalty" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(3)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "leader" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded " + perk2.getName() + ".");
                if(!maxLevel(1)||!maxLevel(2)||!maxLevel(4)){
                    this.SMILE[1] += 1;
                    this.SMILE[2] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }
            case "arbitator" ->{
                if(!maxLevel(2)||!maxLevel(3)||!maxLevel(4)){
                    this.SMILE[2] += 1;
                    this.SMILE[3] += 1;
                    this.SMILE[4] += 1;
                } else {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"This stat has reached the maximum level for your age of " + this.getAge() + ".");
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can age up by completing Tests and Attending Maturity Workshops.");
                }
                break;
            }

            default -> {
            }
            
        }
    doCRAFT();
    }
public void doCRAFT() {
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
                    
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have unlocked the " + s2 + " CRAFT.");
                }
            }       }
        }
    public int getNapTime() {
        int napTime = age+5;
        return napTime;
	}
    public String getHungerThirst(Player player) {
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
    public void bldderFull(int i) {
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
    int getHunger() {
        return this.hunger;
    }
    int getThirst() {
        return this.thirst;
    }
    Item getItemByName(String itemName) {
        for(Item items : inventory) {
            if(items.getName().equals(itemName)) {
                return items;
            }
        }
        return null;    
    }
    ArrayList<Item> getArrayInventory() {
        return this.inventory;
    }
    void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    void putItem(Item item, Item containerByName) {
        containerByName.addItem(item);
        this.inventory.remove(item);
    }
    void throwAway(Item item) {
        this.inventory.remove(item);
        this.currentRoom.getItemByName("Trash Can").addItem(item);
    }
    void potty(Room room) {
        if(this.equipment[2] == null) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in "+ getCurrentRoom().getName());
            this.currentRoom.getArrayInventory().add(makePuddle());
            this.addResilience(-10);
            this.addExperience(1);
            return;
        }
        if(this.currentRoom.getName().equalsIgnoreCase("Bathroom")) {
            game.endTurn();
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in the potty.");
            this.addResilience(10);
            this.addExperience(10);
            return;
        }
        switch (getEquipment()[2].getName()) {
            case "Training Pants" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your training pants.");
                getEquipment()[2].setName("Wet Training Pants");
                this.addResilience(-10);
                this.addExperience(1);
            }
            case "Wet Training Pants" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your wet training pants.");
                getEquipment()[2].setName("Soggy Training Pants");
                this.currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(1);
            }
            case "Wet Diaper" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your wet diaper.");
                getEquipment()[2].setName("Soggy Diaper");
                this.addResilience(-10);
                this.addExperience(1);
            }
            case "Diaper" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your diaper.");
                getEquipment()[2].setName("Wet Diaper");
                this.addResilience(-10);
                this.addExperience(1);
            }
            case "Soggy Diaper" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your soggy diaper.");
                getEquipment()[2].setName("Leaky Diaper");
                this.currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(1);
            }
            case "Underwear" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty in your undies.");
                this.currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(1);
            }
            default -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You go potty on the floor in "+ getCurrentRoom().getName());
                this.currentRoom.getArrayInventory().add(makePuddle());
                this.addResilience(-10);
                this.addExperience(1);
            }
        }
    }
    void eatDrink() {
        if(getItemByType("food")==null){
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have nothing to eat or drink.");
            return;
        }
        game.endTurn();
        getItemByType("food").use(this, game);
        getItemByType("drink").use(this, game);
    }
    void reflect() {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You reflect on your day.");
        this.addResilience(5);
        this.addExperience(5);
        game.moveTime(10);
    }
    public void eatDrink(Item aThis) {
        switch(aThis.getType()){
            case "food" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You eat the " + aThis.getName());
                this.addResilience(5);
                this.addExperience(5);
                this.inventory.remove(aThis);
                game.endTurn();
            }
            case "drink" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You drink the " + aThis.getName());
                this.addResilience(5);
                this.addExperience(5);
                this.inventory.remove(aThis);
                game.endTurn();
            }
            default -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't eat that.");
            }
        }
    }
    void setThirst(int i) {
        this.thirst = i;
    }
   public  void interact(Item selectedItem) {
        if(selectedItem.getType().equalsIgnoreCase("toy")){
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You played with the toy.");
            this.addResilience(1);
            this.addExperience(66);
        } else if(selectedItem.getType().equalsIgnoreCase("food") || selectedItem.getType().equalsIgnoreCase("drink")){
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You ate the " + selectedItem);
            this.addResilience(5);
            this.addExperience(5);
        } else if(selectedItem.getType().equalsIgnoreCase("potty")){ 
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You went potty.");
            this.addResilience(10);
            this.addExperience(10);
        } else {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't play with that.");
            this.addResilience(-10);
            this.addExperience(1);
        }
    }
    void playedWith(Item aThis) {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You played with the " + aThis.getName());
        this.addResilience(1);
        this.addExperience(66);
        this.game.endTurn();
        
    }
   public void open(Container aThis) {
        aThis.displayInventory();
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You open the " + aThis.getName());
    }
    private void generatePerks() {
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
    private void listPerks() {
        for(Perk perk : perks) {
            if(perk.getCost()==0){
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),perk.getName() + ": " + perk.getDescription());
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),"");
        }
    }
    }
    private String slotWord(int index) {
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
    private Item makePuddle() {
        Item puddles = new Item("Puddle of pee", "A puddle of pee.", "mess", game);
        puddles.setTakeable(false);
        return puddles;
        
    }

    private void setCRAFT(String[] string) {
        this.CRAFT = string;
    }

    void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public void setClass() {
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

    private boolean maxLevel(int index ) {
     return this.SMILE[index] > this.getAge();   
    }
}
