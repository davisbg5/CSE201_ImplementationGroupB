public class Kitchen extends Room {
    public Kitchen() {
        super("Kitchen",
            "The kitchen light flickers as you step inside. Dishes pile in the sink, some cracked. The scent of bleach masks something metallic beneath.");

        addObject(new Item("Fridge",
            "You open the fridge. Cold air rushes out. Old leftovers — nothing useful.", 
            0, false, null, Item.ItemType.FURNITURE, null));

        addObject(new Item("Counter",
            "You run your hand along the counter and notice initials scratched faintly into the wood — half-hidden, hurried.", 
            10, false, null, Item.ItemType.EVIDENCE, null));

        addObject(new Item("Photographs",
            "A framed photo sits crooked on the shelf. A fingerprint smudges the glass; it doesn’t match your client.", 
            10, false, null, Item.ItemType.EVIDENCE, null));
    }

    @Override
    public String getNextRoomName() {
        return "Chase";
    }
}
