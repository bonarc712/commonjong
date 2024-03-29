package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class PossiblePairingsTest
{
    @Test
    public void testCreatePossiblePairings()
    {
        // 135567s case
        var collisionList = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var pairings = new PossiblePairings("135567s");
        var possiblePairings = pairings.createFrom(collisionList);
        List<List<TileGroup>> expectedPossiblePairings = new ArrayList<>();

        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "5s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "55s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "35s", "5s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "35s", "567s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "3s", "55s", "67s"));
        expectedPossiblePairings.add(TileGroupUtils.tileGroupsOf("1s", "3s", "5s", "567s"));

        assertEquals(expectedPossiblePairings, possiblePairings, "Possible pairings for 135567s is not as expected");

        // 135567s case with 77z also in the hand
        var parser2 = new PossiblePairings("135567s77z");
        var possiblePairings2 = parser2.createFrom(collisionList);

        assertEquals(expectedPossiblePairings, possiblePairings2, "Possible pairings for 135567s77z is not as expected");
    }

    @Test
    public void testGetFlagsForTilesToKeep()
    {
        Function<String, Integer> indexOf = tile -> {
            var mpsz = new MPSZNotation();
            return mpsz.getTilesFrom(tile).get(0).getTileKind().getIndex();
        };

        Map<Integer, List<TileGroup>> combination = new TreeMap<>();
        combination.put(indexOf.apply("1s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("3s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("5s"), TileGroupUtils.tileGroupsOf("35s", "55s"));
        combination.put(indexOf.apply("6s"), TileGroupUtils.tileGroupsOf("567s"));
        combination.put(indexOf.apply("7s"), TileGroupUtils.tileGroupsOf("567s"));

        var collidingGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var pairings = new PossiblePairings("135567s");
        var resultFlags = pairings.getFlagsForTilesToKeep(combination, collidingGroups);

        List<List<Boolean>> expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, true));
        expectedFlags.add(Arrays.asList(true, false));
        expectedFlags.add(Arrays.asList(false, true, true));

        assertEquals(expectedFlags, resultFlags, "Flags for 13-13-35+55-567-567 are not as expected");

        // ---

        combination.clear();
        combination.put(indexOf.apply("1s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("3s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("5s"), TileGroupUtils.tileGroupsOf("55s"));
        combination.put(indexOf.apply("6s"), TileGroupUtils.tileGroupsOf("567s"));
        combination.put(indexOf.apply("7s"), TileGroupUtils.tileGroupsOf("567s"));

        collidingGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        pairings = new PossiblePairings("135567s");
        resultFlags = pairings.getFlagsForTilesToKeep(combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, false));
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, true, true));

        assertEquals(expectedFlags, resultFlags, "Flags for 13-13-55-567-567 are not as expected");

        // ---

        combination.clear();
        combination.put(indexOf.apply("1s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("3s"), TileGroupUtils.tileGroupsOf("13s"));
        combination.put(indexOf.apply("5s"), TileGroupUtils.tileGroupsOf("55s", "567s"));
        combination.put(indexOf.apply("6s"), TileGroupUtils.tileGroupsOf("567s"));
        combination.put(indexOf.apply("7s"), TileGroupUtils.tileGroupsOf("567s"));

        collidingGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        pairings = new PossiblePairings("135567s");
        resultFlags = pairings.getFlagsForTilesToKeep(combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true));
        expectedFlags.add(Arrays.asList(false, false));
        expectedFlags.add(Arrays.asList(true, false));
        expectedFlags.add(Arrays.asList(true, true, true));

        assertEquals(expectedFlags, resultFlags, "Flags for 13-13-55-567-567 are not as expected");

        // ---

        combination.clear();
        combination.put(indexOf.apply("4s"), TileGroupUtils.tileGroupsOf("456s"));
        combination.put(indexOf.apply("5s"), TileGroupUtils.tileGroupsOf("456s"));
        combination.put(indexOf.apply("6s"), TileGroupUtils.tileGroupsOf("456s", "666s"));

        collidingGroups = TileGroupUtils.tileGroupsOf("456s", "666s");

        pairings = new PossiblePairings("45666s");
        resultFlags = pairings.getFlagsForTilesToKeep(combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true, true));
        expectedFlags.add(Arrays.asList(true, true, false));

        assertEquals(expectedFlags, resultFlags, "Flags for 456-456-456+666 are not as expected");

        // ---

        combination.clear();
        combination.put(indexOf.apply("4s"), TileGroupUtils.tileGroupsOf("456s"));
        combination.put(indexOf.apply("5s"), TileGroupUtils.tileGroupsOf("456s"));
        combination.put(indexOf.apply("6s"), TileGroupUtils.tileGroupsOf("666s"));

        collidingGroups = TileGroupUtils.tileGroupsOf("456s", "666s");

        pairings = new PossiblePairings("45666s");
        resultFlags = pairings.getFlagsForTilesToKeep(combination, collidingGroups);

        expectedFlags = new ArrayList<>();
        expectedFlags.add(Arrays.asList(true, true, false));
        expectedFlags.add(Arrays.asList(true, true, true));

        assertEquals(expectedFlags, resultFlags, "Flags for 456-456-666 are not as expected");
    }

    @Test
    public void testAddPossiblePairings()
    {
        var groupsToSelectFrom = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s");

        var pairings = new PossiblePairings("135567s");
        var possiblePairings = pairings.addPossiblePairing(MahjongTileKind.BAMBOOS_5, 2, groupsToSelectFrom);

        List<List<TileGroup>> expectedPairings = new ArrayList<>();
        expectedPairings.add(TileGroupUtils.tileGroupsOf("35s", "55s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("35s", "567s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("55s"));
        expectedPairings.add(TileGroupUtils.tileGroupsOf("55s", "567s"));

        assertEquals(expectedPairings, possiblePairings, "Possible pairings for 5s within 135567s are not as expected");
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

        var pairings = new PossiblePairings("135567s");
        var resultGroups = pairings.listDifferentCombinations(knownPairings);

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

        assertEquals(expectedGroups, resultGroups, "Different combinations were not listed properly for 135567s");
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
