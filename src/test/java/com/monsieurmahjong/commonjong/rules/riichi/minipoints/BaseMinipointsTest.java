package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class BaseMinipointsTest
{
    private RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "11z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");

    @Test
    public void baseMinipoints_IfPlayerWonOnClosedRon_IsThirty()
    {
        var minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups, anyParameters);
        when(anyParameters.doesPlayerWinOnRon()).thenReturn(true);

        var fuValue = minipoints.getFuValue();

        assertEquals(30, fuValue, "Base minipoints should be 30 on closed ron");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnSevenPairs_IsTwentyFive()
    {
        var minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuHandGroups)), completeChiitoitsuHandGroups, anyParameters);

        var fuValue = minipoints.getFuValue();

        assertEquals(25, fuValue, "Base minipoints should be 25 on seven pairs");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnOpenRon_IsTwenty()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var meld = Arrays.asList(new Tile(MahjongTileKind.CIRCLES_3), new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5));
        hand.addMeld(meld);
        var minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyParameters);
        when(anyParameters.doesPlayerWinOnRon()).thenReturn(true);

        var fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on open ron");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnClosedTsumo_IsTwenty()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyParameters);
        when(anyParameters.doesPlayerWinOnRon()).thenReturn(false);

        var fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on closed tsumo");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnOpenTsumo_IsTwenty()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var meld = Arrays.asList(new Tile(MahjongTileKind.CIRCLES_3), new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5));
        hand.addMeld(meld);
        var minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyParameters);
        when(anyParameters.doesPlayerWinOnRon()).thenReturn(false);

        var fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on open tsumo");
    }

    @Test
    public void baseMinipoints_IsAlwaysValid()
    {
        var minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups, anyParameters);

        var valid = minipoints.isValid();

        assertTrue(valid, "Base minipoints are always valid");
    }
}
