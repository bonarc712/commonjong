package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.TileFamily;

public class Chinitsu extends TileBasedYaku
{
    public Chinitsu(Hand hand)
    {
        super(hand);
    }

    @Override
    public boolean isValid()
    {
        TileFamily chosenFamily = TileFamily.NONE;

        for (Tile tile : hand.getTiles())
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
            else
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getHanValue()
    {
        return hand.isOpen() ? 5 : 6;
    }
}
