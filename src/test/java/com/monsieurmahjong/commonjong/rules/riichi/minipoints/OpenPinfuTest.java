package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class OpenPinfuTest
{
    private static final int ANY_FU = 0;
    private static final int INITIAL_FU_TWENTY = 20;
    private static final int INITIAL_FU_NOT_TWENTY = 40;
    private Hand anyHand = mock(Hand.class);

    private List<TileGroup> completeHandGroups = TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p");

    @Test
    public void whenInitialFuIs20AndHandIsOpen_OpenPinfu_IsValid()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups));
        hand.addMeld(TileGroupUtils.getTilesFromMPSZNotation("345m"));
        var openPinfu = new OpenPinfu(hand, INITIAL_FU_TWENTY);

        var isValid = openPinfu.isValid();

        assertTrue(isValid, "Open pinfu should be valid with an open hand and an initial fu amount of 20");
    }

    @Test
    public void whenInitialFuIs40AndHandIsOpen_OpenPinfu_IsNotValid()
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups));
        hand.addMeld(TileGroupUtils.getTilesFromMPSZNotation("345m"));
        var openPinfu = new OpenPinfu(hand, INITIAL_FU_NOT_TWENTY);

        var isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with an open hand and an initial fu amount of 40");
    }

    @Test
    public void whenInitialFuIs40AndHandIsClosed_OpenPinfu_IsNotValid()
    {
        var openPinfu = new OpenPinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups)), INITIAL_FU_NOT_TWENTY);

        var isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with a closed hand and an initial fu amount of 40");
    }

    @Test
    public void forAClosedHand_OpenPinfu_IsNotValid()
    {
        var openPinfu = new OpenPinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups)), INITIAL_FU_TWENTY);

        var isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with a closed hand and an initial fu amount of 20");
    }

    @Test
    public void getFuValue_ForOpenPinfu_IsTwo()
    {
        var openPinfu = new OpenPinfu(anyHand, ANY_FU);

        var value = openPinfu.getFuValue();

        assertEquals(2, value, "Open pinfu value should be 2");
    }
}
