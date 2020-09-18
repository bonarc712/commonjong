package com.monsieurmahjong.commonjong.game;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.ui.locale.TileLabels_fr;

public class Tile
{
    private MahjongTileKind tileKind;

    public Tile(MahjongTileKind tileKind)
    {
        this.tileKind = tileKind;
    }

    public MahjongTileKind getTileKind()
    {
        return tileKind;
    }

    @Override
    public String toString()
    {
        return TileLabels_fr.getLabel(tileKind);
    }
}
