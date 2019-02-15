package ml.extbukkit.main.nms;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.MojangsonParser;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

import java.io.StringReader;

public class NBTUtils {
    public static NBTTagCompound jsonToNbt(JsonObject jsonObject) {
        try {
            return MojangsonParser.parse(jsonObject.toString().replaceAll("\"(-?[0-9]*(\\.[0-9]*)?)([fsbdlFSBDL])\"", "$1$3"));
        } catch (CommandSyntaxException e) {
            return null;
        }
    }
    public static JsonObject nbtToJson(NBTTagCompound nbt) {
        JsonParser p = new JsonParser();
        JsonReader r = new JsonReader(new StringReader(nbt.toString()));
        r.setLenient(true);
        return p.parse(r).getAsJsonObject();
    }
    public static NBTTagCompound getEntityNbt(Entity entity) {
        NBTTagCompound nbt = new NBTTagCompound();
        ((CraftEntity) entity).getHandle().save(nbt);
        return nbt;
    }
    public static void setEntityNbt(Entity entity, NBTTagCompound nbt) {
        try {
            ((CraftEntity) entity).getHandle().f(nbt);
        } catch (Exception e) {}
    }
}