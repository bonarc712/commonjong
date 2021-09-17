package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class TsumoFu implements Fu
{
    private Hand hand;
    private GameStateLog log;

    public TsumoFu(Hand hand, GameStateLog log)
    {
        this.hand = hand;
        this.log = log;
    }

    @Override
    public boolean isValid()
    {
        return log.doesPlayerWinOnTsumo(hand.getSeatWind());
    }

    @Override
    public int getFuValue()
    {
        return 2;
    }
}
