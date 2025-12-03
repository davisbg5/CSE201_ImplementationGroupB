import java.util.ArrayList;

/**
 * Abstract Room class
 * Represents a room in the "Echoes of Innocence" game.
 * Each room has a name, description, and contains items.
 * Subclasses must define the next room name.
 * 
 */
public abstract class Room {
    protected String name;                  // Name of the room
    protected String description;           // Description of the room
    protected ArrayList<Item> objects;      // Items in the room

    /**
     * Constructor for Room.
     *
     * @param name Name of the room
     * @param description Description of the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.objects = new ArrayList<>();
    }

    /**
     * Adds an item to the room.
     *
     * @param item Item to add
     */
    public void addObject(Item item) {
        objects.add(item);
    }

    /**
     * Returns the number of items in the room.
     *
     * @return number of items
     */
    public int getObjectCount() {
        return objects.size();
    }

    /**
     * Gets an item by its number in the list (1-based).
     *
     * @param index Number of the item (1-based)
     * @return Item object or null if out of range
     */
    public Item getObjectByNumber(int index) {
        if (index < 1 || index > objects.size()) return null;
        return objects.get(index - 1);
    }

    /**
     * Called when the player enters the room.
     * Prints the list of items.
     */
    public void enter() {
        listObjectsNumbered();
    }

    /**
     * Lists all items in the room with numbers.
     */
    public void listObjectsNumbered() {
        if (objects.isEmpty()) {
            System.out.println("There’s nothing left here.");
            return;
        }

        System.out.println("\nItems:");
        for (int i = 0; i < objects.size(); i++) {
            Item item = objects.get(i);
            System.out.println((i + 1) + ". " + item.getDisplayName());
        }
    }

    /**
     * Returns all items in the room.
     *
     * @return ArrayList of items
     */
    public ArrayList<Item> getAllItems() {
        return objects;
    }

    /**
     * Returns the room's name.
     *
     * @return room name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the room's description.
     *
     * @return room description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract method to get the next room's name.
     * Must be implemented by subclasses.
     *
     * @return name of the next room
     */
    public abstract String getNextRoomName();
}
