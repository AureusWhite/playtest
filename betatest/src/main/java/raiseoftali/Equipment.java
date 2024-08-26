// File name: Equipment.java
package raiseoftali;

public class Equipment extends Item{
    private int slot;
    private String type;
    private String className;
    private boolean equpped = false;

    public boolean isEqupped() { //returns true if the equipment is equipped
        return equpped;
    }
    public void setEqupped(boolean equpped) { //sets the equipment to equipped or not
        this.equpped = equpped;
    }
    public Equipment(String name, String description, int slot, String type) {
        super(name, description, type, null);
        this.type = "equipment";
        this.slot = slot;
        this.className = "none";
    }
    @Override
    public String getType() { //returns the type of the item
        return type;
    }
    @Override
    public void setType(String type) { //sets the type of the item
        this.type = type;
    }
    public void equip(Player player) { //equips the item or unequips it if it is already equipped
        if(!this.isEqupped()){
            player.equip(this, this.slot);
            this.setEqupped(true);
        player.setSMILE(this.StatBoost(player));
    }else{
        this.unequip(player);
    }
    }
    @Override
    public void use(Player player, Game game) { //calls the equip method when the item is used by the player.
        this.equip(player);
    }
    public void unequip(Player player) {
        player.unequip(this, this.slot);
        this.setEqupped(false);
        player.setSMILE(this.statDebuff(player));    
    }
    @Override
    public int getSlot() { //returns the slot of the equipment
        return slot;
    }
    public void setSlot(int slot) { //sets the slot of the equipment
        this.slot = slot;
    }
    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nSlot: " + this.getSlot();
    }
    public int[] StatBoost(Player player) { //returns the stat boost of the equipment based on the class of the player
        int[] stats = new int[5];
        switch (this.className.toLowerCase()) {
            case "performer" ->{
                stats[0] = player.getSMILE()[0] + 2;
                stats[1] = player.getSMILE()[1] + 2;
                stats[2] = player.getSMILE()[2] + 2;
                stats[3] = player.getSMILE()[3] + 0;
                stats[4] = player.getSMILE()[4] + 0;
            }
            case "prefect" ->{
                stats[0] = player.getSMILE()[0] + 2;
                stats[1] = player.getSMILE()[1] + 0;
                stats[2] = player.getSMILE()[2] + 2;
                stats[3] = player.getSMILE()[3] + 0;
                stats[4] = player.getSMILE()[4] + 2;
            }
            case "teachers" ->{
                stats[0] = player.getSMILE()[0] + 0;
                stats[1] = player.getSMILE()[1] + 2;
                stats[2] = player.getSMILE()[2] + 2;
                stats[3] =  player.getSMILE()[3] + 2;
                stats[4] = player.getSMILE()[4] + 0;
            }
            case "bookworm" ->{
                stats[0] = player.getSMILE()[0] + 2;
                stats[1] = player.getSMILE()[1] + 0;
                stats[2] = player.getSMILE()[2] + 2;
                stats[3] = player.getSMILE()[3] + 2;
                stats[4] = player.getSMILE()[4] + 0;
            }
            case "explorer" ->{
                stats[0] = player.getSMILE()[0] + 2;
                stats[1] = player.getSMILE()[1] + 2;
                stats[2] = player.getSMILE()[2] + 0;
                stats[3] = player.getSMILE()[3] + 2;
                stats[4] = player.getSMILE()[4] + 0;
            }
            case "mediator" ->{
                stats[0] = player.getSMILE()[0] + 0;
                stats[1] = player.getSMILE()[1] + 2;
                stats[2] = player.getSMILE()[2] + 2;
                stats[3] = player.getSMILE()[3] + 0;
                stats[4] = player.getSMILE()[4] + 2;
            }
            default ->{
                stats[0] = player.getSMILE()[0];
                stats[1] = player.getSMILE()[1];
                stats[2] = player.getSMILE()[2];
                stats[3] = player.getSMILE()[3];
                stats[4] = player.getSMILE()[4];
            }
        }
        return  stats;
    }
    public int[] statDebuff(Player player){ //undoes the stat boost of the equipment based on the class of the player
        int[] stats = new int[5];
        switch (this.className.toLowerCase()) {
            case "performer" ->{
                stats[0] = player.getSMILE()[0] - 2;
                stats[1] = player.getSMILE()[1] - 2;
                stats[2] = player.getSMILE()[2] - 2;
                stats[3] = player.getSMILE()[3] - 0;
                stats[4] = player.getSMILE()[4] - 0;
            }
            case "prefect" ->{
                stats[0] = player.getSMILE()[0] - 2;
                stats[1] = player.getSMILE()[1] - 0;
                stats[2] = player.getSMILE()[2] - 2;
                stats[3] = player.getSMILE()[3] - 0;
                stats[4] = player.getSMILE()[4] - 2;
            }
            case "teachers" ->{
                stats[0] = player.getSMILE()[0] - 0;
                stats[1] = player.getSMILE()[1] - 2;
                stats[2] = player.getSMILE()[2] - 2;
                stats[3] =  player.getSMILE()[3] - 2;
                stats[4] = player.getSMILE()[4] - 0;
            }
            case "bookworm" ->{
                stats[0] = player.getSMILE()[0] - 2;
                stats[1] = player.getSMILE()[1] - 0;
                stats[2] = player.getSMILE()[2] - 2;
                stats[3] = player.getSMILE()[3] - 2;
                stats[4] = player.getSMILE()[4] - 0;
            }
            case "explorer" ->{
                stats[0] = player.getSMILE()[0] - 2;
                stats[1] = player.getSMILE()[1] - 2;
                stats[2] = player.getSMILE()[2] - 0;
                stats[3] = player.getSMILE()[3] - 2;
                stats[4] = player.getSMILE()[4] - 0;
            }
            case "mediator" ->{
                stats[0] = player.getSMILE()[0] - 0;
                stats[1] = player.getSMILE()[1] - 2;
                stats[2] = player.getSMILE()[2] - 2;
                stats[3] = player.getSMILE()[3] - 0;
                stats[4] = player.getSMILE()[4] - 2;
            }
            default ->{
                stats[0] = 0;
                stats[1] = 0;
                stats[2] = 0;
                stats[3] = 0;
                stats[4] = 0;
            }
        }
        return  stats;
    }

    void setClassName(String string) { //sets the class name of the equipment.
        this.className = string.toLowerCase();
    }
}
