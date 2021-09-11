package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class ChihouTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfChihou_WhenObtained_ShouldBeTrue()
    {
        Chihou chihou = new Chihou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.SOUTH);
        when(anyLog.doesPlayerWinOnChihou(any())).thenReturn(true);

        boolean isValid = chihou.isValid();

        assertTrue(isValid, "Chihou should be valid when obtained");
    }

    @Test
    public void testValidityOfChihou_WhenNotObtained_ShouldBeFalse()
    {
        Chihou chihou = new Chihou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.SOUTH);
        when(anyLog.doesPlayerWinOnChihou(any())).thenReturn(false);

        boolean isValid = chihou.isValid();

        assertFalse(isValid, "Chihou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Chihou_ShouldBeThirteen()
    {
        Chihou chihou = new Chihou(anyHand, anyLog);

        int hanValue = chihou.getHanValue();

        assertEquals(hanValue, 13, "Chihou value should be 13");
    }

    @Test
    public void testValueOf_Chihou_ShouldBeYakuman()
    {
        Chihou chihou = new Chihou(anyHand, anyLog);

        boolean isYakuman = chihou.isYakuman();

        assertTrue(isYakuman, "Chihou should be yakuman");
    }
}
