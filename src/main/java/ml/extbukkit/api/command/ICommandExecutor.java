package ml.extbukkit.api.command;

import com.google.gson.JsonArray;

/**
 * Command executor class
 */
public interface ICommandExecutor {
    /**
     * Check permission
     *
     * @param permission Permission to check
     * @return true, if object has permission
     */
    boolean hasPermission(String permission);
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
    void sendMessage(JsonArray message);

    /**
     * Get name of this executor
     *
     * @return name
     */
    String getName();
}