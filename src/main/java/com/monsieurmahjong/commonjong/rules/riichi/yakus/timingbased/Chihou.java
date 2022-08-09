package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Chihou extends TimingBasedYaku
{
    public Chihou(RiichiScoringParameters parameters)
    {
        super(parameters);
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
