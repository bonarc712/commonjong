package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

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

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertTrue(chiitoitsuIsValid, "113377m7799p99s11z should be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyChiitoitsuTiles_ShouldBeTrue()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteChiitoitsuHandGroups)), incompleteChiitoitsuHandGroups);

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertTrue(chiitoitsuIsValid, "1122m11z should be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChiitoitsuTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups);

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "123345m22345678p should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChiitoitsuTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonChiitoitsuHandGroups)), incompleteNonChiitoitsuHandGroups);

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111m555p11s should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithToitoiTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeToitoiHandGroups)), completeToitoiHandGroups);

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111m111777p11199s should not be valid for chiitoitsu");
    }

    @Test
    public void testValidityOf_HandWithChiitoitsuDuplicatedTiles_ShouldBeFalse()
    {
        Yaku chiitoitsu = new Chiitoitsu(new Hand(TileGroupUtils.getTilesFromTileGroups(chiitoitsuWithDuplicatedPairsHandGroups)), chiitoitsuWithDuplicatedPairsHandGroups);

        var chiitoitsuIsValid = chiitoitsu.isValid();

        assertFalse(chiitoitsuIsValid, "111177m7799p99s11z should not be valid for chiitoitsu");
    }

    @Test
    public void testValueOf_Chiitoitsu_ShouldBeTwo()
    {
        Yaku chiitoitsu = new Chiitoitsu(anyHand, anyGroups);

        var chiitoitsuValue = chiitoitsu.getHanValue();

        assertEquals(2, chiitoitsuValue, "Chiitoitsu value should be 2");
    }

}
