package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.Hand;

public class Honroutou extends TileBasedYaku
{
    public Honroutou(Hand hand)
    {
        super(hand);
    }

    /**
     * Honroutou requires the presence of both honours and terminals.
     */
    @Override
    public boolean isValid()
    {
        var terminalFound = false;
        var honourFound = false;

        for (var tile : hand.getTiles())
        {
            if (tile.getTileKind().isTerminal())
            {
                terminalFound = true;
            }
            else if (tile.getTileKind().isHonour())
            {
                honourFound = true;
            }
            else
            {
                return false;
            }
        }

        return terminalFound && honourFound;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}
