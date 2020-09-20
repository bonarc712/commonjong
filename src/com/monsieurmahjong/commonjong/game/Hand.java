package com.monsieurmahjong.commonjong.game;

import java.util.*;

public class Hand
{
    private List<Tile> tiles; // in hand
    private List<List<Tile>> melds; // called tiles, they are not removed from hand
    private List<Tile> bonus; // flowers, peis, etc.

    public Hand()
    {
        tiles = new ArrayList<>();
        melds = new ArrayList<>();
        bonus = new ArrayList<>();
    }

    public List<Tile> getTiles()
    {
        return tiles;
    }

    public List<List<Tile>> getMelds()
    {
        return melds;
    }

    public List<Tile> getBonus()
    {
        return bonus;
    }

    @Override
    public String toString()
    {
        return tiles.toString();
    }

}
