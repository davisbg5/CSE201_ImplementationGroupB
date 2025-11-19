public class Kitchen extends Room {
    public Kitchen() {
        super("Kitchen",
            "You step into the kitchen. The light overhead flickers. Dishes pile up in the sink — a silent testimony to interrupted lives.\n" +
            "The air smells faintly of bleach, masking something metallic beneath. This is it — the final place where the truth might still whisper.");

        addObject(new Item("Fridge",
            "You open the fridge. Cold air escapes, carrying the faint scent of old leftovers. Nothing of value.", 0, false, null));

        addObject(new Item("Counter",
            "You run your hand along the counter’s edge and pause. Scratched faintly — the killer’s initials.", +10, false, null));

        addObject(new Item("Photographs",
            "A fingerprint smudges a framed photo — it doesn’t match your client.", +10, false, null));
    }

    @Override
    public String getNextRoomName() {
        return "Chase";
    }
}
