package com.monsieurmahjong.commonjong.game;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.ui.locale.TileLabels_fr;

public class Tile extends Object
{
    private MahjongTileKind tileKind;
    private boolean red; // red/special tile

    public Tile(MahjongTileKind tileKind)
    {
        this.tileKind = tileKind;
    }

    public MahjongTileKind getTileKind()
    {
        return tileKind;
    }

    public boolean isRed()
    {
        return red;
    }

    public void setRed(boolean red)
    {
        this.red = red;
    }

    @Override
    public String toString()
    {
        return TileLabels_fr.getLabel(tileKind);
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Tile))
        {
            return false;
        }
        Tile otherTile = (Tile) other;
        return tileKind == otherTile.tileKind && red == otherTile.red;
    }
}
