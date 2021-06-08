package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class SuuankouTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeSuuankouHandGroups = TileGroupUtils.tileGroupsOf("444m", "555p", "777s", "999s", "11z");
    private List<TileGroup> incompleteSuuankouHandGroups = TileGroupUtils.tileGroupsOf("1111m", "222p", "222s", "111z");
    private List<TileGroup> completeNonSuuankouHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonSuuankouHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");

    private List<TileGroup> toitoiHandGroups = TileGroupUtils.tileGroupsOf("4444m", "555p", "777s", "999s", "11z");
    private static List<List<Tile>> toitoiHandMelds = new ArrayList<>();

    private MahjongTileKind tankiWinningTile = MahjongTileKind.EAST;

    @BeforeAll
    public static void initialize()
    {
        toitoiHandMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("4444m")));
        toitoiHandMelds.add(TileGroupUtils.getTilesFromTileGroups(TileGroupUtils.tileGroupsOf("777s")));
    }

    @Test
    public void testValidityOf_CompleteSuuankouHand_ShouldBeTrue()
    {
        Yaku suuankou = new Suuankou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSuuankouHandGroups)), completeSuuankouHandGroups);

        boolean suuankouIsValid = suuankou.isValid();

        assertTrue(suuankouIsValid, "444m555p777999s11z should be valid for Suuankou");
    }

    @Test
    public void testValidityOf_IncompleteSuuankouHand_ShouldBeTrue()
    {
        Yaku suuankou = new Suuankou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSuuankouHandGroups)), incompleteSuuankouHandGroups);

        boolean suuankouIsValid = suuankou.isValid();

        assertTrue(suuankouIsValid, "1111m222p222s111z should be valid for Suuankou");
    }

    @Test
    public void testValidityOf_CompleteNonSuuankouHand_ShouldBeFalse()
    {
        Yaku suuankou = new Suuankou(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSuuankouHandGroups)), completeNonSuuankouHandGroups);

        boolean suuankouIsValid = suuankou.isValid();

        assertFalse(suuankouIsValid, "123345m22345678p should not be valid for Suuankou");
    }

    @Test
    public void testValidityOf_IncompleteNonSuuankouHand_ShouldBeFalse()
    {
        Yaku suuankou = new Suuankou(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSuuankouHandGroups)), incompleteNonSuuankouHandGroups);

        boolean suuankouIsValid = suuankou.isValid();

        assertFalse(suuankouIsValid, "111m555p11s should not be valid for Suuankou");
    }

    @Test
    public void testValidityOf_ToitoiHand_ShouldBeFalse()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(toitoiHandGroups));
        hand.setMelds(toitoiHandMelds);
        Yaku suuankou = new Suuankou(hand, toitoiHandGroups);

        boolean suuankouIsValid = suuankou.isValid();

        assertFalse(suuankouIsValid, "4444m555p777999s11z should not be valid for Suuankou");
    }

    @Test
    public void testValueOf_Suuankou_ShouldBeThirteen()
    {
        Yaku suuankou = new Suuankou(anyHand, anyGroups);

        int suuankouValue = suuankou.getHanValue();

        assertEquals(13, suuankouValue, "Suuankou value should be 13");
    }

    @Test
    public void testValueOf_SuuankouWithTankiWait_ShouldBeTwentySix()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeSuuankouHandGroups));
        hand.setWinningTile(tankiWinningTile);
        Yaku suuankou = new Suuankou(hand, completeSuuankouHandGroups);

        int suuankouValue = suuankou.getHanValue();

        assertEquals(26, suuankouValue, "Suuankou value should be 26");
    }

    @Test
    public void testValueOf_Suuankou_ShouldBeYakuman()
    {
        Yaku suuankou = new Suuankou(anyHand, anyGroups);

        boolean suuankouIsYakuman = suuankou.isYakuman();

        assertTrue(suuankouIsYakuman, "Suuankou should be yakuman");
    }

    @Test
    public void testValueOf_SuuankouWithTankiWait_ShouldBeDoubleYakuman()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeSuuankouHandGroups));
        hand.setWinningTile(tankiWinningTile);
        Yaku suuankou = new Suuankou(hand, completeSuuankouHandGroups);

        boolean suuankouTankiIsDoubleYakuman = suuankou.isDoubleYakuman();

        assertTrue(suuankouTankiIsDoubleYakuman, "Suuankou tanki should be double yakuman");
    }
}