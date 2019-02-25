package ml.extbukkit.api.chat;

import lombok.Data;

/**
 * Represents a chat message
 */
@Data
public class ChatMessage
{

    private boolean bold = false;
    private boolean obfuscated = false;
    private boolean strikeout = false;
    private boolean italic = false;
    private boolean underlined = false;
    private String message;

    public ChatMessage(String message)
    {
        this.message = message;
    }

}