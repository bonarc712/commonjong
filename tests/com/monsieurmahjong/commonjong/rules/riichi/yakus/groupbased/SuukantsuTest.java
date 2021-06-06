package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SuukantsuTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSuukantsuHandGroups = TileGroupUtils.tileGroupsOf("4444m", "5555p", "7777s", "9999s", "11z");
    private List<TileGroup> incompleteSuukantsuHandGroups = TileGroupUtils.tileGroupsOf("1111m", "2222p", "2222s", "1111z");
    private List<TileGroup> completeNonSuukantsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSuukantsuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> sankantsuHandGroups = TileGroupUtils.tileGroupsOf("4444m", "5555p", "7777s", "999s", "11z");

    @Test
    public void testValidityOf_CompleteSuukantsuHand_ShouldBeTrue()
    {
        Yaku suukantsu = new Suukantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSuukantsuHandGroups)), completeSuukantsuHandGroups);

        boolean suukantsuIsValid = suukantsu.isValid();

        assertTrue(suukantsuIsValid, "4444m5555p77779999s11z should be valid for Suukantsu");
    }

    @Test
    public void testValidityOf_IncompleteSuukantsuHand_ShouldBeTrue()
    {
        Yaku suukantsu = new Suukantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSuukantsuHandGroups)), incompleteSuukantsuHandGroups);

        boolean suukantsuIsValid = suukantsu.isValid();

        assertTrue(suukantsuIsValid, "1111m2222p2222s1111z should be valid for Suukantsu");
    }

    @Test
    public void testValidityOf_CompleteNonSuukantsuHand_ShouldBeFalse()
    {
        Yaku suukantsu = new Suukantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSuukantsuHandGroups)), completeNonSuukantsuHandGroups);

        boolean suukantsuIsValid = suukantsu.isValid();

        assertFalse(suukantsuIsValid, "123345m22345678p should not be valid for Suukantsu");
    }

    @Test
    public void testValidityOf_IncompleteNonSuukantsuHand_ShouldBeFalse()
    {
        Yaku suukantsu = new Suukantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSuukantsuHandGroups)), incompleteNonSuukantsuHandGroups);

        boolean suukantsuIsValid = suukantsu.isValid();

        assertFalse(suukantsuIsValid, "111m555p11s should not be valid for Suukantsu");
    }

    @Test
    public void testValidityOf_SankantsuHand_ShouldBeFalse()
    {
        Yaku suukantsu = new Suukantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(sankantsuHandGroups)), sankantsuHandGroups);

        boolean suukantsuIsValid = suukantsu.isValid();

        assertFalse(suukantsuIsValid, "4444m5555p7777999s11z should not be valid for Suukantsu");
    }

    @Test
    public void testValueOf_Suukantsu_ShouldBeThirteen()
    {
        Yaku suukantsu = new Suukantsu(anyHand, anyGroups);

        int suukantsuValue = suukantsu.getHanValue();

        assertEquals(13, suukantsuValue, "Suukantsu value should be 13");
    }

    @Test
    public void testValueOf_Suukantsu_ShouldBeYakuman()
    {
        Yaku suukantsu = new Suukantsu(anyHand, anyGroups);

        boolean suukantsuIsYakuman = suukantsu.isYakuman();

        assertTrue(suukantsuIsYakuman, "Suukantsu should be yakuman");
    }
}