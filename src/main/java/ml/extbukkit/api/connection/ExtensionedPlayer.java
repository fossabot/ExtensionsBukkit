package ml.extbukkit.api.connection;

import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.world.entity.Entity;

/**
 * Represents a player, that is extensioned for our purpose :)
 */
public interface ExtensionedPlayer extends Entity {

  /**
   * Gets the player display name
   *
   * @return player display name
   */
  String getDisplayName();

  /**
   * Sets a new display name for the player
   *
   * @param displayName new display name
   */
  void setDisplayName(String displayName);

  /**
   * Sets the player's tablist name and the name above his head
   * <b>NOTE: You can't change the player's name with this, but nickname.</b>
   *
   * @param name new name
   */
  void setPlayerListName(String name);

  /**
   * Checks if the player sneaks
   *
   * @return true if sneaking
   */
  boolean isSneaking();

  /**
   * Sends a title with default timings. (20, 80, 20)
   *
   * @param title sent title
   * @param subtitle sent subtitle
   */
  void sendTitle(String title, String subtitle);

  /**
   * Sends a title with the ChatMessage API this API provides with default timings. (20, 80, 20)
   *
   * @param title sent title
   * @param subTitle sent subtitle
   */
  void sendTitle(ChatMessage title, ChatMessage subTitle);

  /**
   * Sends a title.
   *
   * @param title sent title
   * @param subtitle sent subtitle
   * @param fadeIn fade in time (ticks)
   * @param stay stay time (ticks)
   * @param fadeOut fade out time (ticks)
   */
  void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);

  /**
   * Sends a title with the ChatMessage API this API provides
   *
   * @param title sent title
   * @param subtitle sent subtitle
   * @param fadeIn fade in time (ticks)
   * @param stay stay time (ticks)
   * @param fadeOut fade out time (ticks)
   */
  void sendTitle(ChatMessage title, ChatMessage subtitle, int fadeIn, int stay, int fadeOut);

  /**
   * Sends an actionbar message
   *
   * @param message sent message
   */
  void sendActionbar(String message);

  /**
   * Sends an actionbar message via the ChatMessage API this API provides
   *
   * @param message sent message
   */
  void sendActionbar(ChatMessage message);

  /**
   * Sends an actionbar message with stay time
   *
   * @param message sent message
   * @param extension extension, assigned to
   * @param stayTime stay time (seconds)
   */
  void sendActionbar(String message, Extension extension, int stayTime);

  /**
   * Sends an actionbar message via the ChatMessage API this API provides
   * with stay time.
   *
   * @param message sent message
   * @param extension extension, assigned to
   * @param stayTime stay time (seconds)
   */
  void sendActionbar(ChatMessage message, Extension extension, int stayTime);
}