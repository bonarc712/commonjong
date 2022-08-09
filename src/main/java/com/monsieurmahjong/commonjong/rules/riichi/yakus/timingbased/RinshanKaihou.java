package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RinshanKaihou extends TimingBasedYaku
{
    public RinshanKaihou(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnRinshanKaihou(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
