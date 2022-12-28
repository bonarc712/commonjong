package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class TsuuiisouTest
{
    private Hand anyHand = new Hand();

    private Hand completeTsuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("11122233366777z"));
    private Hand incompleteTsuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("112233z"));
    private Hand completeNonTsuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("111999p12345677s"));
    private Hand incompleteNonTsuuiisouHand = new Hand(new MPSZNotation().getTilesFrom("111p111s111z"));

    @Test
    public void testValidityOf_HandWithFourteenTsuuiisouTiles_ShouldBeTrue()
    {
        Yaku tsuuiisou = new Tsuuiisou(completeTsuuiisouHand);

        var tsuuiisouIsValid = tsuuiisou.isValid();

        assertTrue(tsuuiisouIsValid, "111999m111999p11s should be valid for tsuuiisou");
    }

    @Test
    public void testValidityOf_HandWithOnlyTsuuiisouTiles_ShouldBeTrue()
    {
        Yaku tsuuiisou = new Tsuuiisou(incompleteTsuuiisouHand);

        var tsuuiisouIsValid = tsuuiisou.isValid();

        assertTrue(tsuuiisouIsValid, "111999m11p should be valid for tsuuiisou");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonTsuuiisouTiles_ShouldBeFalse()
    {
        Yaku tsuuiisou = new Tsuuiisou(completeNonTsuuiisouHand);

        var tsuuiisouIsValid = tsuuiisou.isValid();

        assertFalse(tsuuiisouIsValid, "123456m22345678p should not be valid for tsuuiisou");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonTsuuiisouTiles_ShouldBeFalse()
    {
        Yaku tsuuiisou = new Tsuuiisou(incompleteNonTsuuiisouHand);

        var tsuuiisouIsValid = tsuuiisou.isValid();

        assertFalse(tsuuiisouIsValid, "111p11122z should not be valid for tsuuiisou");
    }

    @Test
    public void testValueOf_Tsuuiisou_ShouldBeThirteen()
    {
        Yaku tsuuiisou = new Tsuuiisou(anyHand);

        var tsuuiisouValue = tsuuiisou.getHanValue();

        assertEquals(13, tsuuiisouValue, "Tsuuiisou value should be 13");
    }

    @Test
    public void testValueOf_Tsuuiisou_ShouldBeYakuman()
    {
        Yaku tsuuiisou = new Tsuuiisou(anyHand);

        var tsuuiisouIsYakuman = tsuuiisou.isYakuman();

        assertTrue(tsuuiisouIsYakuman, "Tsuuiisou should be yakuman");
    }
}
