package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class GreenDragonYakuhai extends DragonYakuhai
{
    public GreenDragonYakuhai(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    protected MahjongTileKind getYakuhaiTile()
    {
        return MahjongTileKind.GREEN;
    }
}
