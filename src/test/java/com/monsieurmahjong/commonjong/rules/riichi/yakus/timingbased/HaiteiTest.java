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

public class HaiteiTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfHaitei_WhenObtained_ShouldBeTrue()
    {
        var haitei = new Haitei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHaitei(any())).thenReturn(true);

        var isValid = haitei.isValid();

        assertTrue(isValid, "Haitei should be valid when it is obtained");
    }

    @Test
    public void testValidityOfHaitei_WhenNotObtained_ShouldBeFalse()
    {
        var haitei = new Haitei(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnHaitei(any())).thenReturn(false);

        var isValid = haitei.isValid();

        assertFalse(isValid, "Haitei should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Haitei_ShouldBeOne()
    {
        var haitei = new Haitei(anyHand, anyLog);

        var hanValue = haitei.getHanValue();

        assertEquals(hanValue, 1, "Haitei value should be 1");
    }
}
