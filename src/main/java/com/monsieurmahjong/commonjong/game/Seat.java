package com.monsieurmahjong.commonjong.game;

public enum Seat
{
    EAST, // Dealer
    SOUTH,
    WEST,
    NORTH;

    public String getSeatName()
    {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }
}
