package ml.extbukkit.main.secure.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageJSONer;

public class SimpleJSONer extends ChatMessageJSONer
{

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter( ChatMessage.class, new ChatMessageSerializer() )
            .create();

    @Override
    public ChatMessage parse(String jsonString)
    {
        return gson.fromJson( jsonString, ChatMessage.class );
    }

    @Override
    public ChatMessage parse(JsonObject object)
    {
        return gson.fromJson( object, ChatMessage.class );
    }

    @Override
    public String toJson(ChatMessage message)
    {
        return gson.toJson( message );
    }

}