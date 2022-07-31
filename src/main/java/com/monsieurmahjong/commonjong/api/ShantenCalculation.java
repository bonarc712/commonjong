package com.monsieurmahjong.commonjong.api;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;

public class ShantenCalculation
{
    /**
     * Count shanten with a hand in mpsz notation.
     */
    public static int countShanten(String hand)
    {
        List<Tile> asHand = TileKindUtils.asHand(hand);
        WaitShapeEngine engine = new WaitShapeEngine(asHand);
        return engine.getShanten();
    }
}
