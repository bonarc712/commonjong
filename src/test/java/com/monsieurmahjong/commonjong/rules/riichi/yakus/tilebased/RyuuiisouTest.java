package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class RyuuiisouTest
{
    private Hand anyHand = new Hand();

    private Hand completeRyuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("22234666888s666z"));
    private Hand incompleteRyuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("22234666s"));
    private Hand completeNonRyuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("11122233344s777z"));
    private Hand incompleteNonRyuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("222345666s"));

    @Test
    public void testValidityOf_HandWithFourteenRyuuiisouTiles_ShouldBeTrue()
    {
        Yaku ryuuiisou = new Ryuuiisou(completeRyuuiisouHand);

        var ryuuiisouIsValid = ryuuiisou.isValid();

        assertTrue(ryuuiisouIsValid, "22234666888s666z should be valid for ryuuiisou");
    }

    @Test
    public void testValidityOf_HandWithOnlyRyuuiisouTiles_ShouldBeTrue()
    {
        Yaku ryuuiisou = new Ryuuiisou(incompleteRyuuiisouHand);

        var ryuuiisouIsValid = ryuuiisou.isValid();

        assertTrue(ryuuiisouIsValid, "22234666s should be valid for ryuuiisou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonRyuuiisouTiles_ShouldBeFalse()
    {
        Yaku ryuuiisou = new Ryuuiisou(completeNonRyuuiisouHand);

        var ryuuiisouIsValid = ryuuiisou.isValid();

        assertFalse(ryuuiisouIsValid, "11122233344s777z should not be valid for ryuuiisou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonRyuuiisouTiles_ShouldBeFalse()
    {
        Yaku ryuuiisou = new Ryuuiisou(incompleteNonRyuuiisouHand);

        var ryuuiisouIsValid = ryuuiisou.isValid();

        assertFalse(ryuuiisouIsValid, "222345666s should not be valid for ryuuiisou");
    }

    @Test
    public void testValueOf_Ryuuiisou_ShouldBeThirteen()
    {
        Yaku ryuuiisou = new Ryuuiisou(anyHand);

        var ryuuiisouValue = ryuuiisou.getHanValue();

        assertEquals(13, ryuuiisouValue, "Ryuuiisou value should be 13");
    }

    @Test
    public void testValueOf_Ryuuiisou_ShouldBeYakuman()
    {
        Yaku ryuuiisou = new Ryuuiisou(anyHand);

        var ryuuiisouIsYakuman = ryuuiisou.isYakuman();

        assertTrue(ryuuiisouIsYakuman, "Ryuuiisou should be yakuman");
    }
}