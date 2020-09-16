package com.monsieurmahjong.commonjong.rules.generic;

public class TileConverter
{
    public static MahjongTileKind getTileKindFromIndex(int index)
    {
        return MahjongTileKind.values()[index];
    }
}
