package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.ChuurenPoutou;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
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
    private List<TileGroup> incompleteNonChuurenHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> openChuurenHandGroups = TileGroupUtils.tileGroupsOf("111m", "234m", "456m", "789m", "99m");
    private MahjongTileKind junseiChuurenWinningTile = MahjongTileKind.CHARACTERS_4;
    private MahjongTileKind nonJunseiWinningTile = MahjongTileKind.CHARACTERS_1;

    @Test
    public void testValidityOf_CompleteChuurenHand_ShouldBeTrue()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups)), completeChuurenHandGroups);

        boolean chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertTrue(chuurenPoutouIsValid, "11123445678999m should be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_CompleteNonChuurenHand_ShouldBeFalse()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChuurenHandGroups)), completeNonChuurenHandGroups);

        boolean chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "123345m22345678p should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_IncompleteNonChuurenHand_ShouldBeFalse()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonChuurenHandGroups)), incompleteNonChuurenHandGroups);

        boolean chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "111m555p11s should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValidityOf_OpenChuurenHand_ShouldBeFalse()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(openChuurenHandGroups));
        List<List<Tile>> openChuurenMelds = new ArrayList<>();
        openChuurenMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("234m")));
        hand.setMelds(openChuurenMelds);
        Yaku chuurenPoutou = new ChuurenPoutou(hand, openChuurenHandGroups);

        boolean chuurenPoutouIsValid = chuurenPoutou.isValid();

        assertFalse(chuurenPoutouIsValid, "11123445678999m with an open chii should not be valid for ChuurenPoutou");
    }

    @Test
    public void testValueOf_ChuurenPoutou_ShouldBeThirteen()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(anyHand, anyGroups);
        when(anyHand.getWinningTile()).thenReturn(nonJunseiWinningTile);

        int chuurenPoutouValue = chuurenPoutou.getHanValue();

        assertEquals(13, chuurenPoutouValue, "ChuurenPoutou value should be 13");
    }

    @Test
    public void testValueOf_ChuurenPoutouWaitingOnAnyTile_ShouldBeTwentySix()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups));
        hand.setWinningTile(junseiChuurenWinningTile);
        Yaku junseiChuurenPoutou = new ChuurenPoutou(hand, completeChuurenHandGroups);

        int chuurenPoutouValue = junseiChuurenPoutou.getHanValue();

        assertEquals(26, chuurenPoutouValue, "Junsei ChuurenPoutou value should be 26");
    }

    @Test
    public void testValueOf_ChuurenPoutou_ShouldBeYakuman()
    {
        Yaku chuurenPoutou = new ChuurenPoutou(anyHand, anyGroups);
        when(anyHand.getWinningTile()).thenReturn(nonJunseiWinningTile);

        boolean chuurenPoutouIsYakuman = chuurenPoutou.isYakuman();

        assertTrue(chuurenPoutouIsYakuman, "ChuurenPoutou should be yakuman");
    }

    @Test
    public void testValueOf_JunseiChuurenPoutou_ShouldBeDoubleYakuman()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChuurenHandGroups));
        hand.setWinningTile(junseiChuurenWinningTile);
        Yaku chuurenPoutou = new ChuurenPoutou(hand, completeChuurenHandGroups);

        boolean junseiChuurenPoutouIsDoubleYakuman = chuurenPoutou.isDoubleYakuman();

        assertTrue(junseiChuurenPoutouIsDoubleYakuman, "Junsei chuuren poutou should be double yakuman");
    }
}