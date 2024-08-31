package raiseoftali;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


public class GUI extends JFrame {
private JTextPane jTextPane;
private Player player;
private final  JTextField jTextFeild;
private final  JPanel btnPanel;
private JPanel inputPanel;
private final  JButton inventoryButton;
private final JButton dialogButton;
private final JButton moveButton;
private final JButton inspectButton;
private final  JPanel statsPanel;
private String[] roomChoices, npcChoices, itemChoices;
private final  Color periwinkle, lightBlue, lightPink;
private JLabel statsLabel;
private final Object lock = new Object();
private final JButton carebutton;
private final JButton specialButton;
private final JButton takeButton;
private final JButton playButton;
private Game game;
public List<Item> items;
public GUI(Game game, Player player) {
    this.player = player;
    periwinkle = new Color(204, 204, 255);
    lightBlue = new Color(209, 202, 237);
    lightPink = new Color(250, 162, 162);
    this.setVisible(true);
    
    // Initialize JFrame 
    setTitle("Busy Beavers by Jackal Face games"); 
    setSize(1200, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);
    setResizable(false);

    // Initialize statsPanel and statsLabel
    statsPanel = new JPanel();
    statsPanel.setBackground(periwinkle);
    statsPanel.setPreferredSize(new Dimension(1200, 35));
    statsLabel = new JLabel();
    statsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    statsLabel.setText("Player: " + this.player.getName() + " | Experience: " + this.player.getExperience() + " | Shiny Pennies: " + this.player.getMoney() + " | Resilience: " + this.player.getResilience());
    statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
    statsLabel.setForeground(Color.BLACK);
    statsPanel.add(statsLabel);
    add(statsPanel, BorderLayout.NORTH);

    // Initialize textPanal
    inputPanel = new JPanel();
    inputPanel.setBackground(periwinkle);

    // Initialize buttonPanel
    btnPanel = new JPanel();
    btnPanel.setBackground(periwinkle);
    add(btnPanel, BorderLayout.SOUTH);

    // Initialize inputPanel
    inputPanel = new JPanel();

    // Initialize jTextPane
    jTextPane = new JTextPane();
    jTextPane.setContentType("text/html");
    jTextPane.setEditable(false);
    jTextPane.setFont(new Font("Monospaced", Font.PLAIN, 24));
    jTextPane.setBackground(lightBlue);
    jTextPane.setForeground(lightPink);
    jTextPane.addHyperlinkListener((HyperlinkEvent e) -> {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        if (e.getURL() != null) {
            String filePath = e.getURL().getPath();
            filePath = filePath.substring(0,filePath.indexOf(".txt"));
            String content = Game.readFile(filePath);
            showPopupWithContent(content);
        }
    }
    });
    JScrollPane scrollPane = new JScrollPane(jTextPane);
    scrollPane.setPreferredSize(new Dimension(700, 400));
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
@Override
protected void configureScrollBarColors() {
    thumbColor = Color.MAGENTA; 
    trackColor = Color.CYAN;
}
});
    add(scrollPane, BorderLayout.CENTER);
    jTextPane.setEditable(false);
    
    // Initialize jTextFeild
    jTextFeild = new JTextField(20);
    jTextFeild.setFont(new Font("Arial", Font.PLAIN, 16));

    // Create buttons
    takeButton = new JButton("Take");
    takeButton.setFont(new Font("Arial", Font.BOLD, 16));
    moveButton = new JButton("Move");
    moveButton.setFont(new Font("Arial", Font.BOLD, 16));
    dialogButton = new JButton("Dialog");
    dialogButton.setFont(new Font("Arial", Font.BOLD, 16));
    inspectButton = new JButton("Interact");
    inspectButton.setFont(new Font("Arial", Font.BOLD, 16));
    inventoryButton = new JButton("Inventory");
    inventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
    carebutton = new JButton("care");
    carebutton.setFont(new Font("Arial", Font.BOLD, 16));
    specialButton = new JButton("Special");
    specialButton.setFont(new Font("Arial", Font.BOLD, 16));
    playButton = new JButton("Play");
    playButton.setFont(new Font("Arial", Font.BOLD, 16));

    // Set button size
    Dimension buttonSize = new Dimension(100, 50);
    moveButton.setPreferredSize(buttonSize);
    dialogButton.setPreferredSize(buttonSize);
    inspectButton.setPreferredSize(buttonSize);
    inventoryButton.setPreferredSize(buttonSize);
    carebutton.setPreferredSize(buttonSize);
    specialButton.setPreferredSize(buttonSize);
    takeButton.setPreferredSize(buttonSize);
    playButton.setPreferredSize(buttonSize);

    // Add buttons to panel
    btnPanel.add(playButton);
    btnPanel.add(specialButton);
    btnPanel.add(moveButton);
    btnPanel.add(dialogButton);
    btnPanel.add(jTextFeild);
    btnPanel.add(inspectButton);
    btnPanel.add(inventoryButton);
    btnPanel.add(carebutton);
    btnPanel.add(takeButton);

 // Add borders to components
