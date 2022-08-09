package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public abstract class TimingBasedYaku implements Yaku
{
    protected Hand hand;
    protected RiichiScoringParameters parameters;

    public TimingBasedYaku(Hand hand, RiichiScoringParameters parameters)
    {
        this.hand = hand;
        this.parameters = parameters;
    }
}
