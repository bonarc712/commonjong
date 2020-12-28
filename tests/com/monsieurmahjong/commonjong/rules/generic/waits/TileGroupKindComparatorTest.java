package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;

public class TileGroupKindComparatorTest
{
    @Test
    public void testCompare()
    {
        List<TileGroup> groups = new ArrayList<>();
        groups.addAll(TileGroupUtils.tileGroupsOf("123m", "111m", "11m"));
        groups.sort(new TileGroupKindComparator());

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_1), groups.get(0), "11m is not sorted properly");
        Assertions.assertEquals(TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_1), groups.get(1), "111m is not sorted properly");
        Assertions.assertEquals(TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3), groups.get(2), "123m is not sorted properly");
    }
}
