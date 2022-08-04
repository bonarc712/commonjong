package com.monsieurmahjong.commonjong.rules.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MahjongTileKindTest
{
    /**
     * The tile kinds need to use this int-based index when working on some
     * algorithms, for instance for waits or shanten.
     */
    @Test
    public void testGetIndex()
    {
        assertEquals(0, MahjongTileKind.CHARACTERS_1.getIndex());
        assertEquals(2, MahjongTileKind.CHARACTERS_3.getIndex());
        assertEquals(26, MahjongTileKind.BAMBOOS_9.getIndex());
        assertEquals(29, MahjongTileKind.WEST.getIndex());
        assertEquals(31, MahjongTileKind.WHITE.getIndex());
    }

    @Test
    public void testGetFamily()
    {
        assertEquals(TileFamily.BAMBOOS, MahjongTileKind.BAMBOOS_1.getFamily());
        assertEquals(TileFamily.BAMBOOS, MahjongTileKind.BAMBOOS_3.getFamily());
        assertEquals(TileFamily.BAMBOOS, MahjongTileKind.BAMBOOS_7.getFamily());
        assertEquals(TileFamily.CHARACTERS, MahjongTileKind.CHARACTERS_1.getFamily());
        assertEquals(TileFamily.CHARACTERS, MahjongTileKind.CHARACTERS_5.getFamily());
        assertEquals(TileFamily.CHARACTERS, MahjongTileKind.CHARACTERS_6.getFamily());
        assertEquals(TileFamily.CHARACTERS, MahjongTileKind.CHARACTERS_9.getFamily());
        assertEquals(TileFamily.CIRCLES, MahjongTileKind.CIRCLES_1.getFamily());
        assertEquals(TileFamily.CIRCLES, MahjongTileKind.CIRCLES_2.getFamily());
        assertEquals(TileFamily.CIRCLES, MahjongTileKind.CIRCLES_4.getFamily());
        assertEquals(TileFamily.CIRCLES, MahjongTileKind.CIRCLES_6.getFamily());
        assertEquals(TileFamily.CIRCLES, MahjongTileKind.CIRCLES_8.getFamily());
        assertEquals(TileFamily.HONOURS, MahjongTileKind.SOUTH.getFamily());
        assertEquals(TileFamily.HONOURS, MahjongTileKind.GREEN.getFamily());
    }
}
