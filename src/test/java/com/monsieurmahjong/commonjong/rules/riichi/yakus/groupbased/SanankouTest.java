package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SanankouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSanankouHandGroups = TileGroupUtils.tileGroupsOf("444m", "555p", "7777s", "789s", "11z");
    private List<TileGroup> incompleteSanankouHandGroups = TileGroupUtils.tileGroupsOf("111m", "222p", "222s", "11z");
    private List<TileGroup> completeNonSanankouHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSanankouHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> suuankouHandGroups = TileGroupUtils.tileGroupsOf("444m", "5555p", "7777s", "999s", "11z");

    private List<TileGroup> ryanankouHandGroups = TileGroupUtils.tileGroupsOf("444m", "555p", "7777s", "999s", "11z");
    private static List<List<Tile>> ryanankouHandMelds = new ArrayList<>();
    private List<TileGroup> sanankouWithOpenRunHandGroups = TileGroupUtils.tileGroupsOf("444m", "555p", "777s", "789s", "11z");
    private static List<List<Tile>> sanankouWithOpenRunHandMelds = new ArrayList<>();

    @BeforeAll
    public static void initialize()
    {
        ryanankouHandMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("444m")));
        ryanankouHandMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("7777s")));
        sanankouWithOpenRunHandMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("789s")));
    }

    @Test
    public void testValidityOf_CompleteSanankouHand_ShouldBeTrue()
    {
        Yaku sanankou = new Sanankou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSanankouHandGroups)), completeSanankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertTrue(sanankouIsValid, "444m555p7777789s11z should be valid for Sanankou");
    }

    @Test
    public void testValidityOf_IncompleteSanankouHand_ShouldBeTrue()
    {
        Yaku sanankou = new Sanankou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSanankouHandGroups)), incompleteSanankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertTrue(sanankouIsValid, "111m222p222s11z should be valid for Sanankou");
    }

    @Test
    public void testValidityOf_CompleteNonSanankouHand_ShouldBeFalse()
    {
        Yaku sanankou = new Sanankou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSanankouHandGroups)), completeNonSanankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertFalse(sanankouIsValid, "123345m22345678p should not be valid for Sanankou");
    }

    @Test
    public void testValidityOf_IncompleteNonSanankouHand_ShouldBeFalse()
    {
        Yaku sanankou = new Sanankou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSanankouHandGroups)), incompleteNonSanankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertFalse(sanankouIsValid, "111m555p11s should not be valid for Sanankou");
    }

    @Test
    public void testValidityOf_SuuankouHand_ShouldBeFalse()
    {
        Yaku sanankou = new Sanankou(new Hand(TileGroupUtils.getTilesFromTileGroups(suuankouHandGroups)), suuankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertFalse(sanankouIsValid, "444m5555p7777999s11z should not be valid for Sanankou");
    }

    @Test
    public void testValidityOf_RyanankouHand_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(ryanankouHandGroups));
        hand.setMelds(ryanankouHandMelds);
        Yaku sanankou = new Sanankou(hand, ryanankouHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertFalse(sanankouIsValid, "444m555p7777999s11z should not be valid for Sanankou");
    }

    @Test
    public void testValidityOf_SanankouWithOpenRunHand_ShouldBeTrue()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(sanankouWithOpenRunHandGroups));
        hand.setMelds(sanankouWithOpenRunHandMelds);
        Yaku sanankou = new Sanankou(hand, sanankouWithOpenRunHandGroups);

        var sanankouIsValid = sanankou.isValid();

        assertTrue(sanankouIsValid, "444m555p777789s11z should be valid for Sanankou");
    }

    @Test
    public void testValueOf_Sanankou_ShouldBeTwo()
    {
        Yaku sanankou = new Sanankou(anyHand, anyGroups);

        var sanankouValue = sanankou.getHanValue();

        assertEquals(2, sanankouValue, "Sanankou value should be 2");
    }
}