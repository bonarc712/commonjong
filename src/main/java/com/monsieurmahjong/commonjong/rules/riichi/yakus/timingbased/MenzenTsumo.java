package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class MenzenTsumo extends TimingBasedYaku
{
    public MenzenTsumo(Hand hand, GameStateLog log)
    {
        super(hand, log);
    }

    @Override
    public boolean isValid()
    {
        return log.doesPlayerWinOnMenzenTsumo(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
