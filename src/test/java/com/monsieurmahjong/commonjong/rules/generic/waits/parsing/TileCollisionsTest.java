package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.generic.waits.parsing.TileCollisions.CollisionPair;

public class TileCollisionsTest
{
    @Test
    public void whenSearchingCollisionPairsForTilesWithCollisions_thenShouldReturnThePairs()
    {
        // 135567s case
        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var mpsz = new MPSZNotation();
        var tileCollisions = new TileCollisions(tileGroups);
        var collisionPairs = tileCollisions.findCollisionPairs(tileGroups);
        List<CollisionPair> expectedCollisionPairs = new ArrayList<>();

        var tileGroupArray = new TileGroup[2];
        expectedCollisionPairs.add(new CollisionPair(mpsz.getTileGroupsFrom("13s", "35s").toArray(tileGroupArray)));
        expectedCollisionPairs.add(new CollisionPair(mpsz.getTileGroupsFrom("35s", "55s").toArray(tileGroupArray)));
        expectedCollisionPairs.add(new CollisionPair(mpsz.getTileGroupsFrom("35s", "567s").toArray(tileGroupArray)));
        expectedCollisionPairs.add(new CollisionPair(mpsz.getTileGroupsFrom("55s", "567s").toArray(tileGroupArray)));

        assertEquals(expectedCollisionPairs, collisionPairs, "Collision pairs for 135567s are not as expected");
    }

    @Test
    public void whenSearchingCollisionPairsForTilesWithoutCollisions_thenShouldBeEmpty()
    {
        // 77z case
        List<TileGroup> tileGroups = new ArrayList<>();
        tileGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));

        var tileCollisions = new TileCollisions(tileGroups);
        var collisionPairs2 = tileCollisions.findCollisionPairs(tileGroups);
        List<List<TileGroup>> expectedCollisionPairs2 = new ArrayList<>();

        assertEquals(expectedCollisionPairs2, collisionPairs2, "There should be not collision pair for 77z");
    }

    @Test
    public void testCreateCollisionList()
    {
        // 135567s case
        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var tileCollisions = new TileCollisions(tileGroups);
        var collisionList = tileCollisions.createCollisionList();

        List<List<TileGroup>> expectedResultCollisionList = new ArrayList<>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s"));

        assertEquals(expectedResultCollisionList, collisionList, "Collision list for 135567s is not as expected");
    }

}
