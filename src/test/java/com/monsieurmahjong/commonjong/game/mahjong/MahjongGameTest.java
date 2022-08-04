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
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(4);
        when(player.getSeat()).thenReturn(Seat.EAST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.SOUTH, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterSouthPlays_shouldBeWest()
    {
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(4);
        when(player.getSeat()).thenReturn(Seat.SOUTH);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.WEST, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterWestPlays_shouldBeNorth()
    {
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(4);
        when(player.getSeat()).thenReturn(Seat.WEST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.NORTH, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterNorthPlays_shouldBeEast()
    {
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(4);
        when(player.getSeat()).thenReturn(Seat.NORTH);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.EAST, nextSeat);
    }

    @Test
    public void test_determineNextPlayer_afterWestPlaysInSanma_shouldBeEast()
    {
        when(game.determineNextPlayer(any(Player.class))).thenCallRealMethod();
        when(game.getAmountOfPlayers()).thenReturn(3);
        when(player.getSeat()).thenReturn(Seat.WEST);

        var nextSeat = game.determineNextPlayer(player);

        Assertions.assertEquals(Seat.EAST, nextSeat);
    }
}
