package raiseoftali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    Player player;
    private  Clock clock;
    private  GUI gui;
    private  Room room;
    public void setClock(Clock clock) {
        this.clock = clock;
    }
    public Clock getClock() {
        return clock;
    }
    public Game() {
        Game game = this;
        this.player = new Player(game);
        player.setGame(game);
        this.gui = new GUI(game, player);
        this.clock = new Clock(game);
        this.room = new Room(game);
        room.buildRooms(game);
        room.generateItems();

    }
    public void nap() {
        player.nap();
    }
    public void dialog(String selectedNPC) {
        player.dialog(selectedNPC);
    }
    public GUI getGUI() {
        return gui;
    }
    public void startGame() {
        readFile("intro1");
        player.setUp();
        readFile("intro2");
        player.setCurrentRoom(room.getRoomByName("bedroom"));

    }
    public String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(fileName.concat(".txt"));
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
    }
        return sb.toString();
    }
    public void moveTime(int time) {
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
    public GUI getGui() {
        return gui;
    }
    public void setGui(GUI gui) {
        this.gui = gui;
    }
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    }
    
