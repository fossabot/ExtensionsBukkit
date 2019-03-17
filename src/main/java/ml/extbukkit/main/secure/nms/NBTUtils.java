package ml.extbukkit.main.secure.nms;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import ml.extbukkit.main.secure.nms.reflection.NMSUtil;
import org.bukkit.entity.Entity;

import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTUtils {

    public static Object jsonToNbt(JsonObject jsonObject) {
        try {
            Class<?> mojangsonParserClass = NMSUtil.getNMSClass("MojangsonParser");
            Method parseMethod = mojangsonParserClass.getDeclaredMethod("parse", String.class);
            parseMethod.setAccessible(true);
            return parseMethod.invoke(mojangsonParserClass,
                    jsonObject.toString().replaceAll("\"(-?[0-9]*(\\.[0-9]*)?)([fsbdlFSBDL])\"", "$1$3"));
//                        return MojangsonParser.parse(jsonObject.toString().replaceAll("\"(-?[0-9]*(\\.[0-9]*)?)([fsbdlFSBDL])\"", "$1$3"));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            return null;
        }
    }

    public static JsonObject nbtToJson(Object nbt) {
        try {
            if (!nbt.getClass().isAssignableFrom(NMSUtil.getNMSClass("NBTTagCompound"))) {
                throw new IllegalArgumentException("Input is not NBTTagCompound");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JsonParser p = new JsonParser();
        JsonReader r = new JsonReader(new StringReader(nbt.toString()));
        r.setLenient(true);
        return p.parse(r).getAsJsonObject();
    }

    public static Object getEntityNbt(Entity entity) {
        try {
            Class<?> nbtClass = NMSUtil.getNMSClass("NBTTagCompound");
            Constructor<?> nbtConstructor = nbtClass.getDeclaredConstructor();
            nbtConstructor.setAccessible(true);
            Object nbt = nbtConstructor.newInstance();
            Object nmsEntity = getEntityConnection(entity);
            Method save = nmsEntity.getClass().getDeclaredMethod("save", nbtClass);
            save.setAccessible(true);
            return save.invoke(nmsEntity, nbt);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            return null;
        }
    }

    public static void setEntityNbt(Entity entity, Object nbt) {
        try {
            if (!nbt.getClass().isAssignableFrom(NMSUtil.getNMSClass("NBTTagCompound"))) {
                throw new IllegalArgumentException("Input is not NBTTagCompound");
            }
            Object nmsEntity = getEntityConnection(entity);
            Method f = nmsEntity.getClass().getDeclaredMethod("f", NMSUtil.getNMSClass("NBTTagCompound"));
            f.setAccessible(true);
            f.invoke(nmsEntity, nbt);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object getEntityConnection(Entity entity) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method getHandleMethod = entity.getClass().getDeclaredMethod("getHandle");
        getHandleMethod.setAccessible(true);
        return getHandleMethod.invoke(entity);
    }

}