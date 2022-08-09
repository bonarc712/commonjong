package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Pinfu;

public class TsumoFu implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;
    private RiichiScoringParameters parameters;

    public TsumoFu(Hand hand, List<TileGroup> groups, RiichiScoringParameters parameters)
    {
        this.hand = hand;
        this.groups = groups;
        this.parameters = parameters;
    }

    @Override
    public boolean isValid()
    {
        if (!parameters.doesPlayerWinOnTsumo(hand.getSeatWind()))
        {
            return false;
        }

        if (isChiitoitsu())
        {
            return false;
        }

        if (isPinfu())
        {
            return false;
        }

        return true;
    }

    private boolean isChiitoitsu()
    {
        return groups.size() == 7;
    }

    private boolean isPinfu()
    {
        var pinfu = new Pinfu(hand, groups);
        return pinfu.isValid();
    }

    @Override
    public int getFuValue()
    {
        return 2;
    }
}
