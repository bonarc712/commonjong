package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.minipoints.SeatWindPair;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class SeatWindPairTest
{
    private Hand anyHand = mock(Hand.class);

    private Seat seatWind = Seat.WEST;

    private List<TileGroup> completeSeatWindPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "111z", "33z");
    private List<TileGroup> incompleteSeatWindPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "111z", "33z");
    private List<TileGroup> completeNonSeatWindPairHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> completeChiitoitsuWithSeatWindPairHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "33z");

    @Test
    public void withAHandWithASeatWindPair_SeatWindPairFu_IsValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeSeatWindPairHandGroups));
        hand.setSeatWind(seatWind);
        SeatWindPair seatWindPair = new SeatWindPair(hand, completeSeatWindPairHandGroups);

        boolean isValid = seatWindPair.isValid();

        assertTrue(isValid, "Seat wind pair should be valid for a hand that contains a seat wind pair");
    }

    @Test
    public void withAnIncompleteHandWithASeatWindPair_SeatWindPairFu_IsValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSeatWindPairHandGroups));
        hand.setSeatWind(seatWind);
        SeatWindPair seatWindPair = new SeatWindPair(hand, incompleteSeatWindPairHandGroups);

        boolean isValid = seatWindPair.isValid();

        assertTrue(isValid, "Seat wind pair should be valid for a hand that contains a seat wind pair");
    }

    @Test
    public void withAHandWithoutASeatWindPair_SeatWindPairFu_IsNotValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSeatWindPairHandGroups));
        hand.setSeatWind(seatWind);
        SeatWindPair seatWindPair = new SeatWindPair(hand, completeNonSeatWindPairHandGroups);

        boolean isValid = seatWindPair.isValid();

        assertFalse(isValid, "Seat wind pair should not be valid for a hand that doesn't contain a seat wind pair");
    }

    @Test
    public void withASevenPairsHandWithASeatWindPair_SeatWindPairFu_IsNotValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuWithSeatWindPairHandGroups));
        hand.setSeatWind(seatWind);
        SeatWindPair seatWindPair = new SeatWindPair(hand, completeChiitoitsuWithSeatWindPairHandGroups);

        boolean isValid = seatWindPair.isValid();

        assertFalse(isValid, "Seat wind pair should not be valid for a seven pairs hand that contains a seat wind pair");
    }

    @Test
    public void getFuValue_ForSeatWindPair_IsTwo()
    {
        SeatWindPair seatWindPair = new SeatWindPair(anyHand, new ArrayList<>());

        int value = seatWindPair.getFuValue();

        assertEquals(2, value, "Seat wind pair value should be 2");
    }
}
