package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Tenhou extends TimingBasedYaku
{
    public Tenhou(RiichiScoringParameters parameters)
    {
        super(parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnTenhou();
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}
