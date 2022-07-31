package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Ittsu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class IttsuTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeIttsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "789m", "999s", "11z");
    private List<TileGroup> completeIttsuHandGroupsButBamboo = TileGroupUtils.tileGroupsOf("123s", "456s", "789s", "11z", "666z");
    private List<TileGroup> incompleteIttsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "789m", "11z");
    private List<TileGroup> completeNonIttsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonIttsuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> allTilesFromAFamilyButNotIttsingHandGroups = TileGroupUtils.tileGroupsOf("111m", "234m", "567m", "789m", "11z");

    @Test
    public void testValidityOf_HandWithFourteenIttsuTiles_ShouldBeTrue()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeIttsuHandGroups)), completeIttsuHandGroups);

        boolean ittsuIsValid = ittsu.isValid();

        assertTrue(ittsuIsValid, "123456789m999s11z should be valid for ittsu");
    }

    @Test
    public void testValidityOf_HandWithBambooIttsuTiles_ShouldBeTrue()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeIttsuHandGroupsButBamboo)), completeIttsuHandGroupsButBamboo);

        boolean ittsuIsValid = ittsu.isValid();

        assertTrue(ittsuIsValid, "123456789s11666z should be valid for ittsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyIttsuTiles_ShouldBeTrue()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteIttsuHandGroups)), incompleteIttsuHandGroups);

        boolean ittsuIsValid = ittsu.isValid();

        assertTrue(ittsuIsValid, "123456789m11z should be valid for ittsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonIttsuTiles_ShouldBeFalse()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonIttsuHandGroups)), completeNonIttsuHandGroups);

        boolean ittsuIsValid = ittsu.isValid();

        assertFalse(ittsuIsValid, "123345m22345678p should not be valid for ittsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonIttsuTiles_ShouldBeFalse()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonIttsuHandGroups)), incompleteNonIttsuHandGroups);

        boolean ittsuIsValid = ittsu.isValid();

        assertFalse(ittsuIsValid, "111m555p11s should not be valid for ittsu");
    }

    @Test
    public void testValidityOf_HandWithAllButNotIttsingTiles_ShouldBeFalse()
    {
        Yaku ittsu = new Ittsu(new Hand(TileGroupUtils.getTilesFromTileGroups(allTilesFromAFamilyButNotIttsingHandGroups)), allTilesFromAFamilyButNotIttsingHandGroups);

        boolean ittsuIsValid = ittsu.isValid();

        assertFalse(ittsuIsValid, "111234567789m11z should not be valid for ittsu");
    }

    @Test
    public void testValueOf_OpenIttsu_ShouldBeOne()
    {
        Yaku ittsu = new Ittsu(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(false);

        int ittsuValue = ittsu.getHanValue();

        assertEquals(1, ittsuValue, "Open ittsu value should be 1");
    }

    @Test
    public void testValueOf_ClosedIttsu_ShouldBeTwo()
    {
        Yaku ittsu = new Ittsu(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(true);

        int ittsuValue = ittsu.getHanValue();

        assertEquals(2, ittsuValue, "Closed ittsu value should be 2");
    }
}
