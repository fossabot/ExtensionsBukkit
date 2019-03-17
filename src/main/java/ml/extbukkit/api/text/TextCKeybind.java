package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextCKeybind {
    private JsonObject obj = new JsonObject();

    public TextCKeybind() {}

    public TextCKeybind selector(String keybind) {
        obj.addProperty("keybind", keybind);
        return this;
    }

    public TextCKeybind bold(boolean bold) {
        obj.addProperty("bold", bold);
        return this;
    }

    public TextCKeybind bold() {
        bold(true);
        return this;
    }

    public TextCKeybind underlined(boolean underlined) {
        obj.addProperty("underlined", underlined);
        return this;
    }

    public TextCKeybind underlined() {
        underlined(true);
        return this;
    }

    public TextCKeybind strikethrough(boolean strikethrough) {
        obj.addProperty("strikethrough", true);
        return this;
    }

    public TextCKeybind strikethrough() {
        strikethrough(true);
        return this;
    }

    public TextCKeybind italic(boolean italic) {
        obj.addProperty("italic", italic);
        return this;
    }

    public TextCKeybind italic() {
        italic(true);
        return this;
    }

    public TextCKeybind obfuscated(boolean obfuscated) {
        obj.addProperty("obfuscated", obfuscated);
        return this;
    }

    public TextCKeybind obfuscated() {
        obfuscated(true);
        return this;
    }

    public TextCKeybind extra(JsonArray extra) {
        obj.add("extra", extra);
        return this;
    }

    public TextCKeybind insertion(String insertion) {
        obj.addProperty("insertion", insertion);
        return this;
    }

    public TextCKeybind clickEvent(JsonObject clickEvent) {
        obj.add("clickEvent", clickEvent);
        return this;
    }

    public TextCKeybind hoverEvent(JsonObject hoverEvent) {
        obj.add("hoverEvent", hoverEvent);
        return this;
    }

    public TextCKeybind clickEvent(TextEvent clickEvent) {
        obj.add("clickEvent", clickEvent.get());
        return this;
    }

    public TextCKeybind hoverEvent(TextEvent hoverEvent) {
        obj.add("hoverEvent", hoverEvent.get());
        return this;
    }

    public JsonArray asText() {
        return new TextBuilder().add(obj).get();
    }

    public JsonObject get() {
        return obj;
    }
}