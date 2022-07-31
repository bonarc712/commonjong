package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.SanshokuDoukou;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SanshokuDoukouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSanshokuDoukouHandGroups = TileGroupUtils.tileGroupsOf("444m", "444p", "444s", "999s", "11z");
    private List<TileGroup> incompleteSanshokuDoukouHandGroups = TileGroupUtils.tileGroupsOf("111m", "111p", "111s", "11z");
    private List<TileGroup> completeNonSanshokuDoukouHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSanshokuDoukouHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> twoRyanshokuDoukouHandGroups = TileGroupUtils.tileGroupsOf("111m", "44m", "777p", "111s", "777s");

    @Test
    public void testValidityOf_HandWithFourteenSanshokuDoukouTiles_ShouldBeTrue()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSanshokuDoukouHandGroups)), completeSanshokuDoukouHandGroups);

        boolean sanshokuDoukouIsValid = sanshokuDoukou.isValid();

        assertTrue(sanshokuDoukouIsValid, "444m444p444999s11z should be valid for sanshoku doukou");
    }

    @Test
    public void testValidityOf_HandWithOnlySanshokuDoukouTiles_ShouldBeTrue()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSanshokuDoukouHandGroups)), incompleteSanshokuDoukouHandGroups);

        boolean sanshokuDoukouIsValid = sanshokuDoukou.isValid();

        assertTrue(sanshokuDoukouIsValid, "111m111p111s11z should be valid for sanshoku doukou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSanshokuDoukouTiles_ShouldBeFalse()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSanshokuDoukouHandGroups)), completeNonSanshokuDoukouHandGroups);

        boolean sanshokuDoukouIsValid = sanshokuDoukou.isValid();

        assertFalse(sanshokuDoukouIsValid, "123345m22345678p should not be valid for sanshoku doukou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSanshokuDoukouTiles_ShouldBeFalse()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSanshokuDoukouHandGroups)), incompleteNonSanshokuDoukouHandGroups);

        boolean sanshokuDoukouIsValid = sanshokuDoukou.isValid();

        assertFalse(sanshokuDoukouIsValid, "111m555p11s should not be valid for sanshoku doukou");
    }

    @Test
    public void testValidityOf_DoubleRyanshokuDoukou_ShouldBeFalse()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(new Hand(TileGroupUtils.getTilesFromTileGroups(twoRyanshokuDoukouHandGroups)), twoRyanshokuDoukouHandGroups);

        boolean sanshokuDoukouIsValid = sanshokuDoukou.isValid();

        assertFalse(sanshokuDoukouIsValid, "11144m777p111777s should be valid for sanshoku doukou");
    }

    @Test
    public void testValueOf_SanshokuDoukou_ShouldBeTwo()
    {
        Yaku sanshokuDoukou = new SanshokuDoukou(anyHand, anyGroups);

        int sanshokuDoukouValue = sanshokuDoukou.getHanValue();

        assertEquals(2, sanshokuDoukouValue, "Sanshoku doukou value should be 2");
    }
}
