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

public class HouteiTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfHoutei_WhenObtained_ShouldBeTrue()
    {
        var houtei = new Houtei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHoutei(any())).thenReturn(true);

        var isValid = houtei.isValid();

        assertTrue(isValid, "Houtei should be valid when it is obtained");
    }

    @Test
    public void testValidityOfHoutei_WhenNotObtained_ShouldBeFalse()
    {
        var houtei = new Houtei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHoutei(any())).thenReturn(false);

        var isValid = houtei.isValid();

        assertFalse(isValid, "Houtei should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Houtei_ShouldBeOne()
    {
        var houtei = new Houtei(anyHand, anyLog);

        var hanValue = houtei.getHanValue();

        assertEquals(hanValue, 1, "Houtei value should be 1");
    }
}
