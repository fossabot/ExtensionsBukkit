package ml.extbukkit.api.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextHelper {
    public static JsonObject color(JsonObject object, String color) {
        object.addProperty("color", color);
        return object;
    }
    public static JsonArray translate(String str, String... with) {
        JsonObject obj = simple("translate", str);
        JsonArray w = new JsonArray();
        for(String s : with)
            w.add(simple("text", s));
        return create(obj);
    }
    public static JsonArray string(String str) {
        return create(simple("text", str));
    }
    public static JsonObject simple(String key, String value) {
        JsonObject o = new JsonObject();
        o.addProperty(key, value);
        return o;
    }
    public static JsonArray create(JsonObject jsonObject) {
        JsonArray a = new JsonArray();
        a.add(jsonObject);
        return a;
    }
}