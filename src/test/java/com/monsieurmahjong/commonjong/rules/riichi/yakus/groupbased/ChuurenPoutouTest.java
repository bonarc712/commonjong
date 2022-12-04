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
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChuurenPoutouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeChuurenHandGroups = TileGroupUtils.tileGroupsOf("111m", "234m", "456m", "789m", "99m");
    private List<TileGroup> completeNonChuurenHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> chinitsuButNotChuurenHandGroups = TileGroupUtils.tileGroupsOf("123m", "22m", "345m", "777m", "789m");
    private List<TileGroup> incompleteNonChuurenHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> openChuurenHandGroups = TileGroupUtils.tileGroupsOf("111m", "234m", "456m", "789m", "99m");
    private MahjongTileKind junseiChuurenWinningTile = MahjongTileKind.CHARACTERS_4;
    private MahjongTileKind nonJunseiWinningTile = MahjongTileKind.CHARACTERS_1;

    @Test
    public void testValidityOf_CompleteChuurenHand_ShouldBeTrue()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups)), completeChuurenHandGroups);

        var chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertTrue(chuurenPoutouIsValid, "11123445678999m should be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_CompleteNonChuurenHand_ShouldBeFalse()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChuurenHandGroups)), completeNonChuurenHandGroups);

        var chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "123345m22345678p should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_ChinitsuButNotChuurenHand_ShouldBeFalse()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(chinitsuButNotChuurenHandGroups)), chinitsuButNotChuurenHandGroups);

        var chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "12223345777789m should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_IncompleteNonChuurenHand_ShouldBeFalse()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonChuurenHandGroups)), incompleteNonChuurenHandGroups);

        var chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "111m555p11s should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_OpenChuurenHand_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(openChuurenHandGroups));
        List<List<Tile>> openChuurenMelds = new ArrayList<>();
        openChuurenMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("234m")));
        hand.setMelds(openChuurenMelds);
        Yaku chuurenPoutou = new ChuurenPoutou(hand, openChuurenHandGroups);

        var chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "11123445678999m with an open chii should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValueOf_ChuurenPoutou_ShouldBeThirteen()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(anyHand, anyGroups);
        when(anyHand.getWinningTile()).thenReturn(nonJunseiWinningTile);

        var chuurenPoutouValue = chuurenPoutou.getHanValue();

        assertEquals(13, chuurenPoutouValue, "ChuurenPoutou value should be 13");
    }

    @Test
    public void testValueOf_ChuurenPoutouWaitingOnAnyTile_ShouldBeTwentySix()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups));
        hand.setWinningTile(junseiChuurenWinningTile);
        Yaku junseiChuurenPoutou = new ChuurenPoutou(hand, completeChuurenHandGroups);

        var chuurenPoutouValue = junseiChuurenPoutou.getHanValue();

        assertEquals(26, chuurenPoutouValue, "Junsei ChuurenPoutou value should be 26");
    }

    @Test
    public void testValueOf_ChuurenPoutou_ShouldBeYakuman()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(anyHand, anyGroups);
        when(anyHand.getWinningTile()).thenReturn(nonJunseiWinningTile);

        var chuurenPoutouIsYakuman = chuurenPoutou.isYakuman();

        assertTrue(chuurenPoutouIsYakuman, "ChuurenPoutou should be yakuman");
    }

    @Test
    public void testValueOf_JunseiChuurenPoutou_ShouldBeDoubleYakuman()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups));
        hand.setWinningTile(junseiChuurenWinningTile);
        Yaku chuurenPoutou = new ChuurenPoutou(hand, completeChuurenHandGroups);

        var junseiChuurenPoutouIsDoubleYakuman = chuurenPoutou.isDoubleYakuman();

        assertTrue(junseiChuurenPoutouIsDoubleYakuman, "Junsei chuuren poutou should be double yakuman");
    }

    @Test
    public void testValueOf_ChuurenPoutouWithWinningTileUnknown_ShouldBeYakuman()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups));
        Yaku chuurenPoutou = new ChuurenPoutou(hand, completeChuurenHandGroups);

        var chuurenPoutouIsYakuman = chuurenPoutou.isYakuman();

        assertTrue(chuurenPoutouIsYakuman, "Chuuren poutou without winning tile defined is yakuman");
    }
}