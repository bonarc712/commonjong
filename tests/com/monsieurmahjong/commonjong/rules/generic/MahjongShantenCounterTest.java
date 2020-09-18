package com.monsieurmahjong.commonjong.rules.generic;

import java.util.List;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class MahjongShantenCounterTest
{
    @Test
    public void TenpaiHandTest()
    {
        List<Tile> hand1 = TileKindUtils.asHand("5z", "5z", "5z", "4z", "4z", "4z", "3z", "3z", "3z", "1z", "1z", "1z", "2z");
        Assertions.assertEquals(0, MahjongShantenCounter.shantenCount(hand1));
    }
}
