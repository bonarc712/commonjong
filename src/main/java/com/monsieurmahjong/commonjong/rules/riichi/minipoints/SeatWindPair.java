package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class SeatWindPair implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;

    public SeatWindPair(Hand hand, List<TileGroup> groups)
    {
        this.hand = hand;
        this.groups = groups;
    }

    @Override
    public boolean isValid()
    {
        if (groups.size() == 7)
        {
            return false;
        }

        for (var group : groups)
        {
            var seatWindTile = Seat.getTileKindFromSeat(hand.getSeatWind());
            if (group.isPair() && group.getTileKindAt(0).equals(seatWindTile))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getFuValue()
    {
        return 2;
    }

}
