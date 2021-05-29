package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ShousangenTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeShousangenHandGroups = TileGroupUtils.tileGroupsOf("111m", "111z", "555z", "666z", "77z");
    private List<TileGroup> incompleteShousangenHandGroups = TileGroupUtils.tileGroupsOf("555z", "66z", "777z");
    private List<TileGroup> completeNonShousangenHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonShousangenHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeShousangenKanHandGroups = TileGroupUtils.tileGroupsOf("111m", "111z", "55z", "6666z", "777z");
    private List<TileGroup> completeDaisangenHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "555z", "666z", "777z");

    @Test
    public void testValidityOf_HandWithFourteenShousangenTiles_ShouldBeTrue()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeShousangenHandGroups)), completeShousangenHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertTrue(shousangenIsValid, "111m1155566677z should be valid for shousangen");
    }

    @Test
    public void testValidityOf_HandWithOnlyShousangenTiles_ShouldBeTrue()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteShousangenHandGroups)), incompleteShousangenHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertTrue(shousangenIsValid, "55566777z should be valid for shousangen");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonShousangenTiles_ShouldBeFalse()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonShousangenHandGroups)), completeNonShousangenHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertFalse(shousangenIsValid, "123345m22345678p should not be valid for shousangen");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonShousangenTiles_ShouldBeFalse()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonShousangenHandGroups)), incompleteNonShousangenHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertFalse(shousangenIsValid, "111m567p11s should not be valid for shousangen");
    }

    @Test
    public void testValidityOf_HandWithShousangenAndKanTiles_ShouldBeTrue()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeShousangenKanHandGroups)), completeShousangenKanHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertTrue(shousangenIsValid, "111m111556666777z should be valid for shousangen");
    }

    @Test
    public void testValidityOf_HandWithDaisangenTiles_ShouldBeFalse()
    {
        Yaku shousangen = new Shousangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeDaisangenHandGroups)), completeDaisangenHandGroups);

        boolean shousangenIsValid = shousangen.isValid();

        assertFalse(shousangenIsValid, "111m11555666777z should not be valid for shousangen");
    }

    @Test
    public void testValueOf_Shousangen_ShouldBeTwo()
    {
        Yaku shousangen = new Shousangen(anyHand, anyGroups);

        int shousangenValue = shousangen.getHanValue();

        assertEquals(2, shousangenValue, "Shousangen value should be 2");
    }
}
