package raiseoftali;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    public static Room room;
    static Room recoveryRoom;
    private final Game game;
    private Clock clock;
    private final ArrayList<Room> rooms;
    private final String type;
    public List<Item> listitems;

             public Room kitchen,mainRoom,dorms,bathroom,hallway,stairs,basement,attic,
                     garage,garden,driveway,frontYard,backYard,shed,pool,patio,deck,porch,balcony,
                     oof,cubbies,dramaArea,changingRoom,floorPlay,quietArea,homeWorkArea,playHouse,
                     treeHouse,storyBookVillage,pillowPile,snackArea,greenHall,blueHall,redHall,
                     peddleToys,lemonaidStand,toolShed,TRSRoom,janitorialRoom,foyer,pantry,roof;
             public HashMap<String, Room> exits;
             private final String name;
             private ArrayList<Item> inventory;

             ArrayList<NPC> npcs;
             private String description;

             public String lockType;
  
    private Item empty;
    private Item trash;
             public Room(Game game1, String name,String type) {
                 this.game = game1;
                 this.npcs = new ArrayList<>();
                 this.exits = new HashMap<>();
                 this.inventory = new ArrayList<>();
                 this.rooms = new ArrayList<>();
                 this.name = name;
                 this.description = "You are in the " + name;
                 this.lockType = "";
                 this.type = type;

             }
    public void initializeRoomFiles() { //initializes the room files and creates them if they don't exist already.
        this.setDescription(Game.readFile(this.getName().concat("desc")));
        File mainFile = new File(this.getName().concat(".txt"));
        File descFile = new File(this.getName().concat("desc.txt"));
        
        if(!mainFile.exists()) {
        try {
                mainFile.createNewFile();
            } catch (IOException e) {
            }
            if(!descFile.exists()) {
                try {
                    descFile.createNewFile();
                } catch (IOException e) {
                }
            }
        }
    }
    public String getName() {
        return this.name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public Room getExit(String roomName) {
        return exits.get(roomName);
    }
public void addExit(String string, Room room) {
        exits.put(string, room);
    }
    public String[] getExits() {
        StringBuilder exitsString = new StringBuilder();
        for (String exit : exits.keySet()) {
            exitsString.append(exit).append(" ");
        }
        return exitsString.toString().split(" ");
    }
    public void removeItem(Item item) {
        this.getArrayInventory().remove(item);
    }
    public String[] getInventory() {
        if(this.getArrayInventory().isEmpty()) {
                return new String[]{"Empty"};
            }
            String[] items = new String[this.getArrayInventory().size()];
            for (int i = 0; i < this.getArrayInventory().size(); i++) {
                items[i] = this.getArrayInventory().get(i).getName();
            }
            return items;
        }
    public ArrayList<Item> getArrayInventory() {
        if(this.inventory!=null) {
                        return this.inventory;
                    }
                    ArrayList<Item> newInventory = new ArrayList<>();
                    this.inventory = newInventory;
                    return this.inventory;
                }


    public Room getExitByName(String string) {
            for (String exit : exits.keySet()) {
                if (exit.equals(string)) {
                    return exits.get(exit);
                }
            }
            return null;
        }

        public void addItem(Item item) {
        game.getGUI().printToJTextPane("You put "+item.getName()+" in the " + this.name);
        this.getArrayInventory().add(item);
            }
                public NPC getNPCByName(String npcName) { //returns the NPC with the given name
                        for (NPC npcz : this.npcs) {
                            if (npcz.getName().equals(npcName)) {
                                System.out.println(npcz.getName());
                                return npcz;
                            }
                        }
                        return null;
                    }
                public String[] getNPCs() {
                    if(this.npcs.isEmpty()) {
                            return new String[]{"empty"};
                        }
                        StringBuilder npcsString = new StringBuilder();
                        for (NPC npcz : npcs) {
                            npcsString.append(npcz.getName()).append(" ");
                        }
                        return npcsString.toString().split(" ");
                    }
                public void addNPC(NPC npc) {
                        this.npcs.add(npc);
                    }
                public Item getItemByName(String selectedItem) {
                    for (Item item : this.getArrayInventory()) {
                        if (item.getName().equalsIgnoreCase(selectedItem)) {
                            return item;
                        }
                    }
                    return null;
                }
                public Item[] getItemsByType(String string) {
                        ArrayList<Item> items = new ArrayList<>();
                        for(Item item : this.getArrayInventory()) {
                            if(item.getType().equalsIgnoreCase(string)) {
                                items.add(item);
                            }
                        }
                        return items.toArray(Item[]::new);
                    }
                    public ArrayList<NPC> getNpcs() {
                        return npcs;
                    }
                public String[] getItems() {
                    String[] items = new String[this.getArrayInventory().size()];
                    for (int i = 0; i < this.getArrayInventory().size(); i++) {
                        items[i] = this.getArrayInventory().get(i).getName();
                    }
                    return items;
                }
            
                    public Item getItemByType(String type) {
                        for(Item item : this.getArrayInventory()) {
                            if(item.getType().equalsIgnoreCase(type)) {
                                return item;
                            }
                        }
                        return null;
                    }
                    public Item[] getContainers() {
                        ArrayList<Item> containers = new ArrayList<>();
                        for(Item item : this.getArrayInventory()) {
                            if(item.getType().equalsIgnoreCase("Container")) {
                                containers.add(item);
                            }
                        }
                        return containers.toArray(Item[]::new);
                    }
                    public String getContainerChoice() {
                        Item[] containers = this.getContainers();
                        StringBuilder containerString = new StringBuilder();
                        for(Item container : containers) {
                            containerString.append(container.getName()).append(" ");
                            
                        }
                        return containerString.toString();
         
                    }
                public Item getContainerByName(String selectedContainer) {
                    for(Item container : this.getContainers()) {
                        if(container.getName().equals(selectedContainer)) {
                            return container;
                        }
                    }
                    return null;
                }
                public Room getRoomByName(String string) {
                    for (Room roomS : getRooms()) {
                        if (roomS.getName().equalsIgnoreCase(string)) {
                            return roomS;
                        }
                    }
                    return null;
                }
                public ArrayList<Room> getRooms() {
                    return rooms;
                }
                public Clock getClock() {
                    return clock;
                }
                public Game getGame() {
                    return game;
                }
                public String listItems() {
                    int i=-1;
                    StringBuilder itemList = new StringBuilder();
                    ArrayList<Item>tempArray =  Player.getCurrentRoom().getArrayInventory();
                    for (Item item : tempArray) {
                        i++;
                        itemList.append(item.getName());
                        if(tempArray.size()> 3 && i % 3 == 0) {
                            itemList.append("<br>");
                        }
                        else {
                            itemList.append(" , ");
                        }
                    }
                    return itemList.toString();
                }
                public String listPeople() {
                    StringBuilder peopleList = new StringBuilder();
                    if (Player.getCurrentRoom().getNpcs().isEmpty()) {
                        peopleList.append("There is no one here.");
                    }
                    for (NPC peep : Player.getCurrentRoom().getNpcs()) {
                        peopleList.append(peep.getName());
                        peopleList.append("<br>");
                    }
                    return peopleList.toString();
                }
                public  void buildRooms(Game game){ //builds the rooms and sets the exits and items for each room in the game world
                    recoveryRoom = new Room(game, "Recovery Room", "Green Room");
                    cubbies = new Room(game, "Cubbies", "Green Room");
                    dramaArea = new Room(game,"Drama Area","Blue Room");
                    changingRoom = new Room(game,"Changing Room","Green Room");
                    floorPlay = new Room(game,"Floor Play","Blue Room");
                    quietArea = new Room(game,"Quiet Area","Green Room");
                    homeWorkArea = new Room(game,"Home Work Area","Blue Room");
                    playHouse = new Room(game,"Play House", "Blue Room");
                    treeHouse = new Room(game,"Tree House", "Blue Room");
                    storyBookVillage = new Room(game,"Story Book Village", "Blue Room");
                    pillowPile = new Room(game,"Pillow Pile","Green Room");
                    snackArea = new Room(game,"Snack Area","Green Room");
                    greenHall = new Room(game,"Green Hall","Green Room");
                    blueHall = new Room(game,"Blue Hall","Blue Room");
                    redHall = new Room(game,"Red Hall","Red Room");
                    peddleToys = new Room(game,"Peddle Toys","Blue Room");
                    lemonaidStand = new Room(game,"Lemonaid Stand","Blue Room");
                    toolShed = new Room(game,"Tool Shed","Blue Room");
                    TRSRoom = new Room(game,"TRS Room","Blue Room");
                    janitorialRoom = new Room(game,"Janitorial Room","Red Room");
                    foyer = new Room(game,"Foyer","Green Room");
                    pantry = new Room(game,"Pantry","Blue Room");   
                    kitchen = new Room(game,"Kitchen","Blue Room");
                    kitchen.lockType = "keycard";
                    mainRoom = new Room(game,"Main Room","Green Room");
                    mainRoom.lockType = "keycard";
                    dorms = new Room(game,"Dorms","Green Room");
                    dorms.lockType = "keycard";
                    bathroom = new Room(game,"Bathroom","Green Room");
                    bathroom.lockType = "keycard";
                    hallway = new Room(game,"Hallway","Blue Room");
                     hallway.lockType = "keycard";
                    stairs = new Room(game,"Stairs","Blue Room");
                     stairs.lockType = "metal Key";
                    basement = new Room(game,"Basement","Red Room");
                     basement.lockType = "metal Key";
                    attic = new Room(game,"Attic","Red Room");
                     attic.lockType = "metal Key";
                    garage = new Room(game,"Garage","Red Room");
                     garage.lockType = "metal Key";
                    garden = new Room(game,"Garden","Blue Room");
                     garden.lockType = "blue card";
                     recoveryRoom.lockType = "keycard";
                    driveway = new Room(game,"Driveway","Red Room");
                     driveway.lockType = "metal key";
                    frontYard = new Room(game,"Front Yard","Blue Room");
                     frontYard.lockType = "metal key";
                    backYard = new Room(game,"Back Yard","Blue Room");
                     backYard.lockType = "metal key";
                    shed = new Room(game,"Shed","Red Room");
                     shed.lockType = "metal key";
                    pool = new Room(game,"Pool","Red Room");
                     pool.lockType = "metal key";
                    patio = new Room(game,"Patio","Red Room");
                     patio.lockType = "metal key";
                    deck = new Room(game,"Deck","Blue Room");
                     deck.lockType = "metal key";
                    porch = new Room(game,"Porch","Blue Room");
                     porch.lockType = "metal key";
                    balcony = new Room(game,"Balcony","Red Room");
                     balcony.lockType = "metal key";
                    roof = new Room(game,"Roof","Red Room");
                     roof.lockType = "metal key";
                    // populate rooms with NPCs
                    NPC mrsssagely = new NPC("Mrs._Sagely", "The headmistress of the BusyBeavers Home For Wayward Rejuves.", "Teacher");
                    NPC dawn = new NPC("Dawn", "A researcher and assistant to Mrs. Sagely.", "Teacher");
                    NPC drwhite = new NPC("Dr._White", "Head operations manger of the labrotory and rejuve research facility.", "Teacher");
                    NPC susy = new NPC("Susy","The head of the kitchen and cafeteria. Suis chef and prefect.", "Rejuve");
                    NPC taliber = new NPC ("Taliber", "The head of the resistance and the leader of the rejuves.", "Rejuve");
                    NPC jenny = new NPC("Jenny", "The head of the dorms and the resident prefect.", "Rejuve");
                    NPC farah = new NPC("Farah", "Head of the art department and prefect of the art room.", "Rejuve");
                    NPC aureus = new NPC("Aureus", "The head of the playroom and prefect of the playroom.", "Rejuve");
                    NPC fuzzy = new NPC("Fuzzy", "A robot in the form of a teddybear, assistant to the residents of the BusyBeavers Home For Wayward Rejuves.", "Rejuve");
                    NPC busybeaver = new NPC("Busy_Beaver", "The mascot of the BusyBeavers Home For Wayward Rejuves.", "Animal");
                    NPC aang = new NPC("Aang", "Aang is a young boy who is a resident of the BusyBeavers. He is a rejuve and is in his first cycle.", "Rejuve"); 
                    recoveryRoom.npcs.add(mrsssagely);
                    Quests fetchQuest = new Quests("Fetch Quest", "Bring me the trash item.", "fetch");
                    fetchQuest.setQuestRequirement(mrsssagely, trash);
                    Quests testQuest = new Quests("Test Quest", "Gain more than 100 experience or bring me the completed test item.", "test");
                    Quests socialQuest = new Quests("Social Quest", "Increase your resilience to more than 80 or bring me the social item.", "social");
                    mrsssagely.setQuest(fetchQuest);
                    aang.setQuest(testQuest);
                    mrsssagely.setQuestItem(fetchQuest.getQuestItem());
                    drwhite.setQuest(testQuest);
                    dawn.setQuest(socialQuest);
                    recoveryRoom.npcs.add(busybeaver);
                    kitchen.npcs.add(susy);
                    mainRoom.npcs.add(dawn);
                    mainRoom.npcs.add(fuzzy);
                    hallway.npcs.add(jenny);
                    dorms.npcs.add(taliber);
                    dorms.npcs.add(aang);
                    bathroom.npcs.add(farah);
                    attic.npcs.add(aureus);
                    roof.npcs.add(drwhite);
                    // initialise room exits
                    recoveryRoom.addExit("Foyer", foyer);
                    foyer.addExit("Recovery_Room", recoveryRoom);
        
                    foyer.addExit("Kitchen", kitchen);
                    kitchen.addExit("Foyer", foyer);
        
                    foyer.addExit("Main_Room", mainRoom);
                    mainRoom.addExit("Foyer", foyer);
        
                    foyer.addExit("Dorms", dorms);
                    dorms.addExit("Foyer", foyer);
        
                    foyer.addExit("Blue_Hall", blueHall);
                    blueHall.addExit("Foyer", foyer);
        
                    foyer.addExit("Green_Hall", greenHall);
                    greenHall.addExit("Foyer", foyer);
        
                    mainRoom.addExit("Cubbiess", cubbies);
                    cubbies.addExit("Main_Room", mainRoom);
        
                    mainRoom.addExit("Drama_Area", dramaArea);
                    dramaArea.addExit("Main_Room", mainRoom);
        
                    bathroom.addExit("Changing_Room", changingRoom);
                    changingRoom.addExit("Bathroom", bathroom);
        
                    mainRoom.addExit("Floor_Play", floorPlay);
                    floorPlay.addExit("Main_Room", mainRoom);
        
                    mainRoom.addExit("Quiet_Area", quietArea);
                    quietArea.addExit("Main_Room", mainRoom);
        
                    mainRoom.addExit("Home_Work_Area", homeWorkArea);
                    homeWorkArea.addExit("Main_Room", mainRoom);
        
                    foyer.addExit("front_Yard", frontYard);
                    frontYard.addExit("Foyer", foyer);
        
                    foyer.addExit("Back_Yard", backYard);
                    backYard.addExit("Foyer", foyer);
        
                    backYard.addExit("Shed", shed);
                    shed.addExit("Back_Yard", backYard);
        
                    backYard.addExit("Pool", pool);
                    pool.addExit("Back_Yard", backYard);
        
                    backYard.addExit("Patio", patio);
                    patio.addExit("Back_Yard", backYard);
        
                    backYard.addExit("Deck", deck);
                    deck.addExit("Back_Yard", backYard);
        
                    frontYard.addExit("Porch", porch);
                    porch.addExit("Front_Yard", frontYard);
        
                    backYard.addExit("tree_House", treeHouse);
                    treeHouse.addExit("Back_Yard", backYard);
        
                    frontYard.addExit("Lemonaid_Stand", lemonaidStand);
                    lemonaidStand.addExit("Front_Yard", frontYard);
        
                    backYard.addExit("Tool_Shed", toolShed);
                    toolShed.addExit("Back_Yard", backYard);
        
                    backYard.addExit("Play_House", playHouse);
                    playHouse.addExit("Back_Yard", backYard);
        
                    mainRoom.addExit("Snack_Area", snackArea);
                    snackArea.addExit("Main_Room", mainRoom);
        
                    dorms.addExit("snack_Area", snackArea);
                    snackArea.addExit("Dorms", dorms);
        
                    greenHall.addExit("Main_Room", mainRoom);
        
                    blueHall.addExit("Main_Room", mainRoom);
        
                    greenHall.addExit("Dorms", dorms);
                    dorms.addExit("Green_Hall", greenHall);
        
                    greenHall.addExit("Blue_Hall", blueHall);
                    blueHall.addExit("Green_Hall", greenHall);
        
                    blueHall.addExit("Red_Hall", redHall);
                    redHall.addExit("Blue_Hall", blueHall);
        
                    mainRoom.addExit("Pillow_Pile", pillowPile);
                    pillowPile.addExit("Main_Room", mainRoom);
        
                    mainRoom.addExit("Story_Book_Village", storyBookVillage);
                    storyBookVillage.addExit("Main_Room", mainRoom);
        
                    kitchen.addExit("Pantry", pantry);
                    pantry.addExit("Kitchen", kitchen);
        
                    mainRoom.addExit("TRS_Room", TRSRoom);
                    TRSRoom.addExit("Main_Room", mainRoom);
        
                    hallway.addExit("Janitorial_Room", janitorialRoom);
                    janitorialRoom.addExit("Hallway", hallway);
        
                    kitchen.addExit("Hallway", hallway);
                    hallway.addExit("Kitchen", kitchen);
        
                    mainRoom.addExit("Hallway", hallway);
                    hallway.addExit("Main_Room", mainRoom);
        
                    dorms.addExit("Hallway", hallway);
                    hallway.addExit("Dorms", dorms);
        
                    mainRoom.addExit("Bathroom", bathroom);
                    bathroom.addExit("Main_Room", mainRoom);
        
                    foyer.addExit("Stairs", stairs);
                    stairs.addExit("Foyer", foyer);
        
                    basement.addExit("Hallway", hallway);
                    hallway.addExit("Basement", basement);
        
                    attic.addExit("Hallway", hallway);
                    hallway.addExit("Attic", attic);
        
                    garage.addExit("Hallway", hallway);
                    hallway.addExit("Garage", garage);
        
                    garden.addExit("Back_Yard", backYard);
                    backYard.addExit("Garden", garden);
        
                    garage.addExit("Driveway", driveway);
                    driveway.addExit("Garage", garage);
        
                    garage.addExit("peddle_Toys", peddleToys);
                    peddleToys.addExit("Garage", garage);
        
                    bathroom.addExit("Hallway", hallway);
                    hallway.addExit("Bathroom", bathroom);
        
                    hallway.addExit("stairs", stairs);
                    stairs.addExit("Hallway", hallway);
        
                    balcony.addExit("attic", attic);
                    attic.addExit("Balcony", balcony);
        
                    roof.addExit("attic", attic);
                    attic.addExit("Roof", roof);
        
                    roof.addExit("stairs", stairs);
                    stairs.addExit("Roof", roof);
        
                    basement.addExit("stairs", stairs);
                    stairs.addExit("Basement", basement);
        
                    deck.addExit("main_Room", mainRoom);
                    mainRoom.addExit("Deck", deck);
        
                    porch.addExit("front_Yard", frontYard);
                    frontYard.addExit("Porch", porch);
                    janitorialRoom.addExit("Hallway", hallway);
                // mainRoom exits are all connected to each other
                    pillowPile.addExit("Story_Book_Village", storyBookVillage);
                    pillowPile.addExit("Home_Work_Area", homeWorkArea);
                    pillowPile.addExit("Quiet_Area", quietArea);
                    pillowPile.addExit("Floor_Play", floorPlay);
                    pillowPile.addExit("Drama_Area", dramaArea);
                    pillowPile.addExit("Cubbies", cubbies);
                    storyBookVillage.addExit("Pillow_Pile", pillowPile);
                    storyBookVillage.addExit("Home_Work_Area", homeWorkArea);
                    storyBookVillage.addExit("Quiet_Area", quietArea);
                    storyBookVillage.addExit("Floor_Play", floorPlay);
                    storyBookVillage.addExit("Drama_Area", dramaArea);
                    storyBookVillage.addExit("Cubbies", cubbies);
                    homeWorkArea.addExit("Pillow_Pile", pillowPile);
                    homeWorkArea.addExit("Story_Book_Village", storyBookVillage);
                    homeWorkArea.addExit("Quiet_Area", quietArea);
                    homeWorkArea.addExit("Floor_Play", floorPlay);
                    homeWorkArea.addExit("Drama_Area", dramaArea);
                    homeWorkArea.addExit("Cubbies", cubbies);
                    quietArea.addExit("Pillow_Pile", pillowPile);
                    quietArea.addExit("Story_Book_Village", storyBookVillage);
                    quietArea.addExit("Home_Work_Area", homeWorkArea);
                    quietArea.addExit("Floor_Play", floorPlay);
                    quietArea.addExit("Drama_Area", dramaArea);
                    quietArea.addExit("Cubbies", cubbies);
                    floorPlay.addExit("Pillow_Pile", pillowPile);
                    floorPlay.addExit("Story_Book_Village", storyBookVillage);
                    floorPlay.addExit("Home_Work_Area", homeWorkArea);
                    floorPlay.addExit("Quiet_Area", quietArea);
                    floorPlay.addExit("Drama_Area", dramaArea);
                    floorPlay.addExit("Cubbies", cubbies);
                    dramaArea.addExit("Pillow_Pile", pillowPile);
                    dramaArea.addExit("Story_Book_Village", storyBookVillage);
                    dramaArea.addExit("Home_Work_Area", homeWorkArea);
                    dramaArea.addExit("Quiet_Area", quietArea);
                    dramaArea.addExit("Floor_Play", floorPlay);
                    dramaArea.addExit("Cubbies", cubbies);
                    cubbies.addExit("Pillow_Pile", pillowPile);
                    cubbies.addExit("Story_Book_Village", storyBookVillage);
                    cubbies.addExit("Home_Work_Area", homeWorkArea);
                    cubbies.addExit("Quiet_Area", quietArea);
                    cubbies.addExit("Floor_Play", floorPlay);
                    cubbies.addExit("Drama_Area", dramaArea);
        
                    
                    }
            
                public  void generateItems() { //generates the items for each room in the game world
                            Item empty1 = new Item("Room is empty", "You can't pick up nothing!", "empty", game);
                            empty1.setTakeable(false);
                            this.inventory.add(empty1);
                            
                            Furniture artStation = new Furniture("Art Station", "A station with a logo on the front of a happy beaver, This station is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Furniture", game);
                            Container artSupplies = new Container("Art Supplies", "A box with a logo on the front of a happy beaver, This box is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                            artSupplies.setType("Container");
                            Equipment artSmock = new Equipment("Art Smock", "A smock with a logo on the front of a happy beaver, This smock is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 2, "Clothing");
                            Item kickBall = new Item("Kick Ball", "A ball with a logo on the front of a happy beaver, This ball is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            Item jumpRope = new Item("Jump Rope", "A rope with a logo on the front of a happy beaver, This rope is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            Item hulaHoop = new Item("Hula Hoop", "A hoop with a logo on the front of a happy beaver, This hoop is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            Item pegBoard = new Item("Peg Board", "A board with a logo on the front of a happy beaver, This board is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            Item buildingBlocks = new Item("Building Blocks", "A box with a logo on the front of a happy beaver, This box is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                            Item dolly = new Item("Dolly", "A dolly with a logo on the front of a happy beaver, This dolly is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            Container dressUpTrunk = new Container("Dress Up Trunk", "A trunk with a logo on the front of a happy beaver, This trunk is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                            Container puppetTheater = new Container("Puppet Theater", "A theater with a logo on the front of a happy beaver, This theater is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "toy", game);
                            puppetTheater.setTakeable(true);
                            Item fruit = new Item("Fruit", "A piece of fruit with a logo on the front of a happy beaver, This fruit is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "food", game);
                            fruit.setTakeable(true);
                            fruit.setQuestItem(true);
                            Item storyBoard = new Item("Story Board", "A board with a logo on the front of a happy beaver, This board is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Furniture", game);
                            Item apptitude = new Item("Apptitude Test", "A test with a logo on the front of a happy beaver, This test is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "test", game);
                            Item tablet = new Item("Tablet", "A tablet with a logo on the front of a happy beaver, This tablet is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item labTablet = new Item("Lab Tablet", "A tablet with a logo on the front of a happy beaver, This tablet is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item computer = new Item("Computer", "A computer with a logo on the front of a happy beaver, This computer is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item labComputer = new Item("Lab Computer", "A computer with a logo on the front of a happy beaver, This computer is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item prefectBadge = new Item("Prefect Badge", "A badge with a logo on the front of a happy beaver, This badge is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item prefectKey = new Item("Prefect Key", "A key with a logo on the front of a happy beaver, This key is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Item prefectCard = new Item("Prefect Card", "A card with a logo on the front of a happy beaver, This card is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "tool", game);
                            Equipment prefectUniform = new Equipment("Prefect Uniform", "A uniform with a logo on the front of a happy beaver, This uniform is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 2, "Clothing");
                            Item labCoat = new Item("Lab Coat", "A coat with a logo on the front of a happy beaver, This coat is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Clothing", game);
                            Item labGoggles = new Item("Lab Goggles", "A pair of goggles with a logo on the front of a happy beaver, These goggles are a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Clothing", game);
                            Item labGloves = new Item("Lab Gloves", "A pair of gloves with a logo on the front of a happy beaver, These gloves are a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Clothing", game);
                            Equipment uniformtop = new Equipment("Uniform Top", "A bague polo style top with a logo on the front of a happy beaver, This top is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 1, description);
                            Equipment uniformbottom = new Equipment("Uniform Bottom", "A pair of khaki pants with a logo on the back pocket of a happy beaver, These bottoms are switch between a skirt and shorts with a large velcro strip along the inseem.", 3, description);
                            Equipment uniformshoes = new Equipment("Uniform Shoes", "A pair of black shoes with a logo on the side of a happy beaver, These shoes are a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 4, description);
                            Item schoolbag = new Item("School Bag", "A large bag with a logo on the front of a happy beaver, This bag is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                            Item lunchbox = new Item("Lunch Box", "A small box with a logo on the front of a happy beaver, This box is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                            Item waterbottle = new Item("Water Bottle", "A bottle with a logo on the front of a happy beaver, This bottle is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Bottle",game);
                            Container pencilcase = new Container("Pencil Case", "A case with a logo on the front of a happy beaver, This case is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container",game);
                            pencilcase.setTakeable(true);
                            Equipment diaper = new Equipment("Diaper", "A plain white diaper, this diaper is avalible in all sizes. In the dorms or changing rooms throughout the BusyBeavers Home For Wayward Rejuves you can find a diaper changing station with a variety of sizes.", 2, description);
                            diaper.setClassName("bookworm");
                            Item plasticknife = new Item("Plastic Knife", "A plastic knife, this knife is used for cutting food in the cafeteria. The knife is avalible in the cafeteria.", "tool",game); 
                                Item rubberspoon = new Item("Rubber Spoon", "A rubber spoon, this spoon is used for eating food in the cafeteria. The spoon is avalible in the cafeteria.", "tool",game);
                                Item schoolbook = new Item("A guide for the newly rejuvenated", "A book containig advice for those who are in their first cycle.", "toy",game);
                                Item schoolbook2 = new Item("Stroies from under the bus", "A book containig stories from the residents of the BusyBeavers Home For Wayward Rejuves.", "Book",game); 
                                Item schoolbook3 = new Item("Welcome to BusyBeavers!", "A book containig the history of the BusyBeavers Home For Wayward Rejuves.", "Book",game);
                                Item shinyPennys = new Item("Shiny Penny", "A shiny penny, this penny is used for making wishes in the fountain. The penny is a reward given for good behaviour", "money",game);
                                Item goldstar = new Item("Gold Star", "A gold star, The star is a reward given for small acts of kindness or good behaviour", "money",game);
                                Item trashy = new Item("Trash", "A bit of trash, it is generated when you make a mess. You can throw it away in the trash can.", "trash",game);
                                trashy.setTakeable(true);
                                trashy.setQuestItem(true);
                                Item trashcan = new Item("Trash Can", "A trash can, this can is used for throwing away trash. The trash can is avalible in the cafeteria.", "Container",game);
                                trashcan.setTakeable(false);
                                Item crayons = new Item("Crayons", "A box of crayons, this box is used for drawing in the art room. The crayons are avalible in the art room.", "tool",game);
                                Item coloringbook = new Item("Coloring Book", "A coloring book, this book is used for drawing in the art room. The book is avalible in the art room.", "Book",game);
                                Item toytrain = new Item("Toy Train", "A toy train, this train is used for playing in the playroom. The train is avalible in the playroom.", "toy",game);
                                Item doll = new Item("Doll", "A doll, this doll is used for playing in the playroom. The doll is avalible in the playroom.", "toy",game);
                                Item puzzle = new Item("Puzzle", "A puzzle, this puzzle is used for playing in the playroom. The puzzle is avalible in the playroom.", "toy",game);
                                Item cleaningset = new Item("Cleaning Set", "A cleaning set, this set is used for cleaning in the dorms. The set is avalible in the dorms.", "tool",game);
                                Item labreports = new Item("Lab Reports", " A stack of lab reposts, they are pretty dificult to read with all the acronyms and jargon. These reports belong to a research student.", "Book",game);
                                Item labcoat = new Item("Lab Coat", "A lab coat, this coat is used for working in the lab. The coat is avalible in the lab.", "Clothing",game);
                                Item puddle = new Item("Puddle of pee", "A puddle of pee, eeeew", "mess", game);
                                Item snackDispenser = new Item("Snack Dispenser", "A snack dispenser, this dispenser is used for getting snacks in the snack area. The dispenser is avalible in the snack area.", "Container",game);
                                kitchen.getArrayInventory().add(snackDispenser);
                                snackArea.getArrayInventory().add(snackDispenser);
                                mainRoom.getArrayInventory().add(artStation);
                                mainRoom.getArrayInventory().add(artSupplies);
                                mainRoom.getArrayInventory().add(artSmock);
                                backYard.getArrayInventory().add(kickBall);
                                backYard.getArrayInventory().add(jumpRope);
                                backYard.getArrayInventory().add(hulaHoop);
                                mainRoom.getArrayInventory().add(pegBoard);
                                mainRoom.getArrayInventory().add(buildingBlocks);
                                dramaArea.getArrayInventory().add(dolly);
                                dramaArea.getArrayInventory().add(dressUpTrunk);
                                dramaArea.getArrayInventory().add(puppetTheater);
                                mainRoom.getArrayInventory().add(storyBoard);
                                storyBookVillage.getArrayInventory().add(apptitude);
                                storyBookVillage.getArrayInventory().add(tablet);
                                homeWorkArea.getArrayInventory().add(labTablet);
                                homeWorkArea.getArrayInventory().add(computer);
                                homeWorkArea.getArrayInventory().add(labComputer);
                                dorms.getArrayInventory().add(prefectBadge);
                                dorms.getArrayInventory().add(prefectKey);
                                dorms.getArrayInventory().add(prefectCard);
                                mainRoom.getArrayInventory().add(prefectUniform);
                                mainRoom.getArrayInventory().add(labCoat);
                                mainRoom.getArrayInventory().add(labGoggles);
                                mainRoom.getArrayInventory().add(labGloves);
                                dorms.getArrayInventory().add(prefectBadge);
                                dorms.getArrayInventory().add(prefectKey);
                                dorms.getArrayInventory().add(prefectCard);
                                dorms.getArrayInventory().add(prefectUniform);
                                dorms.getArrayInventory().add(labCoat);
                                puddle.setTakeable(false);
                                recoveryRoom.getArrayInventory().add(trashcan);
                            recoveryRoom.getArrayInventory().add(diaper);
                            homeWorkArea.getArrayInventory().add(schoolbag);
                            snackArea.getArrayInventory().add(lunchbox);
                            snackArea.getArrayInventory().add(waterbottle);
                            homeWorkArea.getArrayInventory().add(pencilcase);
                            kitchen.getArrayInventory().add(plasticknife);
                            kitchen.getArrayInventory().add(rubberspoon);
                            kitchen.getArrayInventory().add(trashcan);
                            kitchen.getArrayInventory().add(trash);
                            kitchen.getArrayInventory().add(fruit);
                            mainRoom.getArrayInventory().add(schoolbook);
                            mainRoom.getArrayInventory().add(schoolbook2);
                            mainRoom.getArrayInventory().add(schoolbook3);
                            mainRoom.getArrayInventory().add(shinyPennys);
                            mainRoom.getArrayInventory().add(goldstar);
                            dorms.getArrayInventory().add(uniformtop);
                            dorms.getArrayInventory().add(uniformbottom);
                            dorms.getArrayInventory().add(uniformshoes);
                            bathroom.getArrayInventory().add(trashcan);
                            bathroom.getArrayInventory().add(trash);
                            hallway.getArrayInventory().add(trashcan);
                            hallway.getArrayInventory().add(trash);
                            stairs.getArrayInventory().add(trashcan);
      
                            stairs.getArrayInventory().add(trash);
                            basement.getArrayInventory().add(trashcan);
                            basement.getArrayInventory().add(trash);
                            attic.getArrayInventory().add(trashcan);
                            attic.getArrayInventory().add(trash);
                            garage.getArrayInventory().add(trashcan);
                            garage.getArrayInventory().add(trash);
                            garden.getArrayInventory().add(trashcan);
                            garden.getArrayInventory().add(trash);
                            driveway.getArrayInventory().add(trashcan);
                            driveway.getArrayInventory().add(trash);
                            frontYard.getArrayInventory().add(trashcan);
                            frontYard.getArrayInventory().add(trash);
                            backYard.getArrayInventory().add(trashcan);
                            backYard.getArrayInventory().add(trash);
                            shed.getArrayInventory().add(trashcan);
                            shed.getArrayInventory().add(trash);
                            pool.getArrayInventory().add(trashcan);
                            pool.getArrayInventory().add(trash);
                            patio.getArrayInventory().add(trashcan);
                            patio.getArrayInventory().add(trash);
                            deck.getArrayInventory().add(trashcan);
                            deck.getArrayInventory().add(trash);
                            porch.getArrayInventory().add(trashcan);
                            porch.getArrayInventory().add(trash);
                            balcony.getArrayInventory().add(trashcan);
                            balcony.getArrayInventory().add(trash);
                            roof.getArrayInventory().add(trashcan);
                            roof.getArrayInventory().add(trash);
                            roof.getArrayInventory().add(labreports);
                            roof.getArrayInventory().add(labcoat);
                            mainRoom.getArrayInventory().add(crayons);
                            mainRoom.getArrayInventory().add(coloringbook);
                            mainRoom.getArrayInventory().add(toytrain);
                            mainRoom.getArrayInventory().add(doll);
                            mainRoom.getArrayInventory().add(puzzle);
                            mainRoom.getArrayInventory().add(cleaningset);
                            foyer.getArrayInventory().add(trashcan);
                            foyer.getArrayInventory().add(trash);
                            cubbies.getArrayInventory().add(trashcan);
                            cubbies.getArrayInventory().add(trash);
                            dramaArea.getArrayInventory().add(trashcan);
                            dramaArea.getArrayInventory().add(trash);
                            changingRoom.getArrayInventory().add(trashcan);
                            changingRoom.getArrayInventory().add(trash);
                            floorPlay.getArrayInventory().add(trashcan);
                            floorPlay.getArrayInventory().add(trash);
                            quietArea.getArrayInventory().add(trashcan);
                            quietArea.getArrayInventory().add(trash);
                            homeWorkArea.getArrayInventory().add(trashcan);
                            homeWorkArea.getArrayInventory().add(trash);
                            playHouse.getArrayInventory().add(trashcan);
                            playHouse.getArrayInventory().add(trash);
                            treeHouse.getArrayInventory().add(trashcan);
                            treeHouse.getArrayInventory().add(trash);
                            storyBookVillage.getArrayInventory().add(trashcan);
                            storyBookVillage.getArrayInventory().add(trash);
                            pillowPile.getArrayInventory().add(trashcan);
                            pillowPile.getArrayInventory().add(trash);
                            snackArea.getArrayInventory().add(trashcan);
                            snackArea.getArrayInventory().add(trash);
                            greenHall.getArrayInventory().add(trashcan);
                            greenHall.getArrayInventory().add(trash);
                            blueHall.getArrayInventory().add(trashcan);
                            blueHall.getArrayInventory().add(trash);
                            redHall.getArrayInventory().add(trashcan);
                            redHall.getArrayInventory().add(trash);
                            peddleToys.getArrayInventory().add(trashcan);
                            peddleToys.getArrayInventory().add(trash);
                            lemonaidStand.getArrayInventory().add(trashcan);
                            lemonaidStand.getArrayInventory().add(trash);
                            toolShed.getArrayInventory().add(trashcan);
                            toolShed.getArrayInventory().add(trash);
                            TRSRoom.getArrayInventory().add(trashcan);
                            TRSRoom.getArrayInventory().add(trash);
                            janitorialRoom.getArrayInventory().add(trashcan);
                            janitorialRoom.getArrayInventory().add(trash);
                            pantry.getArrayInventory().add(trashcan);
                            pantry.getArrayInventory().add(trash);
                        }
                List<Item> getListItems() {
                    for (Room roomLI : rooms) {
                        if (roomLI.getArrayInventory() != null)
                        listitems.addAll(roomLI.getArrayInventory());
                        else {
                            listitems.add(empty);
                        }
                    } 
                    return inventory;
                }
            String getType() {
                return type;
            }
}
