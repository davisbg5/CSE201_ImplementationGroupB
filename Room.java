// Room.java
import java.util.ArrayList;

public abstract class Room {
    protected String name;
    protected String description;
    protected ArrayList<Item> objects;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.objects = new ArrayList<>();
    }

    public void addObject(Item item) {
        objects.add(item);
    }

    public int getObjectCount() {
        return objects.size();
    }

    public Item getObjectByNumber(int index) {
        if (index < 1 || index > objects.size()) return null;
        return objects.get(index - 1);
    }

    public void enter() {
        listObjectsNumbered();
    }

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

    public ArrayList<Item> getAllItems() {
        return objects;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getNextRoomName();
}
