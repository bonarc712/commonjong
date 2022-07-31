package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.RinshanKaihou;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class RinshanKaihouTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfRinshanKaihou_WhenObtained_ShouldBeTrue()
    {
        RinshanKaihou rinshanKaihou = new RinshanKaihou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnRinshanKaihou(any())).thenReturn(true);

        boolean isValid = rinshanKaihou.isValid();

        assertTrue(isValid, "Rinshan kaihou should be valid when it is obtained");
    }

    @Test
    public void testValidityOfRinshanKaihou_WhenNotObtained_ShouldBeFalse()
    {
        RinshanKaihou rinshanKaihou = new RinshanKaihou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnRinshanKaihou(any())).thenReturn(false);

        boolean isValid = rinshanKaihou.isValid();

        assertFalse(isValid, "Rinshan kaihou should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_RinshanKaihou_ShouldBeOne()
    {
        RinshanKaihou rinshanKaihou = new RinshanKaihou(anyHand, anyLog);

        int hanValue = rinshanKaihou.getHanValue();

        assertEquals(hanValue, 1, "Rinshan kaihou value should be 1");
    }
}
