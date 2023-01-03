package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class HandConfigurationParserTest
{
    @Test
    public void whenCreatingHandConfigurationWithoutCollision_thenShouldBringBackJustOneConfiguration()
    {
        var mpsz = new MPSZNotation();
        var hand = new Hand(mpsz.getTilesFrom("123m123p123s11177z"));
        var tileGroups = TileGroupUtils.tileGroupsOf("123m", "123p", "123s", "111z", "77z");

        var parser = new HandConfigurationParser(hand);
        var resultConfigurations = parser.getHandConfigurations(tileGroups);
        var expectedResultConfigurations = new ArrayList<List<TileGroup>>();
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("123m", "123p", "123s", "111z", "77z"));

        assertEquals(expectedResultConfigurations, resultConfigurations, "Result configurations for 123m123p123s11177z were not as expected");
    }

//    @Test
//    public void whenCreatingHandConfigurationWithOverlaps_thenShouldBringBackConfigurationsThatWorkWellWithOverlap()
//    {
//        var mpsz = new MPSZNotation();
//        var hand = new Hand(mpsz.getTilesFrom("12345p"));
//        var tileGroups = TileGroupUtils.tileGroupsOf("123p", "234p", "345p");
//
//        var parser = new HandConfigurationParser(hand);
//        var resultConfigurations = parser.getHandConfigurations(tileGroups);
//        var expectedResultConfigurations = new ArrayList<List<TileGroup>>();
//        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("123p", "45p"));
//        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("12p", "345p"));
//        // we don't want more complex result configurations (like 1p, 23p, 4p, 5p)
//        // because it's not really useful
//
//        assertEquals(expectedResultConfigurations, resultConfigurations, "Result configurations for 12345p were not as expected");
//    }

    @Test
    public void testGetHandConfigurations()
    {
        var mpsz = new MPSZNotation();
        var hand1 = new Hand(mpsz.getTilesFrom("135567s77z"));

        var tileGroups = TileGroupUtils.tileGroupsOf("13s", "35s", "55s", "567s", "77z");

        var parser = new HandConfigurationParser(hand1);
        var resultConfigurations = parser.getHandConfigurations(tileGroups);
        List<List<TileGroup>> expectedResultConfigurations = new ArrayList<>();

        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "5s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "55s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("13s", "5s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "35s", "5s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "35s", "567s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "3s", "55s", "67s", "77z"));
        expectedResultConfigurations.add(TileGroupUtils.tileGroupsOf("1s", "3s", "5s", "567s", "77z"));

        assertEquals(expectedResultConfigurations, resultConfigurations, "Result configurations for 135567s77z were not as expected");
    }
}
