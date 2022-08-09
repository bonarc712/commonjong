package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RinshanKaihouTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfRinshanKaihou_WhenObtained_ShouldBeTrue()
    {
        var rinshanKaihou = new RinshanKaihou(anyParameters);
        when(anyParameters.doesPlayerWinOnRinshanKaihou()).thenReturn(true);

        var isValid = rinshanKaihou.isValid();

        assertTrue(isValid, "Rinshan kaihou should be valid when it is obtained");
    }

    @Test
    public void testValidityOfRinshanKaihou_WhenNotObtained_ShouldBeFalse()
    {
        var rinshanKaihou = new RinshanKaihou(anyParameters);
        when(anyParameters.doesPlayerWinOnRinshanKaihou()).thenReturn(false);

        var isValid = rinshanKaihou.isValid();

        assertFalse(isValid, "Rinshan kaihou should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_RinshanKaihou_ShouldBeOne()
    {
        var rinshanKaihou = new RinshanKaihou(anyParameters);

        var hanValue = rinshanKaihou.getHanValue();

        assertEquals(hanValue, 1, "Rinshan kaihou value should be 1");
    }
}
