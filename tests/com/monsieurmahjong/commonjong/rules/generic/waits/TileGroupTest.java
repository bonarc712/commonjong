package com.monsieurmahjong.commonjong.rules.generic.waits;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileGroupTest
{
    @Test
    public void testCollidesWith()
    {
        TileGroup bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        TileGroup ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        TileGroup suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);

        Assertions.assertTrue(bambooRun123.collidesWith(ryanzouTriplet), "1-2-3 run should collide with 2 triplet.");
        Assertions.assertFalse(bambooRun123.collidesWith(suuzouTriplet), "1-2-3 run should not collide with 4 triplet.");
    }
}
