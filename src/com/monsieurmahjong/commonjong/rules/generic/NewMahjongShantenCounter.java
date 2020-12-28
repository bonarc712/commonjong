package com.monsieurmahjong.commonjong.rules.generic;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.*;

public class NewMahjongShantenCounter
{
    /**
     * This method counts all shanten for a specific hand combination. It does not count all
     * combinations that are possible for the current hand. See {@link WaitShapeEngine} for
     * hand parsing.
     * 
     * @param tileGroups
     * @return shanten count. 0 is tenpai, -1 is mahjong-complete
     */
    public static int countShanten(List<TileGroup> tileGroups)
    {
        int kokushiShantenCount = countKokushiShanten(tileGroups);
        int sevenPairsShantenCount = countSevenPairsShanten(tileGroups);
        int fourGroupsOnePairShantenCount = countFourGroupsOnePairShanten(tileGroups);

        int lowestShantenCount = Math.min(kokushiShantenCount, sevenPairsShantenCount);
        lowestShantenCount = Math.min(lowestShantenCount, fourGroupsOnePairShantenCount);
        return lowestShantenCount;
    }

    private static int countKokushiShanten(List<TileGroup> tileGroups)
    {
        List<MahjongTileKind> terminalAndHonourCatalog = new ArrayList<>(TileKindUtils.getAllTerminalsAndHonours());
        boolean pairFound = false;

        for (TileGroup group : tileGroups)
        {
            if (!pairFound && group.getIndices().size() > 1)
            {
                if (group.isPair() || group.isTriplet()) // check also for triplets, they can also influence shanten
                {
                    MahjongTileKind kind = TileKindUtils.getKindFromIndex(group.getIndices().get(0));
                    boolean isRemoved = terminalAndHonourCatalog.remove(kind);
                    if (isRemoved)
                    {
                        pairFound = true;
                        continue;
                    }
                }
            }

            Optional<Integer> currentTerminalOrHonour = group.getIndices().stream().filter(index -> {
                MahjongTileKind kind = TileKindUtils.getKindFromIndex(index);
                return kind.isTerminal() || kind.isHonour();
            }).findFirst();

            if (currentTerminalOrHonour.isPresent())
            {
                MahjongTileKind kind = TileKindUtils.getKindFromIndex(currentTerminalOrHonour.get());
                terminalAndHonourCatalog.remove(kind);
            }
        }

        int shantenCount = terminalAndHonourCatalog.size();
        if (pairFound)
        {
            shantenCount--;
        }
        return shantenCount;
    }

    private static int countSevenPairsShanten(List<TileGroup> tileGroups)
    {
        int amountOfPairs = 0;

        for (TileGroup group : tileGroups)
        {
            if (group.isPair() || group.isTriplet())
            {
                amountOfPairs++;
            }
        }

        return 6 - amountOfPairs;
    }

    private static int countFourGroupsOnePairShanten(List<TileGroup> tileGroups)
    {
        int shanten = 8;
        boolean pairFound = false;

        tileGroups.sort(new TileGroupLengthComparator());
        for (int i = 0; i < Math.min(tileGroups.size(), 5); i++)
        {
            TileGroup currentGroup = tileGroups.get(i);
            if (!pairFound && currentGroup.isPair())
            {
                pairFound = true;
            }

            int currentGroupSize = Math.min(currentGroup.getSize(), 3);
            shanten = shanten - (currentGroupSize - 1);
        }

        if (!pairFound)
        {
            shanten++;
        }

        return shanten;
    }
}
