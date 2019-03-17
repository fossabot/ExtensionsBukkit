package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextCTranslate {
    private JsonObject obj = new JsonObject();

    public TextCTranslate() {}

    public TextCTranslate translate(String translate) {
        obj.addProperty("translate", translate);
        return this;
    }

    public TextCTranslate with(JsonArray with) {
        obj.add("with", with);
        return this;
    }

    public TextCTranslate color(String color) {
        obj.addProperty("color", color);
        return this;
    }

    public TextCTranslate bold(boolean bold) {
        obj.addProperty("bold", bold);
        return this;
    }

    public TextCTranslate bold() {
        bold(true);
        return this;
    }

    public TextCTranslate underlined(boolean underlined) {
        obj.addProperty("underlined", underlined);
        return this;
    }

    public TextCTranslate underlined() {
        underlined(true);
        return this;
    }

    public TextCTranslate strikethrough(boolean strikethrough) {
        obj.addProperty("strikethrough", true);
        return this;
    }

    public TextCTranslate strikethrough() {
        strikethrough(true);
        return this;
    }

    public TextCTranslate italic(boolean italic) {
        obj.addProperty("italic", italic);
        return this;
    }

    public TextCTranslate italic() {
        italic(true);
        return this;
    }

    public TextCTranslate obfuscated(boolean obfuscated) {
        obj.addProperty("obfuscated", obfuscated);
        return this;
    }

    public TextCTranslate obfuscated() {
        obfuscated(true);
        return this;
    }

    public TextCTranslate extra(JsonArray extra) {
        obj.add("extra", extra);
        return this;
    }

    public TextCTranslate insertion(String insertion) {
        obj.addProperty("insertion", insertion);
        return this;
    }

    public TextCTranslate clickEvent(JsonObject clickEvent) {
        obj.add("clickEvent", clickEvent);
        return this;
    }

    public TextCTranslate hoverEvent(JsonObject hoverEvent) {
        obj.add("hoverEvent", hoverEvent);
        return this;
    }

    public TextCTranslate clickEvent(TextEvent clickEvent) {
        obj.add("clickEvent", clickEvent.get());
        return this;
    }

    public TextCTranslate hoverEvent(TextEvent hoverEvent) {
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