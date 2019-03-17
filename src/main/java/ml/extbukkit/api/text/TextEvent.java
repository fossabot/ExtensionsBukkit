package ml.extbukkit.api.text;

import com.google.gson.JsonObject;

public class TextEvent {
    private JsonObject obj = new JsonObject();

    public static final String CLICK_RUN_COMMAND = "run_command";
    public static final String CLICK_SUGGEST_COMMAND = "suggest_command";
    public static final String CLICK_OPEN_URL = "open_url";
    public static final String CLICK_CHANGE_PAGE = "change_page";

    public static final String HOVER_SHOW_TEXT = "show_text";
    public static final String HOVER_SHOW_ITEM = "show_item";
    public static final String HOVER_SHOW_ENTITY = "show_entity";

    public TextEvent() {}

    public TextEvent action(String action) {
        obj.addProperty("action", action);
        return this;
    }

    public TextEvent value(String value) {
        obj.addProperty("value", value);
        return this;
    }

    public JsonObject get() {
        return obj;
    }
}