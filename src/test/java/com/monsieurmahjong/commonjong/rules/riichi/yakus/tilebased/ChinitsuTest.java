package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChinitsuTest
{
    private Hand anyHand = mock(Hand.class);

    private Hand completeChinitsuHand = new Hand(new MPSZNotation().getTilesFrom("11123445566799m"));
    private Hand incompleteChinitsuHand = new Hand(new MPSZNotation().getTilesFrom("11123477m"));
    private Hand completeNonChinitsuHand = new Hand(new MPSZNotation().getTilesFrom("123456m22345678p"));
    private Hand incompleteNonChinitsuHand = new Hand(new MPSZNotation().getTilesFrom("111p111s22z"));
    private Hand completeHonitsuHand = new Hand(new MPSZNotation().getTilesFrom("111234455667m55z"));

    @Test
    public void testValidityOf_HandWithFourteenChinitsuTiles_ShouldBeTrue()
    {
        Yaku chinitsu = new Chinitsu(completeChinitsuHand);

        var chinitsuIsValid = chinitsu.isValid();

        assertTrue(chinitsuIsValid, "11123445566799m should be valid for chinitsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyChinitsuTiles_ShouldBeTrue()
    {
        Yaku chinitsu = new Chinitsu(incompleteChinitsuHand);

        var chinitsuIsValid = chinitsu.isValid();

        assertTrue(chinitsuIsValid, "11123477m should be valid for chinitsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChinitsuTiles_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(completeNonChinitsuHand);

        var chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "123456m22345678p should not be valid for chinitsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChinitsuTiles_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(incompleteNonChinitsuHand);

        var chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "111p111s22z should not be valid for chinitsu");
    }

    @Test
    public void testValidityOf_CompleteHonitsuHand_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(completeHonitsuHand);

        var chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "111234455667m55z should not be valid for chinitsu");
    }

    @Test
    public void testValueOf_ChinitsuWhileOpen_ShouldBeFive()
    {
        Yaku chinitsu = new Chinitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(true);

        var chinitsuValue = chinitsu.getHanValue();

        assertEquals(5, chinitsuValue, "Chinitsu value should be 5");
    }

    @Test
    public void testValueOf_ChinitsuWhileClosed_ShouldBeSix()
    {
        Yaku chinitsu = new Chinitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(false);

        var chinitsuValue = chinitsu.getHanValue();

        assertEquals(6, chinitsuValue, "Chinitsu value should be 6");
    }
}
