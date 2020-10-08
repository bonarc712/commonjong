package com.monsieurmahjong.commonjong.utils;

public class MathUtils
{
    public static int roundToUpperTen(int score)
    {
        if (score % 10 == 0)
        {
            return score;
        }
        return (score / 10 + 1) * 10;
    }

    public static int roundToUpperHundred(int score)
    {
        if (score % 100 == 0)
        {
            return score;
        }
        return (score / 100 + 1) * 100;
    }
}
