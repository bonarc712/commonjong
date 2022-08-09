package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Riichi extends TimingBasedYaku
{
    public Riichi(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.hasPlayerDeclaredRiichi();
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
