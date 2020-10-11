package com.monsieurmahjong.commonjong.rules.generic;

import org.junit.jupiter.api.*;

public class MahjongTileKindTest
{
    /**
     * The tile kinds need to use this int-based index when working on some
     * algorithms, for instance for waits or shanten.
     */
    @Test
    public void testGetIndex()
    {
        Assertions.assertEquals(0, MahjongTileKind.CHARACTERS_1.getIndex());
        Assertions.assertEquals(2, MahjongTileKind.CHARACTERS_3.getIndex());
        Assertions.assertEquals(26, MahjongTileKind.BAMBOOS_9.getIndex());
        Assertions.assertEquals(29, MahjongTileKind.WEST.getIndex());
        Assertions.assertEquals(31, MahjongTileKind.WHITE.getIndex());
    }
}
