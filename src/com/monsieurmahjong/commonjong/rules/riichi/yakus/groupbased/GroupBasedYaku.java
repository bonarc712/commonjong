package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public abstract class GroupBasedYaku implements Yaku
{
    protected Hand hand;
    protected List<TileGroup> groups;

    public GroupBasedYaku(Hand hand, List<TileGroup> groups)
    {
        this.hand = hand;
        this.groups = groups;
    }
}
