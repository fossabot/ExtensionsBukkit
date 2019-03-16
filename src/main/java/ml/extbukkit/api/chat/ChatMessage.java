package ml.extbukkit.api.chat;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a chat message, which works with json
 */
@Data
@Builder(builderClassName = "Builder")
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
    private boolean bold;
    private boolean underline;
    private boolean italic;
    private boolean strikethrough;
    private boolean obfuscated;
    private TextColor color;
    //--------------------------------------------------------------------------

    public ChatMessage(String message)
    {
        init( message, false, false, false, false, false, TextColor.WHITE );
    }

    public ChatMessage(ChatMessage original)
    {
        init( original.getMessage(), original.isBold(), original.isUnderline(), original.isItalic(), original.isStrikethrough(), original.isObfuscated(), original.getColor() );
    }

    public ChatMessage(String message, boolean bold, boolean underline, boolean italic, boolean strikethrough, boolean obfuscated, TextColor color)
    {
        init( message, bold, underline, italic, strikethrough, obfuscated, color );
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
        newMessage.setColor( color );
        return newMessage;
    }

    private void init(String message, boolean bold, boolean underline, boolean italic, boolean strikethrough, boolean obfuscated, TextColor color)
    {
        this.message = message;
        this.bold = bold;
        this.underline = underline;
        this.italic = italic;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
    }

}