package com.monsieurmahjong.commonjong.rules.riichi.yakus;

import com.monsieurmahjong.commonjong.game.Hand;

public abstract class HandBasedYaku implements Yaku
{
    Hand hand;

    public HandBasedYaku(Hand hand)
    {
        this.hand = hand;
    }
}
