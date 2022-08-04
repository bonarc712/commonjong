package com.monsieurmahjong.commonjong.rules.generic;

import java.util.Comparator;

import com.monsieurmahjong.commonjong.game.Tile;

public class MahjongTileOrderingComparator implements Comparator<Tile>
{
    @Override
    public int compare(Tile tile1, Tile tile2)
    {
        var firstTile = tile1.getTileKind();
        var secondTile = tile2.getTileKind();
        if (firstTile.equals(secondTile))
        {
            return 0;
        }
        return firstTile.ordinal() - secondTile.ordinal();
    }
}
