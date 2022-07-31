package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.GroupBasedYaku;

public class TableWindYakuhai extends GroupBasedYaku
{
    public TableWindYakuhai(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        for (TileGroup group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                MahjongTileKind currentTile = group.getTileKindAt(0);
                if (currentTile.isWind() && hand.isTableWind(TileKindUtils.getSeatFromTileKind(currentTile)))
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
