package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TableWindPair implements Fu
{
    private Hand hand;
    private List<TileGroup> groups;

    public TableWindPair(Hand hand, List<TileGroup> groups)
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
            if (group.isPair() && group.getTileKindAt(0).isWind())
            {
                var windTile = group.getTileKindAt(0);
                var wind = Seat.getSeatFromTileKind(windTile);
                if (hand.isTableWind(wind))
                {
                    return true;
                }
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
