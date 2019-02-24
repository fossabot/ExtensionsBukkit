package ml.extbukkit.api.util;

/**
 * Class for checking values
 */
public class Validator {
    private Validator() {

    }

    /**
     * Check if any of objects is null
     *
     * @param check List of objects to check
     * @return true, if one of it is null
     */
    public static boolean isNull(Object... check) {
        for (Object c : check)
        {
            if (c == null)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all longs is positive
     *
     * @param n List of longs
     * @return true, if all longs is positive
     */
    public static boolean isPositive(long... n) {
        for (long l : n)
        {
            if (l < 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all ints is positive
     *
     * @param n List of ints
     * @return true, if all ints is positive
     */
    public static boolean isPositive(int... n) {
        for (int i : n)
        {
            if (i < 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all longs is negative
     *
     * @param n List of longs
     * @return true, if all longs is negative
     */
    public static boolean isNegative(long... n) {
        for (long l : n)
        {
            if (l > 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if all ints is negative
     *
     * @param n List of ints
     * @return true, if all ints is negative
     */
    public static boolean isNegative(int... n) {
        for (int i : n)
        {
            if (i > 0)
            {
                return false;
            }
        }
        return true;
    }
}