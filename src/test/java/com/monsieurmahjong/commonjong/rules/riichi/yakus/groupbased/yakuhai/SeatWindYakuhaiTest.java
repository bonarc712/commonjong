package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.SeatWindYakuhai;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SeatWindYakuhaiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSeatWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "333z");
    private List<TileGroup> incompleteSeatWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "333z");
    private List<TileGroup> completeNonSeatWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSeatWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeOtherWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "222z");
    private List<TileGroup> completeSeatWindKanYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "3333z");

    private Seat seatWind = Seat.WEST;

    @Test
    public void testValidityOf_HandWithFourteenSeatWindYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeSeatWindYakuhaiHandGroups));
        hand.setSeatWind(seatWind);
        Yaku seatWindYakuhai = new SeatWindYakuhai(hand, completeSeatWindYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertTrue(seatWindYakuhaiIsValid, "111m888p999s11333z should be valid for seat wind yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOnlySeatWindYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSeatWindYakuhaiHandGroups));
        hand.setSeatWind(seatWind);
        Yaku seatWindYakuhai = new SeatWindYakuhai(hand, incompleteSeatWindYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertTrue(seatWindYakuhaiIsValid, "111m11333z should be valid for seat wind yakuhai");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSeatWindYakuhaiTiles_ShouldBeFalse()
    {
        Yaku seatWindYakuhai = new SeatWindYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSeatWindYakuhaiHandGroups)), completeNonSeatWindYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertFalse(seatWindYakuhaiIsValid, "123345m22345678p should not be valid for seat wind yakuhai");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSeatWindYakuhaiTiles_ShouldBeFalse()
    {
        Yaku seatWindYakuhai = new SeatWindYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSeatWindYakuhaiHandGroups)), incompleteNonSeatWindYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertFalse(seatWindYakuhaiIsValid, "111m567p11s should not be valid for seat wind yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOtherWindYakuhaiTiles_ShouldBeFalse()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeOtherWindYakuhaiHandGroups));
        hand.setSeatWind(seatWind);
        Yaku seatWindYakuhai = new SeatWindYakuhai(hand, completeOtherWindYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertFalse(seatWindYakuhaiIsValid, "111m888p999s11222z should not be valid for seat wind yakuhai");
    }

    @Test
    public void testValidityOf_HandWithSeatWindKanYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeSeatWindKanYakuhaiHandGroups));
        hand.setSeatWind(seatWind);
        Yaku seatWindYakuhai = new SeatWindYakuhai(hand, completeSeatWindKanYakuhaiHandGroups);

        boolean seatWindYakuhaiIsValid = seatWindYakuhai.isValid();

        assertTrue(seatWindYakuhaiIsValid, "111888p999s113333z should be valid for seat wind yakuhai");
    }

    @Test
    public void testValueOf_SeatWindYakuhai_ShouldBeOne()
    {
        Yaku seatWindYakuhai = new SeatWindYakuhai(anyHand, anyGroups);

        int seatWindYakuhaiValue = seatWindYakuhai.getHanValue();

        assertEquals(1, seatWindYakuhaiValue, "SeatWindYakuhai value should be 1");
    }
}