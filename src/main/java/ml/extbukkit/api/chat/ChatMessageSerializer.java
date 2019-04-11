package ml.extbukkit.api.chat;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import lombok.Getter;

/**
 * A simple {@link com.google.gson.Gson} serializer and
 * deserializer of {@link ChatMessage}
 */
public abstract class ChatMessageSerializer {

    @Getter
    private static ChatMessageSerializer instance;

    public static void setInstance(ChatMessageSerializer instance) {
        Preconditions.checkNotNull(instance, "New instance cannot be null");
        Preconditions.checkArgument(ChatMessageSerializer.instance == null, "Instance already set");
        ChatMessageSerializer.instance = instance;
    }

    /**
     * Parses a JSON string into a chat message
     *
     * @param json json string
     * @return ChatMessage
     */
    public abstract ChatMessage parse(String json);

    /**
     * Parses a JSON object into a chat message
     *
     * @param object json object
     * @return ChatMessage
     */
    public abstract ChatMessage parse(JsonObject object);

    /**
     * Gives a json string from the specified chat message
     *
     * @param message message
     * @return json string
     */
    public abstract String toString(ChatMessage message);

}
