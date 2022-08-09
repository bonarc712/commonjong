package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public abstract class TimingBasedYaku implements Yaku
{
    protected RiichiScoringParameters parameters;

    public TimingBasedYaku(RiichiScoringParameters parameters)
    {
        this.parameters = parameters;
    }
}
