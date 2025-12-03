/**
 * Kitchen class
 * Represents the Kitchen room in the "Echoes of Innocence" game.
 * Players can inspect items like fridge, counter, and photographs to gather evidence.
 * 
 */
public class Kitchen extends Room {

    /**
     * Constructor for Kitchen.
     * Sets the room name, description, and adds items to the room.
     */
    public Kitchen() {
        super("Kitchen",
            "The kitchen light flickers as you step inside. Dishes pile in the sink, some cracked. The scent of bleach masks something metallic beneath.");

        // Add a fridge (furniture, no innocence effect)
        addObject(new Item("Fridge",
            "You open the fridge. Cold air rushes out. Old leftovers — nothing useful.", 
            0, false, null, Item.ItemType.FURNITURE, null));

        // Add a counter (evidence, slight innocence effect)
        addObject(new Item("Counter",
            "You run your hand along the counter and notice initials scratched faintly into the wood — half-hidden, hurried.", 
            10, false, null, Item.ItemType.EVIDENCE, null));

        // Add photographs (evidence, slight innocence effect)
        addObject(new Item("Photographs",
            "A framed photo sits crooked on the shelf. A fingerprint smudges the glass; it doesn’t match your client.", 
            10, false, null, Item.ItemType.EVIDENCE, null));
    }

    /**
     * Returns the name of the next room.
     *
     * @return String name of next room
     */
    @Override
    public String getNextRoomName() {
        return "Chase";
    }
}
