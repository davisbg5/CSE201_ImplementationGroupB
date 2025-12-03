/**
 * Player class
 * Represents the player in the "Echoes of Innocence" game.
 * Tracks innocence meter, inspections left, and the player's inventory.
 * 
 */
public class Player {
    private int innocenceMeter;    // Tracks player's innocence (0-100)
    private int inspectionsLeft;   // How many inspections player can do in the current room
    private Inventory inventory;   // Player's inventory of items

    /**
     * Constructor for Player.
     * Initializes innocence meter, inspections, and inventory.
     */
    public Player() {
        this.innocenceMeter = 50;  // Starting innocence
        this.inspectionsLeft = 2;  // Two inspections per room
        this.inventory = new Inventory();
    }

    /**
     * Gets the current innocence meter value.
     *
     * @return current innocence (0-100)
     */
    public int getInnocenceMeter() {
        return innocenceMeter;
    }

    /**
     * Gets the player's inventory.
     *
     * @return Inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the number of inspections left for the current room.
     *
     * @return inspections left
     */
    public int getInspectionsLeft() {
        return inspectionsLeft;
    }

    /**
     * Resets inspections to the default (2) for a new room.
     */
    public void resetInspections() {
        this.inspectionsLeft = 2;
    }

    /**
     * Decreases the number of inspections left by 1.
     */
    public void decreaseInspection() {
        if (inspectionsLeft > 0) {
            inspectionsLeft--;
        }
    }

    /**
     * Uses an inspection and prints remaining inspections.
     */
    public void useInspection() {
        decreaseInspection();
        System.out.println("🔎 Inspections left in this room: " + inspectionsLeft);
    }

    /**
     * Adjusts the player's innocence meter by a value.
     * Ensures the meter stays within 0-100 and prints a message.
     *
     * @param value amount to adjust innocence (+/-)
     */
    public void adjustInnocence(int value) {
        innocenceMeter += value;

        if (innocenceMeter > 100) innocenceMeter = 100;
        if (innocenceMeter < 0) innocenceMeter = 0;

        if (value > 0) {
            System.out.println("Evidence supports your case! Innocence +" + value);
        } else if (value < 0) {
            System.out.println("Misleading clue. Innocence " + value);
        }
        System.out.println("Current Innocence: " + innocenceMeter + "%");
    }

    /**
     * Prints the current innocence meter.
     */
    public void viewInnocence() {
        System.out.println("Innocence Meter: " + innocenceMeter + "%");
    }

    /**
     * Shows the contents of the player's inventory.
     */
    public void viewInventory() {
        inventory.showInventory();
    }
}
