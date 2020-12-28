package com.monsieurmahjong.commonjong.rules.generic;

import java.util.Comparator;

public class MahjongTileKindComparator implements Comparator<MahjongTileKind>
{
    @Override
    public int compare(MahjongTileKind firstTile, MahjongTileKind secondTile)
    {
        if (firstTile.equals(secondTile))
        {
            return 0;
        }
        return firstTile.ordinal() - secondTile.ordinal();
    }
}
