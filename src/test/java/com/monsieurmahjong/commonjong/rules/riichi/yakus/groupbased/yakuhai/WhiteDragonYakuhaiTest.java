package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.WhiteDragonYakuhai;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class WhiteDragonYakuhaiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeWhiteDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "555z");
    private List<TileGroup> incompleteWhiteDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "555z");
    private List<TileGroup> completeNonWhiteDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonWhiteDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeWhiteDragonKanYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "5555z");

    @Test
    public void testValidityOf_HandWithFourteenWhiteDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeWhiteDragonYakuhaiHandGroups)), completeWhiteDragonYakuhaiHandGroups);

        boolean whiteDragonYakuhaiIsValid = whiteDragonYakuhai.isValid();

        assertTrue(whiteDragonYakuhaiIsValid, "111888p999s11555z should be valid for white dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOnlyWhiteDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteWhiteDragonYakuhaiHandGroups)), incompleteWhiteDragonYakuhaiHandGroups);

        boolean whiteDragonYakuhaiIsValid = whiteDragonYakuhai.isValid();

        assertTrue(whiteDragonYakuhaiIsValid, "111m11555z should be valid for white dragon yakuhai");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonWhiteDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonWhiteDragonYakuhaiHandGroups)), completeNonWhiteDragonYakuhaiHandGroups);

        boolean whiteDragonYakuhaiIsValid = whiteDragonYakuhai.isValid();

        assertFalse(whiteDragonYakuhaiIsValid, "123345m22345678p should not be valid for white dragon yakuhai");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonWhiteDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonWhiteDragonYakuhaiHandGroups)), incompleteNonWhiteDragonYakuhaiHandGroups);

        boolean whiteDragonYakuhaiIsValid = whiteDragonYakuhai.isValid();

        assertFalse(whiteDragonYakuhaiIsValid, "111m567p11s should not be valid for white dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithWhiteDragonKanYakuhaiTiles_ShouldBeTrue()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeWhiteDragonKanYakuhaiHandGroups)), completeWhiteDragonKanYakuhaiHandGroups);

        boolean whiteDragonYakuhaiIsValid = whiteDragonYakuhai.isValid();

        assertTrue(whiteDragonYakuhaiIsValid, "111888p999s117777z should be valid for white dragon yakuhai");
    }

    @Test
    public void testValueOf_WhiteDragonYakuhai_ShouldBeOne()
    {
        Yaku whiteDragonYakuhai = new WhiteDragonYakuhai(anyHand, anyGroups);

        int whiteDragonYakuhaiValue = whiteDragonYakuhai.getHanValue();

        assertEquals(1, whiteDragonYakuhaiValue, "WhiteDragonYakuhai value should be 1");
    }
}
