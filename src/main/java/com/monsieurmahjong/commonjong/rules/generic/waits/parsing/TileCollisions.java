package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

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

        public boolean equals(CollisionPair other)
        {
            return firstTileGroup.equals(other.firstTileGroup) && secondTileGroup.equals(other.secondTileGroup);
        }
    }

}
