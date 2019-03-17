package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextCScore {
    private JsonObject obj = new JsonObject();
    
    public TextCScore() {
        obj.add("score", new JsonObject());
    }

    public TextCScore name(String name) {
        obj.get("score").getAsJsonObject().addProperty("name", name);
        return this;
    }

    public TextCScore objective(String objective) {
        obj.get("score").getAsJsonObject().addProperty("objective", objective);
        return this;
    }

    public TextCScore value(String value) {
        obj.get("score").getAsJsonObject().addProperty("value", value);
        return this;
    }

    public TextCScore color(String color) {
        obj.addProperty("color", color);
        return this;
    }

    public TextCScore bold(boolean bold) {
        obj.addProperty("bold", bold);
        return this;
    }

    public TextCScore bold() {
        bold(true);
        return this;
    }

    public TextCScore underlined(boolean underlined) {
        obj.addProperty("underlined", underlined);
        return this;
    }

    public TextCScore underlined() {
        underlined(true);
        return this;
    }

    public TextCScore strikethrough(boolean strikethrough) {
        obj.addProperty("strikethrough", true);
        return this;
    }

    public TextCScore strikethrough() {
        strikethrough(true);
        return this;
    }

    public TextCScore italic(boolean italic) {
        obj.addProperty("italic", italic);
        return this;
    }

    public TextCScore italic() {
        italic(true);
        return this;
    }

    public TextCScore obfuscated(boolean obfuscated) {
        obj.addProperty("obfuscated", obfuscated);
        return this;
    }

    public TextCScore obfuscated() {
        obfuscated(true);
        return this;
    }

    public TextCScore extra(JsonArray extra) {
        obj.add("extra", extra);
        return this;
    }

    public TextCScore insertion(String insertion) {
        obj.addProperty("insertion", insertion);
        return this;
    }

    public TextCScore clickEvent(JsonObject clickEvent) {
        obj.add("clickEvent", clickEvent);
        return this;
    }

    public TextCScore hoverEvent(JsonObject hoverEvent) {
        obj.add("hoverEvent", hoverEvent);
        return this;
    }

    public TextCScore clickEvent(TextEvent clickEvent) {
        obj.add("clickEvent", clickEvent.get());
        return this;
    }

    public TextCScore hoverEvent(TextEvent hoverEvent) {
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