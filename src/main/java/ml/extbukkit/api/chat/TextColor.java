package ml.extbukkit.api.chat;

import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;

/**
 * Represents a color
 */
public enum TextColor {
  /**
   * Black color
   */
  BLACK('0'),

  /**
   * Dark blue color
   */
  DARK_BLUE('1'),

  /**
   * Dark green color
   */
  DARK_GREEN('2'),

  /**
   * Dark aqua color
   */
  DARK_AQUA('3'),

  /**
   * Dark red color
   */
  DARK_RED('4'),

  /**
   * Dark purple color
   */
  DARK_PURPLE('5'),

  /**
   * Gold color
   */
  GOLD('6'),

  /**
   * Gray color
   */
  GRAY('7'),

  /**
   * Dark gray color
   */
  DARK_GRAY('8'),

  /**
   * Blue color
   */
  BLUE('9'),

  /**
   * Green color
   */
  GREEN('a'),

  /**
   * Aqua color
   */
  AQUA('b'),

  /**
   * Red color
   */
  RED('c'),

  /**
   * Light purple color
   */
  LIGHT_PURPLE('d'),

  /**
   * Yellow color
   */
  YELLOW('e'),

  /**
   * White color
   */
  WHITE('f'),

    /*MAGIC('k'),
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINE('n'),
    ITALIC('o'),**/

  /**
   * Resets all colors
   */
  RESET('r');

  /**
   * Upside down: the character used by minecraft for colors; the current color character
   */
  private static final char COLOR_CHAR = '\u00A7';
  private char code;

  /**
   * All color codes, used for translating
   */
  public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

  /**
   * Map, containing all colors by theirs characters
   */
  private static Map<Character, TextColor> BY_CHAR = new HashMap<>();

  private String toString;

  TextColor(char code) {
    this.code = code;
    this.toString = new String(new char[]
      {
        COLOR_CHAR, code
      });
  }

  @Override
  public String toString() {
    return toString;
  }

  /**
   * Gets the character used by minecraft for handling color
   *
   * @return character
   */
  public static char getCharUsedByMinecraft() {
    return COLOR_CHAR;
  }

  /**
   * Gets the current color's character
   *
   * @return color character
   */
  public char getColorCode() {
    return code;
  }

  static {
    for(TextColor color : values()) {
      BY_CHAR.put(color.code, color);
    }
  }

  /**
   * Gets the color by it's character
   *
   * @param color color character
   * @return text color of the character
   */
  public static TextColor byChar(char color) {
    return BY_CHAR.get(color);
  }

  /**
   * Translates all color codes via a new char
   *
   * @param newChar new color char
   * @param translatedText text which is translated
   * @return translated string
   */
  public static String translateColors(char newChar, String translatedText) {
    char[] mChar = translatedText.toCharArray();
    for(int i = 0; i < mChar.length - 1; i++) {
      if(mChar[i] == newChar && ALL_CODES.indexOf(mChar[i + 1]) > -1) {
        mChar[i] = ChatColor.COLOR_CHAR;
        mChar[i + 1] = Character.toLowerCase(mChar[i + 1]);
      }
    }
    return new String(mChar);
  }

}
