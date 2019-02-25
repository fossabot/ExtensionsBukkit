package ml.extbukkit.api.chat;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import lombok.Getter;

/**
 * Represents a chat message serializer and deserializer
 */
public abstract class ChatMessageJSONer
{

    @Getter
    private static ChatMessageJSONer instance;

    public static void setInstance(ChatMessageJSONer instance)
    {
        Preconditions.checkNotNull( instance, "Instance cannot be null" );
        Preconditions.checkArgument( ChatMessageJSONer.instance == null, "Instance already set" );
        ChatMessageJSONer.instance = instance;
    }

    /**
     * Parses a JSON string to a {@link ChatMessage}
     *
     * @param jsonString json string
     * @return chat message
     */
    public abstract ChatMessage parse(String jsonString);

    /**
     * Parses a {@link JsonObject} to a {@link ChatMessage}
     *
     * @param object json object
     * @return chat message
     */
    public abstract ChatMessage parse(JsonObject object);

    /**
     * Parses a {@link ChatMessage} to JSON string
     *
     * @param message message
     * @return json string from the specified message
     */
    public abstract String toJson(ChatMessage message);

}
