package ml.extbukkit.api.util;

import java.util.Iterator;

public interface LightList<T> extends Iterable<T> {
    void set(int index, T object);
    T get(int index);
    void add(T object);
    boolean contains(T object);
    int size();
    @Override
    Iterator<T> iterator();
    int indexOf(T object);
    int indexOfLast(T object);
}