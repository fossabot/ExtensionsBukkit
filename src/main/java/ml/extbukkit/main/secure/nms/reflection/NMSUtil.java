package ml.extbukkit.main.secure.nms.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NMSUtil {
    private static String nmsVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    public static Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + nmsVersion + "." + name);
    }

    public static Class<?> getOBCClass(String name) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + nmsVersion + "." + name);
    }

    public static Object getPlayerConnection(Player player) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Method getHandleMethod = player.getClass().getMethod("getHandle");
        getHandleMethod.setAccessible(true);
        Object nmsPlayer = getHandleMethod.invoke(player);
        Field connectionField = nmsPlayer.getClass().getField("playerConnection");
        connectionField.setAccessible(true);
        return connectionField.get(nmsPlayer);
    }

    public static boolean isUnder1_13() {
        return nmsVersion.equalsIgnoreCase("v1_8_R1") ||
                nmsVersion.equalsIgnoreCase("v1_8_R2") ||
                nmsVersion.equalsIgnoreCase("v1_8_R3") ||
                nmsVersion.equalsIgnoreCase("v1_9_R1") ||
                nmsVersion.equalsIgnoreCase("v1_9_R2") ||
                nmsVersion.equalsIgnoreCase("v1_10_R1") ||
                nmsVersion.equalsIgnoreCase("v1_11_R1") ||
                nmsVersion.equalsIgnoreCase("v1_12_R1");
    }

}