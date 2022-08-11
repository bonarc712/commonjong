package com.monsieurmahjong.commonjong.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class SeatTest
{
    @Test
    public void testGetSeatFromTileKind()
    {
        assertEquals(Seat.EAST, Seat.getSeatFromTileKind(MahjongTileKind.EAST));
        assertEquals(Seat.SOUTH, Seat.getSeatFromTileKind(MahjongTileKind.SOUTH));
        assertEquals(Seat.WEST, Seat.getSeatFromTileKind(MahjongTileKind.WEST));
        assertEquals(Seat.NORTH, Seat.getSeatFromTileKind(MahjongTileKind.NORTH));
        assertThrows(IllegalArgumentException.class, () -> Seat.getSeatFromTileKind(MahjongTileKind.BAMBOOS_3));
    }

    @Test
    public void testGetTileKindFromSeat()
    {
        assertEquals(MahjongTileKind.EAST, Seat.getTileKindFromSeat(Seat.EAST));
        assertEquals(MahjongTileKind.SOUTH, Seat.getTileKindFromSeat(Seat.SOUTH));
        assertEquals(MahjongTileKind.WEST, Seat.getTileKindFromSeat(Seat.WEST));
        assertEquals(MahjongTileKind.NORTH, Seat.getTileKindFromSeat(Seat.NORTH));
    }
}
