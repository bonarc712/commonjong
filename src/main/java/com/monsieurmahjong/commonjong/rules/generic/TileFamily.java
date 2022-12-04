package com.monsieurmahjong.commonjong.rules.generic;

public enum TileFamily
{
    CHARACTERS, //
    CIRCLES, //
    BAMBOOS, //
    HONOURS, //
    NONE;

    public boolean isNumeral()
    {
        return this == CHARACTERS || this == CIRCLES || this == BAMBOOS;
    }
}
