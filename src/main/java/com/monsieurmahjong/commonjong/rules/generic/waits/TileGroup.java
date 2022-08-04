package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.utils.WaitShapeUtils;

/**
 * A tile group represents a simple group of several tiles (for instance a pair,
 * a triplet or a sequence)
 */
public class TileGroup
{
    private List<Integer> tileIndices;

    public TileGroup()
    {
        tileIndices = new ArrayList<>();
    }

    public TileGroup(List<Integer> indices)
    {
        tileIndices = new ArrayList<>();
        tileIndices.addAll(indices);
    }

    public static TileGroup of(MahjongTileKind... tileKinds)
    {
        var tileGroup = new TileGroup();
        for (var tileKind : tileKinds)
        {
            var index = tileKind.getIndex();
            tileGroup.add(index);
        }
        return tileGroup;
    }

    public int getSize()
    {
        return tileIndices.size();
    }

    public boolean isEmpty()
    {
        return getSize() == 0;
    }

    public List<Integer> getIndices()
    {
        return tileIndices;
    }

    public MahjongTileKind getTileKindAt(int index)
    {
        return TileKindUtils.getKindFromIndex(tileIndices.get(index));
    }

    public List<MahjongTileKind> getTileKinds()
    {
        List<MahjongTileKind> tileKinds = new ArrayList<>();
        for (int index : tileIndices)
        {
            tileKinds.add(TileKindUtils.getKindFromIndex(index));
        }
        return tileKinds;
    }

    public List<Integer> getTileNumbers()
    {
        List<Integer> tileNumbers = new ArrayList<>();
        for (MahjongTileKind tileKind : getTileKinds())
        {
            tileNumbers.add(tileKind.getTileNumber());
        }
        return tileNumbers;
    }

    public void add(Integer index)
    {
        tileIndices.add(index);
    }

    public void addAll(Integer... indices)
    {
        tileIndices.addAll(Arrays.asList(indices));
    }

    /**
     * @return true if one of the tiles matches the predicate; false otherwise
     */
    public boolean contains(Predicate<MahjongTileKind> predicate)
    {
        for (int index : tileIndices)
        {
            var tileKind = TileKindUtils.getKindFromIndex(index);
            if (predicate.test(tileKind))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * An exclusive group is a group where all the tiles are the same (eg. lone
     * tile, pair, triplet, quad, etc.)
     */
    public boolean isExclusiveGroup()
    {
        return tileIndices.stream().distinct().count() == 1;
    }

    public boolean isPair()
    {
        return isExclusiveGroup() && tileIndices.size() == 2;
    }

    public boolean isProtogroup()
    {
        return !isExclusiveGroup() && tileIndices.size() == 2;
    }

    public boolean isTriplet()
    {
        return isExclusiveGroup() && tileIndices.size() == 3;
    }

    public boolean isQuad()
    {
        return isExclusiveGroup() && tileIndices.size() == 4;
    }

    /**
     * A complete exclusive group is a group that is formed of one tile kind and
     * that contains at least three tiles. A triplet, a quad or a quint (with
     * jokers) form up a complete exclusive group.
     */
    public boolean isCompleteExclusiveGroup()
    {
        return isExclusiveGroup() && tileIndices.size() >= 3;
    }

    /**
     * A run is a sequence of three tiles (eg. 345m)
     */
    public boolean isRun()
    {
        if (tileIndices.size() < 3)
        {
            return false;
        }
        return WaitShapeUtils.isRun(tileIndices.get(0), tileIndices.get(1), tileIndices.get(2));
    }

    public boolean isComplete()
    {
        if (tileIndices.size() < 3)
        {
            return false;
        }
        return WaitShapeUtils.isTriplet(tileIndices.get(0), tileIndices.get(1), tileIndices.get(2)) || WaitShapeUtils.isRun(tileIndices.get(0), tileIndices.get(1), tileIndices.get(2));
    }

    /**
     * Improving tiles are any tiles that improve the current group that is worked
     * on. All waiting tiles are included in that, but for lone tiles, we must also
     * add all tiles that can match up with the current tile to form a protogroup.
     * 
     * These are not tiles that will make the hand win, but they are part of the
     * ukeire and also contribute to reduce shanten.
     */
    public List<Integer> getImprovingTiles()
    {
        var improvingTiles = getWaitingTiles();

        // add tiles that can form a protogroup with the current tile
        if (tileIndices.size() == 1)
        {
            int tileIndex = tileIndices.get(0);
            if (TileKindUtils.getKindFromIndex(tileIndex).isNumeral())
            {
                if (tileIndex % 9 != 0 && tileIndex % 9 != 1) // tile is not a 1 or a 2
                {
                    improvingTiles.add(tileIndex - 2);
                }
                if (tileIndex % 9 != 0) // tile is not a 1
                {
                    improvingTiles.add(tileIndex - 1);
                }
                if (tileIndex % 9 != 8) // tile is not a 9
                {
                    improvingTiles.add(tileIndex + 1);
                }
                if (tileIndex % 9 != 8 && tileIndex % 9 != 7) // tile is not a 8 or a 9
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
            var first = Math.min(tileIndices.get(0), tileIndices.get(1));
            var second = Math.max(tileIndices.get(0), tileIndices.get(1));

            if (WaitShapeUtils.isPair(first, second))
            {
                wait.add(first);
            } else if (WaitShapeUtils.isProtogroup(first, second))
            {
                if (WaitShapeUtils.isDoubleSidedBlock(first, second))
                {
                    wait.add(first - 1);
                    wait.add(second + 1);
                } else if (WaitShapeUtils.isInsideBlock(first, second))
                {
                    wait.add(first + 1);
                } else if (WaitShapeUtils.isEndBlock(first, second))
                {
                    if (TileKindUtils.isTerminal(first))
                    {
                        wait.add(second + 1);
                    } else if (TileKindUtils.isTerminal(second))
                    {
                        wait.add(first - 1);
                    }
                }
            }
        }

        return wait;
    }

    /**
     * Return true if any tile index is the same in this group and in the other
     * group.
     * 
     * @param other TileGroup to compare with the current group
     */
    public boolean collidesWith(TileGroup other)
    {
        for (int index : other.getIndices())
        {
            if (tileIndices.contains(index))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof TileGroup))
        {
            return false;
        }
        return tileIndices.equals(((TileGroup) other).tileIndices);
    }

    @Override
    public String toString()
    {
        var toReturn = "TileGroup : ";
        for (Integer index : tileIndices)
        {
            toReturn += TileKindUtils.getKindFromIndex(index).name() + " ";
        }

        return toReturn;
    }

    public String toMPSZNotation()
    {
        var toReturn = "";
        for (Integer index : tileIndices)
        {
            toReturn += TileKindUtils.getKindFromIndex(index).getMPSZNumber();
        }
        toReturn += TileKindUtils.getKindFromIndex(tileIndices.get(0)).getMPSZFamily();

        return toReturn;
    }
}
