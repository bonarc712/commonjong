package com.monsieurmahjong.commonjong.rules.generic;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroupLengthComparator;
import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;

public class MahjongShantenCounter
{
    private List<TileGroup> tileGroups;

    public MahjongShantenCounter(List<TileGroup> tileGroups)
    {
        this.tileGroups = tileGroups;
    }

    /**
     * This method counts all shanten for a specific hand combination. It does not
     * count all combinations that are possible for the current hand. See
     * {@link WaitShapeEngine} for hand parsing.
     *
     * @param tileGroups
     * @return shanten count. 0 is tenpai, -1 is mahjong-complete
     */
    public int countShanten()
    {
        var kokushiShantenCount = countKokushiShanten();
        var sevenPairsShantenCount = countSevenPairsShanten();
        var fourGroupsOnePairShantenCount = countFourGroupsOnePairShanten();

        var lowestShantenCount = Math.min(kokushiShantenCount, sevenPairsShantenCount);
        lowestShantenCount = Math.min(lowestShantenCount, fourGroupsOnePairShantenCount);
        return lowestShantenCount;
    }

    private int countKokushiShanten()
    {
        List<MahjongTileKind> terminalAndHonourCatalog = new ArrayList<>(MahjongTileKind.getAllTerminalsAndHonours());
        var pairFound = false;

        for (TileGroup group : tileGroups)
        {
            if (!pairFound && group.getIndices().size() > 1)
            {
                if (group.isPair() || group.isTriplet()) // check also for triplets, they can also influence shanten
                {
                    var kind = group.getTileKindAt(0);
                    var isRemoved = terminalAndHonourCatalog.remove(kind);
                    if (isRemoved)
                    {
                        pairFound = true;
                        continue;
                    }
                }
            }

            var currentTerminalOrHonour = group.getIndices().stream().filter(index -> {
                var kind = MahjongTileKind.getKindFromIndex(index);
                return kind.isTerminal() || kind.isHonour();
            }).findFirst();

            if (currentTerminalOrHonour.isPresent())
            {
                var kind = MahjongTileKind.getKindFromIndex(currentTerminalOrHonour.get());
                terminalAndHonourCatalog.remove(kind);
            }
        }

        var shantenCount = terminalAndHonourCatalog.size();
        if (pairFound)
        {
            shantenCount--;
        }
        return shantenCount;
    }

    private int countSevenPairsShanten()
    {
        var amountOfPairs = 0;

        for (TileGroup group : tileGroups)
        {
            if (group.isPair() || group.isTriplet())
            {
                amountOfPairs++;
            }
        }

        return 6 - amountOfPairs;
    }

    private int countFourGroupsOnePairShanten()
    {
        var shanten = 8;
        var pairFound = false;

        tileGroups.sort(new TileGroupLengthComparator());
        for (var i = 0; i < Math.min(tileGroups.size(), 5); i++)
        {
            var currentGroup = tileGroups.get(i);
            if (!pairFound && currentGroup.isPair())
            {
                pairFound = true;
            }

            var currentGroupSize = Math.min(currentGroup.getSize(), 3);
            shanten = shanten - (currentGroupSize - 1);
        }

        if (!pairFound)
        {
            shanten++;
        }

        return shanten;
    }
}
