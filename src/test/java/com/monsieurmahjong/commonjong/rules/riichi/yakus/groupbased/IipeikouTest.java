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

public class IipeikouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeIipeikouHandGroups = TileGroupUtils.tileGroupsOf("234m", "234m", "777p", "456s", "11z");
    private List<TileGroup> incompleteIipeikouHandGroups = TileGroupUtils.tileGroupsOf("234m", "234m", "11z");
    private List<TileGroup> completeNonIipeikouHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonIipeikouHandGroups = TileGroupUtils.tileGroupsOf("111p", "111s", "11z");
    private List<TileGroup> completeRyanpeikouHandGroups = TileGroupUtils.tileGroupsOf("234m", "234m", "456s", "456s", "11z");
    private List<TileGroup> completeSanrenkouHandGroups = TileGroupUtils.tileGroupsOf("234m", "234m", "234m", "456s", "11z");

    @Test
    public void testValidityOf_HandWithFourteenIipeikouTiles_ShouldBeTrue()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeIipeikouHandGroups)), completeIipeikouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertTrue(iipeikouIsValid, "223344m777p456s11z should be valid for iipeikou");
    }

    @Test
    public void testValidityOf_HandWithOnlyIipeikouTiles_ShouldBeTrue()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteIipeikouHandGroups)), incompleteIipeikouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertTrue(iipeikouIsValid, "223344m11z should be valid for iipeikou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonIipeikouTiles_ShouldBeFalse()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonIipeikouHandGroups)), completeNonIipeikouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertFalse(iipeikouIsValid, "123345m22345678p should not be valid for iipeikou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonIipeikouTiles_ShouldBeFalse()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonIipeikouHandGroups)), incompleteNonIipeikouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertFalse(iipeikouIsValid, "111p111s11z should not be valid for iipeikou");
    }

    @Test
    public void testValidityOf_HandWithRyanpeikouTiles_ShouldBeFalse()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeRyanpeikouHandGroups)), completeRyanpeikouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertFalse(iipeikouIsValid, "223344m445566s11z should not be valid for iipeikou");
    }

    @Test
    public void testValidityOf_HandWithSanrenkouTiles_ShouldBeTrue()
    {
        Yaku iipeikou = new Iipeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSanrenkouHandGroups)), completeSanrenkouHandGroups);

        var iipeikouIsValid = iipeikou.isValid();

        assertTrue(iipeikouIsValid, "222333444m456s11z should be valid for iipeikou");
    }

    @Test
    public void testValueOf_Iipeikou_ShouldBeOne()
    {
        Yaku iipeikou = new Iipeikou(anyHand, anyGroups);

        var iipeikouValue = iipeikou.getHanValue();

        assertEquals(1, iipeikouValue, "Iipeikou value should be 1");
    }
}
