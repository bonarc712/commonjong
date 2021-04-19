package com.monsieurmahjong.commonjong.rules.riichi.yakus;

public interface Yaku
{
    public boolean isValid();

    public int getHanValue();

    public default boolean isYakuman()
    {
        return getHanValue() == 13;
    }

    public default boolean isDoubleYakuman()
    {
        return getHanValue() == 26;
    }
}
