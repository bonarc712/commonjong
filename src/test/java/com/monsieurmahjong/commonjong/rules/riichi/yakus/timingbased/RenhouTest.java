package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Renhou;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class RenhouTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfRenhou_WhenObtained_ShouldBeTrue()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.NORTH);
        when(anyLog.doesPlayerWinOnRenhou(any())).thenReturn(true);

        boolean isValid = renhou.isValid();

        assertTrue(isValid, "Renhou should be valid when obtained");
    }

    @Test
    public void testValidityOfRenhou_WhenNotObtained_ShouldBeFalse()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.NORTH);
        when(anyLog.doesPlayerWinOnRenhou(any())).thenReturn(false);

        boolean isValid = renhou.isValid();

        assertFalse(isValid, "Renhou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Renhou_ShouldBeFive()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);

        int hanValue = renhou.getHanValue();

        assertEquals(hanValue, 5, "Renhou value should be 5");
    }

    @Test
    public void testValueOf_Renhou_ShouldNotBeYakuman()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);

        boolean isYakuman = renhou.isYakuman();

        assertFalse(isYakuman, "Renhou should not be yakuman");
    }

    @Test
    public void testValueOf_RenhouWhenSetToYakuman_ShouldBeThirteen()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);
        renhou.setHanValue(13);

        int hanValue = renhou.getHanValue();

        assertEquals(hanValue, 13, "Renhou value should be 13");
    }

    @Test
    public void testValueOf_RenhouWhenSetToYakuman_ShouldBeYakuman()
    {
        Renhou renhou = new Renhou(anyHand, anyLog);
        renhou.setHanValue(13);

        boolean isYakuman = renhou.isYakuman();

        assertTrue(isYakuman, "Renhou should be yakuman");
    }
}
