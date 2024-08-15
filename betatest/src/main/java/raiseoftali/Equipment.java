// File name: Equipment.java
package raiseoftali;

public class Equipment extends Item{
    private int slot;
    private String type;
    private String className;
    private boolean equpped = false;

    public boolean isEqupped() {
        return equpped;
    }
    public void setEqupped(boolean equpped) {
        this.equpped = equpped;
    }
    public Equipment(String name, String description, int slot, String type) {
        super(name, description, type, null);
        this.type = "equipment";
        this.slot = slot;
        this.className = "none";
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }
    public void equip(Player player) {
        if(!this.isEqupped()){
            player.equip(this, this.slot);
            this.setEqupped(true);
        player.setSMILE(this.StatBoost(player));
    }else{
        this.unequip(player);
    }
    }
    @Override
    public void use(Player player, Game game) {
        this.equip(player);
    }
    public void unequip(Player player) {
        player.unequip(this, this.slot);
        this.setEqupped(false);
        player.setSMILE(this.statDebuff(player));    
    }
    @Override
    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }
    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nSlot: " + this.getSlot();
    }
    public int[] StatBoost(Player player) {
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
    public int[] statDebuff(Player player){
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

    void setClassName(String string) {
        this.className = string.toLowerCase();
    }
}
