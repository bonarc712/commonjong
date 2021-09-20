package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TableWindPairTest
{
    private Hand anyHand = mock(Hand.class);

    private Seat tableWind = Seat.SOUTH;

    private List<TileGroup> completeTableWindPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "111z", "22z");
    private List<TileGroup> incompleteTableWindPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "111z", "22z");
    private List<TileGroup> completeNonTableWindPairHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> completeChiitoitsuWithTableWindPairHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "22z");

    @Test
    public void withAHandWithATableWindPair_TableWindPairFu_IsValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        TableWindPair tableWindPair = new TableWindPair(hand, completeTableWindPairHandGroups);

        boolean isValid = tableWindPair.isValid();

        assertTrue(isValid, "Table wind pair should be valid for a hand that contains a table wind pair");
    }

    @Test
    public void withAnIncompleteHandWithATableWindPair_TableWindPairFu_IsValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        TableWindPair tableWindPair = new TableWindPair(hand, incompleteTableWindPairHandGroups);

        boolean isValid = tableWindPair.isValid();

        assertTrue(isValid, "Table wind pair should be valid for a hand that contains a table wind pair");
    }

    @Test
    public void mWithAHandWithoutATableWindPair_TableWindPairFu_IsNotValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        TableWindPair tableWindPair = new TableWindPair(hand, completeNonTableWindPairHandGroups);

        boolean isValid = tableWindPair.isValid();

        assertFalse(isValid, "Table wind pair should not be valid for a hand that doesn't contain a table wind pair");
    }

    @Test
    public void withASevenPairsHandWithATableWindPair_TableWindPairFu_IsNotValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuWithTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        TableWindPair tableWindPair = new TableWindPair(hand, completeChiitoitsuWithTableWindPairHandGroups);

        boolean isValid = tableWindPair.isValid();

        assertFalse(isValid, "Table wind pair should not be valid for a seven pairs hand that contains a table wind pair");
    }

    @Test
    public void getFuValue_ForTableWindPair_IsTwo()
    {
        TableWindPair tableWindPair = new TableWindPair(anyHand, new ArrayList<>());

        int value = tableWindPair.getFuValue();

        assertEquals(2, value, "Table wind pair value should be 2");
    }
}
