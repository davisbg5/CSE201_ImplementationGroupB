// Player.java
public class Player {
    private int innocenceMeter;
    private int inspectionsLeft;
    private Inventory inventory;

    public Player() {
        this.innocenceMeter = 50;  // Starting meter
        this.inspectionsLeft = 2;  // Two per room
        this.inventory = new Inventory();
    }

    public int getInnocenceMeter() {
        return innocenceMeter;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getInspectionsLeft() {
        return inspectionsLeft;
    }

    public void resetInspections() {
        this.inspectionsLeft = 2;
    }

    public void decreaseInspection() {
        if (inspectionsLeft > 0) {
            inspectionsLeft--;
        }
    }

    public void useInspection() {
        decreaseInspection();
        System.out.println("🔎 Inspections left in this room: " + inspectionsLeft);
    }

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

    public void viewInnocence() {
        System.out.println("Innocence Meter: " + innocenceMeter + "%");
    }

    public void viewInventory() {
        inventory.showInventory();
    }
}
