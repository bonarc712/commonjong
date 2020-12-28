package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

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

        init();
    }

    public WaitShapeEngine(List<Tile> tileList)
    {
        hand = new Hand();
        hand.setTiles(tileList);

        init();
    }

    private void init()
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

    private void computeWait()
    {
        unmeldedTiles = hand.getUnmeldedTiles();

        // get unmelded tiles
        List<Tile> characterTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isCharacters()).collect(Collectors.toList());
        List<Tile> circleTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isCircles()).collect(Collectors.toList());
        List<Tile> bambooTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isBamboos()).collect(Collectors.toList());
        List<Tile> honourTiles = getUnmeldedTiles().stream().filter(tile -> tile.getTileKind().isHonour()).collect(Collectors.toList());

        // do parsing

        tileGroups.addAll(TileParser.parseFamilyTiles(characterTiles));
        tileGroups.addAll(TileParser.parseFamilyTiles(circleTiles));
        tileGroups.addAll(TileParser.parseFamilyTiles(bambooTiles));

        tileGroups.addAll(TileParser.parseHonourTiles(honourTiles));

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

        HandConfigurationParser handParser = new HandConfigurationParser(hand);
        handCombinations = handParser.getHandConfigurations(tileGroups);
    }

    /**
     * @return wait
     */
    private List<MahjongTileKind> buildWait()
    {
        List<MahjongTileKind> tilesToAddToWait = new ArrayList<>();

        for (List<TileGroup> handComposition : handCombinations)
        {
            if (handComposition.size() == 5) // if (handComposition.isTenpai()) then ...
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

        return wait;
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
}
