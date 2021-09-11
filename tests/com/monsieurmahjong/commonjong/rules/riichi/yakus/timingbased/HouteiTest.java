package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class HouteiTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfHoutei_WhenObtained_ShouldBeTrue()
    {
        Houtei houtei = new Houtei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHoutei(any())).thenReturn(true);

        boolean isValid = houtei.isValid();

        assertTrue(isValid, "Houtei should be valid when it is obtained");
    }

    @Test
    public void testValidityOfHoutei_WhenNotObtained_ShouldBeFalse()
    {
        Houtei houtei = new Houtei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHoutei(any())).thenReturn(false);

        boolean isValid = houtei.isValid();

        assertFalse(isValid, "Houtei should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Houtei_ShouldBeOne()
    {
        Houtei houtei = new Houtei(anyHand, anyLog);

        int hanValue = houtei.getHanValue();

        assertEquals(hanValue, 1, "Houtei value should be 1");
    }
}
