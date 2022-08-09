package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RenhouTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfRenhou_WhenObtained_ShouldBeTrue()
    {
        var renhou = new Renhou(anyParameters);
        when(anyParameters.doesPlayerWinOnRenhou()).thenReturn(true);

        var isValid = renhou.isValid();

        assertTrue(isValid, "Renhou should be valid when obtained");
    }

    @Test
    public void testValidityOfRenhou_WhenNotObtained_ShouldBeFalse()
    {
        var renhou = new Renhou(anyParameters);
        when(anyParameters.doesPlayerWinOnRenhou()).thenReturn(false);

        var isValid = renhou.isValid();

        assertFalse(isValid, "Renhou should not be valid when not obtained");
    }

    @Test
    public void testValueOf_Renhou_ShouldBeFive()
    {
        var renhou = new Renhou(anyParameters);

        var hanValue = renhou.getHanValue();

        assertEquals(hanValue, 5, "Renhou value should be 5");
    }

    @Test
    public void testValueOf_Renhou_ShouldNotBeYakuman()
    {
        var renhou = new Renhou(anyParameters);

        var isYakuman = renhou.isYakuman();

        assertFalse(isYakuman, "Renhou should not be yakuman");
    }

    @Test
    public void testValueOf_RenhouWhenSetToYakuman_ShouldBeThirteen()
    {
        var renhou = new Renhou(anyParameters);
        renhou.setHanValue(13);

        var hanValue = renhou.getHanValue();

        assertEquals(hanValue, 13, "Renhou value should be 13");
    }

    @Test
    public void testValueOf_RenhouWhenSetToYakuman_ShouldBeYakuman()
    {
        var renhou = new Renhou(anyParameters);
        renhou.setHanValue(13);

        var isYakuman = renhou.isYakuman();

        assertTrue(isYakuman, "Renhou should be yakuman");
    }
}
