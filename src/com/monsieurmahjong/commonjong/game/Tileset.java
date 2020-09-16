package com.monsieurmahjong.commonjong.game;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class Tileset
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
    }

    public void reset()
    {
        tiles.addAll(drawnTiles);
        drawnTiles.clear();
    }

    public Tile draw()
    {
        int result = (int) (Math.random() * tiles.size());
        Tile drawnTile = tiles.remove(result);
        drawnTiles.add(drawnTile);
        return drawnTile;
    }

    private static List<MahjongTileKind> getTileList()
    {
        return Arrays.asList(MahjongTileKind.values());
    }
}
