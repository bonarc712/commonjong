package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Chankan extends TimingBasedYaku
{
    public Chankan(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnChankan(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
