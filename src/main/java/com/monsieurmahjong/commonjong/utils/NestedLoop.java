package com.monsieurmahjong.commonjong.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NestedLoop
{
    public <T> Stream<Pair<T, T>> loopOn(List<T> list)
    {
        var listToReturn = new ArrayList<Pair<T, T>>();
        for (var i = 0; i < list.size(); i++)
        {
            for (var j = i + 1; j < list.size(); j++)
            {
                listToReturn.add(new Pair<>(list.get(i), list.get(j)));
            }
        }
        return listToReturn.stream();
    }
}
