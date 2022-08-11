package com.monsieurmahjong.commonjong.rules.generic.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

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
    public void testGetKindFromIndex()
    {
        assertEquals(MahjongTileKind.CHARACTERS_1, MahjongTileKind.getKindFromIndex(0));
        assertEquals(MahjongTileKind.CHARACTERS_3, MahjongTileKind.getKindFromIndex(2));
        assertEquals(MahjongTileKind.BAMBOOS_9, MahjongTileKind.getKindFromIndex(26));
        assertEquals(MahjongTileKind.WEST, MahjongTileKind.getKindFromIndex(29));
        assertEquals(MahjongTileKind.WHITE, MahjongTileKind.getKindFromIndex(31));
    }
}
