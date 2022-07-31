package com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.MenzenTsumo;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;

public class MenzenTsumoTest
{
    Hand anyHand = mock(Hand.class);
    GameStateLog anyLog = mock(GameStateLog.class);

    @Test
    public void testValidityOfMenzenTsumo_WhenObtained_ShouldBeTrue()
    {
        MenzenTsumo menzenTsumo = new MenzenTsumo(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnMenzenTsumo(any())).thenReturn(true);

        boolean isValid = menzenTsumo.isValid();

        assertTrue(isValid, "Menzen tsumo should be valid when it is obtained");
    }

    @Test
    public void testValidityOfMenzenTsumo_WhenNotObtained_ShouldBeFalse()
    {
        MenzenTsumo menzenTsumo = new MenzenTsumo(anyHand, anyLog);
        when(anyHand.getSeatWind()).thenReturn(Seat.EAST);
        when(anyLog.doesPlayerWinOnMenzenTsumo(any())).thenReturn(false);

        boolean isValid = menzenTsumo.isValid();

        assertFalse(isValid, "Menzen tsumo should not be valid when it is not obtained");
    }

    @Test
    public void testValueOf_MenzenTsumo_ShouldBeOne()
    {
        MenzenTsumo menzenTsumo = new MenzenTsumo(anyHand, anyLog);

        int hanValue = menzenTsumo.getHanValue();

        assertEquals(hanValue, 1, "Menzen tsumo value should be 1");
    }
}
