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
                    if (WaitShapeUtils.isRun(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j)))
                            && !tileGroups.contains(new TileGroup(Arrays.asList(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j))))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)), indexOf(tiles.get(j)));

                        int occurrence = 4;
                        for (int index : runBasedGroup.getIndices())
                        {
                            int currentTileOccurrence = getOccurrencesOfTileIndex(index, tiles);
                            occurrence = Math.min(occurrence, currentTileOccurrence);
                        }

                        for (int k = 0; k < occurrence; k++)
                        {
                            tileGroups.add(runBasedGroup);
                        }

                        runBasedGroup = new TileGroup();

                        int firstTileOccurrence = getOccurrencesOfTileIndex(tiles.get(0).getTileKind().getIndex(), tiles);
                        if (firstTileOccurrence > occurrence) // there might be more protogroups that we could add from that
                        {
                            int secondTileOccurrence = getOccurrencesOfTileIndex(tiles.get(i).getTileKind().getIndex(), tiles);
                            int thirdTileOccurrence = getOccurrencesOfTileIndex(tiles.get(j).getTileKind().getIndex(), tiles);

                            if (secondTileOccurrence > occurrence)
                            {
                                runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)));
                                int protogroupOccurrence = Math.min(firstTileOccurrence - occurrence, secondTileOccurrence - occurrence);
                                for (int k = 0; k < protogroupOccurrence; k++)
                                {
                                    tileGroups.add(runBasedGroup);
                                }
                            }
                            else if (thirdTileOccurrence > occurrence)
                            {
                                runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(j)));
                                int protogroupOccurrence = Math.min(firstTileOccurrence - occurrence, thirdTileOccurrence - occurrence);
                                for (int k = 0; k < protogroupOccurrence; k++)
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

                        int occurrence = 4;
                        for (int index : runBasedGroup.getIndices())
                        {
                            int currentTileOccurrence = getOccurrencesOfTileIndex(index, tiles);
                            occurrence = Math.min(occurrence, currentTileOccurrence);
                        }

                        for (int j = 0; j < occurrence; j++)
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
            MahjongTileKind tileKind = tiles.get(0).getTileKind();

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
