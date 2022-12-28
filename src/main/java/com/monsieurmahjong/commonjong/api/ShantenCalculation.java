package com.monsieurmahjong.commonjong.api;

import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;

public class ShantenCalculation
{
    /**
     * Count shanten with a hand in mpsz notation.
     */
    public static int countShanten(String hand)
    {
        var mpsz = new MPSZNotation();
        var asHand = mpsz.getTilesFrom(hand);
        var engine = new WaitShapeEngine(asHand);
        return engine.getShanten();
    }
}
