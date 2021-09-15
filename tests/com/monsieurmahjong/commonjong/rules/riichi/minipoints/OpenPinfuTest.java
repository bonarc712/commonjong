package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;
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
    public void mWhenInitialFuIs20AndHandIsOpen_OpenPinfu_IsValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups));
        hand.addMeld(TileGroupUtils.getTilesFromMPSZNotation("345m"));
        OpenPinfu openPinfu = new OpenPinfu(hand, INITIAL_FU_TWENTY);

        boolean isValid = openPinfu.isValid();

        assertTrue(isValid, "Open pinfu should be valid with an open hand and an initial fu amount of 20");
    }

    @Test
    public void mWhenInitialFuIs40AndHandIsOpen_OpenPinfu_IsNotValid()
    {
        Hand hand = new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups));
        hand.addMeld(TileGroupUtils.getTilesFromMPSZNotation("345m"));
        OpenPinfu openPinfu = new OpenPinfu(hand, INITIAL_FU_NOT_TWENTY);

        boolean isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with an open hand and an initial fu amount of 40");
    }

    @Test
    public void mWhenInitialFuIs40AndHandIsClosed_OpenPinfu_IsNotValid()
    {
        OpenPinfu openPinfu = new OpenPinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups)), INITIAL_FU_NOT_TWENTY);

        boolean isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with a closed hand and an initial fu amount of 40");
    }

    @Test
    public void mForAClosedHand_OpenPinfu_IsNotValid()
    {
        OpenPinfu openPinfu = new OpenPinfu(new Hand(TileGroupUtils.getTilesFromTileGroups(completeHandGroups)), INITIAL_FU_TWENTY);

        boolean isValid = openPinfu.isValid();

        assertFalse(isValid, "Open pinfu should not be valid with a closed hand and an initial fu amount of 20");
    }

    @Test
    public void mGetFuValue_ForOpenPinfu_IsTwo()
    {
        OpenPinfu openPinfu = new OpenPinfu(anyHand, ANY_FU);

        int value = openPinfu.getFuValue();

        assertEquals(2, value, "Open pinfu value should be 2");
    }
}
