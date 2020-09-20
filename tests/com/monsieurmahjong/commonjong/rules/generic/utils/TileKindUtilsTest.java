package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileKindUtilsTest
{
    @Test
    public void testAsHandString() throws Exception
    {
        List<Tile> hand = TileKindUtils.asHand("345m345p345s1235z");
        Assertions.assertTrue(hand.size() == 13);
        List<Tile> handCopy = new ArrayList<>();
        handCopy.addAll(Arrays.asList(new Tile(MahjongTileKind.CHARACTERS_3), new Tile(MahjongTileKind.CHARACTERS_4), new Tile(MahjongTileKind.CHARACTERS_5), new Tile(MahjongTileKind.CIRCLES_3),
                new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5), new Tile(MahjongTileKind.BAMBOOS_3), new Tile(MahjongTileKind.BAMBOOS_4), new Tile(MahjongTileKind.BAMBOOS_5),
                new Tile(MahjongTileKind.EAST), new Tile(MahjongTileKind.SOUTH), new Tile(MahjongTileKind.WEST), new Tile(MahjongTileKind.WHITE)));
        Assertions.assertTrue(hand.equals(handCopy));
    }
}
