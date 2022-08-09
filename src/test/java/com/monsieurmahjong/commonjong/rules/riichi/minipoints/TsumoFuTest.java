package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class TsumoFuTest
{
    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "77p", "99p", "99s", "11z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private RiichiScoringParameters parameters = mock(RiichiScoringParameters.class);

    @Test
    public void whenPlayerWinsOnTsumo_TsumoFu_AreScored()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, parameters);
        when(parameters.doesPlayerWinOnTsumo()).thenReturn(true);

        var isValid = tsumoFu.isValid();

        assertTrue(isValid, "Winning on tsumo should grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnRon_TsumoFu_AreNotScored()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, parameters);
        when(parameters.doesPlayerWinOnTsumo()).thenReturn(false);

        var isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on ron should not grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnPinfuTsumo_TsumoFu_AreNotScored()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        hand.setWinningTile(MahjongTileKind.CHARACTERS_5);
        var tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, parameters);
        when(parameters.doesPlayerWinOnTsumo()).thenReturn(true);

        var isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on tsumo with pinfu should not grant tsumo fu");
    }

    @Test
    public void whenPlayerWinsOnChiitoiTsumo_TsumoFu_AreNotScored()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeChiitoitsuHandGroups));
        var tsumoFu = new TsumoFu(hand, completeChiitoitsuHandGroups, parameters);
        when(parameters.doesPlayerWinOnTsumo()).thenReturn(true);

        var isValid = tsumoFu.isValid();

        assertFalse(isValid, "Winning on tsumo with seven pairs should not grant tsumo fu");
    }

    @Test
    public void valueOf_TsumoFu_ShouldBeTwo()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonChiitoitsuHandGroups));
        var tsumoFu = new TsumoFu(hand, completeNonChiitoitsuHandGroups, parameters);
        when(parameters.doesPlayerWinOnTsumo()).thenReturn(true);

        var fuValue = tsumoFu.getFuValue();

        assertEquals(2, fuValue, "Tsumo fu should give 2 fu");
    }
}
