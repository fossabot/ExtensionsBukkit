package net.extbukkit.api.nbt;

public interface INBTTag {
    Object get(String path);
    void set(String path);
}