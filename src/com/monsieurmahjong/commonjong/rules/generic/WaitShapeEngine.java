package com.monsieurmahjong.commonjong.rules.generic;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;

public class WaitShapeEngine
{
    private Hand hand;
    private List<Tile> unmeldedTiles;

    private List<MahjongTileKind> wait;

    public WaitShapeEngine(Hand hand)
    {
        this.hand = hand;
    }

    public WaitShapeEngine(List<Tile> tileList)
    {
        hand = new Hand();
        hand.setTiles(tileList);
    }

    private List<Tile> getUnmeldedTiles()
    {
        if (unmeldedTiles == null)
        {
            List<Tile> allTiles = new ArrayList<>();
            allTiles.addAll(hand.getTiles());
            List<Tile> meldedTiles = hand.getMelds().stream().flatMap(List::stream).collect(Collectors.toList());

            meldedTiles.forEach(tile -> allTiles.remove(tile));

            unmeldedTiles = allTiles;
        }
        return unmeldedTiles;
    }

    public List<MahjongTileKind> getWait()
    {
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

        return wait;
    }

    private void parseFamilyTiles(List<Tile> tiles)
    {
        // TODO make this work!
    }

    private void parseHonourTiles(List<Tile> tiles)
    {
        while (!tiles.isEmpty())
        {
            MahjongTileKind tileKind = tiles.get(0).getTileKind();

            int sameTileCount = (int) tiles.stream().filter(tile -> tile.getTileKind() == tileKind).count();
            if (sameTileCount <= 2)
            {
                wait.add(tileKind);
            }

            tiles.removeIf(tile -> tile.getTileKind() == tileKind);
        }
    }
}
