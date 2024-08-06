package raiseoftali;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
private JButton napbutton;
private JButton tantrumButton;
private JButton takeButton;
private JButton playButton;
private Game game;
public Game getGame() {
    return game;
}
public void setGame(Game game) {
    this.game = game;
}
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
    inspectButton = new JButton("Inspect");
    inspectButton.setFont(new Font("Arial", Font.BOLD, 16));
    inventoryButton = new JButton("Inventory");
    inventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
    napbutton = new JButton("Nap");
    napbutton.setFont(new Font("Arial", Font.BOLD, 16));
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
    napbutton.setPreferredSize(buttonSize);
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
    btnPanel.add(napbutton);
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
napbutton.setBorder(new LineBorder(Color.BLACK, 2));
tantrumButton.setBorder(new LineBorder(Color.BLACK, 2));
takeButton.setBorder(new LineBorder(Color.BLACK, 2));
playButton.setBorder(new LineBorder(Color.BLACK, 2));

    // Add action listeners
playButton.addActionListener(e -> {
        String[] inventory = this.player.getCurrentRoom().getInventory();
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
this.player.tantrum();
printToJTextArea(jTextArea, "Tantrum action");
});
napbutton.addActionListener(e -> {
game.nap();
printToJTextArea(jTextArea, "Nap action");
});
moveButton.addActionListener(new ActionListener() {
        private boolean moveSuccessful = false; 
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] exits = player.getCurrentRoom().getExits();
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
                System.out.println("Selected exit: " + selectedExit);
                player.setCurrentRoom(player.getCurrentRoom().getExit(selectedExit));
                game.getGUI().printToJTextArea(jTextArea, game.readFile(selectedExit));
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
            // Handle the selected exit
            System.out.println("Selected exit: " + selectedNPC);
            game.dialog(selectedNPC);
            boolean dialogSuccessful = true;
            if (!dialogSuccessful) {
                System.out.println("There is no one by that name.");
            }
        }
    });
inspectButton.addActionListener(e -> {
        String[] inventory = this.player.getInventory();
        ImageIcon inspectIcon = new ImageIcon("Inspect.png");
        String selectedItem = (String) JOptionPane.showInputDialog(
            null,
            "Choose an item:",
             "Inspect",
              JOptionPane.QUESTION_MESSAGE,
               inspectIcon,
                inventory,
                inventory[0]
                );
        if (selectedItem != null) {
            this.player.inspect(selectedItem);
            printToJTextArea(jTextArea, "Inspect action with " + selectedItem);
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
            switch (action) {
                case 0 ->                     {
                        Item item = this.player.getItemByName(selectedItem);
                        switch (item.getType()) {
                            case "equipment" -> {
                                Equipment equipment = (Equipment) item;
                                this.player.equip(equipment, equipment.getSlot());
                                printToJTextArea(game.getGUI().getjTextArea(),"You equipped the " + item.getName());
                                printToJTextArea(game.getGUI().getjTextArea(), getEquips());
                            }
                            case "toy" -> this.player.play(item);
                            default -> this.player.useItem(item);
                        }                          }
                case 1 ->                     {
                        this.player.inventory(selectedItem);
                        Item item = this.player.getItemByName(selectedItem);
                        this.player.dropItem(item);
                    }
                case 2 ->{
                        this.jTextArea.append("This happened!");
                        this.player.inventory(selectedItem);
                        Item item = this.player.getItemByName(selectedItem);
                        this.player.throwAway(item, this.player.getCurrentRoom().getItemByName("Trash Can"));
                        this.jTextArea.append("This happened!");
                        
                    }
                    case 3 -> {
                        this.player.inventory(selectedItem);
                        Item item = this.player.getItemByName(selectedItem);
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
    });


takeButton.addActionListener(e -> {
        Room currentRoom = this.player.getCurrentRoom();
        String[] items = currentRoom.getItems();
        ImageIcon takeIcon = new ImageIcon("Take.png");
        System.out.println("Available items: " + Arrays.toString(items));
        
        String selectedItem = (String) JOptionPane.showInputDialog(
            null, 
            "What would you like to take?", 
            "Available Items", 
            JOptionPane.QUESTION_MESSAGE, 
            takeIcon, 
            items, 
            items[0]
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
public String[] getItemChoices() {
        return itemChoices;
    }
public void updateStatus(Player player) {
        statsLabel.setText("Player: " + this.player.getName() + " | Experience: " + this.player.getExperience() + " | Shiny Pennies: " + this.player.getMoney() + " | Resilience: " + this.player.getResilience());
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
public String getInput(){
        String input = jTextFeild.getText();
        jTextFeild.setText("");
        return input;
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
jTextArea.append(message + "\n");
jTextArea.setCaretPosition(jTextArea.getDocument().getLength());
    
}
     public JLabel getStatsLabel() {
        return statsLabel;
    }
    public void setStatsLabel(JLabel statsLabel) {
        this.statsLabel = statsLabel;
    }
    public JButton getNapbutton() {
        return napbutton;
    }
    public void setNapbutton(JButton napbutton) {
        this.napbutton = napbutton;
    }
    public JButton getTantrumButton() {
        return tantrumButton;
    }
    public void setTantrumButton(JButton tantrumButton) {
        this.tantrumButton = tantrumButton;
    }
    public JButton getTakeButton() {
        return takeButton;
    }
    public void setTakeButton(JButton takeButton) {
        this.takeButton = takeButton;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }
    private String getEquips() {
            String equips = "";
            for(Equipment equipment : this.player.getEquipment()) {
                if(equipment != null) {
                    equips += equipment.getName() + " ";
                }
    
            }
            return equips;
        }

}   


