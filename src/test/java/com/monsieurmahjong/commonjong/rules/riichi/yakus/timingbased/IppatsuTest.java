package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Ippatsu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class IppatsuTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfIppatsu_WhenObtained_ShouldBeTrue()
    {
        Ippatsu ippatsu = new Ippatsu(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnIppatsu(any())).thenReturn(true);

        boolean isValid = ippatsu.isValid();

        assertTrue(isValid, "Ippatsu should be valid when it is obtained");
    }

    @Test
    public void testValidityOfIppatsu_WhenNotObtained_ShouldBeFalse()
    {
        Ippatsu ippatsu = new Ippatsu(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnIppatsu(any())).thenReturn(false);

        boolean isValid = ippatsu.isValid();

        assertFalse(isValid, "Ippatsu should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Ippatsu_ShouldBeOne()
    {
        Ippatsu ippatsu = new Ippatsu(anyHand, anyLog);

        int hanValue = ippatsu.getHanValue();

        assertEquals(hanValue, 1, "Ippatsu value should be 1");
    }
}
