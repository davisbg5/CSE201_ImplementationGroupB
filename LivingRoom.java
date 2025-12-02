public class LivingRoom extends Room {
    public LivingRoom() {
        super("Living Room",
            "Rain taps steadily against the window. A single lamp buzzes, casting long, uneasy shadows across the floor.\n" +
            "It feels staged — like someone wanted this room to tell the wrong story.");

        // Objects in the room
        addObject(new Item("Body",
            "You kneel beside the body. The cuts are clean, deliberate — surgical. This was planned.", 
            0, false, null, Item.ItemType.FURNITURE, null));

        addObject(new Item("Small Key",
            "A small, brass key you find half-hidden beneath the couch. Someone dropped it in a hurry.", 
            0, false, null, Item.ItemType.KEY, null));

        Item phone = new Item("Phone",
            "A cracked phone with recent outgoing calls to an unknown number. Someone tried to hide the contacts.", 
            20, false, null, Item.ItemType.EVIDENCE, null);

        addObject(new Item("Drawer",
            "A locked drawer sits beneath the TV stand. The wood around the keyhole is scratched — someone tried to open it before.", 
            0, true, "Small Key", Item.ItemType.FURNITURE, phone));
    }

    @Override
    public String getNextRoomName() {
        return "Bedroom";
    }
}
