package com.monsieurmahjong.commonjong.rules.riichi.yakus;

import com.monsieurmahjong.commonjong.game.Hand;

public class Tsuuiisou extends HandBasedYaku
{
    public Tsuuiisou(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        return hand.getTiles().stream().allMatch(tile -> tile.getTileKind().isHonour());
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}
