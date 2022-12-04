package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.utils.Pair;

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

        var family = groups.get(0).getTileKindAt(0).getFamily();
        if (!family.isNumeral())
        {
            return false;
        }

        var amountOfEachNumber = new int[9];
        for (var group : groups)
        {
            for (var kind : group.getTileKinds())
            {
                if (kind.getFamily() != family)
                {
                    return false;
                }

                var currentNumber = kind.getTileNumber();
                amountOfEachNumber[currentNumber - 1]++;
            }
        }

        BiPredicate<Integer, Integer> verifyAmountOfTilesPerNumber = (index, count) -> amountOfEachNumber[index] == count || amountOfEachNumber[index] == count + 1;

        var amountPerNumber = setAmountOfTilesRequiredPerNumber();
        for (var amountForCurrentNumber : amountPerNumber)
        {
            if (!verifyAmountOfTilesPerNumber.test(amountForCurrentNumber.getFirst(), amountForCurrentNumber.getSecond()))
            {
                return false;
            }
        }

        return Arrays.stream(amountOfEachNumber).sum() == 14;
    }

    private List<Pair<Integer, Integer>> setAmountOfTilesRequiredPerNumber()
    {
        var list = new ArrayList<Pair<Integer, Integer>>();
        list.add(new Pair<>(0, 3));
        list.add(new Pair<>(1, 1));
        list.add(new Pair<>(2, 1));
        list.add(new Pair<>(3, 1));
        list.add(new Pair<>(4, 1));
        list.add(new Pair<>(5, 1));
        list.add(new Pair<>(6, 1));
        list.add(new Pair<>(7, 1));
        list.add(new Pair<>(8, 3));
        return list;
    }

    @Override
    public int getHanValue()
    {
        var winningTile = hand.getWinningTile();
        if (winningTile != null && winningTile.isNumeral())
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