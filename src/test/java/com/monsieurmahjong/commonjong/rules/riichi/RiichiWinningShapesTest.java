package com.monsieurmahjong.commonjong.rules.riichi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;

public class RiichiWinningShapesTest
{
    @Test
    public void testRiichiWinningShapes()
    {
        var mpsz = new MPSZNotation();
        var winningShapes = new RiichiWinningShapes();

        var hand1 = mpsz.getTilesFrom("11123333444555z");
        assertFalse(winningShapes.isMahjong(hand1));
        hand1.add(new Tile(MahjongTileKind.SOUTH));
        hand1.sort((tile1, tile2) -> tile1.getTileKind().getIndex() - tile2.getTileKind().getIndex());
        assertTrue(winningShapes.isMahjong(hand1));
        assertTrue(winningShapes.isFourGroupsOnePair(hand1));
        assertFalse(winningShapes.isSevenPairs(hand1));

        var hand2 = mpsz.getTilesFrom("11123334445557z");
        assertFalse(winningShapes.isMahjong(hand2));

        var sevenPairs = mpsz.getTilesFrom("55664433771122z");
        assertTrue(winningShapes.isMahjong(sevenPairs));
        assertFalse(winningShapes.isFourGroupsOnePair(sevenPairs));
        assertTrue(winningShapes.isSevenPairs(sevenPairs));

        var thirteenOrphans = mpsz.getTilesFrom("11234567z19p19m19s");
        assertTrue(winningShapes.isMahjong(thirteenOrphans));
        assertFalse(winningShapes.isFourGroupsOnePair(thirteenOrphans));
        assertTrue(winningShapes.isThirteenOrphans(thirteenOrphans));
    }
}
