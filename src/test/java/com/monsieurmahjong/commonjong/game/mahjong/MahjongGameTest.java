package com.monsieurmahjong.commonjong.game.mahjong;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.players.Player;

public class MahjongGameTest
{
    MahjongGame game = mock(MahjongGame.class);
    Player player = mock(Player.class);

    @Test
    public void test_determineNextPlayer_afterEastPlays_shouldBeSouth()
    {
        setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(4, Seat.EAST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.SOUTH, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterSouthPlays_shouldBeWest()
    {
        setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(4, Seat.SOUTH);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.WEST, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterWestPlays_shouldBeNorth()
    {
        setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(4, Seat.WEST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.NORTH, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterNorthPlays_shouldBeEast()
    {
        setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(4, Seat.NORTH);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.EAST, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterWestPlaysInSanma_shouldBeEast()
    {
        setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(3, Seat.WEST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.EAST, nextSeat);
    }

    private void setupMahjongGameDetermineNextPlayerAndGetAmountOfPlayersAndGetSeatStubs(int getAmountOfPlayersReturnValue, Seat getSeatReturnValue) {
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(getAmountOfPlayersReturnValue);
        when(player.getSeat()).thenReturn(getSeatReturnValue);
    }
}
