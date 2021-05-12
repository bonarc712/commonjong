package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class JunchanTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeJunchanHandGroups = TileGroupUtils.tileGroupsOf("123m", "789m", "789p", "999s", "11s");
    private List<TileGroup> incompleteJunchanHandGroups = TileGroupUtils.tileGroupsOf("123m", "123m", "11s");
    private List<TileGroup> completeNonJunchanHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonJunchanHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> completeChantaHandGroups = TileGroupUtils.tileGroupsOf("111m", "111p", "789p", "111s", "55z");
    private List<TileGroup> completeChinroutouHandGroups = TileGroupUtils.tileGroupsOf("111m", "999m", "11p", "111s", "999s");

    @Test
    public void testValidityOf_HandWithFourteenJunchanTiles_ShouldBeTrue()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(completeJunchanHandGroups)), completeJunchanHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertTrue(junchanIsValid, "123789m789p11999s should be valid for junchan");
    }

    @Test
    public void testValidityOf_HandWithOnlyJunchanTiles_ShouldBeTrue()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteJunchanHandGroups)), incompleteJunchanHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertTrue(junchanIsValid, "112233m11s should be valid for junchan");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonJunchanTiles_ShouldBeFalse()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonJunchanHandGroups)), completeNonJunchanHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertFalse(junchanIsValid, "123345m22345678p should not be valid for junchan");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonJunchanTiles_ShouldBeFalse()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonJunchanHandGroups)), incompleteNonJunchanHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertFalse(junchanIsValid, "111m555p11s should not be valid for junchan");
    }

    @Test
    public void testValidityOf_HandWithChantaTiles_ShouldBeFalse()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChantaHandGroups)), completeChantaHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertFalse(junchanIsValid, "111m111789p111s55z should not be valid for junchan");
    }

    @Test
    public void testValidityOf_HandWithChinroutouTiles_ShouldBeFalse()
    {
        Yaku junchan = new Junchan(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChinroutouHandGroups)), completeChinroutouHandGroups);

        boolean junchanIsValid = junchan.isValid();

        assertFalse(junchanIsValid, "111999m11p111999s should not be valid for junchan");
    }

    @Test
    public void testValueOf_OpenJunchan_ShouldBeTwo()
    {
        Yaku junchan = new Junchan(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(false);

        int junchanValue = junchan.getHanValue();

        assertEquals(2, junchanValue, "Open junchan value should be 2");
    }

    @Test
    public void testValueOf_ClosedJunchan_ShouldBeThree()
    {
        Yaku junchan = new Junchan(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(true);

        int junchanValue = junchan.getHanValue();

        assertEquals(3, junchanValue, "Closed junchan value should be 3");
    }
}