package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class BaseMinipointsTest
{
    private GameStateLog anyLog = mock(GameStateLog.class);

    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "11z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");

    @Test
    public void baseMinipoints_IfPlayerWonOnClosedRon_IsThirty()
    {
        BaseMinipoints minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups, anyLog);
        when(anyLog.doesPlayerWinOnRon(any())).thenReturn(true);

        int fuValue = minipoints.getFuValue();

        assertEquals(30, fuValue, "Base minipoints should be 30 on closed ron");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnSevenPairs_IsTwentyFive()
    {
        BaseMinipoints minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuHandGroups)), completeChiitoitsuHandGroups, anyLog);

        int fuValue = minipoints.getFuValue();

        assertEquals(25, fuValue, "Base minipoints should be 25 on seven pairs");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnOpenRon_IsTwenty()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        List<Tile> meld = Arrays.asList(new Tile(MahjongTileKind.CIRCLES_3), new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5));
        hand.addMeld(meld);
        BaseMinipoints minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyLog);
        when(anyLog.doesPlayerWinOnRon(any())).thenReturn(true);

        int fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on open ron");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnClosedTsumo_IsTwenty()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        BaseMinipoints minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyLog);
        when(anyLog.doesPlayerWinOnRon(any())).thenReturn(false);

        int fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on closed tsumo");
    }

    @Test
    public void baseMinipoints_IfPlayerWonOnOpenTsumo_IsTwenty()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        List<Tile> meld = Arrays.asList(new Tile(MahjongTileKind.CIRCLES_3), new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5));
        hand.addMeld(meld);
        BaseMinipoints minipoints = new BaseMinipoints(hand, completeNonChiitoitsuHandGroups, anyLog);
        when(anyLog.doesPlayerWinOnRon(any())).thenReturn(false);

        int fuValue = minipoints.getFuValue();

        assertEquals(20, fuValue, "Base minipoints should be 20 on open tsumo");
    }

    @Test
    public void baseMinipoints_IsAlwaysValid()
    {
        BaseMinipoints minipoints = new BaseMinipoints(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups)), completeNonChiitoitsuHandGroups, anyLog);

        boolean valid = minipoints.isValid();

        assertTrue(valid, "Base minipoints are always valid");
    }
}
