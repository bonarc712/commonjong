package com.monsieurmahjong.commonjong.game;

import java.util.*;
import java.util.stream.Collectors;

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

    public Hand(List<Tile> tiles)
    {
        this();
        this.tiles = tiles;
    }

    public List<Tile> getTiles()
    {
        return tiles;
    }

    public void setTiles(List<Tile> tiles)
    {
        this.tiles = tiles;
    }

    public List<List<Tile>> getMelds()
    {
        return melds;
    }

    public List<Tile> getBonus()
    {
        return bonus;
    }

    public List<Tile> getUnmeldedTiles()
    {
        List<Tile> unmeldedTiles = new ArrayList<>();
        unmeldedTiles.addAll(getTiles());
        List<Tile> meldedTiles = getMelds().stream().flatMap(List::stream).collect(Collectors.toList());

        meldedTiles.forEach(tile -> unmeldedTiles.remove(tile));

        return unmeldedTiles;
    }

    @Override
    public String toString()
    {
        return tiles.toString();
    }

}
