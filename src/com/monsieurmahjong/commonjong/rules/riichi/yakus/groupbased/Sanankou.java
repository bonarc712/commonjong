package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.*;
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
        List<List<Tile>> melds = hand.getMelds();
        int ankouCount = 0;

        for (TileGroup group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                List<Tile> groupAsTiles = TileGroupUtils.getTilesFromTileGroup(group);
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