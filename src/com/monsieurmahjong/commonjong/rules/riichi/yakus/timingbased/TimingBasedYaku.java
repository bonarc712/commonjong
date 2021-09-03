package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public abstract class TimingBasedYaku implements Yaku
{
    protected Hand hand;
    protected GameStateLog log;

    public TimingBasedYaku(Hand hand, GameStateLog log)
    {
        this.hand = hand;
        this.log = log;
    }
}
