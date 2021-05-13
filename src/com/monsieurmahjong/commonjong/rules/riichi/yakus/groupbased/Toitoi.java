package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Toitoi extends GroupBasedYaku
{
    public Toitoi(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        int pairAmount = 0;
        for (TileGroup group : groups)
        {
            if (group.isPair())
            {
                pairAmount++;
                if (pairAmount > 1)
                {
                    return false;
                }
            }
            else if (!group.isTriplet())
            {
                return false;
            }
        }
        return pairAmount == 1;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}
