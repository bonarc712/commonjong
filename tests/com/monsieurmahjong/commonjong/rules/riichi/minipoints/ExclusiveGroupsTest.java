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
    public void exclusiveGroups_Validity_IsAlwaysTrue()
    {
        ExclusiveGroups exclusiveGroups = new ExclusiveGroups(ANY_HAND, null);

        boolean isValid = exclusiveGroups.isValid();

        assertTrue(isValid, "Exclusive groups is always valid");
    }
}
