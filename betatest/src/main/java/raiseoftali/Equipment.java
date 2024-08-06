// File name: Equipment.java
package raiseoftali;

public class Equipment extends Item{

    private int slot;
    private String type;
    @Override
    public String getType() {
        return type;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }
    public Equipment(String name, String description, int slot, String type) {
        super(name, description, type, null);
        this.type = "equipment";
        this.slot = slot;
    }
    public void equip(Player player) {
        player.equip(this, this.slot);
    }
    public void unequip(Player player) {
        player.unequip(this, this.slot);
    }
    @Override
    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }

}
