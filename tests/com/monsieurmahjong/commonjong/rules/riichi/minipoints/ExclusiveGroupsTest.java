package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
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
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(noExclusiveGroupsHand));
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, noExclusiveGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(0, value, "Value of exclusive groups with no exclusive group should be 0");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleMinkou_ShouldBe2()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleTripletGroupsHand));
        List<List<Tile>> melds = new ArrayList<>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("222m"));
        hand.setMelds(melds);
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneSimpleTripletGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(2, value, "Value of exclusive groups with a simple minkou should be 2");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleAnkou_ShouldBe4()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleTripletGroupsHand));
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneSimpleTripletGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(4, value, "Value of exclusive groups with a simple ankou should be 4");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleMinkou_ShouldBe4()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleTripletGroupsHand));
        List<List<Tile>> melds = new ArrayList<>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("111m"));
        hand.setMelds(melds);
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleTripletGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(4, value, "Value of exclusive groups with a non-simple minkou should be 4");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleAnkou_ShouldBe8()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleTripletGroupsHand));
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleTripletGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(8, value, "Value of exclusive groups with a non-simple ankou should be 8");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleMinkan_ShouldBe8()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleQuadGroupsHand));
        List<List<Tile>> melds = new ArrayList<>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("2222m"));
        hand.setMelds(melds);
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneSimpleQuadGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(8, value, "Value of exclusive groups with a simple minkan should be 8");
    }

    @Test
    public void exclusiveGroupsValue_WithASimpleAnkan_ShouldBe16()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneSimpleQuadGroupsHand));
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneSimpleQuadGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(16, value, "Value of exclusive groups with a simple ankan should be 16");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleMinkan_ShouldBe16()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleQuadGroupsHand));
        List<List<Tile>> melds = new ArrayList<>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("1111m"));
        hand.setMelds(melds);
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleQuadGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(16, value, "Value of exclusive groups with a simple minkan should be 16");
    }

    @Test
    public void exclusiveGroupsValue_WithANonSimpleAnkan_ShouldBe32()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(oneNonSimpleQuadGroupsHand));
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, oneNonSimpleQuadGroupsHand);

        int value = exclusiveGroups.getFuValue();

        assertEquals(32, value, "Value of exclusive groups with a simple ankan should be 32");
    }

    @Test
    public void exclusiveGroupsValue_ForAMeltingPotToitoiHand_ShouldBeAsCalculatedByPlayer()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(randomToitoiGroups));
        List<List<Tile>> melds = new ArrayList<>();
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("2222s"));
        melds.add(TileGroupUtils.getTilesFromMPSZNotation("777p"));
        hand.setMelds(melds);
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(hand, randomToitoiGroups);

        int value = exclusiveGroups.getFuValue();

        assertEquals(50, value, "Value of exclusive groups with the hand 1111m777999p2222s33z, where 2222s and 777p are open, should be 50 (32 + 2 + 8 + 8)");
    }

    @Test
    public void exclusiveGroups_Validity_IsAlwaysTrue()
    {
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(ANY_HAND, null);

        boolean isValid = exclusiveGroups.isValid();

        assertTrue(isValid, "Exclusive groups is always valid");
    }
}
