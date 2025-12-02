public class Bedroom extends Room {
    public Bedroom() {
        super("Bedroom",
            "The bedroom door groans as you push it open. Dust hangs in the air, lit by a thin slice of moonlight.\n" +
            "The smell of floral perfume lingers — not your client's. The bed is unmade; someone left in a hurry.");

        addObject(new Item("Wardrobe",
            "You pull open the wardrobe. Rows of neatly folded clothes… nothing to suggest a struggle.", 
            0, false, null, Item.ItemType.FURNITURE, null));

        Item shoe = new Item("Dirty Shoe",
            "A muddy shoe — larger than your client's size. Soil flakes from the sole; someone else was here.", 
            10, false, null, Item.ItemType.EVIDENCE, null);

        addObject(new Item("Bed",
            "You kneel beside the bed, pulling at the loose sheet. There's something tucked beneath the mattress.", 
            0, false, null, Item.ItemType.FURNITURE, shoe));

        addObject(new Item("Drawer",
            "A small bedside drawer sits slightly ajar, its lock recently tampered with.", 
            0, true, "Small Key", Item.ItemType.FURNITURE, new Item("Folded Note",
                "A folded note reads: 'Meet me in the kitchen. Don’t tell anyone.' The handwriting is sharp and unfamiliar.", 
                20, false, null, Item.ItemType.EVIDENCE, null)));
    }

    @Override
    public String getNextRoomName() {
        return "Kitchen";
    }
}
