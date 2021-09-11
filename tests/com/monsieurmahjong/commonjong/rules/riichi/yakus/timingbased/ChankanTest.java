package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class ChankanTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfChankan_WhenObtained_ShouldBeTrue()
    {
        Chankan chankan = new Chankan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnChankan(any())).thenReturn(true);

        boolean isValid = chankan.isValid();

        assertTrue(isValid, "Chankan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfChankan_WhenNotObtained_ShouldBeFalse()
    {
        Chankan chankan = new Chankan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnChankan(any())).thenReturn(false);

        boolean isValid = chankan.isValid();

        assertFalse(isValid, "Chankan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Chankan_ShouldBeOne()
    {
        Chankan chankan = new Chankan(anyHand, anyLog);

        int hanValue = chankan.getHanValue();

        assertEquals(hanValue, 1, "Chankan value should be 1");
    }
}
