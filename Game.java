import java.util.*;

/**
 * Game class for "Echoes of Innocence"
 * Handles game flow, rooms, player actions, and events.
 * 
 */
public class Game {
    private Player player = new Player();   // The player
    private Room currentRoom;               // Current room the player is in
    private Scanner input = new Scanner(System.in); // Scanner for user input

    /**
     * Starts the game.
     * Shows intro, sets initial room, and begins gameplay loop.
     */
    public void startGame() {
        showIntro();
        currentRoom = new LivingRoom();
        narrativePause("The case begins. You step into the first room...");
        playRoom(currentRoom);
    }

    /**
     * Displays the intro story and instructions.
     */
    private void showIntro() {
        System.out.println("===========================================");
        System.out.println("          ECHOES OF INNOCENCE");
        System.out.println("===========================================");
        System.out.println("A cold night. A murder unsolved. A man on trial for a crime he didn’t commit.");
        System.out.println("You are the detective — the only one who still believes in his innocence.");
        System.out.println("Find the truth. Or lose it forever.");
        System.out.println("-------------------------------------------");
        System.out.println("Press ENTER to begin...");
        input.nextLine();
    }

    /**
     * Plays a single room.
     * Handles inspections, moving, inventory, and innocence meter.
     *
     * @param room The room to play
     */
    private void playRoom(Room room) {
        player.resetInspections();
        System.out.println("\n-------------------------------------------");
        System.out.println("You enter the " + room.getName() + "...");
        System.out.println(room.getDescription());
        System.out.println("-------------------------------------------");

        while (player.getInspectionsLeft() > 0) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Inspect an item");
            System.out.println("2. Move to next room");
            System.out.println("3. Inventory");
            System.out.println("4. View Innocence Meter");
            System.out.println("5. Help");

            int choice = getValidNumber(1, 5);

            switch (choice) {
                case 1 -> handleInspectMenu(room);
                case 2 -> {
                    System.out.println("You decide it's time to move...");
                    nextRoom(room);
                    return;
                }
                case 3 -> player.getInventory().showInventory();
                case 4 -> player.viewInnocence();
                case 5 -> showHelp();
            }
        }

        System.out.println("\nYou’ve used all inspections. Moving on...");
        nextRoom(room);
    }

    /**
     * Handles the inspection of items in a room.
     *
     * @param room The room containing items
     */
    private void handleInspectMenu(Room room) {
        System.out.println("\nSelect an item to inspect:");
        room.listObjectsNumbered();

        int itemNumber = getValidNumber(1, room.getObjectCount());
        Item item = room.getObjectByNumber(itemNumber);

        if (item == null) {
            System.out.println("Invalid selection.");
            return;
        }

        if (item.isInspected()) {
            System.out.println("You already inspected this.");
            return;
        }

        if (item.isLocked()) {
            String keyName = item.getKeyRequired();
            if (keyName != null && player.getInventory().hasItem(keyName)) {
                Item key = player.getInventory().getItem(keyName);
                item.unlock(key);
            } else {
                System.out.println("It’s locked. You need a key.");
                return;
            }
        }

        // Inspect the container or stand alone item
        item.inspect(player);

        // Handle container items
        if (item.isContainer()) {
            Item contained = item.peekContainedItem();
            if (contained != null) {
                Item found = item.takeContainedItem();
                System.out.println("\nYou found: " + found.getName() + "!");
                found.inspect(player);
                found.markInspected();
                player.getInventory().addItem(found);
            }
        } else if (item.getType() == Item.ItemType.EVIDENCE || item.getType() == Item.ItemType.KEY) {
            if (!player.getInventory().hasItem(item.getName())) {
                item.markInspected();
                player.getInventory().addItem(item);
            }
        }

        player.useInspection();
    }

    /**
     * Moves to the next room or triggers a chase event if appropriate.
     *
     * @param room Current room
     */
    private void nextRoom(Room room) {
        String next = room.getNextRoomName();

        if (next.equals("Bedroom")) {
            narrativePause("\nYou step carefully into the bedroom...");
            playRoom(new Bedroom());
        } else if (next.equals("Kitchen")) {
            narrativePause("\nEverything points toward the kitchen...");
            playRoom(new Kitchen());
        } else {
            narrativePause("\nSomeone is watching you from outside...");
            chaseEvent();
        }
    }

    /**
     * Handles the chase event outside the room.
     */
    private void chaseEvent() {
        System.out.println("\nA shadow moves outside the window...");
        System.out.println("Do you chase them?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = getValidNumber(1, 2);
        Random rand = new Random();

        if (choice == 1) {
            if (rand.nextBoolean()) {
                narrativePause("\nYou chase them into the alley...");
                narrativePause("They drop something as they escape...");
                System.out.println("The murder weapon — covered in fingerprints!");
                player.adjustInnocence(+20);
            } else {
                narrativePause("\nYou lose them in the dark...");
                narrativePause("In the chaos, you drop important evidence...");
                player.adjustInnocence(-10);
                player.getInventory().removeRandomItem();
            }
        } else {
            System.out.println("You decide not to chase them...");
        }

        endGame();
    }

    /**
     * Handles the end of the game and displays the verdict.
     */
    private void endGame() {
        narrativePause("\n------------------------------------------");
        System.out.println("       FINAL CASE PRESENTATION");
        System.out.println("------------------------------------------");

        narrativePause("The courtroom waits silently...");
        narrativePause("The judge reviews your findings...");

        System.out.println("\nWhat is your final action?");
        System.out.println("1. Present all evidence confidently");
        System.out.println("2. Appeal to emotion");
        System.out.println("3. Stay silent and let the judge rule");

        int finalChoice = getValidNumber(1, 3);

        if (finalChoice == 2) player.adjustInnocence(+5);

        narrativePause("\nThe verdict is reached...");

        if (player.getInnocenceMeter() >= 80) {
            System.out.println("\n✅ Verdict: NOT GUILTY.");
            narrativePause("Your client collapses in relief...");
        } else {
            System.out.println("\n❌ Verdict: GUILTY.");
            narrativePause("The gavel cracks like thunder...");
        }

        System.out.println("\nFinal Innocence: " + player.getInnocenceMeter() + "%");
        System.out.println("Case Closed.");
    }

    /**
     * Displays help menu for player actions.
     */
    private void showHelp() {
        System.out.println("\nHelp Menu:");
        System.out.println("1 = Inspect item");
        System.out.println("2 = Move to next room");
        System.out.println("3 = View Inventory");
        System.out.println("4 = View Innocence Meter");
        System.out.println("5 = Help");
    }

    /**
     * Gets a valid number input from the player between min and max.
     *
     * @param min Minimum number
     * @param max Maximum number
     * @return Valid number entered by player
     */
    private int getValidNumber(int min, int max) {
        while (true) {
            System.out.print("Enter choice (" + min + "-" + max + "): ");
            String text = input.nextLine();

            try {
                int n = Integer.parseInt(text);
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
    }

    /**
     * Prints narrative text with a short pause.
     *
     * @param text Text to display
     */
    private void narrativePause(String text) {
        System.out.println(text);
        try { Thread.sleep(1300); } catch (InterruptedException ignored) {}
    }

    /**
     * Main method to start the game.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Game().startGame();
    }
}
