package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;

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

        List<List<TileGroup>> collisionList = createCollisionList(tileGroups);

        // then dissect these groups to see the different possible pairings

        // create hand combinations as different ways to look at the hand, with the different pairings
        {
            // if a tilegroup is not in any collision list, just add it directly

            // else create a new combination for each possible pairing
        }

        // add melded tiles to the result
        handConfigurations.forEach(list -> list.addAll(getMeldedTileGroups()));
        return handConfigurations;
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
