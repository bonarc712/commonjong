package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RiichiTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfRiichi_WhenDeclared_ShouldBeTrue()
    {
        var riichi = new Riichi(anyParameters);
        when(anyParameters.hasPlayerDeclaredRiichi()).thenReturn(true);

        var isValid = riichi.isValid();

        assertTrue(isValid, "Riichi should be valid when declared");
    }

    @Test
    public void testValidityOfRiichi_WhenNotDeclared_ShouldBeFalse()
    {
        var riichi = new Riichi(anyParameters);
        when(anyParameters.hasPlayerDeclaredRiichi()).thenReturn(false);

        var isValid = riichi.isValid();

        assertFalse(isValid, "Riichi should not be valid when not declared");
    }

    @Test
    public void testValueOf_Riichi_ShouldBeOne()
    {
        var riichi = new Riichi(anyParameters);

        var hanValue = riichi.getHanValue();

        assertEquals(hanValue, 1, "Riichi value should be 1");
    }
}
