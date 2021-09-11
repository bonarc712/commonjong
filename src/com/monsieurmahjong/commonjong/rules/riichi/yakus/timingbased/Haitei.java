package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class Haitei extends TimingBasedYaku
{
    public Haitei(Hand hand, GameStateLog log)
    {
        super(hand, log);
    }

    @Override
    public boolean isValid()
    {
        return log.doesPlayerWinOnHaitei(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
