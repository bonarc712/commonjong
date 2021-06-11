package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.*;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class ChuurenPoutou extends GroupBasedYaku
{
    public ChuurenPoutou(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        if (!hand.getMelds().isEmpty())
        {
            return false;
        }
        int[] amountOfEachNumber = new int[9];
        boolean duplicateFound = false;
        TileFamily family = TileFamily.NONE;

        for (TileGroup group : groups)
        {
            for (MahjongTileKind kind : group.getTileKinds())
            {
                if (family == TileFamily.NONE)
                {
                    if (kind.isNumeral())
                    {
                        family = kind.getFamily();
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (kind.getFamily() != family)
                {
                    return false;
                }

                int currentNumber = kind.getTileNumber();
                amountOfEachNumber[currentNumber - 1] = amountOfEachNumber[currentNumber - 1]++;
                if ((currentNumber == 1 || currentNumber == 9) && amountOfEachNumber[currentNumber - 1] >= 4)
                {
                    if (duplicateFound == false)
                    {
                        duplicateFound = true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (amountOfEachNumber[currentNumber - 1] >= 2)
                {
                    if (duplicateFound == false)
                    {
                        duplicateFound = true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int getHanValue()
    {
        MahjongTileKind winningTile = hand.getWinningTile();
        if (winningTile.isNumeral())
        {
            int tileCount = (int) groups.stream().map(group -> group.getTileKinds()).flatMap(List::stream).filter(kind -> kind.getTileNumber() == winningTile.getTileNumber()).count();
            if (winningTile.getTileNumber() == 1 || winningTile.getTileNumber() == 9)
            {
                return tileCount == 4 ? 26 : 13;
            }
            else
            {
                return tileCount == 2 ? 26 : 13;
            }
        }
        return 13;
    }
}