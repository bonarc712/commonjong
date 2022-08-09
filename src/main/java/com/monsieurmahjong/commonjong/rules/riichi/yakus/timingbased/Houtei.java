package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Houtei extends TimingBasedYaku
{
    public Houtei(RiichiScoringParameters parameters)
    {
        super(parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnHoutei();
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
