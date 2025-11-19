import java.util.ArrayList;

public abstract class Room {
    protected String name;
    protected String description;
    protected ArrayList<Item> objects;
    private Room nextRoom;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.objects = new ArrayList<>();
        this.nextRoom = null;
    }

    // Basic info
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    // Object handling
    public void addObject(Item item) {
        objects.add(item);
    }

    public Item getObject(String itemName) {
        for (Item obj : objects) {
            if (obj.getName().equalsIgnoreCase(itemName)) {
                return obj;
            }
        }
        return null;
    }

    public void removeObject(Item item) {
        objects.remove(item);
    }

    public void listObjects() {
        if (objects.isEmpty()) {
            System.out.println("There’s nothing left to inspect here.");
        } else {
            System.out.println("You see the following items:");
            for (Item obj : objects) {
                System.out.println("- " + obj.getName());
            }
        }
    }

    public void enter() {
        System.out.println("\n🏠 You enter the " + name + ".");
        System.out.println(description);
        listObjects();
    }

    // Subclasses must tell which named room comes next so Game can use it
    public abstract String getNextRoomName();
}
