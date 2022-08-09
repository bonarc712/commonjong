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
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class ChankanTest
{
    Hand anyHand = mock(Hand.class);
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfChankan_WhenObtained_ShouldBeTrue()
    {
        var chankan = new Chankan(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyParameters.doesPlayerWinOnChankan(any())).thenReturn(true);

        var isValid = chankan.isValid();

        assertTrue(isValid, "Chankan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfChankan_WhenNotObtained_ShouldBeFalse()
    {
        var chankan = new Chankan(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyParameters.doesPlayerWinOnChankan(any())).thenReturn(false);

        var isValid = chankan.isValid();

        assertFalse(isValid, "Chankan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Chankan_ShouldBeOne()
    {
        var chankan = new Chankan(anyHand, anyParameters);

        var hanValue = chankan.getHanValue();

        assertEquals(hanValue, 1, "Chankan value should be 1");
    }
}
