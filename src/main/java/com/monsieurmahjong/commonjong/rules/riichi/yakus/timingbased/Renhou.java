package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class Renhou extends TimingBasedYaku
{
    // Default value for renhou is mangan
    private int value = 5;

    public Renhou(Hand hand, RiichiScoringParameters parameters)
    {
        super(hand, parameters);
    }

    public void setHanValue(int hanValue)
    {
        value = hanValue;
    }

    @Override
    public boolean isValid()
    {
        return parameters.doesPlayerWinOnRenhou();
    }

    @Override
    public int getHanValue()
    {
        return value;
    }
}
