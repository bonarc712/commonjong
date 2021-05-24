package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.*;
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
        Assertions.assertTrue(TileKindUtils.isTerminal(0));
        Assertions.assertTrue(TileKindUtils.isNumeral(0));
        Assertions.assertTrue(TileKindUtils.isTerminalOrHonour(0));
        Assertions.assertFalse(TileKindUtils.isHonor(0));
        // three of characters
        Assertions.assertFalse(TileKindUtils.isTerminal(2));
        Assertions.assertTrue(TileKindUtils.isNumeral(2));
        // nine of bamboos
        Assertions.assertTrue(TileKindUtils.isTerminalOrHonour(26));
        Assertions.assertTrue(TileKindUtils.isTerminal(26));
        Assertions.assertTrue(TileKindUtils.isNumeral(26));
        // west
        Assertions.assertTrue(TileKindUtils.isWind(29));
        Assertions.assertFalse(TileKindUtils.isDragon(29));
        Assertions.assertTrue(TileKindUtils.isTerminalOrHonour(29));
        Assertions.assertFalse(TileKindUtils.isTerminal(29));
        Assertions.assertFalse(TileKindUtils.isNumeral(29));
        // white dragon
        Assertions.assertFalse(TileKindUtils.isWind(31));
        Assertions.assertTrue(TileKindUtils.isDragon(31));
        Assertions.assertTrue(TileKindUtils.isTerminalOrHonour(31));
        Assertions.assertFalse(TileKindUtils.isTerminal(31));
        Assertions.assertFalse(TileKindUtils.isNumeral(31));
    }

    @Test
    public void testAreSameSuit()
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

    @Test
    public void testGetKindFromIndex()
    {
        Assertions.assertEquals(MahjongTileKind.CHARACTERS_1, TileKindUtils.getKindFromIndex(0));
        Assertions.assertEquals(MahjongTileKind.CHARACTERS_3, TileKindUtils.getKindFromIndex(2));
        Assertions.assertEquals(MahjongTileKind.BAMBOOS_9, TileKindUtils.getKindFromIndex(26));
        Assertions.assertEquals(MahjongTileKind.WEST, TileKindUtils.getKindFromIndex(29));
        Assertions.assertEquals(MahjongTileKind.WHITE, TileKindUtils.getKindFromIndex(31));
    }

    @Test
    public void testGetSeatFromTileKind()
    {
        Assertions.assertEquals(Seat.EAST, TileKindUtils.getSeatFromTileKind(MahjongTileKind.EAST));
        Assertions.assertEquals(Seat.SOUTH, TileKindUtils.getSeatFromTileKind(MahjongTileKind.SOUTH));
        Assertions.assertEquals(Seat.WEST, TileKindUtils.getSeatFromTileKind(MahjongTileKind.WEST));
        Assertions.assertEquals(Seat.NORTH, TileKindUtils.getSeatFromTileKind(MahjongTileKind.NORTH));
        Assertions.assertThrows(IllegalArgumentException.class, () -> TileKindUtils.getSeatFromTileKind(MahjongTileKind.BAMBOOS_3));
    }

    @Test
    public void testGetTileKindFromSeat()
    {
        Assertions.assertEquals(MahjongTileKind.EAST, TileKindUtils.getTileKindFromSeat(Seat.EAST));
        Assertions.assertEquals(MahjongTileKind.SOUTH, TileKindUtils.getTileKindFromSeat(Seat.SOUTH));
        Assertions.assertEquals(MahjongTileKind.WEST, TileKindUtils.getTileKindFromSeat(Seat.WEST));
        Assertions.assertEquals(MahjongTileKind.NORTH, TileKindUtils.getTileKindFromSeat(Seat.NORTH));
    }
}
