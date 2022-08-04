package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class ChankanTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfChankan_WhenObtained_ShouldBeTrue()
    {
        var chankan = new Chankan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnChankan(any())).thenReturn(true);

        var isValid = chankan.isValid();

        assertTrue(isValid, "Chankan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfChankan_WhenNotObtained_ShouldBeFalse()
    {
        var chankan = new Chankan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnChankan(any())).thenReturn(false);

        var isValid = chankan.isValid();

        assertFalse(isValid, "Chankan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Chankan_ShouldBeOne()
    {
        var chankan = new Chankan(anyHand, anyLog);

        var hanValue = chankan.getHanValue();

        assertEquals(hanValue, 1, "Chankan value should be 1");
    }
}
