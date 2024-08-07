package raiseoftali;
import java.util.ArrayList;

public class Player {

    public Room room;
    private ArrayList<Item> inventory;
    private ArrayList<Quests> quests;
    private final String[] CRAFT;
    private int[] SMILE;
    private Item item;
    private String name;
    private ArrayList<Perk> perks;
    private String description;
    private int experience;
    private int money;
    private int resilience;
    private String optional;

    private int level;
    private int age;
    Game game;
    public Quests currentQuest;
    private Equipment[] equipment;
    private boolean perkpicked;
    private int maturity;
    private Item container;
    public Item getContainer() {
        return container;
    }
    public void setContainer(Item container) {
        this.container = container;
    }

    private boolean agePicked;
    public Room currentRoom;
    
    public Player(Game game) {
        CRAFT = new String[5];
        SMILE = new int[5];
        inventory = new ArrayList<>();
        perks = new ArrayList<>();
        this.name = "Player";
        this.description = "A player";
        this.level = 1;
        this.money = 20;
        this.resilience = 100;
        this.experience = 0;
        this.optional = "none";
        this.generatePerks();
        this.inventory.add(new Item("Toy", "A toy", "toy", game));
        this.inventory.add(new Item("ID", "An ID", "key", game));
        this.equipment = new Equipment[5];
        this.quests = new ArrayList<>();
        this.currentQuest = null;
        this.perkpicked = false;
        this.agePicked = false;
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
        currentRoom.removeItem(item);
    }else {
        game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't take that item.");
    }
}
    public void dropItem(Item item) {
        if(item.getName().equals("ID")) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't drop that item.");
        } else {
        inventory.remove(item);
        currentRoom.addItem(item);
    }
    }

    public String[] getInventory() {
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
    public void updateStatus(){
        game.getGUI().updateStatus(this);
        if(this.getResilience() <= 0) {
            game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You have run out of resilience and need to take a nap.");
            this.nap();
        }
        if(this.getExperience() >= 100) {
        }
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
    public void inventory(String selectedItem) {
        this.getItemByName(selectedItem);
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
        this.currentRoom = Room.recoveryRoom;
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
   game.getGUI().updateStatus(this);
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
    public void useItem(Item item2) {
        item2.use(this);
    }
    public Equipment[] getEquipment() {
        for (int i = 0; i < equipment.length; i++) {
            if (equipment[i] != null) {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), slotWord(i) + equipment[i].getName());
            } else {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(), slotWord(i) + "Empty");
            }
        }
        return this.equipment;
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
    private void generatePerks() {
        Perk brat   = new Perk("Brat", "You’re a brat, plain and simple. Your antics make teachers sigh and classmates roll their eyes, but there's no denying you add a bit of chaotic fun to the day.\n", 0, 1);
        Perk cute  = new Perk("Cute", "You’re the darling of the school, with a smile that can melt hearts and charm that can get you out of almost any trouble. Everyone loves a cutie like you!.\n", 0, 1);
        Perk classClown = new Perk("Class Clown", "You’re the class clown, always ready with a joke or a prank to keep things lively. Your sense of humor is infectious, and you know how to make even the grumpiest teacher crack a smile.\n", 0, 1);
        Perk geek = new Perk("Geek", "You’re a geek through and through, with a passion for all things nerdy. Whether it’s comics, video games, or science fiction, you know your stuff and aren’t afraid to show it.\n", 0, 1);
        Perk outcast = new Perk("Outcast", "You’re the outcast, the loner, the one who doesn’t quite fit in. But that’s okay, because you’ve got your own unique style and a rebellious spirit that sets you apart from the crowd.\n", 0, 1);
        Perk teacherPet = new Perk("Teacher's Pet", "You’re the teacher’s pet, always eager to please and quick to raise your hand. Your dedication to your studies and your helpful nature make you a favorite among the staff.\n", 0, 1);
        Perk slacker = new Perk("Slacker", "You’re the slacker, the one who’s always cutting corners and looking for the easy way out. Your laid-back attitude and devil-may-care approach to schoolwork make you a master of procrastination.\n", 0, 1);
        Perk shy = new Perk("Shy", "You’re the shy one, the quiet one, the one who prefers to blend into the background. But behind that timid exterior lies a world of creativity and imagination just waiting to be unleashed.\n", 0, 1);   
        Perk smart = new Perk("Smart", "You’ve got a brain that’s always buzzing with ideas. Whether it's acing tests or coming up with clever solutions, your intelligence shines bright.\n", 0, 1);
        Perk goodkid = new Perk("Good", "You’re the teacher’s pet, always following the rules and doing the right thing. Your kindness and helpful nature make you a beacon of goodness.\n", 0, 1);
        Perk rebel = new Perk("Rebel", "Rules? What rules? You live life on your own terms and never shy away from pushing boundaries. Your rebellious spirit keeps things interesting.\n", 0, 1);
        Perk bully = new Perk("Bully", "You’ve got a tough exterior and a knack for getting what you want, even if it means stepping on a few toes. Others might find you intimidating, but deep down, everyone has their reasons.\n", 0, 1);
        Perk nerd = new Perk("Nerd", "Books, gadgets, and games are your world. You’re the go-to for all things geeky, and your passion for learning makes you stand out as a true nerd.\n", 0, 1);
        Perk confederate = new Perk("Confederate", "You help the research staff corral the other rejuves and provide data, is it snitching if no one gets in trouble, no matter what you report?.\n", 0, 1);
        Perk rascal = new Perk("Rascal", "You’re a rascal, a troublemaker, a mischief-maker. You’re always up to something, whether it’s pulling pranks, causing chaos, or just stirring the pot.\n", 0, 1);
        Perk angel = new Perk("Angel", "You’re the teacher’s pet, always following the rules and doing the right thing. Your kindness and helpful nature make you a beacon of goodness.\n", 0, 1);
        Perk rogue = new Perk("Rogue", "Rules? What rules? You live life on your own terms and never shy away from pushing boundaries. Your rebellious spirit keeps things interesting.\n", 0, 1);
        Perk troublemaker = new Perk("Troublemaker", "You’re a rascal, a troublemaker, a mischief-maker. You’re always up to something, whether it’s pulling pranks, causing chaos, or just stirring the pot.\n", 0, 1);
        Perk sweetheart =   new Perk("Sweet Heart", " You’re the darling of the school, with a smile that can melt hearts and charm that can get you out of almost any trouble. Everyone loves a cutie like you!.\n", 0, 1);
        Perk comedian = new Perk("Comedian", "You’re the class clown, always ready with a joke or a prank to keep things lively. Your sense of humor is infectious, and you know how to make even the grumpiest teacher crack a smile.\n", 0, 1);
        Perk brainiac = new Perk("Brainiac", "You’re a geek through and through, with a passion for all things nerdy. Whether it’s comics, video games, or science fiction, you know your stuff and aren’t afraid to show it.\n", 0, 1);
        Perk spy = new Perk("Spy", "You help the research staff corral the other rejuves and provide data, is it snitching if no one gets in trouble, no matter what you report?.\n", 0, 1);
        Perk sloth = new Perk("Sloth", "You’re the slacker, the one who’s always cutting corners and looking for the easy way out. Your laid-back attitude and devil-may-care approach to schoolwork make you a master of procrastination.\n", 0, 1);
        Perk introvert = new Perk("Introvert", "You’re the shy one, the quiet one, the one who prefers to blend into the background. But behind that timid exterior lies a world of creativity and imagination just waiting to be unleashed.\n", 0, 1);
        Perk genius = new Perk("Genius", "You’ve got a brain that’s always buzzing with ideas. Whether it's acing tests or coming up with clever solutions, your intelligence shines bright.\n", 0, 1);
        Perk valedictorian = new Perk("Valedictorian", "You’re the teacher’s pet, always eager to please and quick to raise your hand. Your dedication to your studies and your helpful nature make you a favorite among the staff.\n", 0, 1);
        Perk lazy = new Perk("Lazy", "You’re the slacker, the one who’s always cutting corners and looking for the easy way out. Your laid-back attitude and devil-may-care approach to schoolwork make you a master of procrastination.\n", 0, 1);
        Perk jester = new Perk("Jester  ", "You’re the class clown, always ready with a joke or a prank to keep things lively. Your sense of humor is infectious, and you know how to make even the grumpiest teacher crack a smile.\n", 0, 1);
        Perk adorable = new Perk("Adorable", " You’re the darling of the school, with a smile that can melt hearts and charm that can get you out of almost any trouble. Everyone loves a cutie like you!.\n", 0, 1);
        Perk braniac = new Perk("Braniac", "You’ve got a brain that’s always buzzing with ideas. Whether it's acing tests or coming up with clever solutions, your intelligence shines bright.\n", 0, 1);
        Perk hermit = new Perk("Hermit", "You’re the shy one, the quiet one, the one who prefers to blend into the background. But behind that timid exterior lies a world of creativity and imagination just waiting to be unleashed.\n", 0, 1);
        this.perks.add(spy);
        this.perks.add(sloth);
        this.perks.add(brainiac);
        this.perks.add(comedian);
        this.perks.add(sweetheart);
        this.perks.add(troublemaker);
        this.perks.add(angel);
        this.perks.add(rogue);
        this.perks.add(valedictorian);
        this.perks.add(genius);
        this.perks.add(introvert);
        this.perks.add(lazy);
        this.perks.add(confederate);
        this.perks.add(nerd);
        this.perks.add(jester);
        this.perks.add(adorable);
        this.perks.add(rebel);
        this.perks.add(braniac); 
        this.perks.add(brat);
        this.perks.add(cute);
        this.perks.add(smart);
        this.perks.add(goodkid);
        this.perks.add(bully);
        this.perks.add(classClown);
        this.perks.add(geek);
        this.perks.add(outcast);
        this.perks.add(teacherPet);
        this.perks.add(slacker);
        this.perks.add(shy);
        this.perks.add(rascal);
        this.perks.add(hermit);

    }
    private int getLevel() {
return this.level;
    }
    public Perk getPerkByName(String input) {
        for(Perk perk : getPerks()) {
            if(perk.getName().equals(input)) {
                return perk;
            }
        }
        return null;
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
    public Room getRoom() {

        return this.currentRoom;
    }
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    void setCurrentRoom(Room recoveryRoom) {
        this.currentRoom = recoveryRoom;
    }
    void putItem(Item item, Item containerByName) {
        containerByName.addItem(item);
        this.inventory.remove(item);
    }

    void throwAway(Item item, Item containerByName) {
        this.inventory.remove(item);
        containerByName.addItem(item);
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
            }
        }
    }