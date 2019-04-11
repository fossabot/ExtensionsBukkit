package ml.extbukkit.main.secure.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;

public class SimpleSerializer extends ChatMessageSerializer {

    public Gson gson = new GsonBuilder().registerTypeAdapter(ChatMessage.class, new ChatMessageJSONSerializer()).create();

    @Override
    public ChatMessage parse(String json) {
        return gson.fromJson(json, ChatMessage.class);
    }

    @Override
    public ChatMessage parse(JsonObject object) {
        return gson.fromJson(object, ChatMessage.class);
    }

    @Override
    public String toString(ChatMessage message) {
        return gson.toJson(message);
    }

}
