package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class HandConfigurationParser
{
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

        List<List<Tile>> melds = hand.getMelds();
        for (List<Tile> meld : melds)
        {
            TileGroup tileGroup = new TileGroup(meld.stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList()));
            meldedTileGroups.add(tileGroup);
        }
        return meldedTileGroups;
    }

    /**
     * This method computes all hand configurations that are possible for the current hand using the known tile
     * groups that are supplied as a parameter. All melded tile groups are added automatically to all hand configurations
     * because they can't be changed.
     * 
     * @param tileGroups possible tile groups that can be created with the hand
     */
    public List<List<TileGroup>> getHandConfigurations(List<TileGroup> tileGroups)
    {
        List<List<TileGroup>> handConfigurations = new ArrayList<>();

        // first, create the collision list for all groups that collide with each other. All groups that have no collisions are not added in the list
        List<List<TileGroup>> collisionList = createCollisionList(tileGroups);

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
     * This is package-private to ensure that this method can be unit tested.
     * @param collidingGroups all groups that are part of one collision
     * @return the different ways the colliding groups can be arranged
     */
    List<List<TileGroup>> createPossiblePairings(List<TileGroup> collidingGroups)
    {
        List<List<TileGroup>> possiblePairings = new ArrayList<>();

        List<Integer> indicesForCollidingGroups = collidingGroups.stream().map(tileGroup -> tileGroup.getIndices()).flatMap(list -> list.stream()).distinct().collect(Collectors.toList());
        Map<Integer, List<List<TileGroup>>> possiblePairingsByElement = new HashMap<>();
        int loopIndex = 0;
        while (loopIndex < unmeldedTiles.size())
        {
            MahjongTileKind currentKind = unmeldedTiles.get(loopIndex).getTileKind();
            int count = (int) unmeldedTiles.stream().filter(tile -> tile.getTileKind().equals(currentKind)).count();

            if (indicesForCollidingGroups.contains(currentKind.getIndex()))
            {
                List<List<TileGroup>> possiblePairingsOfCurrentElement = new ArrayList<>();
                possiblePairingsOfCurrentElement = addPossiblePairings(currentKind, count, collidingGroups, possiblePairingsOfCurrentElement, new ArrayList<>());

                possiblePairingsByElement.put(currentKind.getIndex(), possiblePairingsOfCurrentElement);
            }

            loopIndex = loopIndex + count;
        }

        List<List<List<TileGroup>>> differentCombinations = listDifferentCombinations(possiblePairingsByElement.values(), new ArrayList<>(), new ArrayList<>());

        for (List<List<TileGroup>> combination : differentCombinations) // for each combination
        {
            // initialize tile keep flags
            List<List<Boolean>> keepTilesFlags = getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

            List<TileGroup> currentTileGroupPairing = new ArrayList<>();

            for (int i = 0; i < keepTilesFlags.size(); i++)
            {
                TileGroup currentGroup = collidingGroups.get(i);
                TileGroup dividedTileGroup = new TileGroup();
                List<Boolean> keepTilesFlagsForCurrentGroup = keepTilesFlags.get(i);

                for (int j = 0; j < currentGroup.getIndices().size(); j++)
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

    List<List<Boolean>> getFlagsForTilesToKeep(List<Integer> indicesForCollidingGroups, List<List<TileGroup>> combination, List<TileGroup> collidingGroups)
    {
        List<List<Boolean>> keepTilesFlags = new ArrayList<>();
        for (TileGroup group : collidingGroups)
        {
            List<Boolean> keepFlagsForCurrentGroup = new ArrayList<>();
            for (int i = 0; i < group.getIndices().size(); i++)
            {
                keepFlagsForCurrentGroup.add(false);
            }
            keepTilesFlags.add(keepFlagsForCurrentGroup);
        }

        // for each different tile kind
        for (int i = 0; i < indicesForCollidingGroups.size(); i++)
        {
            int currentIndex = indicesForCollidingGroups.get(i);
            int currentIndexCount = (int) unmeldedTiles.stream().filter(tile -> tile.getTileKind().getIndex() == currentIndex).count(); // how many tiles in hand
            List<TileGroup> tileGroupsForCurrentIndex = combination.get(i);
            int originalTileGroupsForCurrentIndexSize = tileGroupsForCurrentIndex.size();

            // for each group in collision
            for (int j = 0; j < collidingGroups.size(); j++)
            {
                TileGroup group = collidingGroups.get(j);
                if (group.getIndices().contains(currentIndex))
                {
                    if (tileGroupsForCurrentIndex.contains(group))
                    {
                        // keep only one tile if amount of groups is equal to count, otherwise keep
                        int countForCurrentGroup = (int) group.getIndices().stream().filter(index -> index == currentIndex).count();
                        if (countForCurrentGroup > 1)
                        {
                            List<Integer> indicesToCheck = group.getIndices();
                            int indicesToKeep = currentIndexCount - originalTileGroupsForCurrentIndexSize + 1;
                            for (int k = 0; k < indicesToKeep; k++)
                            {
                                int kthIndexOfIndex = indicesToCheck.indexOf(currentIndex);
                                indicesToCheck = indicesToCheck.subList(kthIndexOfIndex + 1, indicesToCheck.size());

                                keepTilesFlags.get(j).set(k, true);
                            }
                        }
                        else if (countForCurrentGroup == 1)
                        {
                            int currentIndexOfIndex = group.getIndices().indexOf(currentIndex);
                            keepTilesFlags.get(j).set(currentIndexOfIndex, true);
                        }
                    }
                }
            }
        }
        return keepTilesFlags;
    }

    /**
     * This method is package-private so it can be unit-tested. It creates a list of all
     * the possible tile group lists of lists that can be created with the current colliding
     * tiles. The combinations must be ordered by tile index. A simplified signature is not
     * necessary since this method won't be accessed from the outside.
     */
    List<List<List<TileGroup>>> listDifferentCombinations(Collection<List<List<TileGroup>>> pairings, List<List<List<TileGroup>>> tileGroupsToReturn, List<List<TileGroup>> currentTileGroupSoFar)
    {
        if (pairings.isEmpty())
        {
            return tileGroupsToReturn;
        }

        List<List<List<TileGroup>>> pairingsCopy = new ArrayList<>(pairings);
        List<List<TileGroup>> pairingsForCurrentIndex = pairingsCopy.remove(0);
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
     * Hand configs are built in a similar fashion than combinations are,
     * as they are basically a combination of combinations.
     */
    private List<List<TileGroup>> createHandConfigurations(List<List<List<TileGroup>>> possiblePairingsList, List<List<TileGroup>> handConfigsToReturn, List<TileGroup> currentHandConfigSoFar)
    {
        if (possiblePairingsList.isEmpty())
        {
            return handConfigsToReturn;
        }

        List<List<List<TileGroup>>> possiblePairingsCopy = new ArrayList<>(possiblePairingsList);
        List<List<TileGroup>> pairingsForCurrentIndex = possiblePairingsCopy.remove(0);
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
     * This method is package-private so it can be unit-tested. Create the possible pairings on
     * a specific tile kind. The occurrences is how many of that tile kind need to be found and
     * sorted between groups. This method is recursive as it needs to search into a tree of
     * possibilities.
     * 
     * @param tileKind : the tile kind to search
     * @param occurrences : how many of this tile need to be found
     * @param groupsToSelectFrom : the groups within which the tile is included
     * @param possiblePairingsOfTileKind : the possible pairings that have been built so far
     * @param currentPairingMade : the current pairing on which the code is operating
     * @return all possible pairings for the current tile
     */
    List<List<TileGroup>> addPossiblePairings(MahjongTileKind tileKind, int occurrences, List<TileGroup> groupsToSelectFrom, List<List<TileGroup>> possiblePairingsOfTileKind,
            List<TileGroup> currentPairingMade)
    {
        if (occurrences <= 0)
        {
            return possiblePairingsOfTileKind;
        }

        List<TileGroup> groupsToSelectFromCopy = new ArrayList<>(groupsToSelectFrom);
        while (!groupsToSelectFromCopy.isEmpty())
        {
            TileGroup group = groupsToSelectFromCopy.get(0);

            // if the case the group contains the current tile kind, add it to the group, then redo
            if (group.getIndices().contains(tileKind.getIndex()))
            {
                List<TileGroup> pairingForCurrentGroup = new ArrayList<>(currentPairingMade);
                pairingForCurrentGroup.add(group);

                int amountInCurrentGroup = (int) group.getIndices().stream().filter(index -> index == tileKind.getIndex()).count();

                // branch out depending on the amount (for pairs, triplets, etc.)
                for (int i = 0; i < Math.min(amountInCurrentGroup, occurrences); i++)
                {
                    int countForCurrentPairing = occurrences - (i + 1);
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

    /**
     * This is package-private to ensure that this method can be unit tested.
     * @param tileGroups all tile groups that exist for the current hand
     * @return list of all composite collisions between tile groups
     */
    List<List<TileGroup>> createCollisionList(List<TileGroup> tileGroups)
    {
        List<List<TileGroup>> collisionList = new ArrayList<>();

        // find collision pairs
        List<List<TileGroup>> collisionPairs = findCollisionPairs(tileGroups);

        // then bring all collision pairs together in groups
        while (!collisionPairs.isEmpty())
        {
            List<TileGroup> currentCollision = null;
            List<TileGroup> collisionPair = collisionPairs.get(0);
            TileGroup firstGroup = collisionPair.get(0);
            TileGroup secondGroup = collisionPair.get(1);

            boolean addedToKnownCollision = false;
            for (List<TileGroup> knownCollision : collisionList)
            {
                if (knownCollision.contains(firstGroup))
                {
                    knownCollision.add(secondGroup);
                    currentCollision = knownCollision;
                    addedToKnownCollision = true;
                }
                else if (knownCollision.contains(secondGroup))
                {
                    knownCollision.add(firstGroup);
                    currentCollision = knownCollision;
                    addedToKnownCollision = true;
                }
            }

            if (!addedToKnownCollision)
            {
                List<TileGroup> collision = new ArrayList<>();
                collision.add(firstGroup);
                collision.add(secondGroup);
                collisionList.add(collision);
                currentCollision = collision;
            }

            // check other groups
            int i = 1;
            while (i < collisionPairs.size())
            {
                List<TileGroup> currentCollisionPair = collisionPairs.get(i);
                TileGroup currentCollisionPairFirst = currentCollisionPair.get(0);
                TileGroup currentCollisionPairSecond = currentCollisionPair.get(1);
                if (currentCollision.contains(currentCollisionPairFirst))
                {
                    if (!currentCollision.contains(currentCollisionPairSecond) && !currentCollisionPairFirst.equals(currentCollisionPairSecond))
                    {
                        currentCollision.add(currentCollisionPairSecond);
                    }
                    collisionPairs.remove(i);
                }
                else if (currentCollision.contains(currentCollisionPairSecond))
                {
                    if (!currentCollision.contains(currentCollisionPairFirst) && !currentCollisionPairFirst.equals(currentCollisionPairSecond))
                    {
                        currentCollision.add(currentCollisionPairFirst);
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

    /**
     * This method is package-private to make sure it can be unit tested.
     * @param tileGroups
     * @return
     */
    List<List<TileGroup>> findCollisionPairs(List<TileGroup> tileGroups)
    {
        List<List<TileGroup>> collisionPairs = new ArrayList<>();
        for (int i = 0; i < tileGroups.size(); i++)
        {
            for (int j = i + 1; j < tileGroups.size(); j++)
            {
                if (tileGroups.get(i).collidesWith(tileGroups.get(j)))
                {
                    List<TileGroup> collision = new ArrayList<>();
                    collision.add(tileGroups.get(i));
                    collision.add(tileGroups.get(j));
                    collisionPairs.add(collision);
                }
            }
        }
        return collisionPairs;
    }
}
