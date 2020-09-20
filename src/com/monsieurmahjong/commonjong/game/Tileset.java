package com.monsieurmahjong.commonjong.game;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public abstract class Tileset
{
    protected List<Tile> tiles;
    protected List<Tile> drawnTiles;

    public Tileset()
    {
        tiles = new ArrayList<>();
        drawnTiles = new ArrayList<>();
        initTileset();
    }

    protected void initTileset()
    {
        List<MahjongTileKind> mahjongTiles = getTileList();
        mahjongTiles.forEach(tile -> {
            for (int i = 0; i < 4; ++i)
            {
                Tile physicalTile = new Tile(tile);
                tiles.add(physicalTile);
            }
        });
        System.out.println("Before shuffle " + tiles);
        System.out.println();
        shuffle();
        System.out.println("Shuffling result " + tiles);
        System.out.println(" --- ");
    }

    public void reset()
    {
        tiles.addAll(drawnTiles);
        drawnTiles.clear();
    }

    public void shuffle()
    {
        Collections.shuffle(tiles);
    }

    public Tile draw()
    {
        Tile drawnTile = tiles.remove(0);
        drawnTiles.add(drawnTile);
        return drawnTile;
    }

    public abstract List<MahjongTileKind> getTileList();
}
