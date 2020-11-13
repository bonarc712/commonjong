package com.monsieurmahjong.commonjong.rules.generic.waits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class WaitShapeEngineTest
{
    @Test
    public void testGetWait()
    {
        // tenpai hands
        testGetWait("1112223334445z", MahjongTileKind.WHITE);
        testGetWait("123m23p12366s555z", MahjongTileKind.CIRCLES_1, MahjongTileKind.CIRCLES_4);
        testGetWait("123m22p12366s555z", MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_6);
        testGetWait("123m12p12366s555z", MahjongTileKind.CIRCLES_3);

        // non-tenpai hands
        testGetWait("1112223334567z", MahjongTileKind.NORTH, MahjongTileKind.WHITE, MahjongTileKind.GREEN, MahjongTileKind.RED);
    }

    private void testGetWait(String startingHand, MahjongTileKind... expectedWait)
    {
        List<Tile> hand = TileKindUtils.asHand(startingHand);
        WaitShapeEngine engine = new WaitShapeEngine(hand);

        List<MahjongTileKind> handExpected = new ArrayList<>();
        handExpected.addAll(Arrays.asList(expectedWait));
        assertEquals(handExpected, engine.getWait());
    }
}
