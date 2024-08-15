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
        for(Perk perk : getPerks()) {
            if(perk.getName().equals(input)) {
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
        switch(perk2.getName()){
            case "Brat" -> {
                getPerkByName("Brat").setActive(false);
                getPerkByName("Rascal").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Brat to Rascal.");
                this.SMILE[0] += 4;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 1;
                this.SMILE[4] += 2;
                break;
            }
            case "Cute" -> {
                getPerkByName("Cute").setActive(false);
                getPerkByName("Adorable").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Cute to Adorable.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 1;
                this.SMILE[2] += 2;
                this.SMILE[3] += 2;
                this.SMILE[4] += 4;
                break;
            }
            case "Class Clown" -> {
                getPerkByName("Class Clown").setActive(false);
                getPerkByName("Jester").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Class Clown to Jester.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 4;
                this.SMILE[2] += 2;
                this.SMILE[3] += 1;
                this.SMILE[4] += 3;
                break;
            }
            case "Geek" -> {
                getPerkByName("Geek").setActive(false);
                getPerkByName("Nerd").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Geek to Nerd.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Outcast" -> {
                getPerkByName("Outcast").setActive(false);
                getPerkByName("Rebel").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Outcast to Rebel.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 2;
                this.SMILE[4] += 2;
                break;
            }
            case "Teacher's Pet" -> {
                getPerkByName("Teacher's Pet").setActive(false);
                getPerkByName("Valedictorian").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Teacher's Pet to Valedictorian.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Slacker" -> {
                getPerkByName("Slacker").setActive(false);
                getPerkByName("Lazy").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Slacker to Lazy.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 2;
                this.SMILE[4] += 3;
                break;
            }
            case "Shy" -> {
                getPerkByName("Shy").setActive(false); 
                getPerkByName("Introvert").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Shy to Introvert.");
                this.SMILE[0] += 4;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 1;
                this.SMILE[4] += 2;
                break;
            }
            case "Smart" -> {
                getPerkByName("Smart").setActive(false);
                getPerkByName("Genius").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Smart to Genius.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Good Kid" -> {
                getPerkByName("Good Kid").setActive(false);
                getPerkByName("Angel").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Good Kid to Angel.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Rebel" -> {
                getPerkByName("Rebel").setActive(false);
                getPerkByName("Rogue").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Rebel to Rogue.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 2;
                this.SMILE[4] += 2;
                break;

            }
            case "Rascal" ->  {
                getPerkByName("Rascal").setActive(false);
                getPerkByName("Troublemaker").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Rascal to Troublemaker.");
                this.SMILE[0] += 4;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 1;
                this.SMILE[4] += 2;
                break;
            }
            case "adorable" -> {
                getPerkByName("Adorable").setActive(false);
                getPerkByName("Sweetheart").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Adorable to Sweetheart.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 1;
                this.SMILE[2] += 2;
                this.SMILE[3] += 2;
                this.SMILE[4] += 4;
                break;
            }
            case "Jester" -> {
                getPerkByName("Jester").setActive(false);
                getPerkByName("Comedian").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Jester to Comedian.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 4;
                this.SMILE[2] += 2;
                this.SMILE[3] += 1;
                this.SMILE[4] += 3;
                break;
            }
            case "Nerd" -> {
                getPerkByName("Nerd").setActive(false);
                getPerkByName("Brainiac").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Nerd to Brainiac.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Confederate" -> {
                getPerkByName("Confederate").setActive(false);
                getPerkByName("Spy").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Confederate to Spy.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Lazy" -> {
                getPerkByName("Lazy").setActive(false);
                getPerkByName("Sloth").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Lazy to Sloth.");
                this.SMILE[0] += 3;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 2;
                this.SMILE[4] += 3;
                break;
            }
            case "Introvert" -> {
                getPerkByName("Introvert").setActive(false);
                getPerkByName("Hermit").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Introvert to Hermit.");

                this.SMILE[0] += 4;
                this.SMILE[1] += 2;
                this.SMILE[2] += 3;
                this.SMILE[3] += 1;
                this.SMILE[4] += 2;
                break;
            }
            case "Genius" -> {
                getPerkByName("Genius").setActive(false);
                getPerkByName("Einstein").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Genius to Einstein.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            case "Angel" -> {
                getPerkByName("Angel").setActive(false);
                getPerkByName("Saint").setActive(true);
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have upgraded from Angel to Saint.");
                this.SMILE[0] += 2;
                this.SMILE[1] += 1;
                this.SMILE[2] += 3;
                this.SMILE[3] += 4;
                this.SMILE[4] += 2;
                break;
            }
            
        }
        int i=0;
        for(int s : SMILE){
            switch(i){
                case 0 -> {
                    if(s>5){
                       CRAFT[i] = "Ring Leader";
                    }
                    break;
                }
                case 1 -> {
                    if(s>5){
                        CRAFT[i] = "Care Taker";
                    }
                    break;
                }
                case 2 -> {
                    if(s>5){
                        CRAFT[i] = "Innovator";
                    }
                    break;
                }
                case 3 -> {
                    if(s>5){
                        CRAFT[i] = "Prefect";
                    }
                    break;
                }

                case 4 -> {
                    if(s>5){
                        CRAFT[i] = "Leader";
                    }
                    break;
                }
                    
            }

            i++;
            }          
            for(String s2 : CRAFT) {
                if(s2 != null) {
                    game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have unlocked the " + s2 + " CRAFT.");
                }
            }       }
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
    public Item getAnimalCrackerBox() {
        return animalCrackerBox;
    }
    public void setAnimalCrackerBox(Item animalCrackerBox) {
        this.animalCrackerBox = animalCrackerBox;
    }
    public Item getPuddle() {
        return puddle;
    }
    public void setPuddle(Item puddle) {
        this.puddle = puddle;
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
    void throwAway(Item item, Item container) {
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
        Perk performer = new Perk("Performer", "You are a performer. You are good at making people smile.", age, age);
        Perk prefect = new Perk("Prefect", "You are a prefect. You are good at keeping people in line.", age, age);
        Perk teachers = new Perk("Teachers Pet", "You are a teachers pet. You are good at making teachers smile.", age, age);
        Perk bookworm = new Perk("Book Worm", "You are a bookworm. You are good at learning.", age, age);
        Perk explorer = new Perk("Explorer", "You are an explorer. You are good at finding things.", age, age);
        Perk mediator = new Perk("Mediator", "You are a mediator. You are good at making peace.", age, age);
        this.perks.add(performer);
        this.perks.add(prefect);
        this.perks.add(teachers);
        this.perks.add(bookworm);
        this.perks.add(explorer);
        this.perks.add(mediator);
    }
    private int getLevel() {
return this.level;
    }
    private void listPerks() {
        for(Perk perk : perks) {
           game.getGUI().printToJTextArea (game.getGUI().getjTextArea(),perk.getName() + ": " + perk.getDescription());
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
        switch(this.getPerkName().toLowerCase()){
            case "performer" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "prefect" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 2;
                this.SMILE[4] = 4;
            }
            case "teachers" -> {
                this.SMILE[0] = 2;
                this.SMILE[1] = 4;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "bookworm" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 2;
                this.SMILE[2] = 4;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "explorer" -> {
                this.SMILE[0] = 4;
                this.SMILE[1] = 4;
                this.SMILE[2] = 2;
                this.SMILE[3] = 4;
                this.SMILE[4] = 2;
            }
            case "mediator" -> {
                this.SMILE[0] = 2;
                this.SMILE[1] = 4;
                this.SMILE[2] = 4;
                this.SMILE[3] = 2;
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
}
