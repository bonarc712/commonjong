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

public class TenhouTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfTenhou_WhenObtained_ShouldBeTrue()
    {
        var tenhou = new Tenhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnTenhou(any())).thenReturn(true);

        var isValid = tenhou.isValid();

        assertTrue(isValid, "Tenhou should be valid when obtained");
    }

    @Test
    public void testValidityOfTenhou_WhenNotObtained_ShouldBeFalse()
    {
        var tenhou = new Tenhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnTenhou(any())).thenReturn(false);

        var isValid = tenhou.isValid();

        assertFalse(isValid, "Tenhou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeThirteen()
    {
        var tenhou = new Tenhou(anyHand, anyLog);

        var hanValue = tenhou.getHanValue();

        assertEquals(hanValue, 13, "Tenhou value should be 13");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeYakuman()
    {
        var tenhou = new Tenhou(anyHand, anyLog);

        var isYakuman = tenhou.isYakuman();

        assertTrue(isYakuman, "Tenhou should be yakuman");
    }
}
