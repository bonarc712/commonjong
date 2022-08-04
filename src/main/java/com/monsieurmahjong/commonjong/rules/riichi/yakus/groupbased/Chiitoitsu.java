package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Chiitoitsu extends GroupBasedYaku
{
    public Chiitoitsu(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var knownTileKinds = new ArrayList<MahjongTileKind>();
        for (var group : groups)
        {
            if (!group.isPair())
            {
                return false;
            }
            else
            {
                var tileKind = group.getTileKindAt(0);
                if (knownTileKinds.contains(tileKind))
                {
                    return false;
                }
                knownTileKinds.add(tileKind);
            }
        }
        return true;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}
