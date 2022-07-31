package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Shousangen extends GroupBasedYaku
{
    public Shousangen(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        boolean hasGreen = false;
        boolean hasRed = false;
        boolean hasWhite = false;
        boolean pairIsDragon = false;

        for (TileGroup group : groups)
        {
            if (group.isExclusiveGroup())
            {
                MahjongTileKind tileKind = group.getTileKindAt(0);
                if (tileKind.isDragon())
                {
                    if (group.isPair())
                    {
                        if (pairIsDragon == true)
                        {
                            return false;
                        }
                        pairIsDragon = true;
                    }
                    else if (!group.isCompleteExclusiveGroup())
                    {
                        return false;
                    }

                    switch (tileKind)
                    {
                        case GREEN:
                            hasGreen = true;
                            break;
                        case RED:
                            hasRed = true;
                            break;
                        case WHITE:
                            hasWhite = true;
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        return hasGreen && hasRed && hasWhite && pairIsDragon;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}
