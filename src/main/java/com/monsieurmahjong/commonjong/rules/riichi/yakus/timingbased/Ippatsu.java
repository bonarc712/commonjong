package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Ippatsu extends TimingBasedYaku
{
    public Ippatsu(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnIppatsu(hand.getSeatWind());
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
