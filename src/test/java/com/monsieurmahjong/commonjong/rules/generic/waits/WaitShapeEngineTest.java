package com.monsieurmahjong.commonjong.rules.generic.waits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;
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
        testGetWait("123m123p45666s55z", MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_6, MahjongTileKind.WHITE);
        testGetWait("2345666m123p777z", MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_5, MahjongTileKind.CHARACTERS_7);
        testGetWait("1112345678999s", MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6,
                MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_9);
        testGetWait("3334555s111666z", MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6);

        // non-tenpai hands
        testGetWait("1112223334567z", MahjongTileKind.NORTH, MahjongTileKind.WHITE, MahjongTileKind.GREEN, MahjongTileKind.RED);
        testGetWait("1566799m122345p", MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3, MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_5,
                MahjongTileKind.CHARACTERS_6, MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_8, MahjongTileKind.CHARACTERS_9, MahjongTileKind.CIRCLES_1, MahjongTileKind.CIRCLES_2,
                MahjongTileKind.CIRCLES_3, MahjongTileKind.CIRCLES_4, MahjongTileKind.CIRCLES_5, MahjongTileKind.CIRCLES_6, MahjongTileKind.CIRCLES_7);
    }

    private void testGetWait(String startingHand, MahjongTileKind... expectedWait)
    {
        List<Tile> hand = TileKindUtils.asHand(startingHand);
        WaitShapeEngine engine = new WaitShapeEngine(hand);
        List<MahjongTileKind> waitResult = engine.getWait();

        List<MahjongTileKind> handExpected = new ArrayList<>();
        handExpected.addAll(Arrays.asList(expectedWait));
        assertEquals(handExpected, waitResult, "Hand " + startingHand + " does not give the expected result");
    }
}
