package raiseoftali;


public class Main implements Runnable {
    public static void main(String[] args) {
        System.out.println("Main thread started");
        new Thread(new Main()).start();
    }
    @Override
    public void run() {
        System.out.println("New thread running");
        try {
            Game game = new Game();
            game.startGame();
            System.out.println("Game started successfully");
        } catch (InterruptedException e) {
            System.err.println("Exception in thread: " + e.getMessage());
        }
    }
}