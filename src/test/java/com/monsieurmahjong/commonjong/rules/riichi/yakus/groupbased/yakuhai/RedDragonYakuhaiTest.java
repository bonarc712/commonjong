package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.RedDragonYakuhai;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class RedDragonYakuhaiTest
{
    private Hand anyHand = mock(Hand.class);
    private List<TileGroup> anyGroups = new ArrayList<>();

    private List<TileGroup> completeRedDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "777z");
    private List<TileGroup> incompleteRedDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "11z", "777z");
    private List<TileGroup> completeNonRedDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");
    private List<TileGroup> incompleteNonRedDragonYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "567p", "11s");
    private List<TileGroup> completeRedDragonKanYakuhaiHandGroups = TileGroupUtils.tileGroupsOf("111m", "888p", "999s", "11z", "7777z");

    @Test
    public void testValidityOf_HandWithFourteenRedDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeRedDragonYakuhaiHandGroups)), completeRedDragonYakuhaiHandGroups);

        boolean redDragonYakuhaiIsValid = redDragonYakuhai.isValid();

        assertTrue(redDragonYakuhaiIsValid, "111888p999s11777z should be valid for red dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithOnlyRedDragonYakuhaiTiles_ShouldBeTrue()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteRedDragonYakuhaiHandGroups)), incompleteRedDragonYakuhaiHandGroups);

        boolean redDragonYakuhaiIsValid = redDragonYakuhai.isValid();

        assertTrue(redDragonYakuhaiIsValid, "111m11777z should be valid for red dragon yakuhai");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonRedDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonRedDragonYakuhaiHandGroups)), completeNonRedDragonYakuhaiHandGroups);

        boolean redDragonYakuhaiIsValid = redDragonYakuhai.isValid();

        assertFalse(redDragonYakuhaiIsValid, "123345m22345678p should not be valid for red dragon yakuhai");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonRedDragonYakuhaiTiles_ShouldBeFalse()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonRedDragonYakuhaiHandGroups)), incompleteNonRedDragonYakuhaiHandGroups);

        boolean redDragonYakuhaiIsValid = redDragonYakuhai.isValid();

        assertFalse(redDragonYakuhaiIsValid, "111m567p11s should not be valid for red dragon yakuhai");
    }

    @Test
    public void testValidityOf_HandWithRedDragonKanYakuhaiTiles_ShouldBeTrue()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(new Hand(TileGroupUtils.getTilesFromTileGroups(completeRedDragonKanYakuhaiHandGroups)), completeRedDragonKanYakuhaiHandGroups);

        boolean redDragonYakuhaiIsValid = redDragonYakuhai.isValid();

        assertTrue(redDragonYakuhaiIsValid, "111888p999s117777z should be valid for red dragon yakuhai");
    }

    @Test
    public void testValueOf_RedDragonYakuhai_ShouldBeOne()
    {
        Yaku redDragonYakuhai = new RedDragonYakuhai(anyHand, anyGroups);

        int redDragonYakuhaiValue = redDragonYakuhai.getHanValue();

        assertEquals(1, redDragonYakuhaiValue, "RedDragonYakuhai value should be 1");
    }
}