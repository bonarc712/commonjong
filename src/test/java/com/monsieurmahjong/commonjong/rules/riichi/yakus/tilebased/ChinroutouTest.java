package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class ChinroutouTest
{
    private Hand anyHand = new Hand();

    private Hand completeChinroutouHand = new Hand(TileKindUtils.asHand("111999m111999p11s"));
    private Hand incompleteChinroutouHand = new Hand(TileKindUtils.asHand("111999m11p"));
    private Hand completeNonChinroutouHand = new Hand(TileKindUtils.asHand("123456m22345678p"));
    private Hand incompleteNonChinroutouHand = new Hand(TileKindUtils.asHand("111p11122z"));

    @Test
    public void testValidityOf_HandWithFourteenChinroutouTiles_ShouldBeTrue()
    {
        Yaku chinroutou = new Chinroutou(completeChinroutouHand);

        var chinroutouIsValid = chinroutou.isValid();

        assertTrue(chinroutouIsValid, "111999m111999p11s should be valid for chinroutou");
    }

    @Test
    public void testValidityOf_HandWithOnlyChinroutouTiles_ShouldBeTrue()
    {
        Yaku chinroutou = new Chinroutou(incompleteChinroutouHand);

        var chinroutouIsValid = chinroutou.isValid();

        assertTrue(chinroutouIsValid, "111999m11p should be valid for chinroutou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonChinroutouTiles_ShouldBeFalse()
    {
        Yaku chinroutou = new Chinroutou(completeNonChinroutouHand);

        var chinroutouIsValid = chinroutou.isValid();

        assertFalse(chinroutouIsValid, "123456m22345678p should not be valid for chinroutou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonChinroutouTiles_ShouldBeFalse()
    {
        Yaku chinroutou = new Chinroutou(incompleteNonChinroutouHand);

        var chinroutouIsValid = chinroutou.isValid();

        assertFalse(chinroutouIsValid, "111p11122z should not be valid for chinroutou");
    }

    @Test
    public void testValueOf_Chinroutou_ShouldBeThirteen()
    {
        Yaku chinroutou = new Chinroutou(anyHand);

        var chinroutouValue = chinroutou.getHanValue();

        assertEquals(13, chinroutouValue, "Chinroutou value should be 13");
    }

    @Test
    public void testValueOf_Chinroutou_ShouldBeYakuman()
    {
        Yaku chinroutou = new Chinroutou(anyHand);

        var chinroutouIsYakuman = chinroutou.isYakuman();

        assertTrue(chinroutouIsYakuman, "Chinroutou should be yakuman");
    }
}
