package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Pinfu;

public class TsumoFu implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;
    private GameStateLog log;

    public TsumoFu(Hand hand, List<TileGroup> groups, GameStateLog log)
    {
        this.hand = hand;
        this.groups = groups;
        this.log = log;
    }

    @Override
    public boolean isValid()
    {
        if (!log.doesPlayerWinOnTsumo(hand.getSeatWind()))
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
