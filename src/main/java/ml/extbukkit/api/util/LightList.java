package ml.extbukkit.api.util;

public interface LightList<T> extends Iterable<T> {

    void set(int index, T object);

    T get(int index);

    void add(T object);

    boolean contains(T object);

    int size();

    int indexOf(T object);

    int indexOfLast(T object);
}