statsPanel.setBorder(new LineBorder(Color.BLACK, 2));
inputPanel.setBorder(new LineBorder(Color.BLACK, 2));
jTextPane.setBorder(new LineBorder(Color.BLACK, 2));
jTextFeild.setBorder(new LineBorder(Color.BLACK, 2));
moveButton.setBorder(new LineBorder(Color.BLACK, 2));
dialogButton.setBorder(new LineBorder(Color.BLACK, 2));
inspectButton.setBorder(new LineBorder(Color.BLACK, 2));
inventoryButton.setBorder(new LineBorder(Color.BLACK, 2));
carebutton.setBorder(new LineBorder(Color.BLACK, 2));
specialButton.setBorder(new LineBorder(Color.BLACK, 2));
takeButton.setBorder(new LineBorder(Color.BLACK, 2));
playButton.setBorder(new LineBorder(Color.BLACK, 2));

    // Add action listeners
playButton.addActionListener(e -> {
        String[] inventory = Player.getCurrentRoom().getInventory();
        ImageIcon playIcon = new ImageIcon("play.png");
        String selectedItem = (String) JOptionPane.showInputDialog(
            null,
            "What would you like to play with?",
            "Play",
            JOptionPane.QUESTION_MESSAGE,
            playIcon,
            inventory,
            inventory[0]
        );
        if (selectedItem != null) {
            Item item = Player.getCurrentRoom().getItemByName(selectedItem);
            if (item != null) {
                this.player.playedWith(item);
                printToJTextPane("Playwith action with " + selectedItem);
            } else {
                System.out.println("The selected item is null.");
                printToJTextPane("The selected item is not available.");
            }
        }
    });
jTextFeild.addActionListener((ActionEvent e) -> {
    synchronized (lock) {
        lock.notify();
    }
    });
specialButton.addActionListener(e -> {
    for(String ss : player.getCRAFT()){
        if(ss.isEmpty()){

            } else {
                switch(ss){
                    case "Ring Leader" -> {
                        player.useSpecial("Ring Leader");
                    }
                    case "Care Taker" -> {
                        player.useSpecial("Care Taker");
                    }
                    case "innovator" -> {
                        player.useSpecial("Innovator");
                    }
                    case "Prefect" -> {
                        player.useSpecial("Prefect");
                    }
                    case "Leader" -> {
                        player.useSpecial("Leader");
                    }
                }
        }

    }
});
carebutton.addActionListener((ActionEvent e) -> {
    String[] care = {"Nap","Potty","Tantrum","Eat/Drink","Reflect"};
    ImageIcon moveIcon = new ImageIcon("Care.png");
    
    String selectedCare = (String) JOptionPane.showInputDialog(
            null,
            "Choose a care action:",
            "Available Care Actions",
            JOptionPane.QUESTION_MESSAGE,
            moveIcon,
            care, 
            care[0]
            
    );
    
    if (selectedCare != null) {
        // Handle the selected action
        switch (selectedCare) {
            case "Nap" -> {
                player.nap();
                printToJTextPane( "Nap action");
                game.moveTime(player.getNapTime());
            }
            case "Potty" -> {
                player.potty(Player.currentRoom);
                printToJTextPane( "Potty action");
            }
            case "Eat/Drink" -> {
                try {
                    player.eatDrink();
                } catch (FileNotFoundException e1) {
                }
                printToJTextPane( "Eat/Drink action");
            }
            case "Reflect" -> {
                player.reflect();
                printToJTextPane( "Reflect action");
            }
        }
    }
    });
