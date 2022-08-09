package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Haitei extends TimingBasedYaku
{
    public Haitei(RiichiScoringParameters parameters)
    {
        super(parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnHaitei();
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
