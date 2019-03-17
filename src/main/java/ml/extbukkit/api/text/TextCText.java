package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextCText {
    private JsonObject obj = new JsonObject();

    public TextCText() {}

    public TextCText text(String text) {
        obj.addProperty("text", text);
        return this;
    }

    public TextCText color(String color) {
        obj.addProperty("color", color);
        return this;
    }

    public TextCText bold(boolean bold) {
        obj.addProperty("bold", bold);
        return this;
    }

    public TextCText bold() {
        bold(true);
        return this;
    }

    public TextCText underlined(boolean underlined) {
        obj.addProperty("underlined", underlined);
        return this;
    }

    public TextCText underlined() {
        underlined(true);
        return this;
    }

    public TextCText strikethrough(boolean strikethrough) {
        obj.addProperty("strikethrough", true);
        return this;
    }

    public TextCText strikethrough() {
        strikethrough(true);
        return this;
    }

    public TextCText italic(boolean italic) {
        obj.addProperty("italic", italic);
        return this;
    }

    public TextCText italic() {
        italic(true);
        return this;
    }

    public TextCText obfuscated(boolean obfuscated) {
        obj.addProperty("obfuscated", obfuscated);
        return this;
    }

    public TextCText obfuscated() {
        obfuscated(true);
        return this;
    }

    public TextCText extra(JsonArray extra) {
        obj.add("extra", extra);
        return this;
    }

    public TextCText insertion(String insertion) {
        obj.addProperty("insertion", insertion);
        return this;
    }

    public TextCText clickEvent(JsonObject clickEvent) {
        obj.add("clickEvent", clickEvent);
        return this;
    }

    public TextCText hoverEvent(JsonObject hoverEvent) {
        obj.add("hoverEvent", hoverEvent);
        return this;
    }

    public TextCText clickEvent(TextEvent clickEvent) {
        obj.add("clickEvent", clickEvent.get());
        return this;
    }

    public TextCText hoverEvent(TextEvent hoverEvent) {
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