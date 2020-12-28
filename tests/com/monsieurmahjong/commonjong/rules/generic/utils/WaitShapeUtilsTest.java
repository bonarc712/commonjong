package com.monsieurmahjong.commonjong.rules.generic.utils;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class WaitShapeUtilsTest
{
    @Test
    public void testIsGroup()
    {
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_9.ordinal(), MahjongTileKind.BAMBOOS_8.ordinal(), MahjongTileKind.BAMBOOS_7.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_1.ordinal(), MahjongTileKind.CHARACTERS_1.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.WEST.getIndex(), MahjongTileKind.WEST.getIndex(), MahjongTileKind.WEST.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.BAMBOOS_9.getIndex(), MahjongTileKind.BAMBOOS_8.getIndex(), MahjongTileKind.BAMBOOS_7.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_1.getIndex(), MahjongTileKind.CHARACTERS_1.getIndex(), MahjongTileKind.CHARACTERS_1.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_3.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.EAST.ordinal(), MahjongTileKind.SOUTH.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_8.ordinal(), MahjongTileKind.CHARACTERS_9.ordinal(), MahjongTileKind.CIRCLES_1.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_2.ordinal(), MahjongTileKind.CIRCLES_4.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.CIRCLES_3.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.EAST.getIndex(), MahjongTileKind.SOUTH.getIndex(), MahjongTileKind.WEST.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CHARACTERS_8.getIndex(), MahjongTileKind.CHARACTERS_9.getIndex(), MahjongTileKind.CIRCLES_1.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isGroup(MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.CIRCLES_4.getIndex()));
    }

    @Test
    public void testIsPair()
    {
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WEST.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.CIRCLES_3.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.WEST.getIndex(), MahjongTileKind.WEST.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.getIndex(), MahjongTileKind.CIRCLES_3.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.ordinal(), MahjongTileKind.CIRCLES_4.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.WEST.ordinal(), MahjongTileKind.WHITE.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.CIRCLES_3.getIndex(), MahjongTileKind.CIRCLES_4.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isPair(MahjongTileKind.WEST.getIndex(), MahjongTileKind.WHITE.getIndex()));
    }

    @Test
    public void testIsProtogroup()
    {
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.BAMBOOS_3.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_3.ordinal(), MahjongTileKind.BAMBOOS_1.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_4.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.CHARACTERS_8.ordinal(), MahjongTileKind.CHARACTERS_9.ordinal()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_1.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isProtogroup(MahjongTileKind.CHARACTERS_8.getIndex(), MahjongTileKind.CHARACTERS_9.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.ordinal(), MahjongTileKind.BAMBOOS_2.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.ordinal(), MahjongTileKind.CIRCLES_9.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.WEST.ordinal(), MahjongTileKind.SOUTH.ordinal()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.CIRCLES_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isProtogroup(MahjongTileKind.WEST.getIndex(), MahjongTileKind.SOUTH.getIndex()));
    }

    @Test
    public void testIsDoubleSidedBlock()
    {
        Assertions.assertTrue(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.CHARACTERS_6.getIndex(), MahjongTileKind.CHARACTERS_7.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_1.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.CHARACTERS_8.getIndex(), MahjongTileKind.CHARACTERS_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.CIRCLES_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isDoubleSidedBlock(MahjongTileKind.WEST.getIndex(), MahjongTileKind.SOUTH.getIndex()));
    }

    @Test
    public void testIsEndBlock()
    {
        Assertions.assertTrue(WaitShapeUtils.isEndBlock(MahjongTileKind.CHARACTERS_8.getIndex(), MahjongTileKind.CHARACTERS_9.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isEndBlock(MahjongTileKind.CIRCLES_2.getIndex(), MahjongTileKind.CIRCLES_1.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_1.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.CIRCLES_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isEndBlock(MahjongTileKind.WEST.getIndex(), MahjongTileKind.SOUTH.getIndex()));
    }

    @Test
    public void testIsInsideBlock()
    {
        Assertions.assertTrue(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_3.getIndex(), MahjongTileKind.BAMBOOS_1.getIndex()));
        Assertions.assertTrue(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_4.getIndex()));

        Assertions.assertFalse(WaitShapeUtils.isInsideBlock(MahjongTileKind.CHARACTERS_8.getIndex(), MahjongTileKind.CHARACTERS_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_3.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_2.getIndex(), MahjongTileKind.BAMBOOS_2.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isInsideBlock(MahjongTileKind.BAMBOOS_1.getIndex(), MahjongTileKind.CIRCLES_9.getIndex()));
        Assertions.assertFalse(WaitShapeUtils.isInsideBlock(MahjongTileKind.WEST.getIndex(), MahjongTileKind.SOUTH.getIndex()));
    }
}
