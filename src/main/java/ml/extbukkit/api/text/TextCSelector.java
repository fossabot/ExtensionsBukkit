package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextCSelector {
    private JsonObject obj = new JsonObject();

    public TextCSelector() {}

    public TextCSelector selector(String selector) {
        obj.addProperty("selector", selector);
        return this;
    }

    public TextCSelector bold(boolean bold) {
        obj.addProperty("bold", bold);
        return this;
    }

    public TextCSelector bold() {
        bold(true);
        return this;
    }

    public TextCSelector underlined(boolean underlined) {
        obj.addProperty("underlined", underlined);
        return this;
    }

    public TextCSelector underlined() {
        underlined(true);
        return this;
    }

    public TextCSelector strikethrough(boolean strikethrough) {
        obj.addProperty("strikethrough", true);
        return this;
    }

    public TextCSelector strikethrough() {
        strikethrough(true);
        return this;
    }

    public TextCSelector italic(boolean italic) {
        obj.addProperty("italic", italic);
        return this;
    }

    public TextCSelector italic() {
        italic(true);
        return this;
    }

    public TextCSelector obfuscated(boolean obfuscated) {
        obj.addProperty("obfuscated", obfuscated);
        return this;
    }

    public TextCSelector obfuscated() {
        obfuscated(true);
        return this;
    }

    public TextCSelector extra(JsonArray extra) {
        obj.add("extra", extra);
        return this;
    }

    public TextCSelector insertion(String insertion) {
        obj.addProperty("insertion", insertion);
        return this;
    }

    public TextCSelector clickEvent(JsonObject clickEvent) {
        obj.add("clickEvent", clickEvent);
        return this;
    }

    public TextCSelector hoverEvent(JsonObject hoverEvent) {
        obj.add("hoverEvent", hoverEvent);
        return this;
    }

    public TextCSelector clickEvent(TextEvent clickEvent) {
        obj.add("clickEvent", clickEvent.get());
        return this;
    }

    public TextCSelector hoverEvent(TextEvent hoverEvent) {
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