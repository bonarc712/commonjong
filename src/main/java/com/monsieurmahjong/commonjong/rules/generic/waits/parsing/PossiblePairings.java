package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
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

    public PossiblePairings(String referenceTiles)
    {
        this(new MPSZNotation().getTilesFrom(referenceTiles));
    }

    /**
     * @param collidingGroups all groups that are part of one collision
     * @return the different ways the colliding groups can be arranged
     */
    public List<List<TileGroup>> createFrom(List<TileGroup> collidingGroups)
    {
        List<Integer> indicesForCollidingGroups = collidingGroups.stream().map(tileGroup -> tileGroup.getIndices()).flatMap(list -> list.stream()).distinct().collect(Collectors.toList());
        Map<Integer, List<List<TileGroup>>> possiblePairingsByElement = new TreeMap<>();
        var loopIndex = 0;
        while (loopIndex < referenceTiles.size())
        {
            var currentKind = referenceTiles.get(loopIndex).getTileKind();
            var count = (int) referenceTiles.stream().filter(tile -> tile.getTileKind().equals(currentKind)).count();

            if (indicesForCollidingGroups.contains(currentKind.getIndex()))
            {
                var possiblePairingsOfCurrentElement = addPossiblePairing(currentKind, count, collidingGroups);
                possiblePairingsByElement.put(currentKind.getIndex(), possiblePairingsOfCurrentElement);
            }

            loopIndex = loopIndex + count;
        }

        var differentCombinations = listDifferentCombinations(possiblePairingsByElement.values());

        List<List<TileGroup>> possiblePairings = new ArrayList<>();
        for (List<List<TileGroup>> combination : differentCombinations) // for each combination
        {
            Map<Integer, List<TileGroup>> combinationsByIndex = new TreeMap<>();
            StreamEx.of(indicesForCollidingGroups) //
                    .zipWith(combination.stream()) //
                    .forEach(oneCombination -> combinationsByIndex.put(oneCombination.getKey(), oneCombination.getValue()));

            // initialize tile keep flags
            var keepTilesFlags = getFlagsForTilesToKeep(combinationsByIndex, collidingGroups);

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

    public List<List<Boolean>> getFlagsForTilesToKeep(Map<Integer, List<TileGroup>> combination, List<TileGroup> collidingGroups)
    {
        var indicesLeft = new HashMap<Integer, Integer>();
        combination.keySet().forEach(index -> {
            indicesLeft.put(index, (int) referenceTiles.stream().filter(tile -> tile.getTileKind().getIndex() == index).count());
        });

        return collidingGroups.stream().map(tileGroup -> {
            return tileGroup.getIndices().stream().map(index -> {
                var amountOfIndexLeft = indicesLeft.get(index);
                var currentCombination = combination.get(index);
                if (currentCombination.contains(tileGroup) && amountOfIndexLeft > currentCombination.size() - currentCombination.indexOf(tileGroup) - 1)
                {
                    indicesLeft.put(index, amountOfIndexLeft - 1);
                    return true;
                }
                return false;
            }).toList();
        }).toList();
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
