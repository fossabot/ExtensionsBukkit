package net.extbukkit.api.event;

public interface Preventable {
    void setPrevented(boolean value);
    boolean isPrevented();
}