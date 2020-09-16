package com.monsieurmahjong.commonjong.game;

import java.util.*;

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

    public static List<Tile> asHand(String... tiles)
    {
        List<Tile> hand = new ArrayList<>();
        for (String tileName : tiles)
        {
            try
            {
                MahjongTileKind kind = MahjongTileKind.getMahjongTileByAbbreviation(tileName);
                Tile tile = new Tile(kind);
                hand.add(tile);
            }
            catch (IllegalArgumentException e)
            {
                continue;
            }
        }
        return hand;
    }

    @Override
    public String toString()
    {
        return TileLabels_fr.getLabel(tileKind);
    }
}
