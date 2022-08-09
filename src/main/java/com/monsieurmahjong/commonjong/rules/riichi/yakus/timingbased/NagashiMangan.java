package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class NagashiMangan extends TimingBasedYaku
{
    public NagashiMangan(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnNagashiMangan();
    }

    @Override
    public int getHanValue()
    {
        return 5;
    }
}
