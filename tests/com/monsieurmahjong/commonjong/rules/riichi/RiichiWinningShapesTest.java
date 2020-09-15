package com.monsieurmahjong.commonjong.rules.riichi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.game.mahjong.MahjongTileKind;

public class RiichiWinningShapesTest
{
    @Test
    public void riichiWinningShapesTest()
    {
        RiichiWinningShapes winningShapes = new RiichiWinningShapes();
        List<Tile> hand1 = Tile.asHand("5z", "5z", "5z", "4z", "4z", "4z", "3z", "3z", "3z", "1z", "1z", "1z", "2z");

        assertFalse(winningShapes.isMahjong(hand1));
        hand1.add(new Tile(MahjongTileKind.SOUTH));
        assertTrue(winningShapes.isMahjong(hand1));
        assertTrue(winningShapes.isFourGroupsOnePair(hand1));
        assertFalse(winningShapes.isSevenPairs(hand1));

        List<Tile> hand2 = Tile.asHand("5z", "5z", "5z", "4z", "4z", "4z", "3z", "3z", "3z", "1z", "1z", "1z", "2z", "7z");
        assertFalse(winningShapes.isMahjong(hand2));

        List<Tile> sevenPairs = Tile.asHand("5z", "5z", "6z", "6z", "4z", "4z", "3z", "3z", "7z", "7z", "1z", "1z", "2z", "2z");
        assertTrue(winningShapes.isMahjong(sevenPairs));
        assertFalse(winningShapes.isFourGroupsOnePair(sevenPairs));
        assertTrue(winningShapes.isSevenPairs(sevenPairs));

        List<Tile> thirteenOrphans = Tile.asHand("1z", "1z", "2z", "3z", "4z", "5z", "6z", "7z", "1p", "9p", "1m", "9m", "1s", "9s");
        assertTrue(winningShapes.isMahjong(thirteenOrphans));
        assertFalse(winningShapes.isFourGroupsOnePair(thirteenOrphans));
        assertTrue(winningShapes.isThirteenOrphans(thirteenOrphans));
    }
}
