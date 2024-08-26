package raiseoftali;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    private static Room room;
    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(fileName.concat(".txt"));
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
    }
        return sb.toString();
    }
    Player player;
    private  Clock clock;

    private  GUI gui;
    public Game() {
        Game game = this;
        this.player = new Player(game);
        this.player.setGame(game);
        this.gui = new GUI(game, player);
        this.clock = new Clock(game);
        Game.room = new Room(game,"room","Generic Room");
        room.buildRooms(game);
        this.player.setCurrentRoom(Room.recoveryRoom);
        room.generateItems();
    }
    public void nap() { //calls the nap method of the player
        player.nap();
    }
    public GUI getGUI() {
        return gui;
    }
    public void startGame() throws InterruptedException { //starts the game
        room.getListItems();
        gui.printToJTextPane(Game.readFile("intro1"));
        player.setUp();
        gui.printToJTextPane(Game.readFile("intro2"));

    }
    public void writeRoomFile(File fileName,Room room) throws IOException{ //delete the file if it exists and then writes the room to the file
        if(fileName.exists()){
            fileName.delete();
        }
        if(fileName.createNewFile()){
            FileWriter fw = new FileWriter(fileName);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("<html> <h1><center><Strong>"+room.getName()+"</h1></center></Stong><p style=\"font-size: 24;\">"+room.getDescription()+"</font><h2> Room Details </h2>"+room.getType()+"<br><br><Strong> Items in room</Strong><br>"+room.listItems()+"<br><Strong>People in room</Strong><br>"+room.listPeople()+"<br><br></html>");
            }
        }
    }
    public void moveTime(int time) { //moves the time by the given amount
        clock.moveTime(time);
    }
    public Game getGame() {
        return this;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public synchronized  GUI getGui() {
        return gui;
    }
    public void setGui(GUI gui) {
        this.gui = gui;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        Game.room = room;
    }
    public void setClock(Clock clock) {
        this.clock = clock;
        }
        public Clock getClock() {
            return clock;
        }
        public void endTurn(){
            this.moveTime(5);
            player.setHunger(player.getHunger() + -4);
            player.setThirst(player.getThirst() + -4);

        }

            
}

