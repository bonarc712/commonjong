package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
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
        List<MahjongTileKind> knownTileKinds = new ArrayList<>();
        for (TileGroup group : groups)
        {
            if (!group.isPair())
            {
                return false;
            }
            else
            {
                MahjongTileKind tileKind = TileKindUtils.getKindFromIndex(group.getIndices().get(0));
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
