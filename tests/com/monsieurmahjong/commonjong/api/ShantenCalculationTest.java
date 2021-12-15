package com.monsieurmahjong.commonjong.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShantenCalculationTest
{
    @Test
    public void testShantenCalculation()
    {
        // kokushi
        int shanten = ShantenCalculation.countShanten("19m19p19s1234567z");
        Assertions.assertEquals(0, shanten, "Shanten for 19m19p19s1234567z does not give the correct result");

        shanten = ShantenCalculation.countShanten("1145m19p19s12356z");
        Assertions.assertEquals(2, shanten, "Shanten for 1145m19p19s12356z does not give the correct result");

        shanten = ShantenCalculation.countShanten("119m19p19s1234567z");
        Assertions.assertEquals(-1, shanten, "Shanten for 119m19p19s1234567z does not give the correct result");

        // seven pairs
        shanten = ShantenCalculation.countShanten("1199m1199p1199s7z");
        Assertions.assertEquals(0, shanten, "Shanten for 1199m1199p1199s7z does not give the correct result");

        shanten = ShantenCalculation.countShanten("1399m1199p1199s7z");
        Assertions.assertEquals(1, shanten, "Shanten for 1399m1199p1199s7z does not give the correct result");

        shanten = ShantenCalculation.countShanten("1199m1199p1199s77z");
        Assertions.assertEquals(-1, shanten, "Shanten for 1199m1199p1199s77z does not give the correct result");

        // 4 groups + 1 pair

        shanten = ShantenCalculation.countShanten("123567m123p123s55z");
        Assertions.assertEquals(-1, shanten, "Shanten for 123567m123p123s55z does not give the correct result");

        shanten = ShantenCalculation.countShanten("789m14789p789s77z");
        Assertions.assertEquals(1, shanten, "Shanten for 789m14789p789s77z does not give the correct result");
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
        int resultShanten = ShantenCalculation.countShanten("258m258p258s1235z");
        Assertions.assertEquals(6, resultShanten, "Wrong shanten for 258m258p258s1235z");

        // 5-shanten. 3 protogroups, 5 terminals.
        resultShanten = ShantenCalculation.countShanten("238m2589p58s1225z");
        Assertions.assertEquals(5, resultShanten, "Wrong shanten for 238m2589p58s1225z");

        // 4-shanten. 5 protogroups without pair.
        resultShanten = ShantenCalculation.countShanten("2389m56p1289s235z");
        Assertions.assertEquals(4, resultShanten, "Wrong shanten for 2389m56p1289s235z");

        // 3-shanten. 5 protogroups including a pair.
        resultShanten = ShantenCalculation.countShanten("3389m56p1289s235z");
        Assertions.assertEquals(3, resultShanten, "Wrong shanten for 3389m56p1289s235z");

        // 2-shanten. 1 group, 4 protogroups including a pair.
        resultShanten = ShantenCalculation.countShanten("333m56p1289s2557z");
        Assertions.assertEquals(2, resultShanten, "Wrong shanten for 333m56p1289s2557z");

        // 1-shanten. 2 groups, 3 protogroups including a pair.
        resultShanten = ShantenCalculation.countShanten("333m567p1289s255z");
        Assertions.assertEquals(1, resultShanten, "Wrong shanten for 333m567p1289s255z");

        // Tenpai (0-shanten). 3 groups, 2 protogroups including a pair.
        resultShanten = ShantenCalculation.countShanten("333m567p12789s55z");
        Assertions.assertEquals(0, resultShanten, "Wrong shanten for 333m567p12789s55z");

        // 6-shanten. All crap, 7 different terminals (no pair).
        resultShanten = ShantenCalculation.countShanten("259m159p258s1235z");
        Assertions.assertEquals(6, resultShanten, "Wrong shanten for 259m159p258s1235z");

        // 5-shanten. All crap, 8 different terminals (no pair).
        resultShanten = ShantenCalculation.countShanten("259m159p158s1235z");
        Assertions.assertEquals(5, resultShanten, "Wrong shanten for 259m159p158s1235z");

        // 4-shanten. All crap, 8 different terminals, one terminal pair.
        resultShanten = ShantenCalculation.countShanten("259m159p18s12335z");
        Assertions.assertEquals(4, resultShanten, "Wrong shanten for 259m159p18s12335z");

        // 3-shanten. All crap, 9 different terminals, one terminal pair. (13-10)
        resultShanten = ShantenCalculation.countShanten("159m159p18s12335z");
        Assertions.assertEquals(3, resultShanten, "Wrong shanten for 159m159p18s12335z");

        // 2-shanten. All crap, 10 different terminals, two terminal pairs. (13-11)
        resultShanten = ShantenCalculation.countShanten("199m159p19s12335z");
        Assertions.assertEquals(2, resultShanten, "Wrong shanten for 199m159p19s12335z");

        // 1-shanten. 5 pairs, no groups.
        resultShanten = ShantenCalculation.countShanten("199m199p55s22335z");
        Assertions.assertEquals(1, resultShanten, "Wrong shanten for 199m199p55s22335z");

        // Tenpai (0-shanten). 6 pairs.
        resultShanten = ShantenCalculation.countShanten("199m99p55s223355z");
        Assertions.assertEquals(0, resultShanten, "Wrong shanten for 199m99p55s223355z");

        // 6-shanten. One group (sequence), rest crap.
        resultShanten = ShantenCalculation.countShanten("123m258p258s1235z");
        Assertions.assertEquals(6, resultShanten, "Wrong shanten for 123m258p258s1235z");

        // 5-shanten. One group (triplet), rest crap (pair present obv.).
        resultShanten = ShantenCalculation.countShanten("111m258p258s1235z");
        Assertions.assertEquals(5, resultShanten, "Wrong shanten for 111m258p258s1235z");
    }

    /**
     * Every hand that we've had that run on the commonjong engine and failed to
     * give the shanten at some point (in commonjong tests, sanshoku or elsewhere)
     * should be added here to make sure that they always succeed.
     */
    @Test
    public void testMiscellaneousHands()
    {
        int resultShanten = ShantenCalculation.countShanten("1566799m122345p");
        Assertions.assertEquals(2, resultShanten, "Wrong shanten for 1566799m122345p");

        resultShanten = ShantenCalculation.countShanten("1244566789m112p");
        Assertions.assertEquals(1, resultShanten, "Wrong shanten for 1244566789m112p");
    }
}
