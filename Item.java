public class Item {
    private String name;
    private String description;
    private int innocenceEffect;
    private boolean locked;
    private String keyRequired;

    public Item(String name, String description, int innocenceEffect, boolean locked, String keyRequired) {
        this.name = name;
        this.description = description;
        this.innocenceEffect = innocenceEffect;
        this.locked = locked;
        this.keyRequired = keyRequired;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getInnocenceEffect() { return innocenceEffect; }
    public boolean isLocked() { return locked; }
    public String getKeyRequired() { return keyRequired; }

    public void unlock(Item key) {
        if (key != null && key.getName().equalsIgnoreCase(keyRequired)) {
            locked = false;
            System.out.println("🔓 You unlocked the " + name + " using the " + key.getName() + ".");
        } else {
            System.out.println("❌ That key doesn’t fit.");
        }
    }

    public void inspect(Player player) {
        if (locked) {
            System.out.println("🔒 The " + name + " is locked. Maybe there’s a key somewhere...");
        } else {
            System.out.println("\n🕵️ " + description);
            if (innocenceEffect != 0) {
                player.adjustInnocence(innocenceEffect);
            } else {
                System.out.println("No new evidence found.");
            }
        }
    }
}
