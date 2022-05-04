package us.hiai.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayBuilder <T>
{
    private LinkedList<T> data = new LinkedList<>();

    public ArrayBuilder(T... array)
    {
        data.addAll(Arrays.asList(array));
    }

    public ArrayBuilder<T> add(T elem)
    {
        data.add(elem);
        return this;
    }

    public ArrayBuilder<T> addAll(T... elems)
    {
        data.addAll(Arrays.asList(elems));
        return this;
    }

    public ArrayBuilder<T> addFirst(T elem)
    {
        data.addFirst(elem);
        return this;
    }

    public ArrayBuilder<T> addAllFirst(T... elems)
    {
        for (T t : elems)
        {
            data.addFirst(t);
        }
        return this;
    }

    public T[] build()
    {
        return (T[]) data.toArray();
    }
}
