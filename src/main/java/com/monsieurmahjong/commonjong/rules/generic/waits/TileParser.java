package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            var currentTileKind = tiles.get(0).getTileKind();

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
            for (var i = 1; i < tiles.size(); i++)
            {
                var runBasedGroup = new TileGroup();
                for (var j = i; j < tiles.size(); j++)
                {
                    if (WaitShapeUtils.isRun(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j)))
                            && !tileGroups.contains(new TileGroup(Arrays.asList(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j))))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j)));

                        var occurrence = 4;
                        for (int index : runBasedGroup.getIndices())
                        {
                            var currentTileOccurrence = getOccurrencesOfTileIndex(index, tiles);
                            occurrence = Math.min(occurrence, currentTileOccurrence);
                        }

                        for (var k = 0; k < occurrence; k++)
                        {
                            tileGroups.add(runBasedGroup);
                        }

                        runBasedGroup = new TileGroup();

                        var firstTileOccurrence = getOccurrencesOfTileIndex(tiles.get(0).getTileKind().getIndex(), tiles);
                        if (firstTileOccurrence > occurrence) // there might be more protogroups that we could add from that
                        {
                            var secondTileOccurrence = getOccurrencesOfTileIndex(tiles.get(i).getTileKind().getIndex(), tiles);
                            var thirdTileOccurrence = getOccurrencesOfTileIndex(tiles.get(j).getTileKind().getIndex(), tiles);

                            if (secondTileOccurrence > occurrence)
                            {
                                runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)));
                                var protogroupOccurrence = Math.min(firstTileOccurrence - occurrence, secondTileOccurrence - occurrence);
                                for (var k = 0; k < protogroupOccurrence; k++)
                                {
                                    tileGroups.add(runBasedGroup);
                                }
                            }
                            else if (thirdTileOccurrence > occurrence)
                            {
                                runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(j)));
                                var protogroupOccurrence = Math.min(firstTileOccurrence - occurrence, thirdTileOccurrence - occurrence);
                                for (var k = 0; k < protogroupOccurrence; k++)
                                {
                                    tileGroups.add(runBasedGroup);
                                }
                            }
                        }
                        break;
                    }
                }

                if (runBasedGroup.getIndices().isEmpty() && WaitShapeUtils.isProtogroup(indexOf(tiles.get(0)), indexOf(tiles.get(i))))
                {
                    if (!includedInARunGroup(indexOf(tiles.get(0)), indexOf(tiles.get(i)), tileGroups))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)));

                        var occurrence = 4;
                        for (int index : runBasedGroup.getIndices())
                        {
                            var currentTileOccurrence = getOccurrencesOfTileIndex(index, tiles);
                            occurrence = Math.min(occurrence, currentTileOccurrence);
                        }

                        for (var j = 0; j < occurrence; j++)
                        {
                            tileGroups.add(runBasedGroup);
                        }
                        break;
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

    private static int getOccurrencesOfTileIndex(int index, List<Tile> tiles)
    {
        return (int) tiles.stream().filter(tile -> tile.getTileKind().getIndex() == index).count();
    }

    public static List<TileGroup> parseHonourTiles(List<Tile> tiles)
    {
        List<TileGroup> tileGroups = new ArrayList<>();

        while (!tiles.isEmpty())
        {
            var tileKind = tiles.get(0).getTileKind();

            parsePairsAndTriplets(tiles, tileKind).ifPresent(tileGroups::add);

            if (!includedInAGroup(tileKind, tileGroups))
            {
                tileGroups.add(parseLoneTiles(tileKind));
            }

            tiles.removeIf(tile -> tile.getTileKind() == tileKind);
        }

        return tileGroups;
    }

    protected static Optional<TileGroup> parsePairsAndTriplets(List<Tile> tiles, MahjongTileKind tileKind)
    {
        var sameTileCount = (int) tiles.stream().filter(tile -> tile.getTileKind() == tileKind).count();
        if (sameTileCount > 1)
        {
            var indices = new Integer[sameTileCount];
            Arrays.fill(indices, tileKind.getIndex());

            var tileGroup = new TileGroup();
            tileGroup.addAll(indices);
            return Optional.of(tileGroup);
        }
        return Optional.empty();
    }

    protected static TileGroup parseLoneTiles(MahjongTileKind currentTileKind)
    {
        var loneTile = new TileGroup();
        loneTile.addAll(currentTileKind.getIndex());
        return loneTile;
    }

    protected static boolean includedInAnExclusiveGroup(MahjongTileKind currentTileKind, List<TileGroup> tileGroups)
    {
        return tileGroups.stream().anyMatch(group -> group.getTileKindAt(0) == currentTileKind && group.isExclusiveGroup());
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
