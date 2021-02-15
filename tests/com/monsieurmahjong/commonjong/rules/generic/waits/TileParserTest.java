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
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.EAST));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.SOUTH, MahjongTileKind.SOUTH));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.WEST, MahjongTileKind.WEST, MahjongTileKind.WEST));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH));
        Assertions.assertEquals(expectedWindsGroups, windsGroups, "Winds do not work");

        List<Tile> dragons = TileKindUtils.asHand("556677z");
        List<TileGroup> dragonsGroups = TileParser.parseHonourTiles(dragons);
        List<TileGroup> expectedDragonsGroups = new ArrayList<>();
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.WHITE, MahjongTileKind.WHITE));
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.GREEN, MahjongTileKind.GREEN));
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));
        Assertions.assertEquals(expectedDragonsGroups, dragonsGroups, "Winds do not work");
    }

    @Test
    public void testParseFamilyTiles()
    {
        List<Tile> ryanmenShape = TileKindUtils.asHand("34s");
        List<TileGroup> ryanmenShapedProtogroup = TileParser.parseFamilyTiles(ryanmenShape);
        List<TileGroup> expectedRyanmenShapedProtogroup = new ArrayList<>();
        expectedRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedRyanmenShapedProtogroup, ryanmenShapedProtogroup, "Ryanmen shape does not work");

        List<Tile> nobetanShape = TileKindUtils.asHand("2345s");
        List<TileGroup> nobetanShapedProtogroup = TileParser.parseFamilyTiles(nobetanShape);
        List<TileGroup> expectedNobetanShapedProtogroup = new ArrayList<>();
        expectedNobetanShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedNobetanShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedNobetanShapedProtogroup, nobetanShapedProtogroup, "Nobetan shape does not work");

        List<Tile> doubleRyanmenShape = TileKindUtils.asHand("3344s");
        List<TileGroup> doubleRyanmenShapedProtogroup = TileParser.parseFamilyTiles(doubleRyanmenShape);
        List<TileGroup> expectedDoubleRyanmenShapedProtogroup = new ArrayList<>();
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedDoubleRyanmenShapedProtogroup, doubleRyanmenShapedProtogroup, "Double ryanmen shape does not work");

        List<Tile> iipeikouShape = TileKindUtils.asHand("334455s");
        List<TileGroup> iipeikouShapedGroups = TileParser.parseFamilyTiles(iipeikouShape);
        List<TileGroup> expectedIipeikouShapedGroups = new ArrayList<>();
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedIipeikouShapedGroups, iipeikouShapedGroups, "Iipeikou shape does not work");

        List<Tile> iipeikouKanchanWait = TileKindUtils.asHand("33455s");
        List<TileGroup> iipeikouKanchanGroups = TileParser.parseFamilyTiles(iipeikouKanchanWait);
        List<TileGroup> expectedIipeikouKanchanGroups = new ArrayList<>();
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedIipeikouKanchanGroups, iipeikouKanchanGroups, "Iipeikou kanchan-wait shape does not work");

        List<Tile> tatsumakiShape = TileKindUtils.asHand("3334555s");
        List<TileGroup> tatsumakiShapedGroups = TileParser.parseFamilyTiles(tatsumakiShape);
        List<TileGroup> expectedTatsumakiShapedGroups = new ArrayList<>();
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedTatsumakiShapedGroups, tatsumakiShapedGroups, "Tatsumaki shape does not work");

        List<Tile> entotsuShape = TileKindUtils.asHand("34555s");
        List<TileGroup> entotsuShapedGroups = TileParser.parseFamilyTiles(entotsuShape);
        List<TileGroup> expectedEntotsuShapedGroups = new ArrayList<>();
        expectedEntotsuShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedEntotsuShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedEntotsuShapedGroups, entotsuShapedGroups, "Entotsu shape does not work");

        List<Tile> sanrenkouShape = TileKindUtils.asHand("333444555s");
        List<TileGroup> sanrenkouShapedGroups = TileParser.parseFamilyTiles(sanrenkouShape);
        List<TileGroup> expectedSanrenkouShapedGroups = new ArrayList<>();
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedSanrenkouShapedGroups, sanrenkouShapedGroups, "Sanrenkou shape does not work");

        List<Tile> chuurenShape = TileKindUtils.asHand("1112345678999s");
        List<TileGroup> chuurenShapedGroups = TileParser.parseFamilyTiles(chuurenShape);
        List<TileGroup> expectedChuurenShapedGroups = new ArrayList<>();
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_1));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_9));
        expectedChuurenShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9));
        Assertions.assertEquals(expectedChuurenShapedGroups, chuurenShapedGroups, "Chuuren shape does not work");

        List<Tile> customShape1 = TileKindUtils.asHand("135567s");
        List<TileGroup> customShape1Groups = TileParser.parseFamilyTiles(customShape1);
        List<TileGroup> expectedCustomShape1Groups = new ArrayList<>();
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        Assertions.assertEquals(expectedCustomShape1Groups, customShape1Groups, "Custom shape 135567s does not work");

        List<Tile> customShape2 = TileKindUtils.asHand("566799s");
        List<TileGroup> customShape2Groups = TileParser.parseFamilyTiles(customShape2);
        List<TileGroup> expectedCustomShape2Groups = new ArrayList<>();
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_6));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_9));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9));
        Assertions.assertEquals(expectedCustomShape2Groups, customShape2Groups, "Custom shape 566799s does not work");
    }

    @Test
    public void testParsePairsAndTriplets()
    {
        List<Tile> twoSanZous = TileKindUtils.asHand("33s");
        TileGroup sanZouPair = TileParser.parsePairsAndTriplets(twoSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouPair);

        List<Tile> threeSanZous = TileKindUtils.asHand("333s");
        TileGroup sanZouTriplet = TileParser.parsePairsAndTriplets(threeSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouTriplet);

        List<Tile> fourSanZous = TileKindUtils.asHand("3333s");
        TileGroup sanZouQuad = TileParser.parsePairsAndTriplets(fourSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouQuad);
    }

    @Test
    public void testParseLoneTiles()
    {
        MahjongTileKind sanZou = MahjongTileKind.BAMBOOS_3;
        TileGroup result = TileParser.parseLoneTiles(sanZou);

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3), result);
    }
}
