package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.Hand;

public class Tanyao extends TileBasedYaku
{
    public Tanyao(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        return hand.getTiles().stream().allMatch(tile -> tile.getTileKind().isNumeral() && !tile.getTileKind().isTerminal());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
