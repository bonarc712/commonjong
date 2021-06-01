package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SanshokuDoujunTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf("456m", "456p", "456s", "999s", "11z");
    private List<TileGroup> incompleteSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf("123m", "123p", "123s", "11z");
    private List<TileGroup> completeNonSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> sanshokuIsTheLastThreeRunsHandGroups = TileGroupUtils.tileGroupsOf("123m", "44m", "789m", "789p", "789s");
    private List<TileGroup> ryanshokuIipeikouAKAFakeSanshokuHandGroups = TileGroupUtils.tileGroupsOf("123m", "44m", "789p", "789p", "789s");

    @Test
    public void testValidityOf_HandWithFourteenSanshokuDoujunTiles_ShouldBeTrue()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSanshokuDoujunHandGroups)), completeSanshokuDoujunHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertTrue(sanshokuDoujunIsValid, "456m456p456999s11z should be valid for sanshoku doujun");
    }

    @Test
    public void testValidityOf_HandWithOnlySanshokuDoujunTiles_ShouldBeTrue()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSanshokuDoujunHandGroups)), incompleteSanshokuDoujunHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertTrue(sanshokuDoujunIsValid, "123m123p123s11z should be valid for sanshoku doujun");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSanshokuDoujunTiles_ShouldBeFalse()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSanshokuDoujunHandGroups)), completeNonSanshokuDoujunHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertFalse(sanshokuDoujunIsValid, "123345m22345678p should not be valid for sanshoku doujun");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSanshokuDoujunTiles_ShouldBeFalse()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSanshokuDoujunHandGroups)), incompleteNonSanshokuDoujunHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertFalse(sanshokuDoujunIsValid, "111m555p11s should not be valid for sanshoku doujun");
    }

    @Test
    public void testValidityOf_SanshokuWhereTheLastThreeGroupsAreMatchingRuns_ShouldBeTrue()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(sanshokuIsTheLastThreeRunsHandGroups)), sanshokuIsTheLastThreeRunsHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertTrue(sanshokuDoujunIsValid, "12344789m789p789s should be valid for sanshoku doujun");
    }

    @Test
    public void testValidityOf_RyanshokuWithIipeikou_ShouldBeFalse()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(ryanshokuIipeikouAKAFakeSanshokuHandGroups)), ryanshokuIipeikouAKAFakeSanshokuHandGroups);

        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();

        assertFalse(sanshokuDoujunIsValid, "12344778899p789s should not be valid for sanshoku doujun");
    }

    @Test
    public void testValueOf_OpenSanshokuDoujun_ShouldBeOne()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(false);

        int sanshokuDoujunValue = sanshokuDoujun.getHanValue();

        assertEquals(1, sanshokuDoujunValue, "Open sanshoku doujun value should be 1");
    }

    @Test
    public void testValueOf_ClosedSanshokuDoujun_ShouldBeTwo()
    {
        Yaku sanshokuDoujun = new SanshokuDoujun(anyHand, anyGroups);
        when(anyHand.isClosed()).thenReturn(true);

        int sanshokuDoujunValue = sanshokuDoujun.getHanValue();

        assertEquals(2, sanshokuDoujunValue, "Closed sanshoku doujun value should be 2");
    }
}
