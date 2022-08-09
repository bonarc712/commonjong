package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RinshanKaihou extends TimingBasedYaku
{
    public RinshanKaihou(RiichiScoringParameters parameters)
    {
        super(parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnRinshanKaihou();
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
