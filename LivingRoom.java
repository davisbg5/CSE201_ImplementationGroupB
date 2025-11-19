public class LivingRoom extends Room {
    public LivingRoom() {
        super("Living Room",
            "You step into the living room — the heart of the crime scene.\n" +
            "The air feels heavy. A lamp flickers in the corner, and somewhere outside, rain taps against the window.\n" +
            "You can almost feel the weight of accusation hanging in the silence.");

        // Items: names must match inventory key lookups
        addObject(new Item("Body",
            "You kneel beside the body. The wounds are surgical, deliberate — not a crime of passion.\n" +
            "But without more, this tells you nothing new.", 0, false, null));

        // The key is an item named "Small Key" so unlocking works consistently
        addObject(new Item("Small Key",
            "A small, brass key you find under the couch. It might open a locked drawer.", 0, false, null));

        addObject(new Item("Drawer",
            "A locked drawer sits beneath the TV. Inside rests a phone — recent calls to an unknown number.",
            +20, true, "Small Key"));
    }

    @Override
    public String getNextRoomName() {
        return "Bedroom";
    }
}
