package com.monsieurmahjong.commonjong.rules.generic.waits;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileGroupTest
{
    @Test
    public void testCollidesWith()
    {
        TileGroup bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        TileGroup ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        TileGroup suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);

        assertTrue(bambooRun123.collidesWith(ryanzouTriplet), "1-2-3 run should collide with 2 triplet.");
        assertFalse(bambooRun123.collidesWith(suuzouTriplet), "1-2-3 run should not collide with 4 triplet.");
    }

    @Test
    public void testIsRun()
    {
        TileGroup bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        TileGroup ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        TileGroup suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        TileGroup suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        TileGroup bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        TileGroup loneRed = TileGroup.of(MahjongTileKind.RED);

        assertTrue(bambooRun123.isRun(), "123s run is a run");
        assertFalse(ryanzouTriplet.isRun(), "222s triplet is not a run");
        assertFalse(suuzouTriplet.isRun(), "444s triplet is not a run");
        assertFalse(suuzouPair.isRun(), "44s pair is not a run");
        assertFalse(bambooProtogroup34.isRun(), "34s ryanmen is not a run");
        assertFalse(loneRed.isRun(), "7z tile is not a run");
    }
}
