package ml.extbukkit.api.text;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextBuilder {
    private JsonArray arr = new JsonArray();

    public TextBuilder() {}

    public TextBuilder add(JsonObject obj) {
        arr.add(obj);
        return this;
    }

    public TextBuilder add(TextCText builder) {
        arr.add(builder.get());
        return this;
    }

    public JsonArray get() {
        return arr;
    }
}