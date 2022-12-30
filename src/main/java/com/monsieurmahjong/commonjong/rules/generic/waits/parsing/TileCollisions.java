package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.utils.NestedLoop;

public class TileCollisions
{
    private List<TileGroup> tileGroups;

    public TileCollisions(List<TileGroup> tileGroups)
    {
        this.tileGroups = tileGroups;
    }

    public List<List<TileGroup>> createCollisionList()
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

    protected List<CollisionPair> findCollisionPairs(List<TileGroup> tileGroups)
    {
        return new NestedLoop().loopOn(tileGroups) //
                .filter(pair -> pair.getFirst().collidesWith(pair.getSecond())) //
                .map(pair -> new CollisionPair(pair.getFirst(), pair.getSecond())) //
                .collect(Collectors.toList());
    }

    protected record CollisionPair(TileGroup firstTileGroup, TileGroup secondTileGroup)
    {
        protected CollisionPair(TileGroup... tileGroups)
        {
            this(tileGroups[0], tileGroups[1]);
        }
    }
}
