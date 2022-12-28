package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;

public class HandConfigurationParser
{
    public static void main(String[] args)
    {
//        var hand1 = new Hand(TileKindUtils.asHand("123456789m11122z"));
//        var tileGroups = TileGroupUtils.tileGroupsOf("123m", "234m", "345m", "456m", "567m", "678m", "789m", "111z", "22z");

        var mpsz = new MPSZNotation();
        var hand1 = new Hand(mpsz.getTilesFrom("123456789p11122z"));
        var tileGroups = TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p", "567p", "678p", "789p", "111z", "22z");

        var instance = new HandConfigurationParser(hand1);
        instance.getHandConfigurations(tileGroups);

    }

    private Hand hand;
    private List<Tile> unmeldedTiles;

    public HandConfigurationParser(Hand hand)
    {
        this.hand = hand;
        unmeldedTiles = hand.getUnmeldedTiles();
    }

    private List<TileGroup> getMeldedTileGroups()
    {
        List<TileGroup> meldedTileGroups = new ArrayList<>();

        var melds = hand.getMelds();
        for (List<Tile> meld : melds)
        {
            var tileGroup = new TileGroup(meld.stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList()));
            meldedTileGroups.add(tileGroup);
        }
        return meldedTileGroups;
    }

    /**
     * This method computes all hand configurations that are possible for the
     * current hand using the known tile groups that are supplied as a parameter.
     * All melded tile groups are added automatically to all hand configurations
     * because they can't be changed.
     *
     * @param tileGroups possible tile groups that can be created with the hand
     */
    public List<List<TileGroup>> getHandConfigurations(List<TileGroup> tileGroups)
    {
        List<List<TileGroup>> handConfigurations = new ArrayList<>();

        // first, create the collision list for all groups that collide with each other.
        // All groups that have no collisions are not added in the list
        var collisionList = createCollisionList(tileGroups);

        // then dissect these collision groups to get the different possible pairings
        List<List<List<TileGroup>>> listOfPossiblePairings = new ArrayList<>();
        for (List<TileGroup> collidingGroups : collisionList)
        {
            // create the pairings for the current group
            listOfPossiblePairings.add(createPossiblePairings(collidingGroups));
        }

        // create hand configurations from possible pairing lists
        handConfigurations = createHandConfigurations(listOfPossiblePairings, new ArrayList<>(), new ArrayList<>());

        if (handConfigurations.isEmpty())
        {
            // only one configuration is possible. Create just one list
            handConfigurations.add(new ArrayList<>());
        }

        // add tile groups that are not within a collision list to the result
        for (TileGroup tileGroup : tileGroups)
        {
            if (collisionList.stream().noneMatch(list -> list.contains(tileGroup)))
            {
                handConfigurations.forEach(list -> list.add(tileGroup));
            }
        }

        // add melded tiles to the result
        handConfigurations.forEach(list -> list.addAll(getMeldedTileGroups()));
        return handConfigurations;
    }

    /**
     * @param collidingGroups all groups that are part of one collision
     * @return the different ways the colliding groups can be arranged
     */
    public List<List<TileGroup>> createPossiblePairings(List<TileGroup> collidingGroups)
    {
        List<List<TileGroup>> possiblePairings = new ArrayList<>();

        List<Integer> indicesForCollidingGroups = collidingGroups.stream().map(tileGroup -> tileGroup.getIndices()).flatMap(list -> list.stream()).distinct().collect(Collectors.toList());
        Map<Integer, List<List<TileGroup>>> possiblePairingsByElement = new HashMap<>();
        var loopIndex = 0;
        while (loopIndex < unmeldedTiles.size())
        {
            var currentKind = unmeldedTiles.get(loopIndex).getTileKind();
            var count = (int) unmeldedTiles.stream().filter(tile -> tile.getTileKind().equals(currentKind)).count();

            if (indicesForCollidingGroups.contains(currentKind.getIndex()))
            {
                List<List<TileGroup>> possiblePairingsOfCurrentElement = new ArrayList<>();
                possiblePairingsOfCurrentElement = addPossiblePairings(currentKind, count, collidingGroups, possiblePairingsOfCurrentElement, new ArrayList<>());

                possiblePairingsByElement.put(currentKind.getIndex(), possiblePairingsOfCurrentElement);
            }

            loopIndex = loopIndex + count;
        }

        var differentCombinations = listDifferentCombinations(possiblePairingsByElement.values(), new ArrayList<>(), new ArrayList<>());

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
            var currentIndexCount = (int) unmeldedTiles.stream().filter(tile -> tile.getTileKind().getIndex() == currentIndex).count(); // how many tiles in hand
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
     * ordered by tile index. A simplified signature is not necessary since this
     * method won't be accessed from the outside.
     */
    public List<List<List<TileGroup>>> listDifferentCombinations(Collection<List<List<TileGroup>>> pairings, List<List<List<TileGroup>>> tileGroupsToReturn,
            List<List<TileGroup>> currentTileGroupSoFar)
    {
        if (pairings.isEmpty())
        {
            return tileGroupsToReturn;
        }

        List<List<List<TileGroup>>> pairingsCopy = new ArrayList<>(pairings);
        var pairingsForCurrentIndex = pairingsCopy.remove(0);
        for (List<TileGroup> pairing : pairingsForCurrentIndex)
        {
            List<List<TileGroup>> currentTileGroupSoFarCopy = new ArrayList<>(currentTileGroupSoFar);
            currentTileGroupSoFarCopy.add(pairing);
            if (pairingsCopy.isEmpty())
            {
                tileGroupsToReturn.add(currentTileGroupSoFarCopy);
            }
            else
            {
                tileGroupsToReturn = listDifferentCombinations(pairingsCopy, tileGroupsToReturn, currentTileGroupSoFarCopy);
            }
        }

        return tileGroupsToReturn;
    }

    /**
     * Hand configs are built in a similar fashion than combinations are, as they
     * are basically a combination of combinations.
     */
    private List<List<TileGroup>> createHandConfigurations(List<List<List<TileGroup>>> possiblePairingsList, List<List<TileGroup>> handConfigsToReturn, List<TileGroup> currentHandConfigSoFar)
    {
        if (possiblePairingsList.isEmpty())
        {
            return handConfigsToReturn;
        }

        List<List<List<TileGroup>>> possiblePairingsCopy = new ArrayList<>(possiblePairingsList);
        var pairingsForCurrentIndex = possiblePairingsCopy.remove(0);
        for (List<TileGroup> pairing : pairingsForCurrentIndex)
        {
            List<TileGroup> currentHandConfigSoFarCopy = new ArrayList<>(currentHandConfigSoFar);
            currentHandConfigSoFarCopy.addAll(pairing);
            if (possiblePairingsCopy.isEmpty())
            {
                handConfigsToReturn.add(currentHandConfigSoFarCopy);
            }
            else
            {
                handConfigsToReturn = createHandConfigurations(possiblePairingsCopy, handConfigsToReturn, currentHandConfigSoFarCopy);
            }
        }

        return handConfigsToReturn;
    }

    /**
     * Create the possible pairings on a specific tile kind. The occurrences is how
     * many of that tile kind need to be found and sorted between groups. This
     * method is recursive as it needs to search into a tree of possibilities.
     *
     * @param tileKind                   : the tile kind to search
     * @param occurrences                : how many of this tile need to be found
     * @param groupsToSelectFrom         : the groups within which the tile is
     *                                   included
     * @param possiblePairingsOfTileKind : the possible pairings that have been
     *                                   built so far
     * @param currentPairingMade         : the current pairing on which the code is
     *                                   operating
     * @return all possible pairings for the current tile
     */
    public List<List<TileGroup>> addPossiblePairings(MahjongTileKind tileKind, int occurrences, List<TileGroup> groupsToSelectFrom, List<List<TileGroup>> possiblePairingsOfTileKind,
            List<TileGroup> currentPairingMade)
    {
        if (occurrences <= 0)
        {
            return possiblePairingsOfTileKind;
        }

        List<TileGroup> groupsToSelectFromCopy = new ArrayList<>(groupsToSelectFrom);
        while (!groupsToSelectFromCopy.isEmpty())
        {
            var group = groupsToSelectFromCopy.get(0);

            // if the case the group contains the current tile kind, add it to the group,
            // then redo
            if (group.getIndices().contains(tileKind.getIndex()))
            {
                List<TileGroup> pairingForCurrentGroup = new ArrayList<>(currentPairingMade);
                pairingForCurrentGroup.add(group);

                var amountInCurrentGroup = (int) group.getIndices().stream().filter(index -> index == tileKind.getIndex()).count();

                // branch out depending on the amount (for pairs, triplets, etc.)
                for (var i = 0; i < Math.min(amountInCurrentGroup, occurrences); i++)
                {
                    var countForCurrentPairing = occurrences - (i + 1);
                    List<TileGroup> groupsToSelectFromExcludingCurrent = new ArrayList<>(groupsToSelectFromCopy);
                    groupsToSelectFromExcludingCurrent.remove(group);
                    if (countForCurrentPairing == 0)
                    {
                        possiblePairingsOfTileKind.add(pairingForCurrentGroup);
                    }
                    else
                    {
                        addPossiblePairings(tileKind, countForCurrentPairing, groupsToSelectFromExcludingCurrent, possiblePairingsOfTileKind, pairingForCurrentGroup);
                    }
                }
            }

            groupsToSelectFromCopy.remove(group);
        }

        return possiblePairingsOfTileKind;
    }

    public List<List<TileGroup>> createCollisionList(List<TileGroup> tileGroups)
    {
        List<List<TileGroup>> collisionList = new ArrayList<>();

        // find collision pairs
        var collisionPairs = findCollisionPairs(tileGroups);

        // then bring all collision pairs together in groups
        while (!collisionPairs.isEmpty())
        {
            List<TileGroup> currentCollision = null;
            var collisionPair = collisionPairs.get(0);

            var addedToKnownCollision = false;
            for (List<TileGroup> knownCollision : collisionList)
            {
                if (knownCollision.contains(collisionPair.firstTileGroup))
                {
                    knownCollision.add(collisionPair.secondTileGroup);
                    currentCollision = knownCollision;
                    addedToKnownCollision = true;
                }
                else if (knownCollision.contains(collisionPair.secondTileGroup))
                {
                    knownCollision.add(collisionPair.firstTileGroup);
                    currentCollision = knownCollision;
                    addedToKnownCollision = true;
                }
            }

            if (!addedToKnownCollision)
            {
                List<TileGroup> collision = new ArrayList<>();
                collision.add(collisionPair.firstTileGroup);
                collision.add(collisionPair.secondTileGroup);
                collisionList.add(collision);
                currentCollision = collision;
            }

            // check other groups
            var i = 1;
            while (i < collisionPairs.size())
            {
                var currentCollisionPair = collisionPairs.get(i);
                if (currentCollision.contains(currentCollisionPair.firstTileGroup))
                {
                    if (!currentCollision.contains(currentCollisionPair.secondTileGroup) && !currentCollisionPair.firstTileGroup.equals(currentCollisionPair.secondTileGroup))
                    {
                        currentCollision.add(currentCollisionPair.secondTileGroup);
                    }
                    collisionPairs.remove(i);
                }
                else if (currentCollision.contains(currentCollisionPair.secondTileGroup))
                {
                    if (!currentCollision.contains(currentCollisionPair.firstTileGroup) && !currentCollisionPair.firstTileGroup.equals(currentCollisionPair.secondTileGroup))
                    {
                        currentCollision.add(currentCollisionPair.firstTileGroup);
                    }
                    collisionPairs.remove(i);
                }
                else
                {
                    i++;
                }
            }

            collisionPairs.remove(0);
        }

        return collisionList;
    }

    public List<CollisionPair> findCollisionPairs(List<TileGroup> tileGroups)
    {
        List<CollisionPair> collisionPairs = new ArrayList<>();
        for (var i = 0; i < tileGroups.size(); i++)
        {
            for (var j = i + 1; j < tileGroups.size(); j++)
            {
                if (tileGroups.get(i).collidesWith(tileGroups.get(j)))
                {
                    var collision = new CollisionPair(tileGroups.get(i), tileGroups.get(j));
                    collisionPairs.add(collision);
                }
            }
        }
        return collisionPairs;
    }

    protected record CollisionPair(TileGroup firstTileGroup, TileGroup secondTileGroup)
    {
        protected CollisionPair(TileGroup... tileGroups)
        {
            this(tileGroups[0], tileGroups[1]);
        }
    }
}
