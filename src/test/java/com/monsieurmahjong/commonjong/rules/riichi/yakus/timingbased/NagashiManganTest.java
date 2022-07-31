package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.NagashiMangan;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class NagashiManganTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfNagashiMangan_WhenObtained_ShouldBeTrue()
    {
        NagashiMangan nagashiMangan = new NagashiMangan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnNagashiMangan(any())).thenReturn(true);

        boolean isValid = nagashiMangan.isValid();

        assertTrue(isValid, "Nagashi mangan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfNagashiMangan_WhenNotObtained_ShouldBeFalse()
    {
        NagashiMangan nagashiMangan = new NagashiMangan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnNagashiMangan(any())).thenReturn(false);

        boolean isValid = nagashiMangan.isValid();

        assertFalse(isValid, "Nagashi mangan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_NagashiMangan_ShouldBeFive()
    {
        NagashiMangan nagashiMangan = new NagashiMangan(anyHand, anyLog);

        int hanValue = nagashiMangan.getHanValue();

        assertEquals(hanValue, 5, "Nagashi mangan value should be 5");
    }
}
