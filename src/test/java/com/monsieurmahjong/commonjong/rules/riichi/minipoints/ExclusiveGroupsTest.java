package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class ExclusiveGroupsTest
{
    private static final Hand ANY_HAND = new Hand();

    private List<TileGroup> noExclusiveGroupsHand = TileGroupUtils.tileGroupsOf("123m", "789p", "789p", "123s", "33z");
    private List<TileGroup> oneSimpleTripletGroupsHand = TileGroupUtils.tileGroupsOf("222m", "789p", "789p", "123s", "33z");
    private List<TileGroup> oneNonSimpleTripletGroupsHand = TileGroupUtils.tileGroupsOf("111m", "789p", "789p", "123s", "33z");
    private List<TileGroup> oneSimpleQuadGroupsHand = TileGroupUtils.tileGroupsOf("2222m", "789p", "789p", "123s", "33z");
    private List<TileGroup> oneNonSimpleQuadGroupsHand = TileGroupUtils.tileGroupsOf("1111m", "789p", "789p", "123s", "33z");
    private List<TileGroup> randomToitoiGroups = TileGroupUtils.tileGroupsOf("1111m", "777p", "999p", "2222s", "33z");

    @Test
    public void exclusiveGroupsValue_WithNoExclusiveGroup_ShouldBe0()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(noExclusiveGroupsHand));
        var exclusiveGroups = new ExclusiveGroups(hand, noExclusiveGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(0, value, "Value of exclusive groups with no exclusive group should be 0");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleMinkou_ShouldBe2()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleTripletGroupsHand));
        var melds = new ArrayList<List<Tile>>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("222m"));
        hand.setMelds(melds);
        var exclusiveGroups = new ExclusiveGroups(hand, oneSimpleTripletGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(2, value, "Value of exclusive groups with a simple minkou should be 2");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleAnkou_ShouldBe4()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleTripletGroupsHand));
        var exclusiveGroups = new ExclusiveGroups(hand, oneSimpleTripletGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(4, value, "Value of exclusive groups with a simple ankou should be 4");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleMinkou_ShouldBe4()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleTripletGroupsHand));
        var melds = new ArrayList<List<Tile>>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("111m"));
        hand.setMelds(melds);
        var exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleTripletGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(4, value, "Value of exclusive groups with a non-simple minkou should be 4");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleAnkou_ShouldBe8()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleTripletGroupsHand));
        var exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleTripletGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(8, value, "Value of exclusive groups with a non-simple ankou should be 8");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleMinkan_ShouldBe8()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleQuadGroupsHand));
        var melds = new ArrayList<List<Tile>>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("2222m"));
        hand.setMelds(melds);
        var exclusiveGroups = new ExclusiveGroups(hand, oneSimpleQuadGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(8, value, "Value of exclusive groups with a simple minkan should be 8");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleAnkan_ShouldBe16()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleQuadGroupsHand));
        var exclusiveGroups = new ExclusiveGroups(hand, oneSimpleQuadGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(16, value, "Value of exclusive groups with a simple ankan should be 16");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleMinkan_ShouldBe16()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleQuadGroupsHand));
        var melds = new ArrayList<List<Tile>>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("1111m"));
        hand.setMelds(melds);
        var exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleQuadGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(16, value, "Value of exclusive groups with a simple minkan should be 16");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleAnkan_ShouldBe32()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleQuadGroupsHand));
        var exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleQuadGroupsHand);

        var value = exclusiveGroups.getFuValue();

        assertEquals(32, value, "Value of exclusive groups with a simple ankan should be 32");
    }

    @Test
    public void exclusiveGroupsValue_ForAMeltingPotToitoiHand_ShouldBeAsCalculatedByPlayer()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(randomToitoiGroups));
        var melds = new ArrayList<List<Tile>>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("2222s"));
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("777p"));
        hand.setMelds(melds);
        var exclusiveGroups = new ExclusiveGroups(hand, randomToitoiGroups);

        var value = exclusiveGroups.getFuValue();

        assertEquals(50, value, "Value of exclusive groups with the hand 1111m777999p2222s33z, where 2222s and 777p are open, should be 50 (32 + 2 + 8 + 8)");
    }

    @Test
    public void exclusiveGroups_Validity_IsAlwaysTrue()
    {
        var exclusiveGroups = new ExclusiveGroups(ANY_HAND, null);

        var isValid = exclusiveGroups.isValid();

        assertTrue(isValid, "Exclusive groups is always valid");
    }
}
