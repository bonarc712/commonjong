package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Riichi;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class RiichiTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfRiichi_WhenDeclared_ShouldBeTrue()
    {
        Riichi riichi = new Riichi(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.hasPlayerDeclaredRiichi(any())).thenReturn(true);

        boolean isValid = riichi.isValid();

        assertTrue(isValid, "Riichi should be valid when declared");
    }

    @Test
    public void testValidityOfRiichi_WhenNotDeclared_ShouldBeFalse()
    {
        Riichi riichi = new Riichi(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.hasPlayerDeclaredRiichi(any())).thenReturn(false);

        boolean isValid = riichi.isValid();

        assertFalse(isValid, "Riichi should not be valid when not declared");
    }

    @Test
    public void testValueOf_Riichi_ShouldBeOne()
    {
        Riichi riichi = new Riichi(anyHand, anyLog);

        int hanValue = riichi.getHanValue();

        assertEquals(hanValue, 1, "Riichi value should be 1");
    }
}
