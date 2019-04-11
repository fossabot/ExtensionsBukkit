package ml.extbukkit.api.text;

/**
 * Text colors enum
 */
public enum TextColor {
    /**
     * Black color
     */
    BLACK('0', "black"),

    /**
     * Dark blue color
     */
    DARK_BLUE('1', "dark_blue"),

    /**
     * Dark green color
     */
    DARK_GREEN('2', "dark_green"),

    /**
     * Dark aqua color
     */
    DARK_AQUA('3', "dark_aqua"),

    /**
     * Dark red color
     */
    DARK_RED('4', "dark_red"),

    /**
     * Dark purple color
     */
    DARK_PURPLE('5', "dark_purple"),

    /**
     * Gold color
     */
    GOLD('6', "gold"),

    /**
     * Gray color
     */
    GRAY('7', "gray"),

    /**
     * Dark gray color
     */
    DARK_GRAY('8', "dark_gray"),

    /**
     * Blue color
     */
    BLUE('9', "blue"),

    /**
     * Green color
     */
    GREEN('a', "green"),

    /**
     * Aqua color
     */
    AQUA('b', "aqua"),

    /**
     * Red color
     */
    RED('c', "red"),

    /**
     * Purple color
     */
    PURPLE('d', "purple"),

    /**
     * Yellow color
     */
    YELLOW('e', "yellow"),

    /**
     * White color
     */
    WHITE('f', "white"),

    /**
     * Obfuscated color<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    OBFUSCATED('k', "obfuscated"),

    /**
     * Bold color<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    BOLD('l', "bold"),

    /**
     * Strikethrough color<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    STRIKETHROUGH('m', "strikethrough"),

    /**
     * Underlinedcolor<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    UNDERLINED('n', "underlined"),

    /**
     * Italic color<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    ITALIC('o', "italic"),

    /**
     * Reset color<br>
     * <strong>NOT RECOMMENDED</strong>
     */
    RESET('r', "reset");

    private final char CHAR;
    private final String NAME;
    TextColor(char c, String n) {
        CHAR = c;
        NAME = n;
    }

    /**
     * Get color char
     * @return color char
     */
    public char getChar() {
        return CHAR;
    }

    /**
     * Get color name
     * @return color name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Convert to string (color name)
     * @return color name
     */
    @Override
    public String toString() {
        return getName();
    }
}