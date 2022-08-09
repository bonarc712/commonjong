package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class TenhouTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfTenhou_WhenObtained_ShouldBeTrue()
    {
        var tenhou = new Tenhou(anyParameters);
        when(anyParameters.doesPlayerWinOnTenhou()).thenReturn(true);

        var isValid = tenhou.isValid();

        assertTrue(isValid, "Tenhou should be valid when obtained");
    }

    @Test
    public void testValidityOfTenhou_WhenNotObtained_ShouldBeFalse()
    {
        var tenhou = new Tenhou(anyParameters);
        when(anyParameters.doesPlayerWinOnTenhou()).thenReturn(false);

        var isValid = tenhou.isValid();

        assertFalse(isValid, "Tenhou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeThirteen()
    {
        var tenhou = new Tenhou(anyParameters);

        var hanValue = tenhou.getHanValue();

        assertEquals(hanValue, 13, "Tenhou value should be 13");
    }

    @Test
    public void testValueOf_Tenhou_ShouldBeYakuman()
    {
        var tenhou = new Tenhou(anyParameters);

        var isYakuman = tenhou.isYakuman();

        assertTrue(isYakuman, "Tenhou should be yakuman");
    }
}
