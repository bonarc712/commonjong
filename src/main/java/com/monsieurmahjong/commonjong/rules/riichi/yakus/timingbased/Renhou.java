package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class Renhou extends TimingBasedYaku
{
    // Default value for renhou is mangan
    private int value = 5;

    public Renhou(Hand hand, GameStateLog log)
    {
        super(hand, log);
    }

    public void setHanValue(int hanValue)
    {
        value = hanValue;
    }

    @Override
    public boolean isValid()
    {
        return log.doesPlayerWinOnRenhou(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return value;
    }
}
