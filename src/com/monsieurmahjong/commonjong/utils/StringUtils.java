package com.monsieurmahjong.commonjong.utils;

public class StringUtils
{
    public static String ucFirst(String baseString)
    {
        if (baseString.length() < 2)
        {
            return baseString.toUpperCase();
        }
        String firstLetter = baseString.substring(0, 1);
        return firstLetter.toUpperCase() + baseString.substring(1);
    }

    public static String lcFirst(String baseString)
    {
        if (baseString.length() < 2)
        {
            return baseString.toLowerCase();
        }
        String firstLetter = baseString.substring(0, 1);
        return firstLetter.toLowerCase() + baseString.substring(1);
    }
}