moveButton.addActionListener(new ActionListener() {
        private boolean moveSuccessful = false; 
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] exits = Player.getCurrentRoom().getExits();
            for (int i = 0; i < exits.length; i++) {
                exits[i] = exits[i].replace("_"," ");
                
            }
            ImageIcon moveIcon = new ImageIcon("Move.png");
            System.out.println("Available exits: " + Arrays.toString(exits));
            
            String selectedExit = (String) JOptionPane.showInputDialog(
                null, 
                "Choose an exit:", 
                "Available Exits", 
                JOptionPane.QUESTION_MESSAGE, 
                moveIcon, 
                exits, 
                exits[0]
                
            );
            if (selectedExit != null) {
                // Handle the selected exit
                player.setCurrentRoom(Player.getCurrentRoom().getExit(selectedExit.replace(" ", "_")));
                File file = new File(Player.getCurrentRoom().getName().concat(".txt"));
                try {
                    Player.getCurrentRoom().initializeRoomFiles();
                    game.writeRoomFile(file, Player.getCurrentRoom());
                } catch (IOException e1) {
                }
                game.getGUI().printToJTextPane( Game.readFile(selectedExit));
                moveSuccessful = true;
                if (!moveSuccessful) {
                    System.out.println("There is no exit in that direction.");
                }
            }
    }
    });
dialogButton.addActionListener(e -> {
        Room currentRoom = Player.getCurrentRoom();
        String[] npcs = currentRoom.getNPCs();
        for (int i = 0; i < npcs.length; i++) {
            npcs[i] = npcs[i].replace("_"," ");
            
        }
        ImageIcon dialogIcon = new ImageIcon("Dialog.png");
        System.out.println("Available NPCs: " + Arrays.toString(npcs));
        
        String selectedNPC = (String) JOptionPane.showInputDialog(
            null, 
            "Who you talking to?", 
            "Available NPCs", 
            JOptionPane.QUESTION_MESSAGE, 
            dialogIcon, 
            npcs, 
            npcs[0]
        );
        if (selectedNPC != null) {
            if(selectedNPC.equalsIgnoreCase("empty")){
                JOptionPane.showMessageDialog(null, "There are no NPCs in the room.");
                return;
            }
            NPC npc = Player.getCurrentRoom().getNPCByName(selectedNPC.replace(" ", "_"));
            System.out.println("Selected npc: " + selectedNPC);
            npc.dialog(player);


            boolean dialogSuccessful = true;
            if (!dialogSuccessful) {
                System.out.println("There is no one by that name.");
            }
            System.out.println("Selected npc: " + selectedNPC);
        }
    });
