package com.monsieurmahjong.commonjong.rules.generic.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TileGroupUtilsTest
{
    @Test
    public void testTileGroupsOf()
    {
        var tileGroups = TileGroupUtils.tileGroupsOf("234s", "77z");
        var expectedTileGroups = new ArrayList<TileGroup>();
        expectedTileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedTileGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));

        assertEquals(expectedTileGroups, tileGroups, "234s and 77z weren't properly grouped together.");
    }

    @Test
    public void testGetTilesFromMPSZNotation()
    {
        var tiles = TileGroupUtils.getTilesFromMPSZNotation("234s");
        var expectedTiles = Arrays.asList(new Tile(MahjongTileKind.BAMBOOS_2), //
                new Tile(MahjongTileKind.BAMBOOS_3), new Tile(MahjongTileKind.BAMBOOS_4));

        assertEquals(expectedTiles, tiles, "234s should give 234s as tiles");
    }

    @Test
    public void testGetTileFromTileGroups()
    {
        testTileSpecificTileGroups("234s77z", "234s", "77z");
        testTileSpecificTileGroups("123456m22345678p", "123m", "456m", "22p", "345p", "678p");
        testTileSpecificTileGroups("223344m777p456s11z", "234m", "234m", "777p", "456s", "11z");
    }

    private static void testTileSpecificTileGroups(String tiles, String... tileGroups)
    {
        var tileGroups1 = TileGroupUtils.tileGroupsOf(tileGroups);
        var tileList1 = TileGroupUtils.getTilesFromTileGroups(tileGroups1);
        Assertions.assertEquals(new MPSZNotation().getTilesFrom(tiles), tileList1);
    }

    @Test
    public void testGetTileGroupFromTiles()
    {
        var bambooRun234 = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var redPair = TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED);
        var mpsz = new MPSZNotation();

        var group = TileGroupUtils.getTileGroupFromTiles(mpsz.getTilesFrom("234s"));
        assertEquals(bambooRun234, group);
        group = TileGroupUtils.getTileGroupFromTiles(mpsz.getTilesFrom("77z"));
        assertEquals(redPair, group);
    }
}
