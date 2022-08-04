package com.monsieurmahjong.commonjong.rules.generic.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileKindUtilsTest
{
    @Test
    public void testAsHandString() throws Exception
    {
        var hand = TileKindUtils.asHand("345m345p345s1235z");
        assertTrue(hand.size() == 13);
        var handCopy = new ArrayList<Tile>();
        handCopy.addAll(Arrays.asList(new Tile(MahjongTileKind.CHARACTERS_3), new Tile(MahjongTileKind.CHARACTERS_4), new Tile(MahjongTileKind.CHARACTERS_5), new Tile(MahjongTileKind.CIRCLES_3),
                new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5), new Tile(MahjongTileKind.BAMBOOS_3), new Tile(MahjongTileKind.BAMBOOS_4), new Tile(MahjongTileKind.BAMBOOS_5),
                new Tile(MahjongTileKind.EAST), new Tile(MahjongTileKind.SOUTH), new Tile(MahjongTileKind.WEST), new Tile(MahjongTileKind.WHITE)));
        assertTrue(hand.equals(handCopy));
    }

    @Test
    public void testGetHandAsMPSZNotation() throws Exception
    {
        var handTiles = new ArrayList<Tile>();
        handTiles.addAll(Arrays.asList(new Tile(MahjongTileKind.CHARACTERS_3), new Tile(MahjongTileKind.CHARACTERS_4), new Tile(MahjongTileKind.CHARACTERS_5), new Tile(MahjongTileKind.CIRCLES_3),
                new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5), new Tile(MahjongTileKind.BAMBOOS_3), new Tile(MahjongTileKind.BAMBOOS_4), new Tile(MahjongTileKind.BAMBOOS_5),
                new Tile(MahjongTileKind.EAST), new Tile(MahjongTileKind.SOUTH), new Tile(MahjongTileKind.WEST), new Tile(MahjongTileKind.WHITE)));
        assertEquals("345m345p345s1235z", TileKindUtils.getHandAsMPSZNotation(handTiles), "345m345p345s1235z tiles are not correctly represented");
    }

    @Test
    public void testTileProperties()
    {
        // one of characters
        assertTrue(TileKindUtils.isTerminal(0));
        assertTrue(TileKindUtils.isNumeral(0));
        assertTrue(TileKindUtils.isTerminalOrHonour(0));
        assertFalse(TileKindUtils.isHonor(0));
        // three of characters
        assertFalse(TileKindUtils.isTerminal(2));
        assertTrue(TileKindUtils.isNumeral(2));
        // nine of bamboos
        assertTrue(TileKindUtils.isTerminalOrHonour(26));
        assertTrue(TileKindUtils.isTerminal(26));
        assertTrue(TileKindUtils.isNumeral(26));
        // west
        assertTrue(TileKindUtils.isWind(29));
        assertFalse(TileKindUtils.isDragon(29));
        assertTrue(TileKindUtils.isTerminalOrHonour(29));
        assertFalse(TileKindUtils.isTerminal(29));
        assertFalse(TileKindUtils.isNumeral(29));
        // white dragon
        assertFalse(TileKindUtils.isWind(31));
        assertTrue(TileKindUtils.isDragon(31));
        assertTrue(TileKindUtils.isTerminalOrHonour(31));
        assertFalse(TileKindUtils.isTerminal(31));
        assertFalse(TileKindUtils.isNumeral(31));
    }

    @Test
    public void testAreSameSuit()
    {
        assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.BAMBOOS_9.ordinal()));
        assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_6.ordinal(), MahjongTileKind.CIRCLES_6.ordinal()));
        assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_6.ordinal(), MahjongTileKind.CIRCLES_2.ordinal()));
        assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_9.ordinal()));
        assertTrue(TileKindUtils.areSameSuit(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_7.ordinal()));

        assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.WHITE.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.CIRCLES_9.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));

        // Honours are not part of a suit
        assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.EAST.ordinal(), MahjongTileKind.SOUTH.ordinal()));
        assertFalse(TileKindUtils.areSameSuit(MahjongTileKind.EAST.ordinal(), MahjongTileKind.WHITE.ordinal()));
    }

    @Test
    public void testGetKindFromIndex()
    {
        assertEquals(MahjongTileKind.CHARACTERS_1, TileKindUtils.getKindFromIndex(0));
        assertEquals(MahjongTileKind.CHARACTERS_3, TileKindUtils.getKindFromIndex(2));
        assertEquals(MahjongTileKind.BAMBOOS_9, TileKindUtils.getKindFromIndex(26));
        assertEquals(MahjongTileKind.WEST, TileKindUtils.getKindFromIndex(29));
        assertEquals(MahjongTileKind.WHITE, TileKindUtils.getKindFromIndex(31));
    }

    @Test
    public void testGetSeatFromTileKind()
    {
        assertEquals(Seat.EAST, TileKindUtils.getSeatFromTileKind(MahjongTileKind.EAST));
        assertEquals(Seat.SOUTH, TileKindUtils.getSeatFromTileKind(MahjongTileKind.SOUTH));
        assertEquals(Seat.WEST, TileKindUtils.getSeatFromTileKind(MahjongTileKind.WEST));
        assertEquals(Seat.NORTH, TileKindUtils.getSeatFromTileKind(MahjongTileKind.NORTH));
        assertThrows(IllegalArgumentException.class, () -> TileKindUtils.getSeatFromTileKind(MahjongTileKind.BAMBOOS_3));
    }

    @Test
    public void testGetTileKindFromSeat()
    {
        assertEquals(MahjongTileKind.EAST, TileKindUtils.getTileKindFromSeat(Seat.EAST));
        assertEquals(MahjongTileKind.SOUTH, TileKindUtils.getTileKindFromSeat(Seat.SOUTH));
        assertEquals(MahjongTileKind.WEST, TileKindUtils.getTileKindFromSeat(Seat.WEST));
        assertEquals(MahjongTileKind.NORTH, TileKindUtils.getTileKindFromSeat(Seat.NORTH));
    }
}
