package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TileGroupUtils
{
    public static List<TileGroup> tileGroupsOf(String... groups)
    {
        List<TileGroup> tileGroupList = new ArrayList<>();

        for (String group : groups)
        {
            List<Tile> tiles = TileKindUtils.asHand(group);
            List<Integer> tileIndices = tiles.stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList());
            TileGroup tileGroup = new TileGroup(tileIndices);
            tileGroupList.add(tileGroup);
        }

        return tileGroupList;
    }
}
