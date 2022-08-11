package com.monsieurmahjong.commonjong.game;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public enum Seat
{
    EAST, // Dealer
    SOUTH, //
    WEST, //
    NORTH;

    public String getSeatName()
    {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }

    public String getSeatNameLowercase()
    {
        return name().toLowerCase();
    }

    public static Seat getSeatFromTileKind(MahjongTileKind kind)
    {
        switch (kind)
        {
        case EAST:
            return Seat.EAST;
        case SOUTH:
            return Seat.SOUTH;
        case WEST:
            return Seat.WEST;
        case NORTH:
            return Seat.NORTH;
        default:
            throw new IllegalArgumentException("Tile kind should be a wind");
        }
    }

    public static MahjongTileKind getTileKindFromSeat(Seat seat)
    {
        switch (seat)
        {
        case EAST:
            return MahjongTileKind.EAST;
        case SOUTH:
            return MahjongTileKind.SOUTH;
        case WEST:
            return MahjongTileKind.WEST;
        case NORTH:
            return MahjongTileKind.NORTH;
        default:
            throw new IllegalArgumentException("Seat should be a wind");
        }
    }
}
