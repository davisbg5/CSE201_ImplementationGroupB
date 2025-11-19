public class Bedroom extends Room {
    public Bedroom() {
        super("Bedroom",
            "The bedroom door creaks open. Dust dances in the slivers of light breaking through torn curtains.\n" +
            "The faint scent of perfume lingers — a woman’s, not your client’s. The bed is unmade. Something feels off.");

        addObject(new Item("Wardrobe",
            "You pull open the wardrobe. Rows of neatly folded clothes… nothing unusual.", 0, false, null));

        addObject(new Item("Bed",
            "You kneel beside the bed, pulling at the loose sheet.\n" +
            "A dirty shoe falls out — larger than your client’s size. Someone else was here.", +10, false, null));

        addObject(new Item("Drawer",
            "A folded note lies within: 'Meet me at the kitchen. Don’t tell anyone.'", +20, true, "Small Key"));
    }

    @Override
    public String getNextRoomName() {
        return "Kitchen";
    }
}
