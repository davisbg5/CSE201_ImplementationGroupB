// Item.java
public class Item {

    public enum ItemType { FURNITURE, EVIDENCE, KEY }

    private String name;
    private String description;
    private int innocenceEffect;
    private boolean locked;
    private String keyRequired;

    // state
    private boolean inspected = false;
    private boolean taken = false;

    // type and optional contained item (for containers like drawers)
    private ItemType type;
    private Item containedItem; // null if none

    // Full constructor
    public Item(String name, String description, int innocenceEffect,
                boolean locked, String keyRequired, ItemType type, Item containedItem) {
        this.name = name;
        this.description = description;
        this.innocenceEffect = innocenceEffect;
        this.locked = locked;
        this.keyRequired = keyRequired;
        this.type = type == null ? ItemType.EVIDENCE : type;
        this.containedItem = containedItem;
    }

    // Convenience constructor (backwards compatible)
    public Item(String name, String description, int innocenceEffect, boolean locked, String keyRequired) {
        this(name, description, innocenceEffect, locked, keyRequired, ItemType.EVIDENCE, null);
    }

    // Accessors
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getInnocenceEffect() { return innocenceEffect; }
    public boolean isLocked() { return locked; }
    public String getKeyRequired() { return keyRequired; }
    public ItemType getType() { return type; }
    public boolean isInspected() { return inspected; }
    public boolean isTaken() { return taken; }

    public String getDisplayName() {
        return name + (inspected ? " (INSPECTED ✓)" : "");
    }

    public boolean isContainer() {
        return containedItem != null;
    }

    public Item peekContainedItem() {
        return containedItem;
    }

    public Item takeContainedItem() {
        Item temp = containedItem;
        containedItem = null;
        return temp;
    }

    public void markTaken() { this.taken = true; }
    public void markInspected() { this.inspected = true; }

    public void unlock(Item key) {
        if (key != null && keyRequired != null && key.getName().equalsIgnoreCase(keyRequired)) {
            locked = false;
            System.out.println("You unlocked the " + name + " using the " + key.getName() + ".");
        } else {
            System.out.println("That key doesn't fit.");
        }
    }

    public void inspect(Player player) {
        if (inspected) {
            System.out.println("You already inspected this.");
            return;
        }

        if (locked) {
            System.out.println("It’s locked. You need a key.");
            return;
        }

        System.out.println("\n" + description);

        if (innocenceEffect != 0 && player != null) {
            player.adjustInnocence(innocenceEffect);
        }

        inspected = true;
    }
}
