package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class MenzenTsumoTest
{
    RiichiScoringParameters anyParameters = mock(RiichiScoringParameters.class);

    @Test
    public void testValidityOfMenzenTsumo_WhenObtained_ShouldBeTrue()
    {
        var menzenTsumo = new MenzenTsumo(anyParameters);
        when(anyParameters.doesPlayerWinOnMenzenTsumo()).thenReturn(true);

        var isValid = menzenTsumo.isValid();

        assertTrue(isValid, "Menzen tsumo should be valid when it is obtained");
    }

    @Test
    public void testValidityOfMenzenTsumo_WhenNotObtained_ShouldBeFalse()
    {
        var menzenTsumo = new MenzenTsumo(anyParameters);
        when(anyParameters.doesPlayerWinOnMenzenTsumo()).thenReturn(false);

        var isValid = menzenTsumo.isValid();

        assertFalse(isValid, "Menzen tsumo should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_MenzenTsumo_ShouldBeOne()
    {
        var menzenTsumo = new MenzenTsumo(anyParameters);

        var hanValue = menzenTsumo.getHanValue();

        assertEquals(hanValue, 1, "Menzen tsumo value should be 1");
    }
}
