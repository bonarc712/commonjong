package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.GroupBasedYaku;

public class SeatWindYakuhai extends GroupBasedYaku
{
    public SeatWindYakuhai(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        for (var group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                var currentTile = group.getTileKindAt(0);
                if (currentTile.isWind() && hand.isSeatWind(Seat.getSeatFromTileKind(currentTile)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
