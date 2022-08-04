package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class HandConfigurationParserTest
{
    @Test
    public void testGetHandConfigurations()
    {
        var hand1 = new Hand(TileKindUtils.asHand("135567s77z"));

        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s", "77z");

        var parser = new HandConfigurationParser(hand1);
        var resultConfigurations = parser.getHandConfigurations(tileGroups);
        List<List<TileGroup>> expectedResultConfigurations = new ArrayList<>();

        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "5s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "55s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "35s", "5s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "35s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "3s", "5s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "3s", "55s", "67s", "77z"));

        Assertions.assertEquals(expectedResultConfigurations, resultConfigurations, "Result configurations for 135567s77z were not as expected");
    }

    @Test
    public void testFindCollisionPairs()
    {
        // 135567s case
        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var collisionPairs = parser.findCollisionPairs(tileGroups);
        List<List<TileGroup>> expectedCollisionPairs = new ArrayList<>();

        expectedCollisionPairs.add(TileGroupUtils.tileGroupsOf("13s", "35s"));
        expectedCollisionPairs.add(TileGroupUtils.tileGroupsOf("35s", "55s"));
        expectedCollisionPairs.add(TileGroupUtils.tileGroupsOf("35s", "567s"));
        expectedCollisionPairs.add(TileGroupUtils.tileGroupsOf("55s", "567s"));

        Assertions.assertEquals(expectedCollisionPairs, collisionPairs, "Collision pairs for 135567s are not as expected");

        // 77z case
        List<TileGroup> tileGroups2 = new ArrayList<>();
        tileGroups.add(TileGroup.of(MahjongTileKind.RED, MahjongTileKind.RED));

        var parser2 = new HandConfigurationParser(new Hand(TileKindUtils.asHand("77z")));
        var collisionPairs2 = parser2.findCollisionPairs(tileGroups2);
        List<List<TileGroup>> expectedCollisionPairs2 = new ArrayList<>();

        Assertions.assertEquals(expectedCollisionPairs2, collisionPairs2, "There should be not collision pair for 77z");
    }

    @Test
    public void testCreateCollisionList()
    {
        // 135567s case
        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var collisionList = parser.createCollisionList(tileGroups);

        List<List<TileGroup>> expectedResultCollisionList = new ArrayList<>();
        expectedResultCollisionList.add(TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s"));

        Assertions.assertEquals(expectedResultCollisionList, collisionList, "Collision list for 135567s is not as expected");
    }

    @Test
    public void testCreatePossiblePairings()
    {
        // 135567s case
        var collisionList = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var possiblePairings = parser.createPossiblePairings(collisionList);
        List<List<TileGroup>> expectedPossiblePairings = new ArrayList<>();

        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "5s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "55s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "35s", "5s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "35s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "3s", "5s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "3s", "55s", "67s"));

        Assertions.assertEquals(expectedPossiblePairings, possiblePairings, "Possible pairings for 135567s is not as expected");

        // 135567s case with 77z also in the hand
        var parser2 = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s77z")));
        var possiblePairings2 = parser2.createPossiblePairings(collisionList);

        Assertions.assertEquals(expectedPossiblePairings, possiblePairings2, "Possible pairings for 135567s77z is not as expected");
    }

    @Test
    public void testGetFlagsForTilesToKeep()
    {
        List<Integer> indicesForCollidingGroups = TileKindUtils.asHand("13567s").stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList());
        var collidingGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        List<List<TileGroup>> combination = new ArrayList<>();
        combination.add(TileGroupUtils.tileGroupsOf("13s"));
        combination.add(TileGroupUtils.tileGroupsOf("13s"));
        combination.add(TileGroupUtils.tileGroupsOf("35s", "55s"));
        combination.add(TileGroupUtils.tileGroupsOf("567s"));
        combination.add(TileGroupUtils.tileGroupsOf("567s"));

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var resultFlags = parser.getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

        List<List<Boolean>> expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, true));
        expectedFlags.add(Arrays.asList(true, false));
        expectedFlags.add(Arrays.asList(false, true, true));

        Assertions.assertEquals(expectedFlags, resultFlags, "Flags for 13-13-35+55-567-567 are not as expected");

        combination.clear();
        combination.add(TileGroupUtils.tileGroupsOf("13s"));
        combination.add(TileGroupUtils.tileGroupsOf("13s"));
        combination.add(TileGroupUtils.tileGroupsOf("55s"));
        combination.add(TileGroupUtils.tileGroupsOf("567s"));
        combination.add(TileGroupUtils.tileGroupsOf("567s"));

        resultFlags = parser.getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, false));
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, true, true));

        Assertions.assertEquals(expectedFlags, resultFlags, "Flags for 13-13-55-567-567 are not as expected");

        indicesForCollidingGroups = TileKindUtils.asHand("456s").stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList());
        collidingGroups = TileGroupUtils.tileGroupsOf("456s", "666s");
        parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("45666s")));

        combination.clear();
        combination.add(TileGroupUtils.tileGroupsOf("456s"));
        combination.add(TileGroupUtils.tileGroupsOf("456s"));
        combination.add(TileGroupUtils.tileGroupsOf("456s", "666s"));

        resultFlags = parser.getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true, true));
        expectedFlags.add(Arrays.asList(true, true, false));

        Assertions.assertEquals(expectedFlags, resultFlags, "Flags for 456-456-456+666 are not as expected");

        indicesForCollidingGroups = TileKindUtils.asHand("456s").stream().map(tile -> tile.getTileKind().getIndex()).collect(Collectors.toList());
        collidingGroups = TileGroupUtils.tileGroupsOf("456s", "666s");
        parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("45666s")));

        combination.clear();
        combination.add(TileGroupUtils.tileGroupsOf("456s"));
        combination.add(TileGroupUtils.tileGroupsOf("456s"));
        combination.add(TileGroupUtils.tileGroupsOf("666s"));

        resultFlags = parser.getFlagsForTilesToKeep(indicesForCollidingGroups, combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true, false));
        expectedFlags.add(Arrays.asList(true, true, true));

        Assertions.assertEquals(expectedFlags, resultFlags, "Flags for 456-456-666 are not as expected");
    }

    @Test
    public void testAddPossiblePairings()
    {
        var groupsToSelectFrom = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var possiblePairings = parser.addPossiblePairings(MahjongTileKind.BAMBOOS_5, 2, groupsToSelectFrom, new ArrayList<>(), new ArrayList<>());

        List<List<TileGroup>> expectedPairings = new ArrayList<>();
        expectedPairings.add(TileGroupUtils.tileGroupsOf("35s", "55s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("35s", "567s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("55s", "567s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("55s"));

        Assertions.assertEquals(expectedPairings, possiblePairings, "Possible pairings for 5s within 135567s are not as expected");
    }

    @Test
    public void testListDifferentCombinations()
    {
        List<List<List<TileGroup>>> knownPairings = new ArrayList<>();
        List<List<TileGroup>> pairingsOf1s = new ArrayList<>();
        pairingsOf1s.add(TileGroupUtils.tileGroupsOf("13s"));
        knownPairings.add(pairingsOf1s);
        List<List<TileGroup>> pairingsOf3s = new ArrayList<>();
        pairingsOf3s.add(TileGroupUtils.tileGroupsOf("13s"));
        pairingsOf3s.add(TileGroupUtils.tileGroupsOf("35s"));
        knownPairings.add(pairingsOf3s);
        List<List<TileGroup>> pairingsOf5s = new ArrayList<>();
        pairingsOf5s.add(TileGroupUtils.tileGroupsOf("35s", "55s"));
        pairingsOf5s.add(TileGroupUtils.tileGroupsOf("35s", "567s"));
        pairingsOf5s.add(TileGroupUtils.tileGroupsOf("55s", "567s"));
        pairingsOf5s.add(TileGroupUtils.tileGroupsOf("55s"));
        knownPairings.add(pairingsOf5s);
        List<List<TileGroup>> pairingsOf6s = new ArrayList<>();
        pairingsOf6s.add(TileGroupUtils.tileGroupsOf("567s"));
        knownPairings.add(pairingsOf6s);
        List<List<TileGroup>> pairingsOf7s = new ArrayList<>();
        pairingsOf7s.add(TileGroupUtils.tileGroupsOf("567s"));
        knownPairings.add(pairingsOf7s);

        var parser = new HandConfigurationParser(new Hand(TileKindUtils.asHand("135567s")));
        var resultGroups = parser.listDifferentCombinations(knownPairings, new ArrayList<>(), new ArrayList<>());

        List<List<List<TileGroup>>> expectedGroups = new ArrayList<>();
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s", "55s"), TileGroupUtils.tileGroupsOf("567s"),
                TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s", "567s"),
                TileGroupUtils.tileGroupsOf("567s"), TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("55s", "567s"),
                TileGroupUtils.tileGroupsOf("567s"), TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("55s"), TileGroupUtils.tileGroupsOf("567s"),
                TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s"), TileGroupUtils.tileGroupsOf("35s", "55s"), TileGroupUtils.tileGroupsOf("567s"),
                TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s"), TileGroupUtils.tileGroupsOf("35s", "567s"),
                TileGroupUtils.tileGroupsOf("567s"), TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s"), TileGroupUtils.tileGroupsOf("55s", "567s"),
                TileGroupUtils.tileGroupsOf("567s"), TileGroupUtils.tileGroupsOf("567s")));
        expectedGroups.add(getExpectedListOfList(TileGroupUtils.tileGroupsOf("13s"), TileGroupUtils.tileGroupsOf("35s"), TileGroupUtils.tileGroupsOf("55s"), TileGroupUtils.tileGroupsOf("567s"),
                TileGroupUtils.tileGroupsOf("567s")));

        Assertions.assertEquals(expectedGroups, resultGroups, "Different combinations were not listed properly for 135567s");
    }

    private List<List<TileGroup>> getExpectedListOfList(List<TileGroup>... list)
    {
        List<List<TileGroup>> pairingList = new ArrayList<>();
        for (List<TileGroup> tileGroupList : list)
        {
            pairingList.add(tileGroupList);
        }
        return pairingList;
    }
}
