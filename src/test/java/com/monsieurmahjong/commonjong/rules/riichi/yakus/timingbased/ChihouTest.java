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

public class ChihouTest
{
    Hand anyHand = mock(Hand.class);
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfChihou_WhenObtained_ShouldBeTrue()
    {
        var chihou = new Chihou(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.SOUTH);
        when(anyParameters.doesPlayerWinOnChihou()).thenReturn(true);

        var isValid = chihou.isValid();

        assertTrue(isValid, "Chihou should be valid when obtained");
    }

    @Test
    public void testValidityOfChihou_WhenNotObtained_ShouldBeFalse()
    {
        var chihou = new Chihou(anyHand, anyParameters);
        when(anyHand.getSeatWind()).thenReturn(Seat.SOUTH);
        when(anyParameters.doesPlayerWinOnChihou()).thenReturn(false);

        var isValid = chihou.isValid();

        assertFalse(isValid, "Chihou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Chihou_ShouldBeThirteen()
    {
        var chihou = new Chihou(anyHand, anyParameters);

        var hanValue = chihou.getHanValue();

        assertEquals(hanValue, 13, "Chihou value should be 13");
    }

    @Test
    public void testValueOf_Chihou_ShouldBeYakuman()
    {
        var chihou = new Chihou(anyHand, anyParameters);

        var isYakuman = chihou.isYakuman();

        assertTrue(isYakuman, "Chihou should be yakuman");
    }
}
