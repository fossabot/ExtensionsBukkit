package net.extbukkit.main.nms;

import net.extbukkit.api.nbt.INBTTag;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class NBTTag implements INBTTag {
    NBTTagCompound nbt;

    public NBTTag(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    @Override
    public Object get(String path) {
        NBTTagCompound nbt2 = nbt.clone();
        for(int i = 0; i < path.split("\\.").length; i++) {
            String str = path.split("\\.")[i];
            if(i - 1 == path.split("\\.").length) {
                return null;
            }
            else nbt2 = nbt2.getCompound(str);
        }
        return null;
    }

    @Override
    public void set(String path) {

    }
}