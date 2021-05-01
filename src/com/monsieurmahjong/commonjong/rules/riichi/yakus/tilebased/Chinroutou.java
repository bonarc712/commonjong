package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.Hand;

public class Chinroutou extends TileBasedYaku
{
    public Chinroutou(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        return hand.getTiles().stream().allMatch(tile -> tile.getTileKind().isTerminal());
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}
