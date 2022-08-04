package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Sanankou extends GroupBasedYaku
{
    public Sanankou(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var melds = hand.getMelds();
        var ankouCount = 0;

        for (var group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                var groupAsTiles = TileGroupUtils.getTilesFromTileGroup(group);
                if (!melds.contains(groupAsTiles))
                {
                    ankouCount++;
                }
            }
        }

        return ankouCount == 3;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}