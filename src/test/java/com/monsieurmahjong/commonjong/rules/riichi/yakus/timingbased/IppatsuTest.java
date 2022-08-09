package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class IppatsuTest
{
    Hand anyHand = mock(Hand.class);
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfIppatsu_WhenObtained_ShouldBeTrue()
    {
        var ippatsu = new Ippatsu(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyParameters.doesPlayerWinOnIppatsu()).thenReturn(true);

        var isValid = ippatsu.isValid();

        assertTrue(isValid, "Ippatsu should be valid when it is obtained");
    }

    @Test
    public void testValidityOfIppatsu_WhenNotObtained_ShouldBeFalse()
    {
        var ippatsu = new Ippatsu(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyParameters.doesPlayerWinOnIppatsu()).thenReturn(false);

        var isValid = ippatsu.isValid();

        assertFalse(isValid, "Ippatsu should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Ippatsu_ShouldBeOne()
    {
        var ippatsu = new Ippatsu(anyHand, anyParameters);

        var hanValue = ippatsu.getHanValue();

        assertEquals(hanValue, 1, "Ippatsu value should be 1");
    }
}
