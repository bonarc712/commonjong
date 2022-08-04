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

public class DaisangenTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeDaisangenHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "555z", "666z", "777z");
    private List<TileGroup> incompleteDaisangenHandGroups = TileGroupUtils.tileGroupsOf("555z", "666z", "777z");
    private List<TileGroup> completeNonDaisangenHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonDaisangenHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeDaisangenKanHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "5555z", "6666z", "777z");

    @Test
    public void testValidityOf_HandWithFourteenDaisangenTiles_ShouldBeTrue()
    {
        Yaku daisangen = new Daisangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeDaisangenHandGroups)), completeDaisangenHandGroups);

        var daisangenIsValid = daisangen.isValid();

        assertTrue(daisangenIsValid, "111m11555666777z should be valid for daisangen");
    }

    @Test
    public void testValidityOf_HandWithOnlyDaisangenTiles_ShouldBeTrue()
    {
        Yaku daisangen = new Daisangen(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteDaisangenHandGroups)), incompleteDaisangenHandGroups);

        var daisangenIsValid = daisangen.isValid();

        assertTrue(daisangenIsValid, "555666777z should be valid for daisangen");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonDaisangenTiles_ShouldBeFalse()
    {
        Yaku daisangen = new Daisangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonDaisangenHandGroups)), completeNonDaisangenHandGroups);

        var daisangenIsValid = daisangen.isValid();

        assertFalse(daisangenIsValid, "123345m22345678p should not be valid for daisangen");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonDaisangenTiles_ShouldBeFalse()
    {
        Yaku daisangen = new Daisangen(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonDaisangenHandGroups)), incompleteNonDaisangenHandGroups);

        var daisangenIsValid = daisangen.isValid();

        assertFalse(daisangenIsValid, "111m567p11s should not be valid for daisangen");
    }

    @Test
    public void testValidityOf_HandWithDaisangenAndKanTiles_ShouldBeTrue()
    {
        Yaku daisangen = new Daisangen(new Hand(TileGroupUtils.getTilesFromTileGroups(completeDaisangenKanHandGroups)), completeDaisangenKanHandGroups);

        var daisangenIsValid = daisangen.isValid();

        assertTrue(daisangenIsValid, "111m1155556666777z should be valid for daisangen");
    }

    @Test
    public void testValueOf_Daisangen_ShouldBeThirteen()
    {
        Yaku daisangen = new Daisangen(anyHand, anyGroups);

        var daisangenValue = daisangen.getHanValue();

        assertEquals(13, daisangenValue, "Daisangen value should be 13");
    }

    @Test
    public void testValueOf_Daisangen_ShouldBeYakuman()
    {
        Yaku daisangen = new Daisangen(anyHand, anyGroups);

        var daisangenIsYakuman = daisangen.isYakuman();

        assertTrue(daisangenIsYakuman, "Daisangen should be yakuman");
    }
}