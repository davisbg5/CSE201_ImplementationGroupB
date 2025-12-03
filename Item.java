/**
 * Item class
 * Represents an object in a room in the "Echoes of Innocence" game.
 * Items can be furniture, evidence, keys, or containers holding other items.
 * Players can inspect, take, or unlock items.
 *
 */
public class Item {

    /** Enum for type of item */
    public enum ItemType { FURNITURE, EVIDENCE, KEY }

    private String name;
    private String description;
    private int innocenceEffect; // effect on player's Innocence Meter
    private boolean locked;
    private String keyRequired;

    // state tracking
    private boolean inspected = false;
    private boolean taken = false;

    // item type and optional contained item (for drawers, etc.)
    private ItemType type;
    private Item containedItem; // null if none

    /**
     * Full constructor for Item.
     *
     * @param name Name of the item
     * @param description Description shown when inspecting
     * @param innocenceEffect Effect on Innocence Meter
     * @param locked Whether the item is locked
     * @param keyRequired Key needed to unlock
     * @param type Type of the item
     * @param containedItem Item contained inside this item (if any)
     */
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

    /**
     * Convenience constructor for simple items with no contained item.
     *
     * @param name Name of the item
     * @param description Description shown when inspecting
     * @param innocenceEffect Effect on Innocence Meter
     * @param locked Whether the item is locked
     * @param keyRequired Key needed to unlock
     */
    public Item(String name, String description, int innocenceEffect, boolean locked, String keyRequired) {
        this(name, description, innocenceEffect, locked, keyRequired, ItemType.EVIDENCE, null);
    }

    // Accessors / Getters
    

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getInnocenceEffect() { return innocenceEffect; }
    public boolean isLocked() { return locked; }
    public String getKeyRequired() { return keyRequired; }
    public ItemType getType() { return type; }
    public boolean isInspected() { return inspected; }
    public boolean isTaken() { return taken; }

    /**
     * Returns display name, adding "(INSPECTED ✓)" if already inspected.
     *
     * @return display name string
     */
    public String getDisplayName() {
        return name + (inspected ? " (INSPECTED ✓)" : "");
    }

    /**
     * Checks if this item contains another item.
     *
     * @return true if there is a contained item
     */
    public boolean isContainer() {
        return containedItem != null;
    }

    /**
     * Peek at the contained item without removing it.
     *
     * @return contained Item, or null if none
     */
    public Item peekContainedItem() {
        return containedItem;
    }

    /**
     * Removes and returns the contained item.
     *
     * @return the contained Item
     */
    public Item takeContainedItem() {
        Item temp = containedItem;
        containedItem = null;
        return temp;
    }

    /**
     * Marks the item as taken by the player.
     */
    public void markTaken() { this.taken = true; }

    /**
     * Marks the item as inspected.
     */
    public void markInspected() { this.inspected = true; }

    /**
     * Unlocks this item if the correct key is provided.
     *
     * @param key Key item used to unlock
     */
    public void unlock(Item key) {
        if (key != null && keyRequired != null && key.getName().equalsIgnoreCase(keyRequired)) {
            locked = false;
            System.out.println("You unlocked the " + name + " using the " + key.getName() + ".");
        } else {
            System.out.println("That key doesn't fit.");
        }
    }

    /**
     * Inspect this item.
     * Shows description, applies innocence effect, and marks inspected.
     *
     * @param player The player inspecting this item
     */
    public void inspect(Player player) {
        if (inspected) {
            System.out.println("You already inspected this.");
            return;
        }

        if (locked) {
            System.out.println("It’s locked. You need a key.");
            return;
        }

        // Show description
        System.out.println("\n" + description);

        // Adjust player's innocence meter if applicable
        if (innocenceEffect != 0 && player != null) {
            player.adjustInnocence(innocenceEffect);
        }

        inspected = true;
    }
}
