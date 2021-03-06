package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.*;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Honroutou;

public class HonroutouTest
{
    private Hand anyHand = new Hand();

    private Hand completeHonroutouHand = new Hand(TileKindUtils.asHand("111999m11p333555z"));
    private Hand incompleteHonroutouHand = new Hand(TileKindUtils.asHand("111999m11z"));
    private Hand invalidHonroutouOnlyTerminals = new Hand(TileKindUtils.asHand("111999m111999p11s"));
    private Hand invalidHonroutouOnlyHonours = new Hand(TileKindUtils.asHand("11122233344455z"));
    private Hand completeNonHonroutouHand = new Hand(TileKindUtils.asHand("123456m22345678p"));
    private Hand incompleteNonHonroutouHand = new Hand(TileKindUtils.asHand("456789m"));

    @Test
    public void testValidityOf_HandWithFourteenHonroutouTiles_ShouldBeTrue()
    {
        Yaku honroutou = new Honroutou(completeHonroutouHand);

        boolean honroutouIsValid = honroutou.isValid();

        assertTrue(honroutouIsValid, "111999m11p333555z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyHonroutouTiles_ShouldBeTrue()
    {
        Yaku honroutou = new Honroutou(incompleteHonroutouHand);

        boolean honroutouIsValid = honroutou.isValid();

        assertTrue(honroutouIsValid, "111999m11z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyChinroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(invalidHonroutouOnlyTerminals);

        boolean honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "111999m111999p11s should not be valid for honroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyTsuuiisouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(invalidHonroutouOnlyHonours);

        boolean honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "11122233344455z should be valid for honroutou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonHonroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(completeNonHonroutouHand);

        boolean honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "123456m22345678p should not be valid for honroutou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonHonroutouTiles_ShouldBeFalse()
    {
        Yaku honroutou = new Honroutou(incompleteNonHonroutouHand);

        boolean honroutouIsValid = honroutou.isValid();

        assertFalse(honroutouIsValid, "456789m should not be valid for honroutou");
    }

    @Test
    public void testValueOf_Honroutou_ShouldBeTwo()
    {
        Yaku honroutou = new Honroutou(anyHand);

        int honroutouValue = honroutou.getHanValue();

        assertEquals(2, honroutouValue, "Honroutou value should be 2");
    }
}
