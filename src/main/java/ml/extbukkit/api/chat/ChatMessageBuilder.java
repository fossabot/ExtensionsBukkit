package ml.extbukkit.api.chat;

/**
 * Represents a chat message builder
 */
public class ChatMessageBuilder {

    /**
     * Current chat message
     */
    private ChatMessage currentMessage;

    public ChatMessageBuilder(ChatMessage original) {
        this.currentMessage = original.duplicate();
    }

    public ChatMessageBuilder(String message) {
        this.currentMessage = new ChatMessage(message);
    }

    /**
     * Duplicates the current created message into a new one
     *
     * @return chat message
     */
    public ChatMessage create() {
        return currentMessage.duplicate();
    }

    //-------------------------------------------------------------------------------
    // I don't think its pointless to create comments for these

    public ChatMessageBuilder bold(boolean b) {
        currentMessage.setBold(b);
        return this;
    }

    public ChatMessageBuilder obfuscated(boolean b) {
        currentMessage.setObfuscated(b);
        return this;
    }

    public ChatMessageBuilder italic(boolean b) {
        currentMessage.setItalic(b);
        return this;
    }

    public ChatMessageBuilder strikethrough(boolean b) {
        currentMessage.setStrikethrough(b);
        return this;
    }

    public ChatMessageBuilder underline(boolean b) {
        currentMessage.setUnderline(b);
        return this;
    }

    public ChatMessageBuilder color(TextColor color) {
        currentMessage.setColor(color);
        return this;
    }

}