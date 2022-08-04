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

public class NagashiManganTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfNagashiMangan_WhenObtained_ShouldBeTrue()
    {
        var nagashiMangan = new NagashiMangan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnNagashiMangan(any())).thenReturn(true);

        var isValid = nagashiMangan.isValid();

        assertTrue(isValid, "Nagashi mangan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfNagashiMangan_WhenNotObtained_ShouldBeFalse()
    {
        var nagashiMangan = new NagashiMangan(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnNagashiMangan(any())).thenReturn(false);

        var isValid = nagashiMangan.isValid();

        assertFalse(isValid, "Nagashi mangan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_NagashiMangan_ShouldBeFive()
    {
        var nagashiMangan = new NagashiMangan(anyHand, anyLog);

        var hanValue = nagashiMangan.getHanValue();

        assertEquals(hanValue, 5, "Nagashi mangan value should be 5");
    }
}
