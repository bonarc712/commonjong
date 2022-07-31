package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Sankantsu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SankantsuTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSankantsuHandGroups = TileGroupUtils.tileGroupsOf("4444m", "5555p", "7777s", "999s", "11z");
    private List<TileGroup> incompleteSankantsuHandGroups = TileGroupUtils.tileGroupsOf("1111m", "2222p", "2222s", "11z");
    private List<TileGroup> completeNonSankantsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSankantsuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> ryankantsuWithFourOfAnotherTileHandGroups = TileGroupUtils.tileGroupsOf("4444m", "5555p", "777s", "789s", "11z");
    private List<TileGroup> suukantsuHandGroups = TileGroupUtils.tileGroupsOf("4444m", "5555p", "7777s", "9999s", "11z");

    @Test
    public void testValidityOf_CompleteSankantsuHand_ShouldBeTrue()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSankantsuHandGroups)), completeSankantsuHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertTrue(sankantsuIsValid, "4444m5555p7777999s11z should be valid for Sankantsu");
    }

    @Test
    public void testValidityOf_IncompleteSankantsuHand_ShouldBeTrue()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSankantsuHandGroups)), incompleteSankantsuHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertTrue(sankantsuIsValid, "1111m2222p2222s11z should be valid for Sankantsu");
    }

    @Test
    public void testValidityOf_CompleteNonSankantsuHand_ShouldBeFalse()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSankantsuHandGroups)), completeNonSankantsuHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertFalse(sankantsuIsValid, "123345m22345678p should not be valid for Sankantsu");
    }

    @Test
    public void testValidityOf_IncompleteNonSankantsuHand_ShouldBeFalse()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSankantsuHandGroups)), incompleteNonSankantsuHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertFalse(sankantsuIsValid, "111m555p11s should not be valid for Sankantsu");
    }

    @Test
    public void testValidityOf_RyankantsuWithFourOfAnotherTileHand_ShouldBeFalse()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(ryankantsuWithFourOfAnotherTileHandGroups)), ryankantsuWithFourOfAnotherTileHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertFalse(sankantsuIsValid, "4444m5555p777789s11z should not be valid for Sankantsu");
    }

    @Test
    public void testValidityOf_SuukantsuHand_ShouldBeFalse()
    {
        Yaku sankantsu = new Sankantsu(new Hand(TileGroupUtils.getTilesFromTileGroups(suukantsuHandGroups)), suukantsuHandGroups);

        boolean sankantsuIsValid = sankantsu.isValid();

        assertFalse(sankantsuIsValid, "4444m5555p77779999s11z should not be valid for Sankantsu");
    }

    @Test
    public void testValueOf_Sankantsu_ShouldBeTwo()
    {
        Yaku sankantsu = new Sankantsu(anyHand, anyGroups);

        int sankantsuValue = sankantsu.getHanValue();

        assertEquals(2, sankantsuValue, "Sankantsu value should be 2");
    }
}
