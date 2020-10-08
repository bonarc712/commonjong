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

    @Test
    public void testTileProperties()
    {
        // one of characters
        Assertions.assertTrue(TileKindUtils.isEnd(0));
        Assertions.assertTrue(TileKindUtils.isNumeral(0));
        Assertions.assertTrue(TileKindUtils.isTerminal(0));
        Assertions.assertFalse(TileKindUtils.isHonor(0));
        // three of characters
        Assertions.assertFalse(TileKindUtils.isEnd(2));
        Assertions.assertTrue(TileKindUtils.isNumeral(2));
        // nine of bamboos
        Assertions.assertTrue(TileKindUtils.isTerminal(26));
        Assertions.assertTrue(TileKindUtils.isEnd(26));
        Assertions.assertTrue(TileKindUtils.isNumeral(26));
        // west
        Assertions.assertTrue(TileKindUtils.isWind(29));
        Assertions.assertFalse(TileKindUtils.isDragon(29));
        Assertions.assertTrue(TileKindUtils.isTerminal(29));
        Assertions.assertFalse(TileKindUtils.isEnd(29));
        Assertions.assertFalse(TileKindUtils.isNumeral(29));
        // white dragon
        Assertions.assertFalse(TileKindUtils.isWind(31));
        Assertions.assertTrue(TileKindUtils.isDragon(31));
        Assertions.assertTrue(TileKindUtils.isTerminal(31));
        Assertions.assertFalse(TileKindUtils.isEnd(31));
        Assertions.assertFalse(TileKindUtils.isNumeral(31));
    }

    @Test
    public void areSameSuitTest()
    {
        Assertions.assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.BAMBOOS_9.ordinal()));
        Assertions.assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_6.ordinal(), MahjongTileKind.CIRCLES_6.ordinal()));
        Assertions.assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_6.ordinal(), MahjongTileKind.CIRCLES_2.ordinal()));
        Assertions.assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_9.ordinal()));
        Assertions.assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_7.ordinal()));

        Assertions.assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        Assertions.assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.WHITE.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        Assertions.assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_9.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));

        // Honours are not part of a suit
        Assertions.assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.EAST.ordinal(), MahjongTileKind.SOUTH.ordinal()));
        Assertions.assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.EAST.ordinal(), MahjongTileKind.WHITE.ordinal()));
    }
}
