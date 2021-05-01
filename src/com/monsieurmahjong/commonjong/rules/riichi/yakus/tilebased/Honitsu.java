package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.TileFamily;

public class Honitsu extends TileBasedYaku
{
    public Honitsu(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        boolean honourFound = false;
        TileFamily chosenFamily = TileFamily.NONE;

        for (Tile tile : hand.getTiles())
        {
            if (tile.getTileKind().isHonour())
            {
                honourFound = true;
            }
            else
            {
                if (tile.getTileKind().isNumeral())
                {
                    if (chosenFamily == TileFamily.NONE)
                    {
                        chosenFamily = tile.getTileKind().getFamily();
                    }
                    else
                    {
                        if (tile.getTileKind().getFamily() != chosenFamily)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return chosenFamily != TileFamily.NONE && honourFound;
    }

    @Override
    public int getHanValue()
    {
        return hand.isOpen() ? 2 : 3;
    }
}
