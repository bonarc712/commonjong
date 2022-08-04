package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Suuankou extends GroupBasedYaku
{
    public Suuankou(Hand hand, List<TileGroup> groups)
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

        return ankouCount == 4;
    }

    @Override
    public int getHanValue()
    {
        var winningTile = hand.getWinningTile();
        for (var group : groups)
        {
            if (group.isPair() && group.getTileKindAt(0) == winningTile)
            {
                return 26;
            }
        }
        return 13;
    }
}