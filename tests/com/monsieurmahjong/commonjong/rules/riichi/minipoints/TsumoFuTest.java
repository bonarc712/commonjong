package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TsumoFuTest
{
    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "11z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private GameStateLog log = mock(GameStateLog.class);

    @Test
    public void whenPlayerWinsOnTsumo_TsumoFu_AreScored()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        TsumoFu tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, log);
        when(log.doesPlayerWinOnTsumo(any())).thenReturn(true);

        boolean isValid = tsumoFu.isValid();

        assertTrue(isValid, "Winning on tsumo should grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnRon_TsumoFu_AreNotScored()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        TsumoFu tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, log);
        when(log.doesPlayerWinOnTsumo(any())).thenReturn(false);

        boolean isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on ron should not grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnPinfuTsumo_TsumoFu_AreNotScored()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        hand.setWinningTile(MahjongTileKind.CHARACTERS_5);
        TsumoFu tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, log);
        when(log.doesPlayerWinOnTsumo(any())).thenReturn(true);

        boolean isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on tsumo with pinfu should not grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnChiitoiTsumo_TsumoFu_AreNotScored()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuHandGroups));
        TsumoFu tsumoFu = new TsumoFu(hand, completeChiitoitsuHandGroups, log);
        when(log.doesPlayerWinOnTsumo(any())).thenReturn(true);

        boolean isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on tsumo with seven pairs should not grant tsumo fu");
    }

    @Test
    public void valueOf_TsumoFu_ShouldBeTwo()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        TsumoFu tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, log);
        when(log.doesPlayerWinOnTsumo(any())).thenReturn(true);

        int fuValue = tsumoFu.getFuValue();

        assertEquals(2, fuValue, "Tsumo fu should give 2 fu");
    }
}
