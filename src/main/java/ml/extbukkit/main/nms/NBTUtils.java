package ml.extbukkit.main.nms;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.MojangsonParser;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

import java.io.StringReader;

public class NBTUtils {
    public static NBTTagCompound jsonToNbt(JsonObject jsonObject) {
        try {
            return MojangsonParser.parse(jsonObject.toString());
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
}