package com.monsieurmahjong.commonjong.rules.generic.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class MPSZNotationTest
{
    @Test
    public void whenGettingTileGroupFromSmallGroup_thenShouldReturnCorrectTileGroup()
    {
        var mpsz = new MPSZNotation();

        var group = mpsz.getTileGroupFrom("123s");

        assertEquals(MahjongTileKind.BAMBOOS_1, group.getTileKindAt(0));
        assertEquals(MahjongTileKind.BAMBOOS_2, group.getTileKindAt(1));
        assertEquals(MahjongTileKind.BAMBOOS_3, group.getTileKindAt(2));
    }

    @Test
    public void whenGettingTileGroupFromNonAlphanumericCharacter_thenShouldThrowIllegalArgumentException()
    {
        var mpsz = new MPSZNotation();

        assertThrows(IllegalArgumentException.class, () -> mpsz.getTileGroupFrom("123!"));
    }

    @Test
    public void whenGettingSeveralTileGroups_thenShouldReturnAllTileGroups()
    {
        var mpsz = new MPSZNotation();

        var tileGroups = mpsz.getTileGroupsFrom("123s", "11z");

        var group = tileGroups.get(0);
        var group2 = tileGroups.get(1);
        assertEquals(MahjongTileKind.BAMBOOS_1, group.getTileKindAt(0));
        assertEquals(MahjongTileKind.BAMBOOS_2, group.getTileKindAt(1));
        assertEquals(MahjongTileKind.BAMBOOS_3, group.getTileKindAt(2));
        assertEquals(MahjongTileKind.EAST, group2.getTileKindAt(0));
        assertEquals(MahjongTileKind.EAST, group2.getTileKindAt(1));
    }

    @Test
    public void testAsHandString() throws Exception
    {
        var mpsz = new MPSZNotation();
        var hand = mpsz.getTilesFrom("345m345p345s1235z");
        assertTrue(hand.size() == 13);
        var handCopy = new ArrayList<Tile>();
        handCopy.addAll(Arrays.asList(new Tile(MahjongTileKind.CHARACTERS_3), new Tile(MahjongTileKind.CHARACTERS_4), new Tile(MahjongTileKind.CHARACTERS_5), new Tile(MahjongTileKind.CIRCLES_3),
                new Tile(MahjongTileKind.CIRCLES_4), new Tile(MahjongTileKind.CIRCLES_5), new Tile(MahjongTileKind.BAMBOOS_3), new Tile(MahjongTileKind.BAMBOOS_4), new Tile(MahjongTileKind.BAMBOOS_5),
                new Tile(MahjongTileKind.EAST), new Tile(MahjongTileKind.SOUTH), new Tile(MahjongTileKind.WEST), new Tile(MahjongTileKind.WHITE)));
        assertTrue(hand.equals(handCopy));
    }
}
