package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.GroupBasedYaku;

public abstract class DragonYakuhai extends GroupBasedYaku
{
    public DragonYakuhai(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    protected abstract MahjongTileKind getYakuhaiTile();

    @Override
    public boolean isValid()
    {
        for (TileGroup group : groups)
        {
            if (group.isCompleteExclusiveGroup() && getYakuhaiTile().getIndex() == group.getIndices().get(0))
            {
                return true;
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
