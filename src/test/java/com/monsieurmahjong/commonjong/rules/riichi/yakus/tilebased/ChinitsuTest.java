package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Chinitsu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChinitsuTest
{
    private Hand anyHand = mock(Hand.class);

    private Hand completeChinitsuHand = new Hand(TileKindUtils.asHand("11123445566799m"));
    private Hand incompleteChinitsuHand = new Hand(TileKindUtils.asHand("11123477m"));
    private Hand completeNonChinitsuHand = new Hand(TileKindUtils.asHand("123456m22345678p"));
    private Hand incompleteNonChinitsuHand = new Hand(TileKindUtils.asHand("111p111s22z"));
    private Hand completeHonitsuHand = new Hand(TileKindUtils.asHand("111234455667m55z"));

    @Test
    public void testValidityOf_HandWithFourteenChinitsuTiles_ShouldBeTrue()
    {
        Yaku chinitsu = new Chinitsu(completeChinitsuHand);

        boolean chinitsuIsValid = chinitsu.isValid();

        assertTrue(chinitsuIsValid, "11123445566799m should be valid for chinitsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyChinitsuTiles_ShouldBeTrue()
    {
        Yaku chinitsu = new Chinitsu(incompleteChinitsuHand);

        boolean chinitsuIsValid = chinitsu.isValid();

        assertTrue(chinitsuIsValid, "11123477m should be valid for chinitsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChinitsuTiles_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(completeNonChinitsuHand);

        boolean chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "123456m22345678p should not be valid for chinitsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChinitsuTiles_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(incompleteNonChinitsuHand);

        boolean chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "111p111s22z should not be valid for chinitsu");
    }

    @Test
    public void testValidityOf_CompleteHonitsuHand_ShouldBeFalse()
    {
        Yaku chinitsu = new Chinitsu(completeHonitsuHand);

        boolean chinitsuIsValid = chinitsu.isValid();

        assertFalse(chinitsuIsValid, "111234455667m55z should not be valid for chinitsu");
    }

    @Test
    public void testValueOf_ChinitsuWhileOpen_ShouldBeFive()
    {
        Yaku chinitsu = new Chinitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(true);

        int chinitsuValue = chinitsu.getHanValue();

        assertEquals(5, chinitsuValue, "Chinitsu value should be 5");
    }

    @Test
    public void testValueOf_ChinitsuWhileClosed_ShouldBeSix()
    {
        Yaku chinitsu = new Chinitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(false);

        int chinitsuValue = chinitsu.getHanValue();

        assertEquals(6, chinitsuValue, "Chinitsu value should be 6");
    }
}
