package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Haitei;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class HaiteiTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfHaitei_WhenObtained_ShouldBeTrue()
    {
        Haitei haitei = new Haitei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHaitei(any())).thenReturn(true);

        boolean isValid = haitei.isValid();

        assertTrue(isValid, "Haitei should be valid when it is obtained");
    }

    @Test
    public void testValidityOfHaitei_WhenNotObtained_ShouldBeFalse()
    {
        Haitei haitei = new Haitei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHaitei(any())).thenReturn(false);

        boolean isValid = haitei.isValid();

        assertFalse(isValid, "Haitei should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Haitei_ShouldBeOne()
    {
        Haitei haitei = new Haitei(anyHand, anyLog);

        int hanValue = haitei.getHanValue();

        assertEquals(hanValue, 1, "Haitei value should be 1");
    }
}
