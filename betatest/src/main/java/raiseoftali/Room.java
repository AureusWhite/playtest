/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package raiseoftali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Room {
    private Game game;
    private Clock clock;
    public Clock getClock() {
        return clock;
    }
    public void setClock(Clock clock) {
        this.clock = clock;
    }
    public Game getGame() {
        return game;
    }
    public  Room recoveryRoom;
    public  Room kitchen;
    public  Room mainRoom;
    public  Room dorms;
    public  Room bathroom;
    public  Room hallway;
    public  Room stairs;
    public  Room basement;
    public  Room attic;
    public  Room garage;
    public  Room garden;
    public  Room driveway;
    public  Room frontYard;
    public  Room backYard;
    public  Room shed;
    public  Room pool;
    public  Room patio;
    public  Room deck;
    public  Room porch;
    public  Room balcony;
    public  Room roof;
    public  Room cubbies;
    public  Room dramaArea;
    public  Room changingRoom;
    public  Room floorPlay;
    public  Room quietArea;
    public  Room homeWorkArea;
    public  Room playHouse;
    public  Room treeHouse;
    public  Room storyBookVillage;
    public  Room pillowPile;
    public  Room snackArea;
    public  Room greenHall;
    public  Room blueHall;
    public  Room redHall;
    public  Room peddleToys;
    public  Room lemonaidStand;
    public  Room toolShed;
    public  Room TRSRoom;
    public  Room janitorialRoom;
    public  Room foyer;
    public  Room pantry;

    public  void lockRooms(Clock clock){
        switch(clock.getTimeOfDay()) {
            case "morning" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"It's morning.");
                basement.setLocked(true);
                attic.setLocked(true);
                garage.setLocked(true);
                garden.setLocked(true);
                driveway.setLocked(true);
                frontYard.setLocked(true);
                backYard.setLocked(true);
                shed.setLocked(true);
                pool.setLocked(true);
                patio.setLocked(true);
                deck.setLocked(true);
                porch.setLocked(true);
                balcony.setLocked(true);
                roof.setLocked(true);
                kitchen.setLocked(false);
                mainRoom.setLocked(false);
                dorms.setLocked(false);
                bathroom.setLocked(false);
                hallway.setLocked(false);
                stairs.setLocked(false);
            }
            case "afternoon" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"It's afternoon.");
                basement.setLocked(true);
                attic.setLocked(true);
                garage.setLocked(true);
                garden.setLocked(true);
                driveway.setLocked(true);
                frontYard.setLocked(true);
                backYard.setLocked(true);
                shed.setLocked(true);
                pool.setLocked(true);
                patio.setLocked(true);
                deck.setLocked(true);
                porch.setLocked(true);
                balcony.setLocked(true);
                roof.setLocked(true);
            }
            case "evening" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"It's evening.");
                kitchen.setLocked(true);
                basement.setLocked(true);
                attic.setLocked(true);
                garage.setLocked(true);
                garden.setLocked(true);
                driveway.setLocked(true);
                frontYard.setLocked(true);
                backYard.setLocked(true);
                shed.setLocked(true);
                pool.setLocked(true);
                patio.setLocked(true);
                deck.setLocked(true);
                porch.setLocked(true);
                balcony.setLocked(true);
                roof.setLocked(true);
            }
            case "Night" -> {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"It's night.");
                recoveryRoom.setLocked(false);
                kitchen.setLocked(true);
                mainRoom.setLocked(true);
                dorms.setLocked(true);
                bathroom.setLocked(false);
                hallway.setLocked(false);
                stairs.setLocked(true);
                basement.setLocked(true);
                attic.setLocked(true);
                garage.setLocked(true);
                garden.setLocked(true);
                driveway.setLocked(true);
                frontYard.setLocked(true);
                backYard.setLocked(true);
                shed.setLocked(true);
                pool.setLocked(true);
                patio.setLocked(true);
                deck.setLocked(true);
                porch.setLocked(true);
                balcony.setLocked(true);
                roof.setLocked(true);
            }
        }    

        }
    public   Room getRecoveryRoom() {
        return recoveryRoom;
    }
        HashMap<String, Room> exits;
        private String name;
        private ArrayList<Item> inventory;
        ArrayList<NPC> npcs;
        private NPC npc;
        private String optional;
        private String description;
        private boolean locked;
        public String lockType;
        private GUI gui;
        
        public Room(Game game1) {
            this.game = game1;
            this.npcs = new ArrayList<>();
            this.exits = new HashMap<>();
            this.inventory = new ArrayList<>();

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
        public void removeExit(String string) {
            exits.remove(string);
        }
        public String[] getExits() {
            StringBuilder exitsString = new StringBuilder();
            for (String exit : exits.keySet()) {
                exitsString.append(exit).append(" ");
            }
            return exitsString.toString().split(" ");
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setExits(HashMap<String, Room> exits) {
            this.exits = exits;
        }
        public void addExits(HashMap<String, Room> exits) {
            this.exits.putAll(exits);
        }
        public void removeExits(String exitName) {
            this.exits.remove(exitName);
        }
        public void setExit(String exitName, Room room) {
            this.exits.put(exitName, room);
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
        public void removeItem(Item item) {
            this.getArrayInventory().remove(item);
        }
        public void addItem(Item item) {
            game = game.getGame();
            if(item.getType().equalsIgnoreCase("Container")) {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You put "+item.getName()+" in the " + this.name);
                this.getArrayInventory().add(item);
            } else {
                game.getGUI().printToJTextArea(game.getGUI().getjTextArea(),"You can't put that in the " + this.name);
            }

        }
        public NPC getNPCByName(String npcName) {
            for (NPC npcz : this.npcs) {
                if (npcz.getName().equals(npcName)) {
                    return npc;
                }
            }
            return npc;
        }
        public String[] getNPCs() {
            StringBuilder npcsString = new StringBuilder();
            for (NPC npcz : npcs) {
                npcsString.append(npcz.getName()).append(" ");
            }
            return npcsString.toString().split(" ");
        }
        public void addNPC(NPC npc) {
            this.npcs.add(npc);
        }
        public void removeNPC(NPC npc) {
            this.npcs.remove(npc);
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setOptional(String optional) {
            this.optional = optional;
        }
        public String getOptional() {
            return this.optional;
        }
        public String[] getInventory() {
            String[] items = new String[this.getArrayInventory().size()];
            for (int i = 0; i < this.getArrayInventory().size(); i++) {
                items[i] = this.getArrayInventory().get(i).getName();
            }
            return items;
        }
        public Room getLockedDoor() {
            for(Room exit : exits.values()) {
                if(exit.getLocked()) {
                    return exit;
                }
            }
            return null;
        }
        public void setLocked(boolean b) {
            this.locked = b;
        }
        public void setInventory(ArrayList<Item> inventory) {
            this.inventory = inventory;
        }
        public ArrayList<NPC> getNpcs() {
            return npcs;
        }
        public void setNpcs(ArrayList<NPC> npcs) {
            this.npcs = npcs;
        }
        public NPC getNpc() {
            return npc;
        }
        public void setNpc(NPC npc) {
            this.npc = npc;
        }
        public String getLockType() {
            return lockType;
        }
        public void setLockType(String lockType) {
            this.lockType = lockType;
        }
        public String getName() {
            return this.name;
        }
        public boolean getLocked() {
            return this.locked;
        }
        public String[] getItems() {
            String[] items = new String[this.getArrayInventory().size()];
            for (int i = 0; i < this.getArrayInventory().size(); i++) {
                items[i] = this.getArrayInventory().get(i).getName();
            }
            return items;
        }
        public  void buildRooms(Game game){
            recoveryRoom = new Room(game);
            game.player.setCurrentRoom(recoveryRoom);
            cubbies = new Room(game);
            dramaArea = new Room(game);
            changingRoom = new Room(game);
            floorPlay = new Room(game);
            quietArea = new Room(game);
            homeWorkArea = new Room(game);
            playHouse = new Room(game);
            treeHouse = new Room(game);
            storyBookVillage = new Room(game);
            pillowPile = new Room(game);
            snackArea = new Room(game);
            greenHall = new Room(game);
            blueHall = new Room(game);
            redHall = new Room(game);
            peddleToys = new Room(game);
            lemonaidStand = new Room(game);
            toolShed = new Room(game);
            TRSRoom = new Room(game);
            janitorialRoom = new Room(game);
            foyer = new Room(game);
            pantry = new Room(game); 
            recoveryRoom.lockType = "keycard";  
            kitchen = new Room(game);
            kitchen.lockType = "keycard";
            mainRoom = new Room(game);
            mainRoom.lockType = "keycard";

            dorms = new Room(game);
            dorms.lockType = "keycard";
            bathroom = new Room(game);
            bathroom.lockType = "keycard";
            hallway = new Room(game);
             hallway.lockType = "keycard";
            stairs = new Room(game);
             stairs.lockType = "metal Key";
            basement = new Room(game);
             basement.lockType = "metal Key";
            attic = new Room(game);
             attic.lockType = "metal Key";
            garage = new Room(game);
             garage.lockType = "metal Key";
            garden = new Room(game);
             garden.lockType = "blue card";
             
            driveway = new Room(game);
             driveway.lockType = "metal key";
            frontYard = new Room(game);
             frontYard.lockType = "metal key";
            backYard = new Room(game);
             backYard.lockType = "metal key";
            shed = new Room(game);
             shed.lockType = "metal key";
            pool = new Room(game);
             pool.lockType = "metal key";
            patio = new Room(game);
             patio.lockType = "metal key";
            deck = new Room(game);
             deck.lockType = "metal key";
            porch = new Room(game);
             porch.lockType = "metal key";
            balcony = new Room(game);
             balcony.lockType = "metal key";
            roof = new Room(game);
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
            recoveryRoom.npcs.add(mrsssagely);
            Quests fetchQuest = new Quests("Fetch Quest", "Bring me the trash item.", "fetch");
            Quests testQuest = new Quests("Test Quest", "Gain more than 100 experience or bring me the completed test item.", "test");
            Quests socialQuest = new Quests("Social Quest", "Increase your resilience to more than 80 or bring me the social item.", "social");
            fetchQuest.setQuestRequirement();
            testQuest.setQuestRequirement();
            socialQuest.setQuestRequirement();
            mrsssagely.setQuest(fetchQuest);
            mrsssagely.setQuestItem=fetchQuest.getQuestItem();
            drwhite.setQuest(testQuest);
            dawn.setQuest(socialQuest);
            recoveryRoom.npcs.add(busybeaver);
            kitchen.npcs.add(susy);
            mainRoom.npcs.add(dawn);
            mainRoom.npcs.add(fuzzy);
            hallway.npcs.add(jenny);
            dorms.npcs.add(taliber);
            bathroom.npcs.add(farah);
            attic.npcs.add(aureus);
            roof.npcs.add(drwhite);
            // initialise room exits
            recoveryRoom.addExit("Foyer", foyer);
            foyer.addExit("Recovery_Room", recoveryRoom);

            foyer.addExit("Kitchen", kitchen);
            kitchen.addExit("Foyer", foyer);

            foyer.addExit("Main_Hall", mainRoom);
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
        public  void generateItems() {
                Equipment uniformtop = new Equipment("Uniform Top", "A bague polo style top with a logo on the front of a happy beaver, This top is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 1, description);
                Equipment uniformbottom = new Equipment("Uniform Bottom", "A pair of khaki pants with a logo on the back pocket of a happy beaver, These bottoms are switch between a skirt and shorts with a large velcro strip along the inseem.", 3, description);
                Equipment uniformshoes = new Equipment("Uniform Shoes", "A pair of black shoes with a logo on the side of a happy beaver, These shoes are a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", 4, description);
                Item schoolbag = new Item("School Bag", "A large bag with a logo on the front of a happy beaver, This bag is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                Item lunchbox = new Item("Lunch Box", "A small box with a logo on the front of a happy beaver, This box is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container", game);
                Item waterbottle = new Item("Water Bottle", "A bottle with a logo on the front of a happy beaver, This bottle is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Bottle",game);
                Item pencilcase = new Item("Pencil Case", "A case with a logo on the front of a happy beaver, This case is a status symbol for the residents of the BusyBeavers Home For Wayward Rejuves.", "Container",game);
                Equipment diaper = new Equipment("Diaper", "A plain white diaper, this diaper is avalible in all sizes. In the dorms or changing rooms throughout the BusyBeavers Home For Wayward Rejuves you can find a diaper changing station with a variety of sizes.", 2, description);
                Item plasticknife = new Item("Plastic Knife", "A plastic knife, this knife is used for cutting food in the cafeteria. The knife is avalible in the cafeteria.", "tool",game); 
                    Item rubberspoon = new Item("Rubber Spoon", "A rubber spoon, this spoon is used for eating food in the cafeteria. The spoon is avalible in the cafeteria.", "tool",game);
                    Item schoolbook = new Item("A guide for the newly rejuvenated", "A book containig advice for those who are in their first cycle.", "Book",game);
                    Item schoolbook2 = new Item("Stroies from under the bus", "A book containig stories from the residents of the BusyBeavers Home For Wayward Rejuves.", "Book",game); 
                    Item schoolbook3 = new Item("Welcome to BusyBeavers!", "A book containig the history of the BusyBeavers Home For Wayward Rejuves.", "Book",game);
                    Item shinyPenny = new Item("Shiny Penny", "A shiny penny, this penny is used for making wishes in the fountain. The penny is a reward given for good behaviour", "money",game);
                    Item goldstar = new Item("Gold Star", "A gold star, The star is a reward given for small acts of kindness or good behaviour", "money",game);
                    Item trash = new Item("Trash", "A bit of trash, it is generated when you make a mess. You can throw it away in the trash can.", "trash",game);
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
                recoveryRoom.getArrayInventory().add(trashcan);
                recoveryRoom.getArrayInventory().add(diaper);
                recoveryRoom.getArrayInventory().add(schoolbag);
                recoveryRoom.getArrayInventory().add(lunchbox);
                recoveryRoom.getArrayInventory().add(waterbottle);
                recoveryRoom.getArrayInventory().add(pencilcase);
                kitchen.getArrayInventory().add(plasticknife);
                kitchen.getArrayInventory().add(rubberspoon);
                kitchen.getArrayInventory().add(trashcan);
                kitchen.getArrayInventory().add(trash);
                mainRoom.getArrayInventory().add(schoolbook);
                mainRoom.getArrayInventory().add(schoolbook2);
                mainRoom.getArrayInventory().add(schoolbook3);
                mainRoom.getArrayInventory().add(shinyPenny);
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
        public GUI getGui() {
            return gui;
        }
        public void setGui(GUI gui) {
            this.gui = gui;
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
        for (Room room : getRooms()) {
            if (room.getName().equalsIgnoreCase(string)) {
                return room;
            }
        }
        return null;
    }
    private ArrayList<Room> getRooms() {
        return new ArrayList<>(Arrays.asList(recoveryRoom, kitchen, mainRoom, dorms, bathroom, hallway, stairs, basement, attic, garage, garden, driveway, frontYard, backYard, shed, pool, patio, deck, porch, balcony, roof, cubbies, dramaArea, changingRoom, floorPlay, quietArea, homeWorkArea, playHouse, treeHouse, storyBookVillage, pillowPile, snackArea, greenHall, blueHall, redHall, peddleToys, lemonaidStand, toolShed, TRSRoom, janitorialRoom, foyer, pantry));
    }
}
