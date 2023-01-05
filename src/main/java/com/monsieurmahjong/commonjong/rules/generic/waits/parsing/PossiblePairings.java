package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroupKindComparator;
import com.monsieurmahjong.commonjong.utils.Permutations;

import one.util.streamex.StreamEx;

public class PossiblePairings
{
    private List<Tile> referenceTiles;

    public PossiblePairings(List<Tile> referenceTiles)
    {
        this.referenceTiles = referenceTiles;
    }

    /**
     * @param collidingGroups all groups that are part of one collision
     * @return the different ways the colliding groups can be arranged
     */
    public List<List<TileGroup>> createFrom(List<TileGroup> collidingGroups)
    {
        List<Integer> indicesForCollidingGroups = collidingGroups.stream().map(tileGroup -> tileGroup.getIndices()).flatMap(list -> list.stream()).distinct().collect(Collectors.toList());
        Map<Integer, List<List<TileGroup>>> possiblePairingsByElement = new HashMap<>();
        var loopIndex = 0;
        while (loopIndex < referenceTiles.size())
        {
            var currentKind = referenceTiles.get(loopIndex).getTileKind();
            var count = (int) referenceTiles.stream().filter(tile -> tile.getTileKind().equals(currentKind)).count();

            if (indicesForCollidingGroups.contains(currentKind.getIndex()))
            {
                List<List<TileGroup>> possiblePairingsOfCurrentElement = new ArrayList<>();
                possiblePairingsOfCurrentElement = addPossiblePairing(currentKind, count, collidingGroups);

                possiblePairingsByElement.put(currentKind.getIndex(), possiblePairingsOfCurrentElement);
            }

            loopIndex = loopIndex + count;
        }

        var differentCombinations = listDifferentCombinations(possiblePairingsByElement.values());

        List<List<TileGroup>> possiblePairings = new ArrayList<>();
        for (List<List<TileGroup>> combination : differentCombinations) // for each combination
        {
            // initialize tile keep flags
            var keepTilesFlags = getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

            List<TileGroup> currentTileGroupPairing = new ArrayList<>();

            for (var i = 0; i < keepTilesFlags.size(); i++)
            {
                var currentGroup = collidingGroups.get(i);
                var dividedTileGroup = new TileGroup();
                var keepTilesFlagsForCurrentGroup = keepTilesFlags.get(i);

                for (var j = 0; j < currentGroup.getIndices().size(); j++)
                {
                    if (keepTilesFlagsForCurrentGroup.get(j))
                    {
                        dividedTileGroup.add(currentGroup.getIndices().get(j));
                    }
                }

                if (!dividedTileGroup.isEmpty())
                {
                    currentTileGroupPairing.add(dividedTileGroup);
                }
            }

            possiblePairings.add(currentTileGroupPairing);
        }

        return possiblePairings;
    }

    public List<List<Boolean>> getFlagsForTilesToKeep(List<Integer> indicesForCollidingGroups, List<List<TileGroup>> combination, List<TileGroup> collidingGroups)
    {
        List<List<Boolean>> keepTilesFlags = new ArrayList<>();
        for (TileGroup group : collidingGroups)
        {
            List<Boolean> keepFlagsForCurrentGroup = new ArrayList<>();
            for (var i = 0; i < group.getIndices().size(); i++)
            {
                keepFlagsForCurrentGroup.add(false);
            }
            keepTilesFlags.add(keepFlagsForCurrentGroup);
        }

        // for each different tile kind
        for (var i = 0; i < indicesForCollidingGroups.size(); i++)
        {
            int currentIndex = indicesForCollidingGroups.get(i);
            var currentIndexCount = (int) referenceTiles.stream().filter(tile -> tile.getTileKind().getIndex() == currentIndex).count(); // how many tiles in hand
            var tileGroupsForCurrentIndex = combination.get(i);
            var originalTileGroupsForCurrentIndexSize = tileGroupsForCurrentIndex.size();

            // for each group in collision
            for (var j = 0; j < collidingGroups.size(); j++)
            {
                var group = collidingGroups.get(j);
                if (group.getIndices().contains(currentIndex))
                {
                    if (tileGroupsForCurrentIndex.contains(group))
                    {
                        // keep only one tile if amount of groups is equal to count, otherwise keep
                        var countForCurrentGroup = (int) group.getIndices().stream().filter(index -> index == currentIndex).count();
                        if (countForCurrentGroup > 1)
                        {
                            var indicesToCheck = group.getIndices();
                            var indicesToKeep = currentIndexCount - originalTileGroupsForCurrentIndexSize + 1;
                            for (var k = 0; k < indicesToKeep; k++)
                            {
                                var kthIndexOfIndex = indicesToCheck.indexOf(currentIndex);
                                indicesToCheck = indicesToCheck.subList(kthIndexOfIndex + 1, indicesToCheck.size());

                                keepTilesFlags.get(j).set(k, true);
                            }
                        }
                        else if (countForCurrentGroup == 1)
                        {
                            var currentIndexOfIndex = group.getIndices().indexOf(currentIndex);
                            keepTilesFlags.get(j).set(currentIndexOfIndex, true);
                        }
                    }
                }
            }
        }
        return keepTilesFlags;
    }

    /**
     * This method creates a list of all the possible tile group lists of lists that
     * can be created with the current colliding tiles. The combinations must be
     * ordered by tile index.
     */
    public List<List<List<TileGroup>>> listDifferentCombinations(Collection<List<List<TileGroup>>> pairings)
    {
        return StreamEx.cartesianProduct(pairings).toList();
    }

    /**
     * Create the possible pairings on a specific tile kind. The occurrences is how
     * many of that tile kind need to be found and sorted between groups.
     *
     * @param tileKind           : the tile kind to search
     * @param occurrences        : how many of this tile need to be found
     * @param groupsToSelectFrom : the groups within which the tile is included
     */
    public List<List<TileGroup>> addPossiblePairing(MahjongTileKind tileKind, int occurrences, List<TileGroup> groupsToSelectFrom)
    {
        var pairingsOfTileKind = new ArrayList<List<TileGroup>>();

        Permutations.of(groupsToSelectFrom.stream() //
                .filter(tileGroup -> tileGroup.contains(tileKind)) //
                .toArray(TileGroup[]::new) //
        ) //
                .map(stream -> stream.collect(Collectors.toList())) //
                .forEach(list -> {
                    var countedOccurrences = new AtomicInteger(0);
                    var pairing = new ArrayList<TileGroup>();
                    list.stream().takeWhile(tileGroup -> {
                        pairing.add(tileGroup);
                        var currentCount = countedOccurrences.addAndGet(tileGroup.countOfTile(tileKind));
                        return currentCount < occurrences;
                    }).collect(Collectors.toList());
                    pairing.sort(new TileGroupKindComparator());
                    pairingsOfTileKind.add(pairing);
                });

        return pairingsOfTileKind.stream() //
                .distinct() //
                .toList();
    }
}
