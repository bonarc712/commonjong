package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TileGroupUtils
{
    public static List<TileGroup> tileGroupsOf(String... groups)
    {
        var tileGroupList = new ArrayList<TileGroup>();

        for (String group : groups)
        {
            var tiles = TileKindUtils.asHand(group);
            List<Integer> tileIndices = tiles.stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList());
            var tileGroup = new TileGroup(tileIndices);
            tileGroupList.add(tileGroup);
        }

        return tileGroupList;
    }

    public static List<Tile> getTilesFromMPSZNotation(String... groups)
    {
        return getTilesFromTileGroups(tileGroupsOf(groups));
    }

    public static List<Tile> getTilesFromTileGroup(TileGroup group)
    {
        return getTilesFromTileGroups(List.of(group));
    }

    public static List<Tile> getTilesFromTileGroups(List<TileGroup> groups)
    {
        var tiles = new ArrayList<Tile>();

        for (TileGroup group : groups)
        {
            for (int index : group.getIndices())
            {
                var tile = new Tile(MahjongTileKind.getKindFromIndex(index));
                tiles.add(tile);
            }
        }
        // order by tile kind index ascending
        tiles.sort((first, second) -> first.getTileKind().getIndex() - second.getTileKind().getIndex());

        return tiles;
    }

    public static TileGroup getTileGroupFromTiles(List<Tile> tiles)
    {
        var group = new TileGroup();
        tiles.stream().map(tile -> tile.getTileKind().getIndex()).forEach(group::add);
        return group;
    }
}
