package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class PinfuTest
{
    private Hand anyHand = mock(Hand.class);
    private MahjongTileKind anyTile = MahjongTileKind.RED;
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completePinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "22p", "345p", "678p");
    private List<TileGroup> incompletePinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "123m", "11z");
    private List<TileGroup> completeNonPinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "789m", "789p", "999s", "11z");
    private List<TileGroup> incompleteNonPinfuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> completePinfuWithRyanmenAndKanchanHandGroups = TileGroupUtils.tileGroupsOf("567m", "678m", "22p", "345p", "678p");

    private MahjongTileKind ryanmenWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_6;
    private MahjongTileKind ryanmenWinningTileForIncompleteGroups = MahjongTileKind.CHARACTERS_1;
    private MahjongTileKind winningTileForCompleteNonPinfuGroups = MahjongTileKind.CIRCLES_9;
    private MahjongTileKind winningTileForIncompleteNonPinfuGroups = MahjongTileKind.BAMBOOS_1;
    private MahjongTileKind penchanWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_3;
    private MahjongTileKind kanchanWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_2;

    @Test
    public void testValidityOf_HandWithFourteenPinfuTiles_ShouldBeTrue()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups)), completePinfuHandGroups, ryanmenWinningTileForCompleteGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "123456m22345678p winning on 6m should be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithOnlyPinfuTiles_ShouldBeTrue()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompletePinfuHandGroups)), incompletePinfuHandGroups, ryanmenWinningTileForIncompleteGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "112233m11z should be valid for pinfu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonPinfuTiles_ShouldBeFalse()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonPinfuHandGroups)), completeNonPinfuHandGroups, winningTileForCompleteNonPinfuGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123345m22345678p should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonPinfuTiles_ShouldBeFalse()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonPinfuHandGroups)), incompleteNonPinfuHandGroups, winningTileForIncompleteNonPinfuGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "111m555p11s should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesAndPenchanWait_ShouldBeFalse()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups)), completePinfuHandGroups, penchanWinningTileForCompleteGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m22345678p winning on 3m should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesAndKanchanWait_ShouldBeFalse()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups)), completePinfuHandGroups, kanchanWinningTileForCompleteGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m22345678p winning on 2m should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesWithKanchanAndRyanmen_ShouldBeTrue()
    {
        Yaku pinfu = new Pinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuWithRyanmenAndKanchanHandGroups)), completePinfuWithRyanmenAndKanchanHandGroups,
                ryanmenWinningTileForCompleteGroups);

        boolean pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "566778m22345678p winning on 6m should be valid for pinfu");
    }

    @Test
    public void testValueOf_Pinfu_ShouldBeOne()
    {
        Yaku pinfu = new Pinfu(anyHand, anyGroups, anyTile);

        int pinfuValue = pinfu.getHanValue();

        assertEquals(1, pinfuValue, "Pinfu value should be 1");
    }
}
