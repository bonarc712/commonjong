package com.monsieurmahjong.commonjong.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HandTest
{
    @Test
    public void testIsEastTableWind_DuringEastTurn_ShouldBeTrue()
    {
        Hand hand = new Hand();
        hand.addTableWind(Seat.EAST);

        assertTrue(hand.isTableWind(Seat.EAST), "East should be table wind");
    }

    @Test
    public void testIsSouthTableWind_DuringEastTurn_ShouldBeFalse()
    {
        Hand hand = new Hand();
        hand.addTableWind(Seat.EAST);

        assertFalse(hand.isTableWind(Seat.SOUTH), "South should not be table wind");
    }

    @Test
    public void testIsWestSeatWind_ForWestPlayer_ShouldBeTrue()
    {
        Hand hand = new Hand();
        hand.setSeatWind(Seat.WEST);

        assertTrue(hand.isSeatWind(Seat.WEST), "West should be seat wind");
    }

    @Test
    public void testIsSouthSeatWind_ForWestPlayer_ShouldBeFalse()
    {
        Hand hand = new Hand();
        hand.addTableWind(Seat.WEST);

        assertFalse(hand.isSeatWind(Seat.SOUTH), "South should not be seat wind");
    }

    @Test
    public void testIsEastTableWind_InTonshabaTurn_ShouldBeTrue()
    {
        Hand hand = new Hand();
        hand.addTableWind(Seat.EAST);
        hand.addTableWind(Seat.WEST);

        assertTrue(hand.isTableWind(Seat.EAST), "East should be table wind");
    }
}
