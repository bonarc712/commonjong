package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class ChankanTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfChankan_WhenObtained_ShouldBeTrue()
    {
        var chankan = new Chankan(anyParameters);
        when(anyParameters.doesPlayerWinOnChankan()).thenReturn(true);

        var isValid = chankan.isValid();

        assertTrue(isValid, "Chankan should be valid when it is obtained");
    }

    @Test
    public void testValidityOfChankan_WhenNotObtained_ShouldBeFalse()
    {
        var chankan = new Chankan(anyParameters);
        when(anyParameters.doesPlayerWinOnChankan()).thenReturn(false);

        var isValid = chankan.isValid();

        assertFalse(isValid, "Chankan should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_Chankan_ShouldBeOne()
    {
        var chankan = new Chankan(anyParameters);

        var hanValue = chankan.getHanValue();

        assertEquals(hanValue, 1, "Chankan value should be 1");
    }
}
