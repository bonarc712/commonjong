package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.*;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class HandConfigurationParserTest
{
    @Test
    public void testGetHandConfigurations()
    {
        //        Hand hand1 = new Hand(TileKindUtils.asHand("135567s"));
        //
        //        List<TileGroup> tileGroups = new ArrayList<>();
        //        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        //        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        //        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        //        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        //
        //        // sanity check
        //        Assertions.assertEquals(tileGroups, TileParser.parseFamilyTiles(hand1.getTiles()), "Fix tile parser test");
        //
        //        HandConfigurationParser parser = new HandConfigurationParser(hand1);
        //        List<List<TileGroup>> resultConfigurations = parser.getHandConfigurations(tileGroups);
        //        List<List<TileGroup>> expectedResultConfigurations = new ArrayList<>();
        //
        //        List<TileGroup> group1 = new ArrayList<>();
        //        group1.add(TileGroup.of(MahjongTileKind.BAMBOOS_1));
        //        group1.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        //        group1.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        //        expectedResultConfigurations.add(group1);
        //
        //        List<TileGroup> group2 = new ArrayList<>();
        //        group2.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        //        group2.add(TileGroup.of(MahjongTileKind.BAMBOOS_5));
        //        group2.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        //        expectedResultConfigurations.add(group2);
        //
        //        List<TileGroup> group3 = new ArrayList<>();
        //        group3.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        //        group3.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        //        group3.add(TileGroup.of(MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        //        expectedResultConfigurations.add(group3);
        //
        //        Assertions.assertEquals(expectedResultConfigurations, resultConfigurations, "Result configurations were not as expected");
    }

    @Test
    public void testFindCollisionPairs()
    {
        // 135567s case
        List<TileGroup> tileGroups = new ArrayList<>();
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));

        HandConfigurationParser parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        List<List<TileGroup>> collisionPairs = parser.findCollisionPairs(tileGroups);
        List<List<TileGroup>> expectedCollisionPairs = new ArrayList<>();

        List<TileGroup> collisionPair1 = new ArrayList<>();
        collisionPair1.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        collisionPair1.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        expectedCollisionPairs.add(collisionPair1);

        List<TileGroup> collisionPair2 = new ArrayList<>();
        collisionPair2.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        collisionPair2.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        expectedCollisionPairs.add(collisionPair2);

        List<TileGroup> collisionPair3 = new ArrayList<>();
        collisionPair3.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        collisionPair3.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedCollisionPairs.add(collisionPair3);

        List<TileGroup> collisionPair4 = new ArrayList<>();
        collisionPair4.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        collisionPair4.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        expectedCollisionPairs.add(collisionPair4);

        Assertions.assertEquals(expectedCollisionPairs, collisionPairs, "Collision pairs for 135567s are not as expected");
    }

    @Test
    public void testCreateCollisionList()
    {
        // 135567s case
        List<TileGroup> tileGroups = new ArrayList<>();
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));

        HandConfigurationParser parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        List<List<TileGroup>> collisionList = parser.createCollisionList(tileGroups);
        List<List<TileGroup>> expectedResultCollisionList = new ArrayList<>();

        List<TileGroup> collisionList1 = new ArrayList<>();
        collisionList1.addAll(Arrays.asList(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_3), //
                TileGroup.of(MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_5), //
                TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_5), //
                TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7)));
        expectedResultCollisionList.add(collisionList1);

        Assertions.assertEquals(expectedResultCollisionList, collisionList, "Collision list for 135567s is not as expected");
    }
}
