/**
 * LivingRoom class
 * Represents the Living Room in the "Echoes of Innocence" game.
 * Players can inspect objects like the body, key, phone, and drawer to gather evidence.
 * 
 */
public class LivingRoom extends Room {

    /**
     * Constructor for LivingRoom.
     * Sets the room name, description, and adds items to the room.
     */
    public LivingRoom() {
        super("Living Room",
            "Rain taps steadily against the window. A single lamp buzzes, casting long, uneasy shadows across the floor.\n" +
            "It feels staged — like someone wanted this room to tell the wrong story.");

        // Add body (furniture, no innocence effect)
        addObject(new Item("Body",
            "You kneel beside the body. The wounds are surgical, intentional, crafted by someone who knew exactly what they were doing. Nothing the medical record didn't say.", 
            0, false, null, Item.ItemType.FURNITURE, null));

        // Add small key (key, no innocence effect)
        addObject(new Item("Small Key",
            "A small, brass key you find half-hidden beneath the couch. Someone dropped it in a hurry.", 
            0, false, null, Item.ItemType.KEY, null));

        // Create phone item (evidence, positive innocence effect)
        Item phone = new Item("Phone",
            "A cracked phone with recent outgoing calls to an unknown number. Someone tried to hide the contacts.", 
            20, false, null, Item.ItemType.EVIDENCE, null);

        // Add locked drawer containing the phone
        addObject(new Item("Drawer",
            "A locked drawer sits beneath the TV stand. The wood around the keyhole is scratched — someone tried to open it before.", 
            0, true, "Small Key", Item.ItemType.FURNITURE, phone));
    }

    /**
     * Returns the name of the next room.
     *
     * @return String name of next room
     */
    @Override
    public String getNextRoomName() {
        return "Bedroom";
    }
}
