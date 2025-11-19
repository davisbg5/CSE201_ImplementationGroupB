import java.util.*;

public class Game {
    private Player player = new Player();
    private Room currentRoom;
    private Scanner input = new Scanner(System.in);

    public void startGame() {
        showIntro();
        currentRoom = new LivingRoom();
        narrativePause("The case begins. You step into the first room...");
        playRoom(currentRoom);
    }

    private void showIntro() {
        System.out.println("===========================================");
        System.out.println("          🎮  ECHOES OF INNOCENCE");
        System.out.println("===========================================");
        System.out.println("A cold night. A murder unsolved. A man on trial for a crime he didn’t commit.");
        System.out.println("You are the detective — the only one who still believes in his innocence.");
        System.out.println("Find the truth. Or lose it forever.");
        System.out.println("-------------------------------------------");
        System.out.println("Commands: inspect, move, inventory, help");
        System.out.println("===========================================");
        System.out.println("Press ENTER to begin your investigation...");
        input.nextLine();
    }

    private void playRoom(Room room) {
        player.resetInspections();
        room.enter();

        while (player.getInspectionsLeft() > 0) {
            System.out.print("\nCommand (inspect/move/inventory/help): ");
            String cmd = input.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "inspect":
                    System.out.print("Inspect what? ");
                    String objName = input.nextLine();
                    Item item = room.getObject(objName);
                    if (item == null) {
                        System.out.println("No such item here.");
                        break;
                    }
                    if (item.isLocked()) {
                        if (player.getInventory().hasItem(item.getKeyRequired())) {
                            narrativePause("You recall the small key you found earlier...");
                            item.unlock(player.getInventory().getItem(item.getKeyRequired()));
                            item.inspect(player);
                        } else {
                            System.out.println("It’s locked. You might need a key.");
                        }
                    } else {
                        item.inspect(player);
                    }
                    player.getInventory().addItem(item);
                    player.useInspection();
                    break;

                case "inventory":
                    player.getInventory().showInventory();
                    break;

                case "help":
                    System.out.println("Commands: inspect, move, inventory, help");
                    break;

                case "move":
                    System.out.println("You take one last look around before moving on...");
                    nextRoom(room);
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }

        System.out.println("\nYou’ve used both inspections. Time to move on...");
        nextRoom(room);
    }

    private void nextRoom(Room room) {
      String next = room.getNextRoomName();
      if (next.equals("Bedroom")) {
          narrativePause("\nThe evidence weighs on your mind. You take a deep breath and step into the bedroom...");
          playRoom(new Bedroom());
      } else if (next.equals("Kitchen")) {
          narrativePause("\nEvery clue points toward the kitchen. The truth feels close enough to touch...");
          playRoom(new Kitchen());
      } else {
          narrativePause("\nYou hear movement outside. A figure in the rain — the chase begins...");
          chaseEvent();
      }
  }


    private void chaseEvent() {
        narrativePause("\nThrough the kitchen window, a shadow moves quickly — someone’s watching you...");
        System.out.print("Do you want to chase them? (yes/no): ");
        String choice = input.nextLine().toLowerCase();

        Random rand = new Random();
        if (choice.equals("yes")) {
            if (rand.nextBoolean()) {
                System.out.println("\nYou sprint into the rain-soaked alley. Your shoes splash through puddles.");
                narrativePause("The figure slips away, but something clatters to the ground...");
                System.out.println("It’s the murder weapon — wrapped in the real killer’s fingerprints!");
                player.adjustInnocence(+20);
            } else {
                System.out.println("\nYou chase into the dark, but they vanish into the night.");
                narrativePause("In the chaos, you drop a vital piece of evidence...");
                player.adjustInnocence(-10);
                player.getInventory().removeRandomItem();
            }
        } else {
            System.out.println("You let the figure disappear. Maybe some truths are better left in the dark.");
        }
        endGame();
    }

    private void endGame() {
        narrativePause("\n------------------------------------------");
        System.out.println("⚖️  FINAL CASE PRESENTATION");
        System.out.println("------------------------------------------");
        narrativePause("The courtroom is silent. Your client waits, eyes full of hope and fear...");
        narrativePause("The judge reviews the evidence. Time slows.");
        if (player.getInnocenceMeter() >= 80) {
            System.out.println("\n✅ Verdict: NOT GUILTY.");
            System.out.println("The room erupts in gasps. Your client collapses in relief — free at last.");
            narrativePause("Justice wins. For now.");
        } else {
            System.out.println("\n❌ Verdict: GUILTY.");
            narrativePause("The gavel hits. Your client’s eyes meet yours — full of disbelief.");
            System.out.println("Somewhere deep down, you know: the truth got away.");
        }
        System.out.println("\nFinal Innocence: " + player.getInnocenceMeter() + "%");
        System.out.println("\nCase Closed.");
    }

    private void narrativePause(String text) {
        System.out.println(text);
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    public static void main(String[] args) {
        new Game().startGame();
    }
}
