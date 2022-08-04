package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class PinfuTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completePinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "22p", "345p", "678p");
    private List<TileGroup> incompletePinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "123m", "11s");
    private List<TileGroup> completeNonPinfuHandGroups = TileGroupUtils.tileGroupsOf("123m", "789m", "789p", "999s", "11z");
    private List<TileGroup> incompleteNonPinfuHandGroups = TileGroupUtils.tileGroupsOf("111m", "555p", "11s");
    private List<TileGroup> completePinfuWithRyanmenAndKanchanHandGroups = TileGroupUtils.tileGroupsOf("567m", "678m", "22p", "345p", "678p");
    private List<TileGroup> completeHandWithDragonPairGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "345p", "678p", "77z");
    private List<TileGroup> completeHandWithTableWindPairGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "345p", "678p", "11z");
    private List<TileGroup> completeHandWithSeatWindPairGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "345p", "678p", "33z");
    private List<TileGroup> completeHandWithValuelessWindPairGroups = TileGroupUtils.tileGroupsOf("123m", "456m", "345p", "678p", "44z");

    private MahjongTileKind ryanmenWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_6;
    private MahjongTileKind ryanmenWinningTileForIncompleteGroups = MahjongTileKind.CHARACTERS_1;
    private MahjongTileKind winningTileForCompleteNonPinfuGroups = MahjongTileKind.CIRCLES_9;
    private MahjongTileKind winningTileForIncompleteNonPinfuGroups = MahjongTileKind.BAMBOOS_1;
    private MahjongTileKind penchanWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_3;
    private MahjongTileKind kanchanWinningTileForCompleteGroups = MahjongTileKind.CHARACTERS_2;
    private Seat tableWind = Seat.EAST;
    private Seat seatWind = Seat.WEST;

    @Test
    public void testValidityOf_HandWithFourteenPinfuTiles_ShouldBeTrue()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        Yaku pinfu = new Pinfu(hand, completePinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "123456m22345678p winning on 6m should be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithOnlyPinfuTiles_ShouldBeTrue()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompletePinfuHandGroups));
        hand.setWinningTile(ryanmenWinningTileForIncompleteGroups);
        Yaku pinfu = new Pinfu(hand, incompletePinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "112233m11s should be valid for pinfu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonPinfuTiles_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonPinfuHandGroups));
        hand.setWinningTile(winningTileForCompleteNonPinfuGroups);
        Yaku pinfu = new Pinfu(hand, completeNonPinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123345m22345678p should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonPinfuTiles_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonPinfuHandGroups));
        hand.setWinningTile(winningTileForIncompleteNonPinfuGroups);
        Yaku pinfu = new Pinfu(hand, incompleteNonPinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "111m555p11s should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesAndPenchanWait_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups));
        hand.setWinningTile(penchanWinningTileForCompleteGroups);
        Yaku pinfu = new Pinfu(hand, completePinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m22345678p winning on 3m should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesAndKanchanWait_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuHandGroups));
        hand.setWinningTile(kanchanWinningTileForCompleteGroups);
        Yaku pinfu = new Pinfu(hand, completePinfuHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m22345678p winning on 2m should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithFourteenPinfuTilesWithKanchanAndRyanmen_ShouldBeTrue()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completePinfuWithRyanmenAndKanchanHandGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        Yaku pinfu = new Pinfu(hand, completePinfuWithRyanmenAndKanchanHandGroups);

        var pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "566778m22345678p winning on 6m should be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithPinfuShapedTilesWithDragonPair_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandWithDragonPairGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        Yaku pinfu = new Pinfu(hand, completeHandWithDragonPairGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m345678p77z winning on 6m should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithPinfuShapedTilesWithTableWindPair_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandWithTableWindPairGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        hand.addTableWind(tableWind);
        Yaku pinfu = new Pinfu(hand, completeHandWithTableWindPairGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m345678p11z winning on 6m with table wind East should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithPinfuShapedTilesWithSeatWindPair_ShouldBeFalse()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandWithSeatWindPairGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        hand.setSeatWind(seatWind);
        Yaku pinfu = new Pinfu(hand, completeHandWithSeatWindPairGroups);

        var pinfuIsValid = pinfu.isValid();

        assertFalse(pinfuIsValid, "123456m345678p33z winning on 6m with seat wind West should not be valid for pinfu");
    }

    @Test
    public void testValidityOf_HandWithPinfuShapedTilesWithValuelessWindPair_ShouldBeTrue()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandWithValuelessWindPairGroups));
        hand.setWinningTile(ryanmenWinningTileForCompleteGroups);
        hand.setSeatWind(seatWind);
        Yaku pinfu = new Pinfu(hand, completeHandWithValuelessWindPairGroups);

        var pinfuIsValid = pinfu.isValid();

        assertTrue(pinfuIsValid, "123456m345678p44z winning on 6m with seat wind West should be valid for pinfu");
    }

    @Test
    public void testValueOf_Pinfu_ShouldBeOne()
    {
        Yaku pinfu = new Pinfu(anyHand, anyGroups);

        var pinfuValue = pinfu.getHanValue();

        assertEquals(1, pinfuValue, "Pinfu value should be 1");
    }
}
