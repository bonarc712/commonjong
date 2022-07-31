package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.TableWindYakuhai;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class TableWindYakuhaiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeTableWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "333z");
    private List<TileGroup> incompleteTableWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "333z");
    private List<TileGroup> completeNonTableWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonTableWindYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeTableWindKanYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "3333z");

    private Seat tableWind = Seat.WEST;

    @Test
    public void testValidityOf_HandWithFourteenTableWindYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeTableWindYakuhaiHandGroups));
        hand.addTableWind(tableWind);
        Yaku tableWindYakuhai = new TableWindYakuhai(hand, completeTableWindYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertTrue(tableWindYakuhaiIsValid, "111m888p999s11333z should be valid for table wind yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOnlyTableWindYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteTableWindYakuhaiHandGroups));
        hand.addTableWind(tableWind);
        Yaku tableWindYakuhai = new TableWindYakuhai(hand, incompleteTableWindYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertTrue(tableWindYakuhaiIsValid, "111m11333z should be valid for table wind yakuhai");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonTableWindYakuhaiTiles_ShouldBeFalse()
    {
        Yaku tableWindYakuhai = new TableWindYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonTableWindYakuhaiHandGroups)), completeNonTableWindYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertFalse(tableWindYakuhaiIsValid, "123345m22345678p should not be valid for table wind yakuhai");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonTableWindYakuhaiTiles_ShouldBeFalse()
    {
        Yaku tableWindYakuhai = new TableWindYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonTableWindYakuhaiHandGroups)), incompleteNonTableWindYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertFalse(tableWindYakuhaiIsValid, "111m567p11s should not be valid for table wind yakuhai");
    }

    @Test
    public void testValidityOf_TableWindYakuhaiWithWestTripletInTonshaba_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeTableWindYakuhaiHandGroups));
        hand.addTableWind(Seat.EAST);
        hand.addTableWind(Seat.WEST);
        Yaku tableWindYakuhai = new TableWindYakuhai(hand, completeTableWindYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertTrue(tableWindYakuhaiIsValid, "111m888p999s11333z should be valid for table wind yakuhai in tonshaba");
    }

    @Test
    public void testValidityOf_HandWithTableWindKanYakuhaiTiles_ShouldBeTrue()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeTableWindKanYakuhaiHandGroups));
        hand.addTableWind(tableWind);
        Yaku tableWindYakuhai = new TableWindYakuhai(hand, completeTableWindKanYakuhaiHandGroups);

        boolean tableWindYakuhaiIsValid = tableWindYakuhai.isValid();

        assertTrue(tableWindYakuhaiIsValid, "111888p999s113333z should be valid for table wind yakuhai");
    }

    @Test
    public void testValueOf_TableWindYakuhai_ShouldBeOne()
    {
        Yaku tableWindYakuhai = new TableWindYakuhai(anyHand, anyGroups);

        int tableWindYakuhaiValue = tableWindYakuhai.getHanValue();

        assertEquals(1, tableWindYakuhaiValue, "TableWindYakuhai value should be 1");
    }
}