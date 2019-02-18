package ml.extbukkit.api.util;

import java.util.Map;

public class Validator {
    private Validator() {

    }
    public static boolean isNull(Object... check) {
        for(Object c : check)
            if(c == null)
                return true;
        return false;
    }
    public static boolean isPositive(long... n) {
        for(long l : n)
            if(l < 0)
                return false;
        return true;
    }
    public static boolean isPositive(int... n) {
        for(int i : n)
            if(i < 0)
                return false;
        return true;
    }
    public static boolean isNegative(long... n) {
        for(long l : n)
            if(l > 0)
                return false;
        return true;
    }
    public static boolean isNegative(int... n) {
        for(int i : n)
            if(i > 0)
                return false;
        return true;
    }
}