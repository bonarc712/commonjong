package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.TileFamily;
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
        var amountOfEachNumber = new int[9];
        var duplicateFound = false;
        var family = TileFamily.NONE;

        for (var group : groups)
        {
            for (var kind : group.getTileKinds())
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

                var currentNumber = kind.getTileNumber();
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
        var winningTile = hand.getWinningTile();
        if (winningTile.isNumeral())
        {
            var tileCount = (int) groups.stream().map(group -> group.getTileKinds()).flatMap(List::stream).filter(kind -> kind.getTileNumber() == winningTile.getTileNumber()).count();
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