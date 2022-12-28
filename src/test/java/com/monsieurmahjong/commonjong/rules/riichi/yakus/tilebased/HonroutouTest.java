package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class HonroutouTest
{
    private Hand anyHand = new Hand();

    private Hand completeHonroutouHand = new Hand(new MPSZNotation().getTilesFrom("111999m11p333555z"));
    private Hand incompleteHonroutouHand = new Hand(new MPSZNotation().getTilesFrom("111999m11z"));
    private Hand invalidHonroutouOnlyTerminals = new Hand(new MPSZNotation().getTilesFrom("111999m111999p11s"));
    private Hand invalidHonroutouOnlyHonours = new Hand(new MPSZNotation().getTilesFrom("11122233344455z"));
    private Hand completeNonHonroutouHand = new Hand(new MPSZNotation().getTilesFrom("123456m22345678p"));
    private Hand incompleteNonHonroutouHand = new Hand(new MPSZNotation().getTilesFrom("456789m"));

    @Test
    public void testValidityOf_HandWithFourteenHonroutouTiles_ShouldBeTrue()
    {
        Yaku honroutou = new Honroutou(completeHonroutouHand);

        var honroutouIsValid = honroutou.isValid();

        assertTrue(honroutouIsValid, "111999m11p333555z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyHonroutouTiles_ShouldBeTrue()
    {
        Yaku honroutou = new Honroutou(incompleteHonroutouHand);

        var honroutouIsValid = honroutou.isValid();

        assertTrue(honroutouIsValid, "111999m11z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyChinroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(invalidHonroutouOnlyTerminals);

        var honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "111999m111999p11s should not be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyTsuuiisouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(invalidHonroutouOnlyHonours);

        var honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "11122233344455z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonHonroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(completeNonHonroutouHand);

        var honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "123456m22345678p should not be valid for honroutou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonHonroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(incompleteNonHonroutouHand);

        var honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "456789m should not be valid for honroutou");
    }

    @Test
    public void testValueOf_Honroutou_ShouldBeTwo()
    {
        Yaku honroutou = new Honroutou(anyHand);

        var honroutouValue = honroutou.getHanValue();

        assertEquals(2, honroutouValue, "Honroutou value should be 2");
    }
}
