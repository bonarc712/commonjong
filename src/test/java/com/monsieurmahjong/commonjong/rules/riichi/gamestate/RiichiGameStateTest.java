package com.monsieurmahjong.commonjong.rules.riichi.gamestate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class RiichiGameStateTest
{
    @Test
    public void whenPlayerWinsOnTsumo_thenShouldWinOnTsumo()
    {
        var gameLog = mock(GameStateLog.class);
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnTsumo = riichiGameState.doesPlayerWinOnTsumo();

        assertThat(playerWinsOnTsumo, is(true));
    }

    @Test
    public void whenAnotherPlayerWinsOnTsumo_thenShouldNotWinOnTsumo()
    {
        var gameLog = mock(GameStateLog.class);
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.SOUTH);

        var playerWinsOnTsumo = riichiGameState.doesPlayerWinOnTsumo();

        assertThat(playerWinsOnTsumo, is(false));
    }

    @Test
    public void whenPlayerWinsOnRon_thenShouldWinOnRon()
    {
        var gameLog = mock(GameStateLog.class);
        when(gameLog.getLogs()).thenReturn(List.of("east-ron"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(true));
    }

    @Test
    public void whenAnotherPlayerWinsOnRon_thenShouldNotWinOnRon()
    {
        var gameLog = mock(GameStateLog.class);
        when(gameLog.getLogs()).thenReturn(List.of("east-ron"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.WEST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(false));
    }

    @Test
    public void whenPlayerWinsOnTsumo_thenShouldNotWinOnRon()
    {
        var gameLog = mock(GameStateLog.class);
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(false));
    }

}
