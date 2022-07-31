package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class Ryuuiisou extends TileBasedYaku
{
    public Ryuuiisou(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        for (Tile tile : hand.getTiles())
        {
            if (!tile.getTileKind().is(MahjongTileKind.BAMBOOS_2, //
                    MahjongTileKind.BAMBOOS_3, //
                    MahjongTileKind.BAMBOOS_4, //
                    MahjongTileKind.BAMBOOS_6, //
                    MahjongTileKind.BAMBOOS_8, //
                    MahjongTileKind.GREEN))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getHanValue()
    {
        return 13;
    }
}
