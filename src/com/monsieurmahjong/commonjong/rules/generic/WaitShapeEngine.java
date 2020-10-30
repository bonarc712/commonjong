package com.monsieurmahjong.commonjong.rules.generic;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.WaitShapeUtils;

public class WaitShapeEngine
{
    private Hand hand;
    private List<Tile> unmeldedTiles;

    // mid-computation variables
    private Set<List<Integer>> potentialRuns;
    private Set<Integer> potentialTriplets;
    private Set<Integer> potentialPairs;

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
        potentialRuns = new HashSet<>();
        potentialTriplets = new HashSet<>();
        potentialPairs = new HashSet<>();

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
        List<Tile> tiles = getUnmeldedTiles();
        for (Integer index : potentialTriplets)
        {
            for (int i = 0; i < 3; i++)
            {
                tiles.remove(tiles.stream().filter(tile -> indexOf(tile) == index).findFirst().get());
            }
        }

        for (List<Integer> run : potentialRuns)
        {
            for (Integer index : run)
            {
                tiles.remove(tiles.stream().filter(tile -> indexOf(tile) == index).findFirst().get());
            }
        }

        if (potentialPairs.size() == 1)
        {
            for (Integer index : potentialPairs)
            {
                for (int i = 0; i < 2; i++)
                {
                    tiles.remove(tiles.stream().filter(tile -> indexOf(tile) == index).findFirst().get());
                }
            }
        }

        // Tanki wait
        if (potentialPairs.size() == 0)
        {
            wait.add(tiles.get(0).getTileKind());
        }

        // TODO : other wait forms
    }

    private List<Tile> getUnmeldedTiles()
    {
        return unmeldedTiles;
    }

    private void parseFamilyTiles(List<Tile> tiles)
    {
        for (int i = 0; i < tiles.size(); i++)
        {
            MahjongTileKind currentTileKind = tiles.get(i).getTileKind();

            // check for pairs and triplets (quads are not checked, they should be melded)
            if (!potentialTriplets.contains(currentTileKind.getIndex()) && !potentialPairs.contains(currentTileKind.getIndex()))
            {
                parsePairsAndTriplets(tiles, currentTileKind);
            }

            // check for runs
            for (int j = i; j < tiles.size(); j++)
            {
                for (int k = j; k < tiles.size(); k++)
                {
                    if (WaitShapeUtils.isRun(indexOf(tiles.get(i)), indexOf(tiles.get(j)), indexOf(tiles.get(k))))
                    {
                        potentialRuns.add(Arrays.asList(indexOf(tiles.get(i)), indexOf(tiles.get(j)), indexOf(tiles.get(k))));
                    }
                }
            }
        }
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
        if (sameTileCount == 2)
        {
            potentialPairs.add(tileKind.getIndex());
        }
        else if (sameTileCount >= 3)
        {
            potentialTriplets.add(tileKind.getIndex());
        }
    }

    private int indexOf(Tile tile)
    {
        return tile.getTileKind().getIndex();
    }
}
