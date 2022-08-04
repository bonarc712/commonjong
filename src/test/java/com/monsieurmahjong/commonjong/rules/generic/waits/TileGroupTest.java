package com.monsieurmahjong.commonjong.rules.generic.waits;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileGroupTest
{
    @Test
    public void testCollidesWith()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);

        assertTrue(bambooRun123.collidesWith(ryanzouTriplet), "1-2-3 run should collide with 2 triplet.");
        assertFalse(bambooRun123.collidesWith(suuzouTriplet), "1-2-3 run should not collide with 4 triplet.");
    }

    @Test
    public void testIsRun()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertTrue(bambooRun123.isRun(), "123s run is a run");
        assertFalse(ryanzouTriplet.isRun(), "222s triplet is not a run");
        assertFalse(suuzouTriplet.isRun(), "444s triplet is not a run");
        assertFalse(suuzouPair.isRun(), "44s pair is not a run");
        assertFalse(bambooProtogroup34.isRun(), "34s ryanmen is not a run");
        assertFalse(loneRed.isRun(), "7z tile is not a run");
    }

    @Test
    public void testIsTriplet()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouQuad = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertThat(bambooRun123.isTriplet(), is(false));
        assertThat(ryanzouTriplet.isTriplet(), is(true));
        assertThat(suuzouTriplet.isTriplet(), is(true));
        assertThat(suuzouQuad.isTriplet(), is(false));
        assertThat(suuzouPair.isTriplet(), is(false));
        assertThat(bambooProtogroup34.isTriplet(), is(false));
        assertThat(loneRed.isTriplet(), is(false));
    }

    @Test
    public void testIsQuad()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouQuad = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertFalse(bambooRun123.isQuad(), "123s run is not a quad");
        assertFalse(ryanzouTriplet.isQuad(), "222s triplet is not a quad");
        assertFalse(suuzouTriplet.isQuad(), "444s triplet is not a quad");
        assertTrue(suuzouQuad.isQuad(), "4444s quad is a quad");
        assertFalse(suuzouPair.isQuad(), "44s pair is not a quad");
        assertFalse(bambooProtogroup34.isQuad(), "34s ryanmen is not a quad");
        assertFalse(loneRed.isQuad(), "7z tile is not a quad");
    }

    @Test
    public void testIsComplete()
    {
        var bambooRun234 = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var westTriplet = TileGroup.of(MahjongTileKind.WEST, MahjongTileKind.WEST, MahjongTileKind.WEST);
        var bambooRun987 = TileGroup.of(MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_7);
        var iimanTriplet = TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_1);

        assertThat(bambooRun234.isComplete(), is(true));
        assertThat(ryanzouTriplet.isComplete(), is(true));
        assertThat(westTriplet.isComplete(), is(true));
        assertThat(bambooRun987.isComplete(), is(true));
        assertThat(iimanTriplet.isComplete(), is(true));

        var _2p34s = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var _223p = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var _123z = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var _89m1p = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var _224p = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.CIRCLES_2, MahjongTileKind.BAMBOOS_4);

        assertThat(_2p34s.isComplete(), is(false));
        assertThat(_223p.isComplete(), is(false));
        assertThat(_123z.isComplete(), is(false));
        assertThat(_89m1p.isComplete(), is(false));
        assertThat(_224p.isComplete(), is(false));
    }

    @Test
    public void testIsCompleteExclusiveGroup()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouQuad = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertFalse(bambooRun123.isCompleteExclusiveGroup(), "123s run is not a complete exclusive group");
        assertTrue(ryanzouTriplet.isCompleteExclusiveGroup(), "222s triplet is a complete exclusive group");
        assertTrue(suuzouTriplet.isCompleteExclusiveGroup(), "444s triplet is a complete exclusive group");
        assertTrue(suuzouQuad.isCompleteExclusiveGroup(), "4444s quad is a complete exclusive group");
        assertFalse(suuzouPair.isCompleteExclusiveGroup(), "44s pair is not a complete exclusive group");
        assertFalse(bambooProtogroup34.isCompleteExclusiveGroup(), "34s ryanmen is not a complete exclusive group");
        assertFalse(loneRed.isCompleteExclusiveGroup(), "7z tile is not a complete exclusive group");
    }

    @Test
    public void testContains()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertTrue(bambooRun123.contains(MahjongTileKind::isTerminal), "123s contains a terminal");
        assertTrue(bambooRun123.contains(MahjongTileKind::isNonTerminalNumeral), "123s contains a non-terminal numeral");
        assertTrue(bambooRun123.contains(MahjongTileKind::isBamboos), "123s contains a bamboo");
        assertFalse(bambooRun123.contains(MahjongTileKind::isCharacters), "123s does not contain a character");
        assertFalse(bambooRun123.contains(MahjongTileKind::isHonour), "123s does not contain an honour");

        assertTrue(loneRed.contains(MahjongTileKind::isHonour), "7z contains an honour");
        assertFalse(loneRed.contains(MahjongTileKind::isCircles), "7z does not contain a circle");
        assertFalse(loneRed.contains(MahjongTileKind::isTerminal), "7z does not contain a terminal");
    }

    @Test
    public void testGetTileKindsAt()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertEquals(MahjongTileKind.BAMBOOS_2, bambooRun123.getTileKindAt(1), "Second tile kind should be 2 of bamboos");
        assertThrows(IndexOutOfBoundsException.class, () -> loneRed.getTileKindAt(1), "Red has only one tile");
    }

    @Test
    public void testGetTileKinds()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        List<MahjongTileKind> bambooRun123Kinds = new ArrayList<>();
        bambooRun123Kinds.add(MahjongTileKind.BAMBOOS_1);
        bambooRun123Kinds.add(MahjongTileKind.BAMBOOS_2);
        bambooRun123Kinds.add(MahjongTileKind.BAMBOOS_3);

        List<MahjongTileKind> loneRedKinds = new ArrayList<>();
        loneRedKinds.add(MahjongTileKind.RED);

        assertEquals(bambooRun123Kinds, bambooRun123.getTileKinds(), "Tile kinds should be 1, 2 and 3 of bamboos");
        assertEquals(loneRedKinds, loneRed.getTileKinds(), "Tile kinds should be red (only)");
    }

    @Test
    public void testGetTileNumbers()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var bambooTriplet333 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        List<Integer> bambooRun123Numbers = new ArrayList<>();
        bambooRun123Numbers.add(1);
        bambooRun123Numbers.add(2);
        bambooRun123Numbers.add(3);

        List<Integer> bambooTriplet333Numbers = new ArrayList<>();
        bambooTriplet333Numbers.add(3);
        bambooTriplet333Numbers.add(3);
        bambooTriplet333Numbers.add(3);

        assertEquals(bambooRun123Numbers, bambooRun123.getTileNumbers(), "Tile numbers should be 1, 2 and 3");
        assertEquals(bambooTriplet333Numbers, bambooTriplet333.getTileNumbers(), "Tile numbers should be 3, 3 and 3");
        assertThrows(IllegalArgumentException.class, () -> loneRed.getTileNumbers(), "This tile doesn't have an associated number");
    }

    @Test
    public void testToMPSZNotation()
    {
        var bambooRun123 = TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3);
        var ryanzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_2);
        var suuzouTriplet = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouQuad = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var suuzouPair = TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4);
        var bambooProtogroup34 = TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4);
        var loneRed = TileGroup.of(MahjongTileKind.RED);

        assertEquals("123s", bambooRun123.toMPSZNotation(), "123s run notation is incorrect");
        assertEquals("222s", ryanzouTriplet.toMPSZNotation(), "222s triplet notation is incorrect");
        assertEquals("444s", suuzouTriplet.toMPSZNotation(), "444s triplet notation is incorrect");
        assertEquals("4444s", suuzouQuad.toMPSZNotation(), "4444s quad notation is incorrect");
        assertEquals("44s", suuzouPair.toMPSZNotation(), "44s pair notation is incorrect");
        assertEquals("34s", bambooProtogroup34.toMPSZNotation(), "34s ryanmen notation is incorrect");
        assertEquals("7z", loneRed.toMPSZNotation(), "7z tile notation is incorrect");
    }
}
