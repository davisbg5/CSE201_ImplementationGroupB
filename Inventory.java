import java.util.ArrayList;
import java.util.Random;

/**
 * Inventory class
 * Handles the player's items, including adding, removing, and displaying them.
 * 
 */
public class Inventory {
    private ArrayList<Item> items = new ArrayList<>(); // List of items in inventory

    /**
     * Adds an item to the inventory if it’s not already present.
     *
     * @param item Item to add
     */
    public void addItem(Item item) {
        if (item == null) return;
        if (!hasItem(item.getName())) {
            items.add(item);
            item.markTaken();
            System.out.println(item.getName() + " added to inventory.");
        } else {
            System.out.println("You already have " + item.getName() + " in your inventory.");
        }
    }

    /**
     * Checks if the inventory contains a specific item.
     *
     * @param itemName Name of item
     * @return true if inventory has the item, false otherwise
     */
    public boolean hasItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }

    /**
     * Retrieves an item from the inventory by name.
     *
     * @param itemName Name of item
     * @return The item if found, else null
     */
    public Item getItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) return i;
        }
        return null;
    }

    /**
     * Displays all items currently in the inventory.
     */
    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item i : items) {
                String suffix = i.isInspected() ? " (INSPECTED ✓)" : "";
                System.out.println("- " + i.getName() + suffix);
            }
        }
    }

    /**
     * Removes a random item from inventory, simulating losing evidence.
     */
    public void removeRandomItem() {
        if (items.isEmpty()) {
            System.out.println("You have no evidence to lose.");
            return;
        }
        Random random = new Random();
        int index = random.nextInt(items.size());
        Item lost = items.remove(index);
        System.out.println("In the chaos of the chase, you dropped " + lost.getName() + "!");
    }

    /**
     * Returns all items currently in the inventory.
     *
     * @return ArrayList of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
