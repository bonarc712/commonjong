package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Chiitoitsu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChiitoitsuTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "11z");
    private List<TileGroup> incompleteChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "22m", "11z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> completeToitoiHandGroups = TileGroupUtils.tileGroupsOf("111m", "111p", "777p", "111s", "99s");
    private List<TileGroup> chiitoitsuWithDuplicatedPairsHandGroups = TileGroupUtils.tileGroupsOf("11m", "11m", "77m", "77p", "99p", "99s", "11z"); // invalid in riichi

    @Test
    public void testValidityOf_HandWithFourteenChiitoitsuTiles_ShouldBeTrue()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuHandGroups)), completeChiitoitsuHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertTrue(chiitoitsuIsValid, "113377m7799p99s11z should be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyChiitoitsuTiles_ShouldBeTrue()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteChiitoitsuHandGroups)), incompleteChiitoitsuHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertTrue(chiitoitsuIsValid, "1122m11z should be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChiitoitsuTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "123345m22345678p should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChiitoitsuTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonChiitoitsuHandGroups)), incompleteNonChiitoitsuHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111m555p11s should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithToitoiTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeToitoiHandGroups)), completeToitoiHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111m111777p11199s should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithChiitoitsuDuplicatedTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(chiitoitsuWithDuplicatedPairsHandGroups)), chiitoitsuWithDuplicatedPairsHandGroups);

        boolean chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111177m7799p99s11z should not be valid for chiitoitsu");
    }

    @Test
    public void testValueOf_Chiitoitsu_ShouldBeTwo()
    {
        Yaku chiitoitsu = new Chiitoitsu(anyHand, anyGroups);

        int chiitoitsuValue = chiitoitsu.getHanValue();

        assertEquals(2, chiitoitsuValue, "Chiitoitsu value should be 2");
    }

}