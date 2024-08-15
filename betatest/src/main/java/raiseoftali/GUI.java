package raiseoftali;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class GUI extends JFrame {
private JTextArea jTextArea;
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
private final  Color periwinkle;
private JLabel statsLabel;
private final Object lock = new Object();
private final JButton carebutton;
private final JButton tantrumButton;
private final JButton takeButton;
private final JButton playButton;
private Game game;
public List<Item> items;
public GUI(Game game, Player player) {
    this.player = player;
    periwinkle = new Color(204, 204, 255);
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

    // Initialize jTextArea
    jTextArea = new JTextArea(30, 60);
    jTextArea.setLineWrap(true);
    jTextArea.setWrapStyleWord(true);
    jTextArea.setEditable(false);
    jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
    jTextArea.setBackground(Color.WHITE);
    jTextArea.setForeground(Color.BLACK);
    JScrollPane scrollPane = new JScrollPane(jTextArea);
    scrollPane.setPreferredSize(new Dimension(700, 400));
    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
@Override
protected void configureScrollBarColors() {
    thumbColor = Color.MAGENTA; 
    trackColor = Color.CYAN;
}
});
    add(scrollPane, BorderLayout.CENTER);
    jTextArea.setLineWrap(true);
    jTextArea.setWrapStyleWord(true);
    jTextArea.setEditable(false);
    
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
    tantrumButton = new JButton("Tantrum");
    tantrumButton.setFont(new Font("Arial", Font.BOLD, 16));
    playButton = new JButton("Play");
    playButton.setFont(new Font("Arial", Font.BOLD, 16));

    // Set button size
    Dimension buttonSize = new Dimension(100, 50);
    moveButton.setPreferredSize(buttonSize);
    dialogButton.setPreferredSize(buttonSize);
    inspectButton.setPreferredSize(buttonSize);
    inventoryButton.setPreferredSize(buttonSize);
    carebutton.setPreferredSize(buttonSize);
    tantrumButton.setPreferredSize(buttonSize);
    takeButton.setPreferredSize(buttonSize);
    playButton.setPreferredSize(buttonSize);

    // Add buttons to panel
    btnPanel.add(playButton);
    btnPanel.add(tantrumButton);
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
jTextArea.setBorder(new LineBorder(Color.BLACK, 2));
jTextFeild.setBorder(new LineBorder(Color.BLACK, 2));
moveButton.setBorder(new LineBorder(Color.BLACK, 2));
dialogButton.setBorder(new LineBorder(Color.BLACK, 2));
inspectButton.setBorder(new LineBorder(Color.BLACK, 2));
inventoryButton.setBorder(new LineBorder(Color.BLACK, 2));
carebutton.setBorder(new LineBorder(Color.BLACK, 2));
tantrumButton.setBorder(new LineBorder(Color.BLACK, 2));
takeButton.setBorder(new LineBorder(Color.BLACK, 2));
playButton.setBorder(new LineBorder(Color.BLACK, 2));

    // Add action listeners
playButton.addActionListener(e -> {
        String[] inventory = player.getCurrentRoom().getInventory();
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
            Item item = this.player.getCurrentRoom().getItemByName(selectedItem);
            if (item != null) {
                this.player.play(item);
                printToJTextArea(jTextArea, "Playwith action with " + selectedItem);
            } else {
                System.out.println("The selected item is null.");
                printToJTextArea(jTextArea, "The selected item is not available.");
            }
        }
    });
jTextFeild.addActionListener((ActionEvent e) -> {
    synchronized (lock) {
        lock.notify();
    }
    });
tantrumButton.addActionListener(e -> {
    player.addExperience(65);
printToJTextArea(jTextArea,Arrays.toString(player.getSMILE()));
printToJTextArea(jTextArea, player.getPerkName());
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
                printToJTextArea(jTextArea, "Nap action");
                game.moveTime(player.getNapTime());
            }
            case "Potty" -> {
                player.potty(player.currentRoom);
                printToJTextArea(jTextArea, "Potty action");
            }
            case "Eat/Drink" -> {
                player.eatDrink();
                printToJTextArea(jTextArea, "Eat/Drink action");
            }
            case "Reflect" -> {
                player.reflect();
                printToJTextArea(jTextArea, "Reflect action");
            }
        }
    }
    });
