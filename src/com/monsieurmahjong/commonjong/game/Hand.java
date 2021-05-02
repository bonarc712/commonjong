package com.monsieurmahjong.commonjong.game;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileOrderingComparator;

/**
 * A hand in the game sense : it includes all the tiles we have in front of us.
 * The melds and bonus tiles are separate from the "hidden" tiles.
 */
public class Hand
{
    private List<Tile> tiles; // in hand
    private List<List<Tile>> melds; // called tiles, they are not removed from hand
    private List<Tile> bonus; // flowers, peis, etc.

    private int tileIndexToDiscard; // tile to discard (first tile has index 0); -1 means no tile is discarded

    public Hand()
    {
        tiles = new ArrayList<>();
        melds = new ArrayList<>();
        bonus = new ArrayList<>();

        tileIndexToDiscard = -1;
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

    public void sortTiles()
    {
        tiles.sort(new MahjongTileOrderingComparator());
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

    public int getTileIndexToDiscard()
    {
        return tileIndexToDiscard;
    }

    public void setTileIndexToDiscard(int tileIndex)
    {
        tileIndexToDiscard = tileIndex;
    }

    public boolean isOpen()
    {
        return !isClosed();
    }

    // TODO : make sure closed kan is not considered as open
    public boolean isClosed()
    {
        return melds.isEmpty();
    }

    @Override
    public String toString()
    {
        return tiles.toString();
    }

}
