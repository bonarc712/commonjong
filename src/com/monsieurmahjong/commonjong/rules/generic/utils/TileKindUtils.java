package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileKindUtils
{
    /**
     * This method returns a hand made of the tiles in input. 
     * The strings in input must make use of the
     * {@link MahjongTileKind} abbreviation, which falls under
     * the Tenhou notation (1m, 2m, 3m, 1p, etc.)
     * 
     * @param tiles : all the tiles that make up the hand
     */
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
}
