package ml.extbukkit.main.secure.chat;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.TextColor;

public class ChatMessageJSONSerializer implements JsonSerializer<ChatMessage>, JsonDeserializer<ChatMessage> {

    @Override
    public ChatMessage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        if (!object.has("text")) {
            throw new JsonParseException("Cannot deserialize JSON to message without a text");
        }
        ChatMessage message = new ChatMessage(object.get("text").getAsString());
        if (object.has("bold")) {
            message.setBold(object.get("bold").getAsBoolean());
        }
        if (object.has("underlined")) {
            message.setUnderline(object.get("underlined").getAsBoolean());
        }
        if (object.has("italic")) {
            message.setItalic(object.get("italic").getAsBoolean());
        }
        if (object.has("strikethrough")) {
            message.setStrikethrough(object.get("strikethrough").getAsBoolean());
        }
        if (object.has("obfuscated")) {
            message.setObfuscated(object.get("obfuscated").getAsBoolean());
        }
        if (object.has("color")) {
            String color = object.get("color").getAsString();
            if (color.equalsIgnoreCase("obfuscated") || color.equalsIgnoreCase("bold") || color.equalsIgnoreCase("strikethrough") ||
                    color.equalsIgnoreCase("underline") || color.equalsIgnoreCase("italic")) {
                throw new JsonParseException("Invalid color '" + color + "'");
            }
            message.setColor(TextColor.valueOf(color.toUpperCase()));
        }
        return message;
    }

    @Override
    public JsonElement serialize(ChatMessage message, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("text", message.getMessage());
        if (message.isBold()) {
            object.addProperty("bold", message.isBold());
        }
        if (message.isUnderline()) {
            object.addProperty("underlined", message.isUnderline());
        }
        if (message.isItalic()) {
            object.addProperty("italic", message.isItalic());
        }
        if (message.isStrikethrough()) {
            object.addProperty("strikethrough", message.isStrikethrough());
        }
        if (message.isObfuscated()) {
            object.addProperty("obfuscated", message.isObfuscated());
        }
        if (message.getColor() != TextColor.WHITE) {
            object.addProperty("color", message.getColor().name().toLowerCase());
        }
        return object;
    }

}
