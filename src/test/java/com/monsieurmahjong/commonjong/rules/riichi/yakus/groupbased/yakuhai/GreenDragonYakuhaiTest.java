package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class GreenDragonYakuhaiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeGreenDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "666z");
    private List<TileGroup> incompleteGreenDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "666z");
    private List<TileGroup> completeNonGreenDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonGreenDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeGreenDragonKanYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "6666z");

    @Test
    public void testValidityOf_HandWithFourteenGreenDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeGreenDragonYakuhaiHandGroups)), completeGreenDragonYakuhaiHandGroups);

        var greenDragonYakuhaiIsValid = greenDragonYakuhai.isValid();

        assertTrue(greenDragonYakuhaiIsValid, "111888p999s11666z should be valid for green dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOnlyGreenDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteGreenDragonYakuhaiHandGroups)), incompleteGreenDragonYakuhaiHandGroups);

        var greenDragonYakuhaiIsValid = greenDragonYakuhai.isValid();

        assertTrue(greenDragonYakuhaiIsValid, "111m11666z should be valid for green dragon yakuhai");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonGreenDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonGreenDragonYakuhaiHandGroups)), completeNonGreenDragonYakuhaiHandGroups);

        var greenDragonYakuhaiIsValid = greenDragonYakuhai.isValid();

        assertFalse(greenDragonYakuhaiIsValid, "123345m22345678p should not be valid for green dragon yakuhai");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonGreenDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonGreenDragonYakuhaiHandGroups)), incompleteNonGreenDragonYakuhaiHandGroups);

        var greenDragonYakuhaiIsValid = greenDragonYakuhai.isValid();

        assertFalse(greenDragonYakuhaiIsValid, "111m567p11s should not be valid for green dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithGreenDragonKanYakuhaiTiles_ShouldBeTrue()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeGreenDragonKanYakuhaiHandGroups)), completeGreenDragonKanYakuhaiHandGroups);

        var greenDragonYakuhaiIsValid = greenDragonYakuhai.isValid();

        assertTrue(greenDragonYakuhaiIsValid, "111888p999s116666z should be valid for green dragon yakuhai");
    }

    @Test
    public void testValueOf_GreenDragonYakuhai_ShouldBeOne()
    {
        Yaku greenDragonYakuhai = new GreenDragonYakuhai(anyHand, anyGroups);

        var greenDragonYakuhaiValue = greenDragonYakuhai.getHanValue();

        assertEquals(1, greenDragonYakuhaiValue, "GreenDragonYakuhai value should be 1");
    }
}
