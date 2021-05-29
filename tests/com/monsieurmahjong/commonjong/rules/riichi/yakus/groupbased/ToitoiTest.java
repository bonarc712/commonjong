package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ToitoiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeToitoiHandGroups = TileGroupUtils.tileGroupsOf("111m", "777m", "888p", "999s", "11z");
    private List<TileGroup> incompleteToitoiHandGroups = TileGroupUtils.tileGroupsOf("111m", "333m", "11z");
    private List<TileGroup> completeNonToitoiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonToitoiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeChiitoiHandGroups = TileGroupUtils.tileGroupsOf("11m", "11p", "77p", "99p", "11s", "99s", "11z");
    private List<TileGroup> completeToitoiWithKansHandGroups = TileGroupUtils.tileGroupsOf("111m", "7777m", "888p", "9999s", "11z");

    @Test
    public void testValidityOf_HandWithFourteenToitoiTiles_ShouldBeTrue()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(completeToitoiHandGroups)), completeToitoiHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertTrue(toitoiIsValid, "111777m888p999s11z should be valid for toitoi");
    }

    @Test
    public void testValidityOf_HandWithOnlyToitoiTiles_ShouldBeTrue()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteToitoiHandGroups)), incompleteToitoiHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertTrue(toitoiIsValid, "111333m11z should be valid for toitoi");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonToitoiTiles_ShouldBeFalse()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonToitoiHandGroups)), completeNonToitoiHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertFalse(toitoiIsValid, "123345m22345678p should not be valid for toitoi");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonToitoiTiles_ShouldBeFalse()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonToitoiHandGroups)), incompleteNonToitoiHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertFalse(toitoiIsValid, "111m567p11s should not be valid for toitoi");
    }

    @Test
    public void testValidityOf_HandWithChiitoitsuTiles_ShouldBeFalse()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoiHandGroups)), completeChiitoiHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertFalse(toitoiIsValid, "11m117799p1199s11z should not be valid for toitoi");
    }

    @Test
    public void testValidityOf_HandWithToitoiTilesWithKans_ShouldBeTrue()
    {
        Yaku toitoi = new Toitoi(new Hand(TileGroupUtils.getTilesFromTileGroups(completeToitoiWithKansHandGroups)), completeToitoiWithKansHandGroups);

        boolean toitoiIsValid = toitoi.isValid();

        assertTrue(toitoiIsValid, "1117777m888p9999s11z should be valid for toitoi");
    }

    @Test
    public void testValueOf_Toitoi_ShouldBeTwo()
    {
        Yaku toitoi = new Toitoi(anyHand, anyGroups);

        int toitoiValue = toitoi.getHanValue();

        assertEquals(2, toitoiValue, "Toitoi value should be 2");
    }
}
