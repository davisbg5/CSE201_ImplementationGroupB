// Inventory.java
import java.util.ArrayList;
import java.util.Random;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();

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

    public boolean hasItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }

    public Item getItem(String itemName) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(itemName)) return i;
        }
        return null;
    }

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

    public ArrayList<Item> getItems() {
        return items;
    }
}