inspectButton.addActionListener(e -> {
        String[] inventory = Player.getCurrentRoom().getInventory();
        ImageIcon inspectIcon = new ImageIcon("Interact.png");
        String selectedItem = (String) JOptionPane.showInputDialog(
            null,
            "Choose an item:",
             "Interact",
              JOptionPane.QUESTION_MESSAGE,
               inspectIcon,
                inventory,
                inventory[0]
                );
        if (selectedItem != null) {
            switch(selectedItem) {
                case "Snack Dispenser", "Art Supplies" -> {
                    Item item = Player.getCurrentRoom().getItemByName(selectedItem);
                try {
                    item.use(player,game);
                } catch (FileNotFoundException ex) {
                }
                    printToJTextPane( "You used the " + item.getName());
                }
                default -> {
                    Item item = Player.getCurrentRoom().getItemByName(selectedItem);
                    try {
                        item.use(player,game);
                    } catch (FileNotFoundException e1) {
                    }
                    printToJTextPane( "You interacted with the " + item.getName());
                }
                
            }
        }
    });
inventoryButton.addActionListener(e -> {
        
        String[] options = {"Use", "Drop","Throw Away","Put up","give"};
        ImageIcon inventoryIcon = new ImageIcon("Inventory.png");
        String selectedItem = (String) JOptionPane.showInputDialog(
            null,
            "What's in the bag?",
             "Inventory",
              JOptionPane.QUESTION_MESSAGE,
               inventoryIcon,
                this.player.getInventory(),
                this.player.getInventory()[0]
                );
        if (selectedItem != null) {
            if(selectedItem.equalsIgnoreCase("Empty")){
                JOptionPane.showMessageDialog(null, "There are no items in your inventory.");
            } else{
            int action = JOptionPane.showOptionDialog(
                null,
                "What would you like to do with " + selectedItem + "?",
                "Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );
            
            Item item = this.player.getItemByName(selectedItem);
            switch (action) {
                case 0 ->{
                try {
                    item.use(player,game);
                } catch (FileNotFoundException e1) {
                }
                }
                case 1 ->                     {
                    this.player.dropItem(item);
                    }
                case 2 ->{
                        this.player.throwAway(item);
                        
                    }
                    case 3 -> {
                        if (item != null) {
                            Item[] containers = Player.getCurrentRoom().getContainers();
                            if (containers != null && containers.length > 0) {
                                String[] containerNames = Arrays.stream(containers)
                                                                .map(Item::getName)
                                                                .toArray(String[]::new);
                                JOptionPane.showMessageDialog(null, 
                                    "Put it where? " + item.getName()
                                );
                                String selectedContainer = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Where would you like to put " + item.getName() + "?",
                                    "Containers",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    containerNames,
                                    containerNames[0]
                                );
                                if (selectedContainer != null) {
                                    this.player.putItem(item, Player.getCurrentRoom().getContainerByName(selectedContainer));
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No containers available in the current room.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Item not found.");
                        }
                    }
                    case 4 -> {
                        if (item != null) {
                            String[] npcs = Player.getCurrentRoom().getNPCs();
                            if (npcs != null && npcs.length > 0) {
                                String[] npcNames = Arrays.stream(npcs)
                                                .map(n -> n.replace("_", " "))
                                                .toArray(String[]::new);
                                JOptionPane.showMessageDialog(null, 
                                    "Give it to who? " + item.getName()
                                );
                                String selectedNPC = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Who would you like to give " + item.getName() + " to?",
                                    "NPCs",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    npcNames,
                                    npcNames[0]
                                );
                                if (selectedNPC != null) {
                                    NPC npc = Player.getCurrentRoom().getNPCByName(selectedNPC.replace(" ", "_"));
                                    if (npc != null) {
                                        this.player.giveItem(item, npc);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error: NPC not found.");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No NPCs available in the current room.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Item not found.");
                        }
                    }
            }
        }
    }
    });


takeButton.addActionListener(e -> {
        String[] itemarray = getNonNullItemNames();
        if (itemarray.length == 0) {
            JOptionPane.showMessageDialog(null, "There are no items to take.");
            return;
        }
        ImageIcon takeIcon = new ImageIcon("Take.png");
        System.out.println("Available items: " + Arrays.toString(itemarray));
        
        String selectedItem = (String) JOptionPane.showInputDialog(
            null, 
            "What would you like to take?", 
            "Available Items", 
            JOptionPane.QUESTION_MESSAGE, 
            takeIcon, 
            itemarray, 
            itemarray[0]
        );
        
        if (selectedItem != null) {
            // Handle the selected exit
            System.out.println("Selected item: " + selectedItem);

            this.player.takeItem(Player.currentRoom.getItemByName(selectedItem));
            boolean takeSuccessful = true;
            if (!takeSuccessful) {
                System.out.println("There is no item by that name.");
            }
        }
    });}
public Game getGame() {
    return game;
}
public String[] getItemChoices() { //returns the item choices for the dialog box
        return itemChoices;
    }

public void setItemChoices(String[] itemChoices) { //sets the item choices for the dialog box
        this.itemChoices = itemChoices;
    }
public String[] getNpcChoices() { //returns the npc choices for the dialog box
        return npcChoices;
    }
public void setNpcChoices(String[] npcChoices) { //sets the npc choices for the dialog box
        this.npcChoices = npcChoices;
    }
public String[] getRoomChoices() { //returns the room choices for the dialog box
        return roomChoices;
    }
public void setRoomChoices(String[] roomChoices) { //sets the room choices for the dialog box
        this.roomChoices = roomChoices;
    }
public JTextField getjTextFeild() { 
        return jTextFeild;
    }
    private void showPopupWithContent(String content) {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(content);
        textPane.setEditable(false);
    
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 400));
    
        JDialog dialog = new JDialog((Frame) null, "File Content", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true);
    }
