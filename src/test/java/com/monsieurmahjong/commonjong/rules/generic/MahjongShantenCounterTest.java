package com.monsieurmahjong.commonjong.rules.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;

public class MahjongShantenCounterTest
{
    @Test
    public void testMahjongShantenCounter()
    {
        // kokushi
        var shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("1m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z"));
        var expected = 0;
        assertEquals(expected, shanten, "Shanten for 19m19p19s1234567z does not give the correct result");

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("11m", "45m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "5z", "6z"));
        expected = 2;
        assertEquals(expected, shanten, "Shanten for 1145m19p19s12356z does not give the correct result");

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("11m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z"));
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 119m19p19s1234567z does not give the correct result");

        // seven pairs
        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("11m", "99m", "11p", "99p", "11s", "99s", "7z"));
        expected = 0;
        assertEquals(expected, shanten, "Shanten for 1199m1199p1199s7z does not give the correct result");

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("1m", "3m", "99m", "11p", "99p", "11s", "99s", "7z"));
        expected = 1;
        assertEquals(expected, shanten, "Shanten for 1399m1199p1199s7z does not give the correct result");

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("11m", "99m", "11p", "99p", "11s", "99s", "77z"));
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 1199m1199p1199s77z does not give the correct result");

        // 4 groups + 1 pair

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("123m", "567m", "123p", "123s", "55z"));
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 123567m123p123s55z does not give the correct result");

        shanten = MahjongShantenCounter.countShanten(TileGroupUtils.tileGroupsOf("789m", "1p", "4p", "789p", "789s", "77z"));
        expected = 1;
        assertEquals(expected, shanten, "Shanten for 789m14789p789s77z does not give the correct result");
    }

    /**
     * Integration test including the wait shape engine and the mahjong shanten
     * counter. The tests that are included here come from
     * {@link http://arcturus.su/wiki/Mahjong_programming_tests}.
     */
    @Test
    public void testArcturusProgrammingTests()
    {
        // 6-shanten. All crap, 4 terminals.
        var waitEngine = new WaitShapeEngine(TileKindUtils.asHand("258m258p258s1235z"));
        var resultShanten = waitEngine.getShanten();
        var expectedShanten = 6;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 258m258p258s1235z");

        // 5-shanten. 3 protogroups, 5 terminals.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("238m2589p58s1225z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 5;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 238m2589p58s1225z");

        // 4-shanten. 5 protogroups without pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("2389m56p1289s235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 4;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 2389m56p1289s235z");

        // 3-shanten. 5 protogroups including a pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("3389m56p1289s235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 3;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 3389m56p1289s235z");

        // 2-shanten. 1 group, 4 protogroups including a pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("333m56p1289s2557z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 2;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 333m56p1289s2557z");

        // 1-shanten. 2 groups, 3 protogroups including a pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("333m567p1289s255z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 1;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 333m567p1289s255z");

        // Tenpai (0-shanten). 3 groups, 2 protogroups including a pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("333m567p12789s55z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 0;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 333m567p12789s55z");

        // 6-shanten. All crap, 7 different terminals (no pair).
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("259m159p258s1235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 6;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 259m159p258s1235z");

        // 5-shanten. All crap, 8 different terminals (no pair).
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("259m159p158s1235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 5;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 259m159p158s1235z");

        // 4-shanten. All crap, 8 different terminals, one terminal pair.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("259m159p18s12335z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 4;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 259m159p18s12335z");

        // 3-shanten. All crap, 9 different terminals, one terminal pair. (13-10)
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("159m159p18s12335z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 3;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 159m159p18s12335z");

        // 2-shanten. All crap, 10 different terminals, two terminal pairs. (13-11)
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("199m159p19s12335z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 2;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 199m159p19s12335z");

        // 1-shanten. 5 pairs, no groups.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("199m199p55s22335z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 1;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 199m199p55s22335z");

        // Tenpai (0-shanten). 6 pairs.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("199m99p55s223355z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 0;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 199m99p55s223355z");

        // 6-shanten. One group (sequence), rest crap.
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("123m258p258s1235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 6;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 123m258p258s1235z");

        // 5-shanten. One group (triplet), rest crap (pair present obv.).
        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("111m258p258s1235z"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 5;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 111m258p258s1235z");
    }

    /**
     * Every hand that we've had that run on the commonjong engine and failed to
     * give the shanten at some point (in commonjong tests, sanshoku or elsewhere)
     * should be added here to make sure that they always succeed.
     */
    @Test
    public void testMiscellaneousHands()
    {
        var waitEngine = new WaitShapeEngine(TileKindUtils.asHand("1566799m122345p"));
        var resultShanten = waitEngine.getShanten();
        var expectedShanten = 2;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 1566799m122345p");

        waitEngine = new WaitShapeEngine(TileKindUtils.asHand("1244566789m112p"));
        resultShanten = waitEngine.getShanten();
        expectedShanten = 1;

        assertEquals(expectedShanten, resultShanten, "Wrong shanten for 1244566789m112p");
    }

}
