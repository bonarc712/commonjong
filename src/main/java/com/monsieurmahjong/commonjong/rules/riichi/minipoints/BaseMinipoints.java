package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Chiitoitsu;

public class BaseMinipoints implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;
    private GameStateLog log;

    public BaseMinipoints(Hand hand, List<TileGroup> groups, GameStateLog log)
    {
        this.hand = hand;
        this.groups = groups;
        this.log = log;
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
        return hand.isClosed() && log.doesPlayerWinOnRon(hand.getSeatWind());
    }

    private boolean isSevenPairs()
    {
        var chiitoitsu = new Chiitoitsu(hand, groups);
        return chiitoitsu.isValid();
    }
}
