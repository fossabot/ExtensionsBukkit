package ml.extbukkit.api.command;

import ml.extbukkit.api.chat.ChatMessage;

/**
 * Command executor class
 */
public interface ICommandExecutor extends IPermissible {
    /**
     * Send message to executor
     *
     * @param message Message to send
     */
    void sendMessage(String message);

    /**
     * Send messages to executor
     *
     * @param message Messages to send
     */
    void sendMessages(String... message);

    /**
     * Execute command as executor
     *
     * @param command Command to execute
     */
    void executeCommand(String command);

    /**
     * Sends a chat message to this executor
     *
     * @param message message to send
     */
    void sendMessage(ChatMessage message);
}