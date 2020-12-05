package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class TileParserTest
{
    @Test
    public void testParseHonourTiles()
    {
        List<Tile> winds = TileKindUtils.asHand("1223334444z");
        List<TileGroup> windsGroups = TileParser.parseHonourTiles(winds);
        List<TileGroup> expectedWindsGroups = new ArrayList<>();
        expectedWindsGroups.add(tileGroupOf(MahjongTileKind.EAST));
        expectedWindsGroups.add(tileGroupOf(MahjongTileKind.SOUTH, MahjongTileKind.SOUTH));
        expectedWindsGroups.add(tileGroupOf(MahjongTileKind.WEST, MahjongTileKind.WEST, MahjongTileKind.WEST));
        expectedWindsGroups.add(tileGroupOf(MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH));
        Assertions.assertEquals(expectedWindsGroups, windsGroups, "Winds do not work");

        List<Tile> dragons = TileKindUtils.asHand("556677z");
        List<TileGroup> dragonsGroups = TileParser.parseHonourTiles(dragons);
        List<TileGroup> expectedDragonsGroups = new ArrayList<>();
        expectedDragonsGroups.add(tileGroupOf(MahjongTileKind.WHITE, MahjongTileKind.WHITE));
        expectedDragonsGroups.add(tileGroupOf(MahjongTileKind.GREEN, MahjongTileKind.GREEN));
        expectedDragonsGroups.add(tileGroupOf(MahjongTileKind.RED, MahjongTileKind.RED));
        Assertions.assertEquals(expectedDragonsGroups, dragonsGroups, "Winds do not work");
    }

    @Test
    public void testParseFamilyTiles()
    {
        List<Tile> ryanmenShape = TileKindUtils.asHand("34s");
        List<TileGroup> ryanmenShapedProtogroup = TileParser.parseFamilyTiles(ryanmenShape);
        List<TileGroup> expectedRyanmenShapedProtogroup = new ArrayList<>();
        expectedRyanmenShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedRyanmenShapedProtogroup, ryanmenShapedProtogroup, "Ryanmen shape does not work");

        List<Tile> nobetanShape = TileKindUtils.asHand("2345s");
        List<TileGroup> nobetanShapedProtogroup = TileParser.parseFamilyTiles(nobetanShape);
        List<TileGroup> expectedNobetanShapedProtogroup = new ArrayList<>();
        expectedNobetanShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedNobetanShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedNobetanShapedProtogroup, nobetanShapedProtogroup, "Ryanmen shape does not work");

        List<Tile> doubleRyanmenShape = TileKindUtils.asHand("3344s");
        List<TileGroup> doubleRyanmenShapedProtogroup = TileParser.parseFamilyTiles(doubleRyanmenShape);
        List<TileGroup> expectedDoubleRyanmenShapedProtogroup = new ArrayList<>();
        expectedDoubleRyanmenShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedDoubleRyanmenShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(tileGroupOf(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedDoubleRyanmenShapedProtogroup, doubleRyanmenShapedProtogroup, "Ryanmen shape does not work");

        List<Tile> iipeikouShape = TileKindUtils.asHand("334455s");
        List<TileGroup> iipeikouShapedGroups = TileParser.parseFamilyTiles(iipeikouShape);
        List<TileGroup> expectedIipeikouShapedGroups = new ArrayList<>();
        expectedIipeikouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedIipeikouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedIipeikouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedIipeikouShapedGroups, iipeikouShapedGroups, "Iipeikou shape does not work");

        List<Tile> entotsuShape = TileKindUtils.asHand("34555s");
        List<TileGroup> entotsuShapedGroups = TileParser.parseFamilyTiles(entotsuShape);
        List<TileGroup> expectedEntotsuShapedGroups = new ArrayList<>();
        expectedEntotsuShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedEntotsuShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedEntotsuShapedGroups, entotsuShapedGroups, "Entotsu shape does not work");

        List<Tile> sanrenkouShape = TileKindUtils.asHand("333444555s");
        List<TileGroup> sanrenkouShapedGroups = TileParser.parseFamilyTiles(sanrenkouShape);
        List<TileGroup> expectedSanrenkouShapedGroups = new ArrayList<>();
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedSanrenkouShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedSanrenkouShapedGroups, sanrenkouShapedGroups, "Sanrenkou shape does not work");

        List<Tile> chuurenShape = TileKindUtils.asHand("1112345678999s");
        List<TileGroup> chuurenShapedGroups = TileParser.parseFamilyTiles(chuurenShape);
        List<TileGroup> expectedChuurenShapedGroups = new ArrayList<>();
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_1));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_9));
        expectedChuurenShapedGroups.add(tileGroupOf(MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9));
        Assertions.assertEquals(expectedChuurenShapedGroups, chuurenShapedGroups, "Chuuren shape does not work");
    }

    @Test
    public void testParsePairsAndTriplets()
    {
        List<Tile> twoSanZous = TileKindUtils.asHand("33s");
        TileGroup sanZouPair = TileParser.parsePairsAndTriplets(twoSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouPair);

        List<Tile> threeSanZous = TileKindUtils.asHand("333s");
        TileGroup sanZouTriplet = TileParser.parsePairsAndTriplets(threeSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouTriplet);

        List<Tile> fourSanZous = TileKindUtils.asHand("3333s");
        TileGroup sanZouQuad = TileParser.parsePairsAndTriplets(fourSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(tileGroupOf(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouQuad);
    }

    @Test
    public void testParseLoneTiles()
    {
        MahjongTileKind sanZou = MahjongTileKind.BAMBOOS_3;
        TileGroup result = TileParser.parseLoneTiles(sanZou);

        Assertions.assertEquals(tileGroupOf(MahjongTileKind.BAMBOOS_3), result);
    }

    private TileGroup tileGroupOf(MahjongTileKind... tileKinds)
    {
        TileGroup tileGroup = new TileGroup();
        for (MahjongTileKind tileKind : tileKinds)
        {
            int index = tileKind.getIndex();
            tileGroup.add(index);
        }
        return tileGroup;
    }
}
