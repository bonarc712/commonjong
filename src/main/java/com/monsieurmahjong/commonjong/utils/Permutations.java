package com.monsieurmahjong.commonjong.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * This class was written by Per-Åke Minborg. See
 * {@link https://dzone.com/articles/java-8-master-permutations}
 */
public class Permutations
{
    private Permutations()
    {
    }

    public static long factorial(int n)
    {
        if (n > 20 || n < 0)
        {
            throw new IllegalArgumentException(n + " is out of range");
        }
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

    public static <T> List<T> permutation(long no, List<T> items)
    {
        return permutationHelper(no, new LinkedList<>(Objects.requireNonNull(items)), new ArrayList<>());
    }

    private static <T> List<T> permutationHelper(long no, LinkedList<T> in, List<T> out)
    {
        if (in.isEmpty())
        {
            return out;
        }
        var subFactorial = factorial(in.size() - 1);
        out.add(in.remove((int) (no / subFactorial)));
        return permutationHelper((int) (no % subFactorial), in, out);
    }

    @SafeVarargs
    @SuppressWarnings("varargs") // Creating a List from an array is safe
    public static <T> Stream<Stream<T>> of(T... items)
    {
        List<T> itemList = Arrays.asList(items);
        return LongStream.range(0, factorial(items.length)).mapToObj(no -> permutation(no, itemList).stream());
    }
}