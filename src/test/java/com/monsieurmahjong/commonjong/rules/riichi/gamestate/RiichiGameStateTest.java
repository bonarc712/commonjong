package com.monsieurmahjong.commonjong.rules.riichi.gamestate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

@ExtendWith(MockitoExtension.class)
public class RiichiGameStateTest
{
    @Mock
    private GameStateLog gameLog;

    @Test
    public void whenPlayerWinsOnTsumo_thenShouldWinOnTsumo()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnTsumo = riichiGameState.doesPlayerWinOnTsumo();

        assertThat(playerWinsOnTsumo, is(true));
    }

    @Test
    public void whenAnotherPlayerWinsOnTsumo_thenShouldNotWinOnTsumo()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.SOUTH);

        var playerWinsOnTsumo = riichiGameState.doesPlayerWinOnTsumo();

        assertThat(playerWinsOnTsumo, is(false));
    }

    @Test
    public void whenPlayerWinsOnRon_thenShouldWinOnRon()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-ron"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(true));
    }

    @Test
    public void whenAnotherPlayerWinsOnRon_thenShouldNotWinOnRon()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-ron"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.WEST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(false));
    }

    @Test
    public void whenPlayerWinsOnTsumo_thenShouldNotWinOnRon()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-tsumo"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnRon = riichiGameState.doesPlayerWinOnRon();

        assertThat(playerWinsOnRon, is(false));
    }

    @Test
    public void whenPlayerDeclaresRiichi_thenShouldBeMarkedAsDeclaredRiichi()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerDeclaredRiichi = riichiGameState.hasPlayerDeclaredRiichi();

        assertThat(playerDeclaredRiichi, is(true));
    }

    @Test
    public void whenAnotherPlayerDeclaresRiichi_thenShouldNotBeMarkedAsDeclaredRiichi()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.NORTH);

        var playerDeclaredRiichi = riichiGameState.hasPlayerDeclaredRiichi();

        assertThat(playerDeclaredRiichi, is(false));
    }

    @Test
    public void whenPlayerDeclaredRiichiAndWinsInSameTurn_thenPlayerWinsOnIppatsu()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi", "east-ron-3p"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnIppatsu = riichiGameState.doesPlayerWinOnIppatsu();

        assertThat(playerWinsOnIppatsu, is(true));
    }

    @Test
    public void whenPlayerDeclaredRiichiAndTsumosOnHisTurn_thenPlayerWinsOnIppatsu()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi", "east-discard-4p", //
                "south-draw-7z", "south-discard-7z", //
                "west-draw-8s", "west-discard-8s", //
                "north-draw-3m", "north-discard-3m", //
                "east-tsumo-3p"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnIppatsu = riichiGameState.doesPlayerWinOnIppatsu();

        assertThat(playerWinsOnIppatsu, is(true));
    }

    @Test
    public void whenPlayerDeclaredRiichiAndTsumosTwoTurnsAfter_thenPlayerDoesNotWinOnIppatsu()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi", "east-discard-4p", //
                "south-draw-7z", "south-discard-7z", //
                "west-draw-8s", "west-discard-8s", //
                "north-draw-3m", "north-discard-3m", //
                "east-draw-1m", "east-discard-1m", //
                "south-draw-7z", "south-discard-7z", //
                "west-draw-8s", "west-discard-8s", //
                "north-draw-3m", "north-discard-3m", //
                "east-ron-3p"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnIppatsu = riichiGameState.doesPlayerWinOnIppatsu();

        assertThat(playerWinsOnIppatsu, is(false));
    }

    @Test
    public void whenPlayerDeclaredRiichiAndAnyPlayerCallsBefore_thenPlayerDoesNotWinOnIppatsu()
    {
        when(gameLog.getLogs()).thenReturn(List.of("east-riichi", "east-kan-4p", "east-tsumo-8p"));
        var riichiGameState = new RiichiGameState(gameLog, Seat.EAST);

        var playerWinsOnIppatsu = riichiGameState.doesPlayerWinOnIppatsu();

        assertThat(playerWinsOnIppatsu, is(false));
    }

}
