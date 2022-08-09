package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class HaiteiTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfHaitei_WhenObtained_ShouldBeTrue()
    {
        var haitei = new Haitei(anyParameters);
        when(anyParameters.doesPlayerWinOnHaitei()).thenReturn(true);

        var isValid = haitei.isValid();

        assertTrue(isValid, "Haitei should be valid when it is obtained");
    }

    @Test
    public void testValidityOfHaitei_WhenNotObtained_ShouldBeFalse()
    {
        var haitei = new Haitei(anyParameters);
        when(anyParameters.doesPlayerWinOnHaitei()).thenReturn(false);

        var isValid = haitei.isValid();

        assertFalse(isValid, "Haitei should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Haitei_ShouldBeOne()
    {
        var haitei = new Haitei(anyParameters);

        var hanValue = haitei.getHanValue();

        assertEquals(hanValue, 1, "Haitei value should be 1");
    }
}
