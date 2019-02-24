package ml.extbukkit.api.util;

import java.util.Iterator;

public class LightArrayList<T> implements LightList<T> {

    private Object[] arr;

    public LightArrayList(int initialCapacity) {
        arr = new Object[initialCapacity];
    }
    public LightArrayList() {
        this(0);
    }

    @Override
    public void set(int index, T object) {
        if(index < 0) return;
        if(arr.length < index + 1)  {
            Object[] prev = arr.clone();
            arr = new Object[index + 1];
            for(int i = 0; i < prev.length; i++)
                arr[i] = prev[i];
        }
        arr[index] = object;
    }

    @Override
    public T get(int index) {
        if(arr.length < index + 1) return null;
        return (T) arr[index];
    }

    @Override
    public void add(T object) {
        set(size(), object);
    }

    @Override
    public boolean contains(T object) {
        for(Object obj : arr)
            if(object.equals(obj))
                return true;
        return false;
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public int indexOf(T object) {
        for(int i = 0; i < arr.length; i++)
            if(object.equals(arr[i]))
                return i;
        return -1;
    }

    @Override
    public int indexOfLast(T object) {
        int li = -1;
        for(int i = 0; i < arr.length; i++)
            if(object.equals(arr[i]))
                li = i;
        return li;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            T[] la = (T[]) arr.clone();
            int cur = 0;

            @Override
            public boolean hasNext() {
                return la.length > cur + 1;
            }

            @Override
            public T next() {
                if(!hasNext()) return null;
                cur++;
                return la[cur - 1];
            }

            @Override
            public void remove() {
                arr[cur] = null;
            }
        };
    }
}