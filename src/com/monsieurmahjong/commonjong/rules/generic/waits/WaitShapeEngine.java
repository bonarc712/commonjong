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

    private List<TileGroup> tileGroups;
    // Hand combinations are different ways in which the tiles can be ordered to account for all hand possibilities
    private List<List<TileGroup>> handCombinations;

    private List<MahjongTileKind> wait;
    private List<MahjongTileKind> improvingTiles;

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
        tileGroups = new ArrayList<>();
        handCombinations = new ArrayList<>();

        wait = new ArrayList<>();
        improvingTiles = new ArrayList<>();

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

        // create possible hands (so we can differ between triplet in run in cases like 34555)
        createHandsCombinations();

        // interpret wait from parsing
        buildWait();

        // remove doubles
        wait = wait.stream().distinct().collect(Collectors.toList());
    }

    private void createHandsCombinations()
    {
        // for now add just tileGroups directly (remove when combinations are good)
        handCombinations.add(tileGroups);

        // first detect all groups that have collisions with each other 
        List<List<TileGroup>> collisionList = new ArrayList<>();
        System.out.println("There are " + tileGroups.size() + " tile groups");
        for (TileGroup group : tileGroups)
        {
            System.out.println(group);
            // be sure to include composite collisions (a collides with b which collides with c means that a, b and c are part of the same collision group)
        }

        // then dissect these groups to see the different possible pairings

        // create hand combinations as different ways to look at the hand, with the different pairings
        {
            // if a tilegroup is not in any collision list, just add it directly

            // else create a new combination for each possible pairing
        }
    }

    private void buildWait()
    {
        List<MahjongTileKind> tilesToAddToWait = new ArrayList<>();

        for (List<TileGroup> handComposition : handCombinations)
        {
            if (handComposition.size() == 5)
            {
                int completeGroupCount = (int) handComposition.stream().filter(TileGroup::isComplete).count();

                if (completeGroupCount == 4)
                {
                    if (handComposition.stream().filter(group -> group.getIndices().size() == 1).count() == 1)
                    {
                        // build pair (tanki) wait
                        TileGroup loneTile = handComposition.stream().filter(group -> group.getIndices().size() == 1).findFirst().get();
                        addTilesToWait(tilesToAddToWait, loneTile.getWaitingTiles());
                    }
                }
                if (completeGroupCount == 3)
                {
                    if (handComposition.stream().filter(group -> group.getIndices().size() == 2).count() == 2 && handComposition.stream().anyMatch(TileGroup::isPair))
                    {
                        List<TileGroup> incompleteTileGroups = handComposition.stream().filter(group -> group.getIndices().size() == 2).collect(Collectors.toList());

                        Optional<TileGroup> protogroup = incompleteTileGroups.stream().filter(TileGroup::isProtogroup).findFirst();
                        if (protogroup.isPresent())
                        {
                            // build protogroup-based (ryanmen/kanchan/penchan) wait
                            addTilesToWait(tilesToAddToWait, protogroup.get().getWaitingTiles());
                        }
                        else
                        {
                            // build double pair (shanpon) wait
                            incompleteTileGroups.forEach(group -> {
                                addTilesToWait(tilesToAddToWait, group.getWaitingTiles());
                            });
                        }
                    }
                }
            }

            if (tilesToAddToWait.isEmpty())
            {
                // Hand combination is not tenpai, add improving tiles instead
                List<TileGroup> incompleteTileGroups = handComposition.stream().filter(group -> !group.isComplete()).collect(Collectors.toList());
                incompleteTileGroups.forEach(group -> {
                    addTilesToWait(improvingTiles, group.getImprovingTiles());
                });
            }

            wait.addAll(tilesToAddToWait);
            tilesToAddToWait.clear();
        }

        // no combinations were tenpai, the "wait" are the tiles that improve the hand instead
        if (wait.isEmpty())
        {
            wait.addAll(improvingTiles);
        }
    }

    private void addTilesToWait(List<MahjongTileKind> waitList, List<Integer> indicesToAddToWaitList)
    {
        indicesToAddToWaitList.forEach(index -> {
            MahjongTileKind kind = TileKindUtils.getKindFromIndex(index);
            waitList.add(kind);
        });
    }

    private List<Tile> getUnmeldedTiles()
    {
        return unmeldedTiles;
    }

    private void parseFamilyTiles(List<Tile> tiles)
    {
        while (!tiles.isEmpty())
        {
            MahjongTileKind currentTileKind = tiles.get(0).getTileKind();

            if (!includedInAnExclusiveGroup(currentTileKind))
            {
                parsePairsAndTriplets(tiles, currentTileKind);
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
                    }
                }

                if (WaitShapeUtils.isProtogroup(indexOf(tiles.get(0)), indexOf(tiles.get(i))))
                {
                    if (!includedInARunGroup(indexOf(tiles.get(0)), indexOf(tiles.get(i))))
                    {
                        runBasedGroup.addAll(indexOf(tiles.get(0)), indexOf(tiles.get(i)));
                        tileGroups.add(runBasedGroup);
                    }
                }
            }

            // create a group for that tile
            parseLoneTiles(currentTileKind);

            tiles.remove(0);
        }
    }

    private boolean includedInAnExclusiveGroup(MahjongTileKind currentTileKind)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().get(0) == currentTileKind.getIndex() && group.isExclusiveGroup());
    }

    private boolean includedInAGroup(MahjongTileKind currentTileKind)
    {
        return tileGroups.stream().anyMatch(group -> group.getIndices().contains(currentTileKind.getIndex()));
    }

    private boolean includedInARunGroup(int first, int second)
    {
        return tileGroups.stream().filter(TileGroup::isComplete).anyMatch(group -> group.getIndices().contains(first) && group.getIndices().contains(second));
    }

    private void parseHonourTiles(List<Tile> tiles)
    {
        while (!tiles.isEmpty())
        {
            MahjongTileKind tileKind = tiles.get(0).getTileKind();

            parsePairsAndTriplets(tiles, tileKind);
            parseLoneTiles(tileKind);

            tiles.removeIf(tile -> tile.getTileKind() == tileKind);
        }
    }

    private void parsePairsAndTriplets(List<Tile> tiles, MahjongTileKind tileKind)
    {
        int sameTileCount = (int) tiles.stream().filter(tile -> tile.getTileKind() == tileKind).count();
        if (sameTileCount > 1)
        {
            Integer[] indices = new Integer[sameTileCount];
            Arrays.fill(indices, tileKind.getIndex());

            TileGroup tileGroup = new TileGroup();
            tileGroup.addAll(indices);
            tileGroups.add(tileGroup);
        }
    }

    private void parseLoneTiles(MahjongTileKind currentTileKind)
    {
        if (!includedInAGroup(currentTileKind))
        {
            TileGroup loneTile = new TileGroup();
            loneTile.addAll(currentTileKind.getIndex());
            tileGroups.add(loneTile);
        }
    }

    private int indexOf(Tile tile)
    {
        return tile.getTileKind().getIndex();
    }
}
