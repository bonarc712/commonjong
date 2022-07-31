package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Suukantsu extends GroupBasedYaku
{
    public Suukantsu(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        int quadCount = 0;
        for (TileGroup group : groups)
        {
            if (group.isQuad())
            {
                quadCount++;
            }
        }
        return quadCount == 4;
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}