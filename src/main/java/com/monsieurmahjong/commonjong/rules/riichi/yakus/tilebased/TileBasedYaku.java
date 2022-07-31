package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public abstract class TileBasedYaku implements Yaku
{
    protected Hand hand;

    public TileBasedYaku(Hand hand)
    {
        this.hand = hand;
    }
}
