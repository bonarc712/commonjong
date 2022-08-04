package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
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
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        var tableWindPair = new TableWindPair(hand, completeTableWindPairHandGroups);

        var isValid = tableWindPair.isValid();

        assertTrue(isValid, "Table wind pair should be valid for a hand that contains a table wind pair");
    }

    @Test
    public void withAnIncompleteHandWithATableWindPair_TableWindPairFu_IsValid()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        var tableWindPair = new TableWindPair(hand, incompleteTableWindPairHandGroups);

        var isValid = tableWindPair.isValid();

        assertTrue(isValid, "Table wind pair should be valid for a hand that contains a table wind pair");
    }

    @Test
    public void mWithAHandWithoutATableWindPair_TableWindPairFu_IsNotValid()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        var tableWindPair = new TableWindPair(hand, completeNonTableWindPairHandGroups);

        var isValid = tableWindPair.isValid();

        assertFalse(isValid, "Table wind pair should not be valid for a hand that doesn't contain a table wind pair");
    }

    @Test
    public void withASevenPairsHandWithATableWindPair_TableWindPairFu_IsNotValid()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuWithTableWindPairHandGroups));
        hand.addTableWind(tableWind);
        var tableWindPair = new TableWindPair(hand, completeChiitoitsuWithTableWindPairHandGroups);

        var isValid = tableWindPair.isValid();

        assertFalse(isValid, "Table wind pair should not be valid for a seven pairs hand that contains a table wind pair");
    }

    @Test
    public void getFuValue_ForTableWindPair_IsTwo()
    {
        var tableWindPair = new TableWindPair(anyHand, new ArrayList<>());

        var value = tableWindPair.getFuValue();

        assertEquals(2, value, "Table wind pair value should be 2");
    }
}
