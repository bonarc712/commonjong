package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class RyanpeikouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeRyanpeikouHandGroups = TileGroupUtils.tileGroupsOf("234m", "234m", "456s", "456s", "11z");
    private List<TileGroup> completeNonRyanpeikouHandGroups = TileGroupUtils.tileGroupsOf("123m", "123m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonRyanpeikouHandGroups = TileGroupUtils.tileGroupsOf("111p", "111s", "11z");

    @Test
    public void testValidityOf_HandWithFourteenRyanpeikouTiles_ShouldBeTrue()
    {
        Yaku ryanpeikou = new Ryanpeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeRyanpeikouHandGroups)), completeRyanpeikouHandGroups);

        boolean ryanpeikouIsValid = ryanpeikou.isValid();

        assertTrue(ryanpeikouIsValid, "223344m445566s11z should be valid for ryanpeikou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonRyanpeikouTiles_ShouldBeFalse()
    {
        Yaku ryanpeikou = new Ryanpeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonRyanpeikouHandGroups)), completeNonRyanpeikouHandGroups);

        boolean ryanpeikouIsValid = ryanpeikou.isValid();

        assertFalse(ryanpeikouIsValid, "112233m22345678p should not be valid for ryanpeikou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonRyanpeikouTiles_ShouldBeFalse()
    {
        Yaku ryanpeikou = new Ryanpeikou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonRyanpeikouHandGroups)), incompleteNonRyanpeikouHandGroups);

        boolean ryanpeikouIsValid = ryanpeikou.isValid();

        assertFalse(ryanpeikouIsValid, "111p111s11z should not be valid for ryanpeikou");
    }

    @Test
    public void testValueOf_Ryanpeikou_ShouldBeThree()
    {
        Yaku ryanpeikou = new Ryanpeikou(anyHand, anyGroups);

        int ryanpeikouValue = ryanpeikou.getHanValue();

        assertEquals(3, ryanpeikouValue, "Ryanpeikou value should be 3");
    }
}
