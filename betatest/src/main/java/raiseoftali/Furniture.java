package raiseoftali;

public class Furniture extends Item {
    
        public Furniture(String name, String description, String type, Game game) {
            super(name, description, type, game);
        }
    
        @Override
        public void use(Player player) {
            if(this.getType().equalsIgnoreCase("toy")) {
                player.setExperience(player.getExperience() + 1);
                player.playedWith(this);
            }else if(this.getType().equalsIgnoreCase("bed")) {
                player.nap();
                
            }else if(this.getType().equalsIgnoreCase("seat")) {
                player.setHunger(player.getHunger() + 10);
                if(player.getHunger() > 100) {
                    player.setHunger(100);
                }
            }
        }

}
