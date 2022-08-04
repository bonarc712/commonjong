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
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class KokushiMusouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeKokushiHandGroups = TileGroupUtils.tileGroupsOf("11m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z");
    private List<TileGroup> completeNonKokushiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonKokushiHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> subsetOfKokushiTilesIsNotKokushi = TileGroupUtils.tileGroupsOf("11m", "9m", "1p", "9p", "1s", "9s");
    private List<TileGroup> juusanmenMachiKokushiTenpaiIsNotKokushi = TileGroupUtils.tileGroupsOf("1m", "9m", "1p", "9p", "1s", "9s", "1z", "2z", "3z", "4z", "5z", "6z", "7z");

    private MahjongTileKind juusanmenMachiWinningTile = MahjongTileKind.CHARACTERS_1;

    @Test
    public void testValidityOf_CompleteKokushiHand_ShouldBeTrue()
    {
        Yaku kokushiMusou = new KokushiMusou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeKokushiHandGroups)), completeKokushiHandGroups);

        var kokushiMusouIsValid = kokushiMusou.isValid();

        assertTrue(kokushiMusouIsValid, "119m19p19s1234567z should be valid for KokushiMusou");
    }

    @Test
    public void testValidityOf_CompleteNonKokushiHand_ShouldBeFalse()
    {
        Yaku kokushiMusou = new KokushiMusou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonKokushiHandGroups)), completeNonKokushiHandGroups);

        var kokushiMusouIsValid = kokushiMusou.isValid();

        assertFalse(kokushiMusouIsValid, "123345m22345678p should not be valid for KokushiMusou");
    }

    @Test
    public void testValidityOf_IncompleteNonKokushiHand_ShouldBeFalse()
    {
        Yaku kokushiMusou = new KokushiMusou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonKokushiHandGroups)), incompleteNonKokushiHandGroups);

        var kokushiMusouIsValid = kokushiMusou.isValid();

        assertFalse(kokushiMusouIsValid, "111m555p11s should not be valid for KokushiMusou");
    }

    @Test
    public void testValidityOf_SubsetOfKokushiHand_ShouldBeFalse()
    {
        Yaku kokushiMusou = new KokushiMusou(new Hand(TileGroupUtils.getTilesFromTileGroups(subsetOfKokushiTilesIsNotKokushi)), subsetOfKokushiTilesIsNotKokushi);

        var kokushiMusouIsValid = kokushiMusou.isValid();

        assertFalse(kokushiMusouIsValid, "119m19p19s should not be valid for KokushiMusou");
    }

    @Test
    public void testValidityOf_KokushiTenpaiHand_ShouldBeFalse()
    {
        Yaku kokushiMusou = new KokushiMusou(new Hand(TileGroupUtils.getTilesFromTileGroups(juusanmenMachiKokushiTenpaiIsNotKokushi)), juusanmenMachiKokushiTenpaiIsNotKokushi);

        var kokushiMusouIsValid = kokushiMusou.isValid();

        assertFalse(kokushiMusouIsValid, "19m19p19s1234567z should not be valid for KokushiMusou");
    }

    @Test
    public void testValueOf_KokushiMusouJuusanmenMachi_ShouldBeTwentySix()
    {
        Yaku kokushiMusou = new KokushiMusou(anyHand, anyGroups);
        when(anyHand.getWinningTile()).thenReturn(juusanmenMachiWinningTile);

        var kokushiMusouValue = kokushiMusou.getHanValue();

        assertEquals(13, kokushiMusouValue, "KokushiMusou value should be 13");
    }

    @Test
    public void testValueOf_JuusanmenMachiKokushiMusou_ShouldBeTwentySix()
    {
        Yaku kokushiMusou = new KokushiMusou(anyHand, completeKokushiHandGroups);
        when(anyHand.getWinningTile()).thenReturn(juusanmenMachiWinningTile);

        var kokushiMusouValue = kokushiMusou.getHanValue();

        assertEquals(26, kokushiMusouValue, "Juusanmenmachi KokushiMusou value should be 26");
    }

    @Test
    public void testValueOf_KokushiMusou_ShouldBeYakuman()
    {
        Yaku kokushiMusou = new KokushiMusou(anyHand, anyGroups);

        var kokushiMusouIsYakuman = kokushiMusou.isYakuman();

        assertTrue(kokushiMusouIsYakuman, "KokushiMusou value should be yakuman");
    }

    @Test
    public void testValueOf_KokushiMusou_ShouldBeDoubleYakuman()
    {
        Yaku kokushiMusou = new KokushiMusou(anyHand, completeKokushiHandGroups);
        when(anyHand.getWinningTile()).thenReturn(juusanmenMachiWinningTile);

        var juusanmenMachiKokushiMusouIsDoubleYakuman = kokushiMusou.isDoubleYakuman();

        assertTrue(juusanmenMachiKokushiMusouIsDoubleYakuman, "Juusanmenmachi KokushiMusou value should be double yakuman");
    }
}