moveButton.addActionListener(new ActionListener() {
        private boolean moveSuccessful = false; 
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] exits = player.getCurrentRoom().getExits();
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
                player.setCurrentRoom(player.getCurrentRoom().getExit(selectedExit.replace(" ", "_")));
                game.getGUI().printToJTextArea(jTextArea, Game.readFile(selectedExit));
                moveSuccessful = true;
                if (!moveSuccessful) {
                    System.out.println("There is no exit in that direction.");
                }
            }
    }
    });
dialogButton.addActionListener(e -> {
        Room currentRoom = this.player.getCurrentRoom();
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
            NPC npc = player.getCurrentRoom().getNPCByName(selectedNPC.replace(" ", "_"));
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
        String[] inventory = this.player.getCurrentRoom().getInventory();
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
                    Item item = this.player.getCurrentRoom().getItemByName(selectedItem);
                    item.use(player,game);
                    printToJTextArea(game.getGUI().getjTextArea(), "You used the " + item.getName());
                }
                default -> {
                    Item item = this.player.getCurrentRoom().getItemByName(selectedItem);
                    item.use(player,game);
                    printToJTextArea(game.getGUI().getjTextArea(), "You interacted with the " + item.getName());
                }
                
            }
        }
    });
inventoryButton.addActionListener(e -> {
        
        String[] options = {"Use", "Drop","Throw Away","Put up"};
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
                item.use(player,game);
                }
                case 1 ->                     {
                    this.player.dropItem(item);
                    }
                case 2 ->{
                        this.player.throwAway(item);
                        
                    }
                    case 3 -> {
                        if (item != null) {
                            Item[] containers = this.player.getCurrentRoom().getContainers();
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
                                    this.player.putItem(item, this.player.getCurrentRoom().getContainerByName(selectedContainer));
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No containers available in the current room.");
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

            this.player.takeItem(this.player.currentRoom.getItemByName(selectedItem));
            boolean takeSuccessful = true;
            if (!takeSuccessful) {
                System.out.println("There is no item by that name.");
            }
        }
    });}
public Game getGame() {
    return game;
}
public String[] getItemChoices() {
        return itemChoices;
    }

public void setItemChoices(String[] itemChoices) {
        this.itemChoices = itemChoices;
    }
public String[] getNpcChoices() {
        return npcChoices;
    }
public void setNpcChoices(String[] npcChoices) {
        this.npcChoices = npcChoices;
    }
public String[] getRoomChoices() {
        return roomChoices;
    }
public void setRoomChoices(String[] roomChoices) {
        this.roomChoices = roomChoices;
    }
public JTextField getjTextFeild() {
        return jTextFeild;
    }
public JTextArea getjTextArea() {
        return jTextArea;
    }
public synchronized  String getInput(){
        String input = jTextFeild.getText();
        jTextFeild.setText("");
        return input;
    }
    public Item takeItemByName(Container container, String itemName) {
        Item item = container.getItemByName(itemName);
        if (item != null) {
            container.getItems().remove(item);
        }
        return item;
    }
public void waitForInput() {
    synchronized (lock) {
        try {
            lock.wait();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }


    this.setVisible(true);
}
public void printToJTextArea(JTextArea jTextArea2, String message) {
    player.updateStatus();
jTextArea.append(message + "\n");
jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
    
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

        public void setjTextArea(JTextArea jTextArea) {
            this.jTextArea = jTextArea;
        }
        public Player getPlayer() {
            return player;
        }
        public void setPlayer(Player player) {
            this.player = player;
        }
        public Color getPeriwinkle() {
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
        private String[] getNonNullItemNames() {

            List<Item> itemN = (List<Item>) player.currentRoom.getListItems();
            return itemN.stream()
                    .filter(Item::isTakeable)
                    .map(Item::getName)
                    .toArray(String[]::new);
        }
    }



