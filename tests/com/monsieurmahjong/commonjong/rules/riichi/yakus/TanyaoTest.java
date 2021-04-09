package com.monsieurmahjong.commonjong.rules.riichi.yakus;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class TanyaoTest
{
    private Hand anyHand = new Hand();

    private Hand completeTanyaoHand = new Hand(TileKindUtils.asHand("234567m22345678p"));
    private Hand incompleteTanyaoHand = new Hand(TileKindUtils.asHand("22345678p"));
    private Hand completeNonTanyaoHand = new Hand(TileKindUtils.asHand("123456m22345678p"));
    private Hand incompleteNonTanyaoHand = new Hand(TileKindUtils.asHand("456789m"));

    @Test
    public void testValidityOf_HandWithFourteenSimpleTiles_ShouldBeTrue()
    {
        Yaku tanyao = new Tanyao(completeTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        Assertions.assertTrue(tanyaoIsValid, "234567m22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_HandWithOnlySimpleTiles_ShouldBeTrue()
    {
        Yaku tanyao = new Tanyao(incompleteTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        Assertions.assertTrue(tanyaoIsValid, "22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(completeNonTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        Assertions.assertFalse(tanyaoIsValid, "123456m22345678p should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(incompleteNonTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        Assertions.assertFalse(tanyaoIsValid, "456789m should not be valid for tanyao");
    }

    @Test
    public void testValueOf_Tanyao_ShouldBeOne()
    {
        Yaku tanyao = new Tanyao(anyHand);

        int tanyaoValue = tanyao.getHanValue();

        Assertions.assertEquals(1, tanyaoValue, "Tanyao value should be 1");
    }
}
