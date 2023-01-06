package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

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
        // first, create the collision list for all groups that collide with each other.
        // All groups that have no collisions are not added in the list
        var collisionList = new TileCollisions().createCollisionList(tileGroups);

        // then dissect these collision groups to get the different possible pairings
        List<List<List<TileGroup>>> listOfPossiblePairings = new ArrayList<>();
        var possiblePairings = new PossiblePairings(unmeldedTiles);
        for (List<TileGroup> collidingGroups : collisionList)
        {
            // create the pairings for the current group
            listOfPossiblePairings.add(possiblePairings.createFrom(collidingGroups));
        }

        // create hand configurations from possible pairing lists
        var handConfigurations = createHandConfigurations(listOfPossiblePairings, new ArrayList<>(), new ArrayList<>());

        if (handConfigurations.isEmpty())
        {
            // only one configuration is possible. Create just one list
            handConfigurations.add(new ArrayList<>());
        }

        // add tile groups that are not within a collision list to the result, then add
        // melded tiles to the result
        handConfigurations.forEach(list -> {
            list.addAll(getTileGroupsWithoutCollisions(tileGroups, collisionList));
            list.addAll(getMeldedTileGroups());
        });

        return handConfigurations;
    }

    private List<TileGroup> getTileGroupsWithoutCollisions(List<TileGroup> tileGroups, List<List<TileGroup>> collisionList)
    {
        var tileGroupsWithoutCollisions = new ArrayList<TileGroup>();
        for (TileGroup tileGroup : tileGroups)
        {
            if (collisionList.stream().noneMatch(list -> list.contains(tileGroup)))
            {
                tileGroupsWithoutCollisions.add(tileGroup);
            }
        }
        return tileGroupsWithoutCollisions;
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
}
