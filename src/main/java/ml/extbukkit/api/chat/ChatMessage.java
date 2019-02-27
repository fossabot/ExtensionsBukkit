package ml.extbukkit.api.chat;

import lombok.Data;

/**
 * Represents a chat message, which works with json
 */
@Data
public class ChatMessage
{

    /**
     * Sent message
     */
    private String message;
    //--------------------------------------------------------------------------

    /**
     * Modifiers of the message
     */
    private boolean bold = false;
    private boolean underline = false;
    private boolean italic = false;
    private boolean strikethrough = false;
    private boolean obfuscated = false;
    //--------------------------------------------------------------------------

    public ChatMessage(String message)
    {
        this.message = message;
    }

    public ChatMessage(ChatMessage original)
    {
        this.message = original.getMessage();
        this.bold = original.isBold();
        this.underline = original.isUnderline();
        this.italic = original.isItalic();
        this.strikethrough = original.isStrikethrough();
        this.obfuscated - original.isObfuscated();
    }

    /**
     * Creates a new chat message from this one.
     *
     * @return cloned message
     */
    public ChatMessage duplicate()
    {
        ChatMessage newMessage = new ChatMessage( message );
        newMessage.setBold( bold );
        newMessage.setUnderline( underline );
        newMessage.setItalic( italic );
        newMessage.setStrikethrough( strikethrough );
        newMessage.setObfuscated( obfuscated );
        return newMessage;
    }

}