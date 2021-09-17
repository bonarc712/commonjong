package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class DragonPairTest
{
    private Hand anyHand = mock(Hand.class);

    private List<TileGroup> completeGreenDragonPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "111z", "66z");
    private List<TileGroup> incompleteGreenDragonPairHandGroups = TileGroupUtils.tileGroupsOf("111m", "111z", "66z");
    private List<TileGroup> completeNonGreenDragonPairHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> completeChiitoitsuWithDragonPairHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "77z");

    @Test
    public void mWithAHandWithADragonPair_DragonPairFu_IsValid()
    {
        DragonPair dragonPair = new DragonPair(completeGreenDragonPairHandGroups);

        boolean isValid = dragonPair.isValid();

        assertTrue(isValid, "Dragon pair should be a valid for a hand that contains a dragon pair");
    }

    @Test
    public void mWithAnIncompleteHandWithADragonPair_DragonPairFu_IsValid()
    {
        DragonPair dragonPair = new DragonPair(incompleteGreenDragonPairHandGroups);

        boolean isValid = dragonPair.isValid();

        assertTrue(isValid, "Dragon pair should be a valid for a hand that contains a dragon pair");
    }

    @Test
    public void mWithAHandWithoutADragonPair_DragonPairFu_IsNotValid()
    {
        DragonPair dragonPair = new DragonPair(completeNonGreenDragonPairHandGroups);

        boolean isValid = dragonPair.isValid();

        assertFalse(isValid, "Dragon pair should be a valid for a hand that contains a dragon pair");
    }

    @Test
    public void mWithASevenPairsHandWithADragonPair_DragonPairFu_IsNotValid()
    {
        DragonPair dragonPair = new DragonPair(completeChiitoitsuWithDragonPairHandGroups);

        boolean isValid = dragonPair.isValid();

        assertFalse(isValid, "Dragon pair should be a valid for a seven pairs hand that contains a dragon pair");
    }

    @Test
    public void mGetFuValue_ForDragonPair_IsTwo()
    {
        DragonPair dragonPair = new DragonPair(new ArrayList<>());

        int value = dragonPair.getFuValue();

        assertEquals(2, value, "Dragon pair value should be 2");
    }
}
