package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.*;

public class WaitShapeEngine
{
    private Hand hand;
    private List<Tile> unmeldedTiles;

    // mid-computation variables [needed?]
    //    private Set<List<Integer>> potentialRuns;
    //    private Set<Integer> potentialTriplets;
    //    private Set<Integer> pairs;
    //    private Set<List<Integer>> protogroups;
    private List<TileGroup> tileGroups;

    private List<MahjongTileKind> wait;

    public WaitShapeEngine(Hand hand)
    {
        this.hand = hand;

        mInit();
    }

    public WaitShapeEngine(List<Tile> tileList)
    {
        hand = new Hand();
        hand.setTiles(tileList);

        mInit();
    }

    private void mInit()
    {
        //        potentialRuns = new HashSet<>();
        //        potentialTriplets = new HashSet<>();
        //        pairs = new HashSet<>();
        //        protogroups = new HashSet<>();
        tileGroups = new ArrayList<>();

        computeWait();
    }

    public List<MahjongTileKind> getWait()
    {
        return wait;
    }

    private void computeUnmeldedTiles()
    {
        List<Tile> allTiles = new ArrayList<>();
        allTiles.addAll(hand.getTiles());
        List<Tile> meldedTiles = hand.getMelds().stream().flatMap(List::stream).collect(Collectors.toList());

        meldedTiles.forEach(tile -> allTiles.remove(tile));

        unmeldedTiles = allTiles;
    }

    private void computeWait()
    {
        computeUnmeldedTiles();

        wait = new ArrayList<>();

        // get unmelded tiles
        List<Tile> characterTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isCharacters()).collect(Collectors.toList());
        List<Tile> circleTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isCircles()).collect(Collectors.toList());
        List<Tile> bambooTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isBamboos()).collect(Collectors.toList());
        List<Tile> honourTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isHonour()).collect(Collectors.toList());

        // do parsing

        parseFamilyTiles(characterTiles);
        parseFamilyTiles(circleTiles);
        parseFamilyTiles(bambooTiles);

        parseHonourTiles(honourTiles);

        // TODO : create possible hands (so we can differ between triplet in run in cases like 34555)

        // interpret wait from parsing
        buildWait();
    }

    private void buildWait()
    {
        for (TileGroup tileGroup : tileGroups)
        {
            tileGroup.getWaitingTiles().forEach(index -> {
                MahjongTileKind kind = TileKindUtils.getKindFromIndex(index);
                wait.add(kind);
            });
        }
    }

    private List<Tile> getUnmeldedTiles()
    {
        return unmeldedTiles;
    }

    private void parseFamilyTiles(List<Tile> tiles)
    {
        int i = 0;
        while (!tiles.isEmpty())
        {
            MahjongTileKind currentTileKind = tiles.get(i).getTileKind();

            if (!includedInAnExclusiveGroup(currentTileKind))
            {
                parsePairsAndTriplets(tiles, currentTileKind);
            }

            // check for runs
            for (int j = i; j < tiles.size(); j++)
            {
                TileGroup runBasedGroup = new TileGroup();
                for (int k = j; k < tiles.size(); k++)
                {
                    if (WaitShapeUtils.isRun(indexOf(tiles.get(i)), indexOf(tiles.get(j)), indexOf(tiles.get(k))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(i)), indexOf(tiles.get(j)), indexOf(tiles.get(k)));
                        tileGroups.add(runBasedGroup);
                    }
                }

                if (runBasedGroup.getIndices().isEmpty())
                {
                    if (WaitShapeUtils.isProtogroup(indexOf(tiles.get(i)), indexOf(tiles.get(j))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(i)), indexOf(tiles.get(j)));
                    }
                }
            }

            // create a group for that tile
            if (!includedInAGroup(currentTileKind))
            {
                TileGroup loneTile = new TileGroup();
                loneTile.addAll(currentTileKind.getIndex());
                tileGroups.add(loneTile);
            }

            tiles.remove(0);
        }
    }

    private boolean includedInAnExclusiveGroup(MahjongTileKind pCurrentTileKind)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().get(0) == pCurrentTileKind.getIndex() && group.isExclusiveGroup());
    }

    private boolean includedInAGroup(MahjongTileKind pCurrentTileKind)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().contains(pCurrentTileKind.getIndex()));
    }

    private void parseHonourTiles(List<Tile> tiles)
    {
        while (!tiles.isEmpty())
        {
            MahjongTileKind tileKind = tiles.get(0).getTileKind();

            parsePairsAndTriplets(tiles, tileKind);

            tiles.removeIf(tile -> tile.getTileKind() == tileKind);
        }
    }

    private void parsePairsAndTriplets(List<Tile> tiles, MahjongTileKind tileKind)
    {
        int sameTileCount = (int) tiles.stream().filter(tile -> tile.getTileKind() == tileKind).count();
        Integer[] indices = new Integer[sameTileCount];
        Arrays.fill(indices, tileKind.getIndex());

        TileGroup tileGroup = new TileGroup();
        tileGroup.addAll(indices);
        tileGroups.add(tileGroup);
    }

    private int indexOf(Tile tile)
    {
        return tile.getTileKind().getIndex();
    }
}
