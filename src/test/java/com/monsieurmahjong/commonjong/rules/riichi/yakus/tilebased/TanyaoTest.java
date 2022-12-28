package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class TanyaoTest
{
    private Hand anyHand = new Hand();

    private Hand completeTanyaoHand = new Hand(new MPSZNotation().getTilesFrom("234567m22345678p"));
    private Hand incompleteTanyaoHand = new Hand(new MPSZNotation().getTilesFrom("22345678p"));
    private Hand completeNonTanyaoHand = new Hand(new MPSZNotation().getTilesFrom("123456m22345678p"));
    private Hand incompleteNonTanyaoHand = new Hand(new MPSZNotation().getTilesFrom("456789m"));

    @Test
    public void testValidityOf_HandWithFourteenSimpleTiles_ShouldBeTrue()
    {
        Yaku tanyao = new Tanyao(completeTanyaoHand);

        var tanyaoIsValid = tanyao.isValid();

        assertTrue(tanyaoIsValid, "234567m22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_HandWithOnlySimpleTiles_ShouldBeTrue()
    {
        Yaku tanyao = new Tanyao(incompleteTanyaoHand);

        var tanyaoIsValid = tanyao.isValid();

        assertTrue(tanyaoIsValid, "22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(completeNonTanyaoHand);

        var tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "123456m22345678p should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(incompleteNonTanyaoHand);

        var tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "456789m should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_OpenTanyaoShapeWithKuitanNashi_ShouldBeFalse()
    {
        var meld = new ArrayList<Tile>();
        meld.add(new Tile(MahjongTileKind.CHARACTERS_2));
        meld.add(new Tile(MahjongTileKind.CHARACTERS_3));
        meld.add(new Tile(MahjongTileKind.CHARACTERS_4));
        completeTanyaoHand.setMelds(Arrays.asList(meld));
        var tanyao = new Tanyao(completeTanyaoHand);
        tanyao.setKuitan(false);

        var tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "234567m22345678p with open 234m and kuitan nashi should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_ClosedTanyaoShapeWithKuitanNashi_ShouldBeTrue()
    {
        completeTanyaoHand.setMelds(Collections.emptyList());
        var tanyao = new Tanyao(completeTanyaoHand);
        tanyao.setKuitan(false);

        var tanyaoIsValid = tanyao.isValid();

        assertTrue(tanyaoIsValid, "234567m22345678p with no melds and kuitan nashi should be valid for tanyao");
    }

    @Test
    public void testValueOf_Tanyao_ShouldBeOne()
    {
        Yaku tanyao = new Tanyao(anyHand);

        var tanyaoValue = tanyao.getHanValue();

        assertEquals(1, tanyaoValue, "Tanyao value should be 1");
    }

    @Test
    public void testValueOf_Tanyao_ShouldNotBeYakuman()
    {
        Yaku tanyao = new Tanyao(anyHand);

        var tanyaoIsYakuman = tanyao.isYakuman();

        assertFalse(tanyaoIsYakuman, "Tanyao should not be yakuman");
    }
}
