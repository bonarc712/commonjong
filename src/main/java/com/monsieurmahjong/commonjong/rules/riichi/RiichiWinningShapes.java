package com.monsieurmahjong.commonjong.rules.riichi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.WinningShapes;

public class RiichiWinningShapes implements WinningShapes
{
    /*
     * Hand has 4 groups and 1 pair OR 7 pairs OR 13 orphans
     */
    @Override
    public boolean hasWinningShape(List<Tile> hand)
    {
        if (hand.size() < 14 && hand.size() > 18)
        {
            return false;
        }

        if (isThirteenOrphans(hand))
        {
            return true;
        }

        if (isSevenPairs(hand))
        {
            return true;
        }

        if (isFourGroupsOnePair(hand))
        {
            return true;
        }

        return false;
    }

    public boolean isThirteenOrphans(List<Tile> hand)
    {
        var handCopy = new ArrayList<Tile>();
        handCopy.addAll(hand);

        var tileGroups = new ArrayList<List<Tile>>();

        while (!handCopy.isEmpty())
        {
            var tileWasAdded = false;
            var tile = handCopy.remove(0);
            for (var group : tileGroups)
            {
                if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
                {
                    group.add(tile);
                    tileWasAdded = true;
                }
            }

            if (!tileWasAdded)
            {
                var newGroup = new ArrayList<Tile>();
                newGroup.add(tile);
                tileGroups.add(newGroup);
            }
        }

        if (tileGroups.size() == 13)
        {
            var sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
            if (sizes.stream().filter(size -> size == 1).count() == 12 && sizes.stream().filter(size -> size == 2).count() == 1)
            {
                return true;
            }
        }

        return false;
    }

    public boolean isSevenPairs(List<Tile> hand)
    {
        var handCopy = new ArrayList<Tile>();
        handCopy.addAll(hand);

        var tileGroups = new ArrayList<List<Tile>>();

        while (!handCopy.isEmpty())
        {
            var tileWasAdded = false;
            var tile = handCopy.remove(0);
            for (var group : tileGroups)
            {
                if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
                {
                    group.add(tile);
                    tileWasAdded = true;
                }
            }

            if (!tileWasAdded)
            {
                var newGroup = new ArrayList<Tile>();
                newGroup.add(tile);
                tileGroups.add(newGroup);
            }
        }

        if (tileGroups.size() == 7)
        {
            var sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
            if (sizes.stream().filter(size -> size == 2).count() == 7)
            {
                return true;
            }
        }

        return false;
    }

    public boolean isFourGroupsOnePair(List<Tile> hand)
    {
        var handCopy = new ArrayList<Tile>();
        handCopy.addAll(hand);

        var tileGroups = new ArrayList<List<Tile>>();

        while (!handCopy.isEmpty())
        {
            var tileWasAdded = false;
            var tile = handCopy.remove(0);
            for (var group : tileGroups)
            {
                if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
                {
                    group.add(tile);
                    tileWasAdded = true;
                }
            }

            if (!tileWasAdded)
            {
                var newGroup = new ArrayList<Tile>();
                newGroup.add(tile);
                tileGroups.add(newGroup);
            }
        }

        if (tileGroups.size() == 5)
        {
            var sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
            if (sizes.stream().filter(size -> size >= 3 && size < 5).count() == 4 && sizes.stream().filter(size -> size == 2).count() == 1)
            {
                return true;
            }
        }

        return false;
    }
}
