package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

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

        assertTrue(tanyaoIsValid, "234567m22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_HandWithOnlySimpleTiles_ShouldBeTrue()
    {
        Yaku tanyao = new Tanyao(incompleteTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        assertTrue(tanyaoIsValid, "22345678p should be valid for tanyao");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(completeNonTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "123456m22345678p should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonSimpleTiles_ShouldBeFalse()
    {
        Yaku tanyao = new Tanyao(incompleteNonTanyaoHand);

        boolean tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "456789m should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_OpenTanyaoShapeWithKuitanNashi_ShouldBeFalse()
    {
        List<Tile> meld = new ArrayList<>();
        meld.add(new Tile(MahjongTileKind.CHARACTERS_2));
        meld.add(new Tile(MahjongTileKind.CHARACTERS_3));
        meld.add(new Tile(MahjongTileKind.CHARACTERS_4));
        completeTanyaoHand.setMelds(Arrays.asList(meld));
        Tanyao tanyao = new Tanyao(completeTanyaoHand);
        tanyao.setKuitan(false);

        boolean tanyaoIsValid = tanyao.isValid();

        assertFalse(tanyaoIsValid, "234567m22345678p with open 234m and kuitan nashi should not be valid for tanyao");
    }

    @Test
    public void testValidityOf_ClosedTanyaoShapeWithKuitanNashi_ShouldBeTrue()
    {
        completeTanyaoHand.setMelds(Collections.EMPTY_LIST);
        Tanyao tanyao = new Tanyao(completeTanyaoHand);
        tanyao.setKuitan(false);

        boolean tanyaoIsValid = tanyao.isValid();

        assertTrue(tanyaoIsValid, "234567m22345678p with no melds and kuitan nashi should be valid for tanyao");
    }

    @Test
    public void testValueOf_Tanyao_ShouldBeOne()
    {
        Yaku tanyao = new Tanyao(anyHand);

        int tanyaoValue = tanyao.getHanValue();

        assertEquals(1, tanyaoValue, "Tanyao value should be 1");
    }

    @Test
    public void testValueOf_Tanyao_ShouldNotBeYakuman()
    {
        Yaku tanyao = new Tanyao(anyHand);

        boolean tanyaoIsYakuman = tanyao.isYakuman();

        assertFalse(tanyaoIsYakuman, "Tanyao should not be yakuman");
    }
}
