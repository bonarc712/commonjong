package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TileCollisionsTest
{
    @Test
    public void testCreateCollisionList()
    {
        // 135567s case
        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var tileCollisions = new TileCollisions();
        var collisionList = tileCollisions.createCollisionList(tileGroups);

        var expectedResultCollisionList = new ArrayList<List<TileGroup>>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s"));

        assertEquals(expectedResultCollisionList, collisionList, "Collision list for 135567s is not as expected");
    }

    @Test
    public void whenCreatingCollisionListFor123456789p11122z_thenAllPinzuGroupsShouldBeInThere()
    {
        var tileGroups = TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p", "567p", "678p", "789p", "111z", "22z");

        var tileCollisions = new TileCollisions();
        var collisionList = tileCollisions.createCollisionList(tileGroups);

        var expectedResultCollisionList = new ArrayList<List<TileGroup>>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p", "567p", "678p", "789p"));

        assertEquals(expectedResultCollisionList, collisionList, "Collision list for 123456789p11122z is not as expected");
    }

    @Test
    public void whenCreatingCollisionListForTwoDistinctGroupsOfGroups_thenShouldReturnBothGroups()
    {
        var tileGroups = TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p", "123s", "234s", "345s", "456s");

        var tileCollisions = new TileCollisions();
        var collisionList = tileCollisions.createCollisionList(tileGroups);

        var expectedResultCollisionList = new ArrayList<List<TileGroup>>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p"));
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("123s", "234s", "345s", "456s"));

        assertEquals(expectedResultCollisionList, collisionList, "Collision list for 12345p12345s is not as expected");
    }

    @Test
    public void whenCreatingCollisionListForTwoDistinctUnorderedGroupsOfGroups_thenShouldReturnBothGroups()
    {
        var tileGroups = TileGroupUtils.tileGroupsOf("123p", "234p", "456s", "123s", "234s", "345s", "345p", "456p");

        var tileCollisions = new TileCollisions();
        var collisionList = tileCollisions.createCollisionList(tileGroups);

        var expectedResultCollisionList = new ArrayList<List<TileGroup>>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("123p", "234p", "345p", "456p"));
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("123s", "234s", "345s", "456s"));

        assertEquals(expectedResultCollisionList, collisionList, "Collision list for 123456p123456s is not as expected");
    }

}
