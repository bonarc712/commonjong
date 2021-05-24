package com.monsieurmahjong.commonjong.game;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.rules.generic.*;

/**
 * A hand in the game sense : it includes all the tiles we have in front of us.
 * The melds and bonus tiles are separate from the "hidden" tiles. It also contains
 * information relative to the current hand that is played (winning tile, table
 * wind, seat wind, etc.)
 */
public class Hand
{
    private List<Tile> tiles; // in hand
    private List<List<Tile>> melds; // called tiles, they are not removed from hand
    private List<Tile> bonus; // flowers, peis, etc.

    private List<Seat> tableWinds; // TODO : check whether we can rename Seat to Wind... I feel it works worse with the table wind
    private Seat seatWind;

    private MahjongTileKind winningTile;
    private int tileIndexToDiscard; // tile to discard (first tile has index 0); -1 means no tile is discarded

    public Hand()
    {
        tiles = new ArrayList<>();
        melds = new ArrayList<>();
        bonus = new ArrayList<>();

        tableWinds = new ArrayList<>();

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

    public MahjongTileKind getWinningTile()
    {
        return winningTile;
    }

    public void setWinningTile(MahjongTileKind winningTile)
    {
        this.winningTile = winningTile;
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

    public void addTableWind(Seat seat)
    {
        tableWinds.add(seat);
    }

    public void setSeatWind(Seat seat)
    {
        seatWind = seat;
    }

    public void setSeat(Seat seat)
    {
        seatWind = seat;
    }

    public boolean isTableWind(Seat seat)
    {
        return tableWinds.contains(seat);
    }

    public boolean isSeatWind(Seat seat)
    {
        return seatWind == seat;
    }

    @Override
    public String toString()
    {
        return tiles.toString();
    }
}
