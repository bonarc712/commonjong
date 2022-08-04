package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChantaTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeChantaHandGroups = TileGroupUtils.tileGroupsOf("123m", "789m", "789p", "999s", "11z");
    private List<TileGroup> incompleteChantaHandGroups = TileGroupUtils.tileGroupsOf("123m", "123m", "11z");
    private List<TileGroup> completeNonChantaHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonChantaHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> completeJunchanHandGroups = TileGroupUtils.tileGroupsOf("111m", "111p", "789p", "111s", "99s");
    private List<TileGroup> completeHonroutouHandGroups = TileGroupUtils.tileGroupsOf("111m", "999m", "11p", "333z", "555z");

    @Test
    public void testValidityOf_HandWithFourteenChantaTiles_ShouldBeTrue()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChantaHandGroups)), completeChantaHandGroups);

        var chantaIsValid = chanta.isValid();

        assertTrue(chantaIsValid, "123789m789p999s11z should be valid for chanta");
    }

    @Test
    public void testValidityOf_HandWithOnlyChantaTiles_ShouldBeTrue()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteChantaHandGroups)), incompleteChantaHandGroups);

        var chantaIsValid = chanta.isValid();

        assertTrue(chantaIsValid, "112233m11z should be valid for chanta");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChantaTiles_ShouldBeFalse()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChantaHandGroups)), completeNonChantaHandGroups);

        var chantaIsValid = chanta.isValid();

        assertFalse(chantaIsValid, "123345m22345678p should not be valid for chanta");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChantaTiles_ShouldBeFalse()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonChantaHandGroups)), incompleteNonChantaHandGroups);

        var chantaIsValid = chanta.isValid();

        assertFalse(chantaIsValid, "111m555p11s should not be valid for chanta");
    }

    @Test
    public void testValidityOf_HandWithJunchanTiles_ShouldBeFalse()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(completeJunchanHandGroups)), completeJunchanHandGroups);

        var chantaIsValid = chanta.isValid();

        assertFalse(chantaIsValid, "111m111789p11199s should not be valid for chanta");
    }

    @Test
    public void testValidityOf_HandWithHonroutouTiles_ShouldBeFalse()
    {
        Yaku chanta = new Chanta(new Hand(TileGroupUtils.getTilesFromTileGroups(completeHonroutouHandGroups)), completeHonroutouHandGroups);

        var chantaIsValid = chanta.isValid();

        assertFalse(chantaIsValid, "111999m11p333555z should not be valid for chanta");
    }

    @Test
    public void testValueOf_OpenChanta_ShouldBeOne()
    {
        Yaku chanta = new Chanta(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(false);

        var chantaValue = chanta.getHanValue();

        assertEquals(1, chantaValue, "Open chanta value should be 1");
    }

    @Test
    public void testValueOf_ClosedChanta_ShouldBeTwo()
    {
        Yaku chanta = new Chanta(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(true);

        var chantaValue = chanta.getHanValue();

        assertEquals(2, chantaValue, "Closed chanta value should be 2");
    }
}
