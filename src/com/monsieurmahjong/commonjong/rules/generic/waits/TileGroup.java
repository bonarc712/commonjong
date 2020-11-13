package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.generic.utils.*;

public class TileGroup
{
    private List<Integer> tileIndices;

    public TileGroup()
    {
        tileIndices = new ArrayList<>();
    }

    public void addAll(Integer... indices)
    {
        tileIndices.addAll(Arrays.asList(indices));
    }

    public boolean isComplete()
    {
        if (tileIndices.size() < 3)
        {
            return false;
        }
        return WaitShapeUtils.isGroup(tileIndices.get(0), tileIndices.get(1), tileIndices.get(2));
    }

    /**
     * Improving tiles are any tiles that improve the current group that is worked on.
     * All waiting tiles are included in that, but for lone tiles, we must also add all
     * tiles that can match up with the current tile to form a protogroup. 
     * 
     * These are not tiles that will make the hand win, but they are part of the ukeire 
     * and also contribute to reduce shanten.
     */
    public List<Integer> getImprovingTiles()
    {
        List<Integer> improvingTiles = getWaitingTiles();

        // add tiles that can form a protogroup with the current tile
        if (tileIndices.size() == 1)
        {
            int tileIndex = tileIndices.get(0);
            if (TileKindUtils.getKindFromIndex(tileIndex).isNumeral())
            {
                if (tileIndex % 9 != 1 && tileIndex % 9 != 2) // tile is not a 1 or a 2
                {
                    improvingTiles.add(tileIndex - 2);
                }
                if (tileIndex % 9 != 1) // tile is not a 1
                {
                    improvingTiles.add(tileIndex - 1);
                }
                if (tileIndex % 9 != 0) // tile is not a 9
                {
                    improvingTiles.add(tileIndex + 1);
                }
                if (tileIndex % 9 != 0 && tileIndex % 9 != 8) // tile is not a 8 or a 9
                {
                    improvingTiles.add(tileIndex + 2);
                }
            }
        }

        return improvingTiles;
    }

    public List<Integer> getWaitingTiles()
    {
        List<Integer> wait = new ArrayList<>();

        if (tileIndices.size() == 1)
        {
            wait.add(tileIndices.get(0));
        }

        if (tileIndices.size() == 2)
        {
            int first = Math.min(tileIndices.get(0), tileIndices.get(1));
            int second = Math.max(tileIndices.get(0), tileIndices.get(1));

            if (WaitShapeUtils.isPair(first, second))
            {
                wait.add(first);
            }
            else if (WaitShapeUtils.isProtogroup(first, second))
            {
                if (WaitShapeUtils.isDoubleSidedBlock(first, second))
                {
                    wait.add(first - 1);
                    wait.add(second + 1);
                }
                else if (WaitShapeUtils.isInsideBlock(first, second))
                {
                    wait.add(first + 1);
                }
                else if (WaitShapeUtils.isEndBlock(first, second))
                {
                    if (TileKindUtils.isTerminal(first))
                    {
                        wait.add(second + 1);
                    }
                    else if (TileKindUtils.isTerminal(second))
                    {
                        wait.add(first - 1);
                    }
                }
            }
        }

        return wait;
    }
}
