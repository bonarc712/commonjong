package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class TileParserTest
{
    @Test
    public void testParseHonourTiles()
    {
        var winds = TileKindUtils.asHand("1223334444z");
        var windsGroups = TileParser.parseHonourTiles(winds);
        var expectedWindsGroups = new ArrayList<TileGroup>();
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.EAST));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.SOUTH, MahjongTileKind.SOUTH));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.WEST, MahjongTileKind.WEST, MahjongTileKind.WEST));
        expectedWindsGroups.add(TileGroup.of(MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH));
        Assertions.assertEquals(expectedWindsGroups, windsGroups, "Winds do not work");

        var dragons = TileKindUtils.asHand("556677z");
        var dragonsGroups = TileParser.parseHonourTiles(dragons);
        var expectedDragonsGroups = new ArrayList<TileGroup>();
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.WHITE, MahjongTileKind.WHITE));
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.GREEN, MahjongTileKind.GREEN));
        expectedDragonsGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));
        Assertions.assertEquals(expectedDragonsGroups, dragonsGroups, "Winds do not work");
    }

    @Test
    public void testParseFamilyTiles()
    {
        var ryanmenShape = TileKindUtils.asHand("34s");
        var ryanmenShapedProtogroup = TileParser.parseFamilyTiles(ryanmenShape);
        var expectedRyanmenShapedProtogroup = new ArrayList<TileGroup>();
        expectedRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedRyanmenShapedProtogroup, ryanmenShapedProtogroup, "Ryanmen shape does not work");

        var nobetanShape = TileKindUtils.asHand("2345s");
        var nobetanShapedProtogroup = TileParser.parseFamilyTiles(nobetanShape);
        var expectedNobetanShapedProtogroup = new ArrayList<TileGroup>();
        expectedNobetanShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedNobetanShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedNobetanShapedProtogroup, nobetanShapedProtogroup, "Nobetan shape does not work");

        var doubleRyanmenShape = TileKindUtils.asHand("3344s");
        var doubleRyanmenShapedProtogroup = TileParser.parseFamilyTiles(doubleRyanmenShape);
        var expectedDoubleRyanmenShapedProtogroup = new ArrayList<TileGroup>();
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        expectedDoubleRyanmenShapedProtogroup.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        Assertions.assertEquals(expectedDoubleRyanmenShapedProtogroup, doubleRyanmenShapedProtogroup, "Double ryanmen shape does not work");

        var iipeikouShape = TileKindUtils.asHand("334455s");
        var iipeikouShapedGroups = TileParser.parseFamilyTiles(iipeikouShape);
        var expectedIipeikouShapedGroups = new ArrayList<TileGroup>();
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedIipeikouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedIipeikouShapedGroups, iipeikouShapedGroups, "Iipeikou shape does not work");

        var iipeikouKanchanWait = TileKindUtils.asHand("33455s");
        var iipeikouKanchanGroups = TileParser.parseFamilyTiles(iipeikouKanchanWait);
        var expectedIipeikouKanchanGroups = new ArrayList<TileGroup>();
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedIipeikouKanchanGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedIipeikouKanchanGroups, iipeikouKanchanGroups, "Iipeikou kanchan-wait shape does not work");

        var tatsumakiShape = TileKindUtils.asHand("3334555s");
        var tatsumakiShapedGroups = TileParser.parseFamilyTiles(tatsumakiShape);
        var expectedTatsumakiShapedGroups = new ArrayList<TileGroup>();
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedTatsumakiShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedTatsumakiShapedGroups, tatsumakiShapedGroups, "Tatsumaki shape does not work");

        var entotsuShape = TileKindUtils.asHand("34555s");
        var entotsuShapedGroups = TileParser.parseFamilyTiles(entotsuShape);
        var expectedEntotsuShapedGroups = new ArrayList<TileGroup>();
        expectedEntotsuShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedEntotsuShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedEntotsuShapedGroups, entotsuShapedGroups, "Entotsu shape does not work");

        var sanrenkouShape = TileKindUtils.asHand("333444555s");
        var sanrenkouShapedGroups = TileParser.parseFamilyTiles(sanrenkouShape);
        var expectedSanrenkouShapedGroups = new ArrayList<TileGroup>();
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_4));
        expectedSanrenkouShapedGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        Assertions.assertEquals(expectedSanrenkouShapedGroups, sanrenkouShapedGroups, "Sanrenkou shape does not work");

        var chuurenShape = TileKindUtils.asHand("1112345678999s");
        var chuurenShapedGroups = TileParser.parseFamilyTiles(chuurenShape);
        var expectedChuurenShapedGroups = new ArrayList<TileGroup>();
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

        var customShape1 = TileKindUtils.asHand("135567s");
        var customShape1Groups = TileParser.parseFamilyTiles(customShape1);
        var expectedCustomShape1Groups = new ArrayList<TileGroup>();
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        expectedCustomShape1Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        Assertions.assertEquals(expectedCustomShape1Groups, customShape1Groups, "Custom shape 135567s does not work");

        var customShape2 = TileKindUtils.asHand("566799s");
        var customShape2Groups = TileParser.parseFamilyTiles(customShape2);
        var expectedCustomShape2Groups = new ArrayList<TileGroup>();
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_6));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_9));
        expectedCustomShape2Groups.add(TileGroup.of(MahjongTileKind.BAMBOOS_9, MahjongTileKind.BAMBOOS_9));
        Assertions.assertEquals(expectedCustomShape2Groups, customShape2Groups, "Custom shape 566799s does not work");
    }

    @Test
    public void testParsePairsAndTriplets()
    {
        var twoSanZous = TileKindUtils.asHand("33s");
        var sanZouPair = TileParser.parsePairsAndTriplets(twoSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouPair);

        var threeSanZous = TileKindUtils.asHand("333s");
        var sanZouTriplet = TileParser.parsePairsAndTriplets(threeSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouTriplet);

        var fourSanZous = TileKindUtils.asHand("3333s");
        var sanZouQuad = TileParser.parsePairsAndTriplets(fourSanZous, MahjongTileKind.BAMBOOS_3).get();

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_3), sanZouQuad);
    }

    @Test
    public void testParseLoneTiles()
    {
        var sanZou = MahjongTileKind.BAMBOOS_3;
        var result = TileParser.parseLoneTiles(sanZou);

        Assertions.assertEquals(TileGroup.of(MahjongTileKind.BAMBOOS_3), result);
    }
}
