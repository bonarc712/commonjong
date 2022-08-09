package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Chiitoitsu;

public class BaseMinipoints implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;
    private RiichiScoringParameters parameters;

    public BaseMinipoints(Hand hand, List<TileGroup> groups, RiichiScoringParameters parameters)
    {
        this.hand = hand;
        this.groups = groups;
        this.parameters = parameters;
    }

    @Override
    public boolean isValid()
    {
        return true;
    }

    @Override
    public int getFuValue()
    {
        if (isSevenPairs())
        {
            return 25;
        }
        else if (isClosedRon())
        {
            return 30;
        }
        return 20;
    }

    private boolean isClosedRon()
    {
        return hand.isClosed() && parameters.doesPlayerWinOnRon(hand.getSeatWind());
    }

    private boolean isSevenPairs()
    {
        var chiitoitsu = new Chiitoitsu(hand, groups);
        return chiitoitsu.isValid();
    }
}
