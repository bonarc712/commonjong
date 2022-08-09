package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class NagashiManganTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfNagashiMangan_WhenObtained_ShouldBeTrue()
    {
        var nagashiMangan = new NagashiMangan(anyParameters);
        when(anyParameters.doesPlayerWinOnNagashiMangan()).thenReturn(true);

        var isValid = nagashiMangan.isValid();

        assertTrue(isValid, "Nagashi mangan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfNagashiMangan_WhenNotObtained_ShouldBeFalse()
    {
        var nagashiMangan = new NagashiMangan(anyParameters);
        when(anyParameters.doesPlayerWinOnNagashiMangan()).thenReturn(false);

        var isValid = nagashiMangan.isValid();

        assertFalse(isValid, "Nagashi mangan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_NagashiMangan_ShouldBeFive()
    {
        var nagashiMangan = new NagashiMangan(anyParameters);

        var hanValue = nagashiMangan.getHanValue();

        assertEquals(hanValue, 5, "Nagashi mangan value should be 5");
    }
}
