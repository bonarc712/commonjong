package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Tenhou;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class TenhouTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfTenhou_WhenObtained_ShouldBeTrue()
    {
        Tenhou tenhou = new Tenhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnTenhou(any())).thenReturn(true);

        boolean isValid = tenhou.isValid();

        assertTrue(isValid, "Tenhou should be valid when obtained");
    }

    @Test
    public void testValidityOfTenhou_WhenNotObtained_ShouldBeFalse()
    {
        Tenhou tenhou = new Tenhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnTenhou(any())).thenReturn(false);

        boolean isValid = tenhou.isValid();

        assertFalse(isValid, "Tenhou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeThirteen()
    {
        Tenhou tenhou = new Tenhou(anyHand, anyLog);

        int hanValue = tenhou.getHanValue();

        assertEquals(hanValue, 13, "Tenhou value should be 13");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeYakuman()
    {
        Tenhou tenhou = new Tenhou(anyHand, anyLog);

        boolean isYakuman = tenhou.isYakuman();

        assertTrue(isYakuman, "Tenhou should be yakuman");
    }
}
