package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongShantenCounter;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKindComparator;
import com.monsieurmahjong.commonjong.rules.generic.waits.parsing.HandConfigurationParser;
import com.monsieurmahjong.commonjong.rules.generic.waits.parsing.TileParser;

public class WaitShapeEngine
{
    private Hand hand;
    private List<Tile> unmeldedTiles;

    private List<TileGroup> tileGroups;
    // Hand combinations are different ways in which the tiles can be ordered to
    // account for all hand possibilities
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

    public int getShanten() throws IllegalStateException
    {
        if (handCombinations.isEmpty())
        {
            throw new IllegalStateException("The combinations need to be computed in order to determine shanten");
        }

        var minimumShanten = Integer.MAX_VALUE;
        for (List<TileGroup> combination : handCombinations)
        {
            var currentShanten = new MahjongShantenCounter(combination).countShanten();
            if (currentShanten < minimumShanten)
            {
                minimumShanten = currentShanten;
            }
        }

        return minimumShanten;
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

        // create possible hands (so we can differ between triplet in run in cases like
        // 34555)
        createHandsCombinations();

        // interpret wait from parsing
        buildWait();

        // remove doubles and sort
        wait = wait.stream().distinct().collect(Collectors.toList());
        wait.sort(new MahjongTileKindComparator());
    }

    private void createHandsCombinations()
    {
        // for now add just tileGroups directly (remove when combinations are good)
        handCombinations.add(tileGroups);

        var handParser = new HandConfigurationParser(hand);
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
                var completeGroupCount = (int) handComposition.stream().filter(TileGroup::isComplete).count();

                if (completeGroupCount == 4)
                {
                    if (handComposition.stream().filter(group -> group.getIndices().size() == 1).count() == 1)
                    {
                        // build pair (tanki) wait
                        var loneTile = handComposition.stream().filter(group -> group.getIndices().size() == 1).findFirst().get();
                        addTilesToWait(tilesToAddToWait, loneTile.getWaitingTiles());
                    }
                }
                if (completeGroupCount == 3)
                {
                    if (handComposition.stream().filter(group -> group.getIndices().size() == 2).count() == 2 && handComposition.stream().anyMatch(TileGroup::isPair))
                    {
                        List<TileGroup> incompleteTileGroups = handComposition.stream().filter(group -> group.getIndices().size() == 2).collect(Collectors.toList());

                        var protogroup = incompleteTileGroups.stream().filter(TileGroup::isProtogroup).findFirst();
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

        // no combinations were tenpai, the "wait" are the tiles that improve the hand
        // instead
        if (wait.isEmpty())
        {
            wait.addAll(improvingTiles);
        }

        return wait;
    }

    private void addTilesToWait(List<MahjongTileKind> waitList, List<Integer> indicesToAddToWaitList)
    {
        indicesToAddToWaitList.forEach(index -> {
            var kind = MahjongTileKind.getKindFromIndex(index);
            waitList.add(kind);
        });
    }

    private List<Tile> getUnmeldedTiles()
    {
        return unmeldedTiles;
    }
}