public JTextPane getjTextPane() {
        return jTextPane;
    }
public synchronized  String getInput(){ //returns the input from the text field and clears the text field
        String input = jTextFeild.getText();
        jTextFeild.setText("");
        return input;
    }
    public Item takeItemByName(Container container, String itemName) { //takes an item from the container by name
        Item item = container.getItemByName(itemName);
        if (item != null) {
            container.getItems().remove(item);
        }
        return item;
    }
public void waitForInput() { //waits for the user to input something
    synchronized (lock) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }


    this.setVisible(true);
}
public void printToJTextPane(String newText) { //converts the text to html and prints it to the text pane
    HTMLEditorKit editorKit = (HTMLEditorKit) jTextPane.getEditorKit();
    HTMLDocument doc = (HTMLDocument) jTextPane.getDocument();

    try {

        editorKit.insertHTML(doc, doc.getLength(), "<p style=\"font-size: 16;\">"+ newText.concat("<br>"), 0, 0, null);
    } catch (BadLocationException | IOException e) {
    }
    jTextPane.setCaretPosition(doc.getLength());
}

     public JLabel getStatsLabel() {
        return statsLabel;
    }
    public void setStatsLabel(JLabel statsLabel) {
        this.statsLabel = statsLabel;
    }
    public String getEquips() {
            String equips = "";
            for(Equipment equipment : this.player.getEquipment()) {
                if(equipment != null) {
                    equips += equipment.getName() + " ";
                }
    
            }
            return equips;
        }

        public void setjTextPane(JTextPane jTextPane) {
            this.jTextPane = jTextPane;
        }
        public Player getPlayer() {
            return player;
        }
        public void setPlayer(Player player) {
            this.player = player;
        }
        public Color getPeriwinkle() { //returns the color periwinkle default color for the GUI
            return periwinkle;
        }
        public Object getLock() {
            return lock;
        }
        public List<Item> getItems() {
            return items;
        }
        public void setItems(List<Item> items) {
            this.items = items;
        }
        private String[] getNonNullItemNames() { //returns the names of the items that are not null
            List<Item> itemN = (List<Item>) Player.currentRoom.getListItems();
            return itemN.stream()
                    .filter(Item::isTakeable)
                    .map(Item::getName)
                    .toArray(String[]::new);
        }
    }



