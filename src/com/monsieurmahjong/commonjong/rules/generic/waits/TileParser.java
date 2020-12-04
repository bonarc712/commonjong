package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.WaitShapeUtils;

/**
 * All parsing methods (for runs, pairs, triplets, etc.) for tiles are here.
 */
public class TileParser
{
    public static List<TileGroup> parseFamilyTiles(List<Tile> tiles)
    {
        List<TileGroup> tileGroups = new ArrayList<>();

        MahjongTileKind previousTileKind = null;

        while (!tiles.isEmpty())
        {
            MahjongTileKind currentTileKind = tiles.get(0).getTileKind();

            if (currentTileKind == previousTileKind)
            {
                // this tile index was already analysed. Continue
                tiles.remove(0);
                continue;
            }

            // check for pairs/triplets
            if (!includedInAnExclusiveGroup(currentTileKind, tileGroups))
            {
                parsePairsAndTriplets(tiles, currentTileKind).ifPresent(tileGroups::add);
            }

            // check for runs
            for (int i = 1; i < tiles.size(); i++)
            {
                TileGroup runBasedGroup = new TileGroup();
                for (int j = i; j < tiles.size(); j++)
                {
                    if (WaitShapeUtils.isRun(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j)));

                        tileGroups.add(runBasedGroup);
                        break;
                    }
                }

                if (runBasedGroup.getIndices().isEmpty() && WaitShapeUtils.isProtogroup(indexOf(tiles.get(0)), indexOf(tiles.get(i))))
                {
                    if (!includedInARunGroup(indexOf(tiles.get(0)), indexOf(tiles.get(i)), tileGroups))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)));
                        tileGroups.add(runBasedGroup);
                    }
                }
            }

            // create a group for that tile
            if (!includedInAGroup(currentTileKind, tileGroups))
            {
                tileGroups.add(parseLoneTiles(currentTileKind));
            }

            tiles.remove(0);

            previousTileKind = currentTileKind;
        }
        return tileGroups;
    }

    public static List<TileGroup> parseHonourTiles(List<Tile> tiles)
    {
        List<TileGroup> tileGroups = new ArrayList<>();

        while (!tiles.isEmpty())
        {
            MahjongTileKind tileKind = tiles.get(0).getTileKind();

            parsePairsAndTriplets(tiles, tileKind).ifPresent(tileGroups::add);

            if (!includedInAGroup(tileKind, tileGroups))
            {
                parseLoneTiles(tileKind);
            }

            tiles.removeIf(tile -> tile.getTileKind() == tileKind);
        }

        return tileGroups;
    }

    protected static Optional<TileGroup> parsePairsAndTriplets(List<Tile> tiles, MahjongTileKind tileKind)
    {
        int sameTileCount = (int) tiles.stream().filter(tile -> tile.getTileKind() == tileKind).count();
        if (sameTileCount > 1)
        {
            Integer[] indices = new Integer[sameTileCount];
            Arrays.fill(indices, tileKind.getIndex());

            TileGroup tileGroup = new TileGroup();
            tileGroup.addAll(indices);
            return Optional.of(tileGroup);
        }
        return Optional.empty();
    }

    protected static TileGroup parseLoneTiles(MahjongTileKind currentTileKind)
    {
        TileGroup loneTile = new TileGroup();
        loneTile.addAll(currentTileKind.getIndex());
        return loneTile;
    }

    protected static boolean includedInAnExclusiveGroup(MahjongTileKind currentTileKind, List<TileGroup> tileGroups)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().get(0) == currentTileKind.getIndex() && group.isExclusiveGroup());
    }

    protected static boolean includedInAGroup(MahjongTileKind currentTileKind, List<TileGroup> tileGroups)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().contains(currentTileKind.getIndex()));
    }

    protected static boolean includedInARunGroup(int first, int second, List<TileGroup> tileGroups)
    {
        return tileGroups.stream().filter(TileGroup::isComplete).anyMatch(group -> group.getIndices().contains(first) && group.getIndices().contains(second));
    }

    protected static int indexOf(Tile tile)
    {
        return tile.getTileKind().getIndex();
    }
}
