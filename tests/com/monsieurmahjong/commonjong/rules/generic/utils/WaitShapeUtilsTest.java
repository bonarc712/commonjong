package com.monsieurmahjong.commonjong.rules.generic.utils;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class WaitShapeUtilsTest
{
    @Test
    public void isGroupTest()
    {
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_9.ordinal(), MahjongTileKind.BAMBOOS_8.ordinal(), MahjongTileKind.BAMBOOS_7.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_1.ordinal()));

        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_3.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.EAST.ordinal(), MahjongTileKind.SOUTH.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_8.ordinal(), MahjongTileKind.CHARACTERS_9.ordinal(), MahjongTileKind.CIRCLES_1.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_4.ordinal()));
    }

    @Test
    public void isPairTest()
    {
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.CIRCLES_3.ordinal()));

        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.CIRCLES_4.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WHITE.ordinal()));
    }

    @Test
    public void isProtogroupTest()
    {
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.CHARACTERS_8.ordinal(), MahjongTileKind.CHARACTERS_9.ordinal()));

        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.CIRCLES_9.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.WEST.ordinal(), MahjongTileKind.SOUTH.ordinal()));
    }
}
