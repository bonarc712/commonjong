package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Ippatsu extends TimingBasedYaku
{
    public Ippatsu(RiichiScoringParameters parameters)
    {
        super(parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnIppatsu();
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
