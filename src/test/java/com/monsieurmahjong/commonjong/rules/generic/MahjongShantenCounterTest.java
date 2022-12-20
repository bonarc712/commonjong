package com.monsieurmahjong.commonjong.rules.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;

public class MahjongShantenCounterTest
{
    @Test
    public void testMahjongShantenCounter()
    {
        // kokushi
        var shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("1m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z")).countShanten();
        var expected = 0;
        assertEquals(expected, shanten, "Shanten for 19m19p19s1234567z does not give the correct result");

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("11m", "45m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "5z", "6z")).countShanten();
        expected = 2;
        assertEquals(expected, shanten, "Shanten for 1145m19p19s12356z does not give the correct result");

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("11m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z")).countShanten();
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 119m19p19s1234567z does not give the correct result");

        // seven pairs
        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("11m", "99m", "11p", "99p", "11s", "99s", "7z")).countShanten();
        expected = 0;
        assertEquals(expected, shanten, "Shanten for 1199m1199p1199s7z does not give the correct result");

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("1m", "3m", "99m", "11p", "99p", "11s", "99s", "7z")).countShanten();
        expected = 1;
        assertEquals(expected, shanten, "Shanten for 1399m1199p1199s7z does not give the correct result");

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("11m", "99m", "11p", "99p", "11s", "99s", "77z")).countShanten();
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 1199m1199p1199s77z does not give the correct result");

        // 4 groups + 1 pair

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("123m", "567m", "123p", "123s", "55z")).countShanten();
        expected = -1;
        assertEquals(expected, shanten, "Shanten for 123567m123p123s55z does not give the correct result");

        shanten = new MahjongShantenCounter(TileGroupUtils.tileGroupsOf("789m", "1p", "4p", "789p", "789s", "77z")).countShanten();
        expected = 1;
        assertEquals(expected, shanten, "Shanten for 789m14789p789s77z does not give the correct result");
    }
}
