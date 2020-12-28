package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class TileGroupUtilsTest
{
    @Test
    public void testTileGroupsOf()
    {
        List<TileGroup> tileGroups = TileGroupUtils.tileGroupsOf("234s", "77z");
        List<TileGroup> expectedTileGroups = new ArrayList<>();
        expectedTileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedTileGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));

        Assertions.assertEquals(expectedTileGroups, tileGroups, "234s and 77z weren't properly grouped together.");
    }
}
