package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Chihou extends TimingBasedYaku
{
    public Chihou(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnChihou();
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}
