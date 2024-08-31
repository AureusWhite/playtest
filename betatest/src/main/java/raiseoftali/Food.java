package raiseoftali;

public class Food extends Item{

    public Food(String name, String description, String type, Game game) {
        super(name, description, type, game);
        
    }
    @Override
    public void use(Player player, Game game) { //increases the hunger or thirst of the player based on the type of the food
        if(this.getType().equalsIgnoreCase("drink")) {
            player.setThirst(player.getThirst() + 10);
            if(player.getThirst() > 100) {
                player.bladderFull(player.getThirst() - 100);
                player.setThirst(100);
                
            }
        }else if(this.getType().equalsIgnoreCase("food")) {
            player.setHunger(player.getHunger() + 10);
            if(player.getHunger() > 100) {
                player.setHunger(100);
            }
        }
    }

}
