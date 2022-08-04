package com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;

public class HonitsuTest
{
    private Hand anyHand = mock(Hand.class);

    private Hand completeHonitsuHand = new Hand(TileKindUtils.asHand("111234567m11177z"));
    private Hand incompleteHonitsuHand = new Hand(TileKindUtils.asHand("111234m77z"));
    private Hand completeNonHonitsuHand = new Hand(TileKindUtils.asHand("123456m22345678p"));
    private Hand chinitsuHand = new Hand(TileKindUtils.asHand("11123445678999m"));
    private Hand tsuuiisouHand = new Hand(TileKindUtils.asHand("11122233344455z"));
    private Hand circlesHonitsuHand = new Hand(TileKindUtils.asHand("11123456p111333z"));
    private Hand bamboosHonitsuHand = new Hand(TileKindUtils.asHand("44456789s111666z"));
    private Hand terminalLessHonitsuHand = new Hand(TileKindUtils.asHand("34445678s111666z"));
    private Hand incompleteNonHonitsuHand = new Hand(TileKindUtils.asHand("111p111s22z"));

    @Test
    public void testValidityOf_HandWithFourteenHonitsuTiles_ShouldBeTrue()
    {
        Yaku honitsu = new Honitsu(completeHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertTrue(honitsuIsValid, "111234567m11177z should be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithOnlyHonitsuTiles_ShouldBeTrue()
    {
        Yaku honitsu = new Honitsu(incompleteHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertTrue(honitsuIsValid, "111234m77z should be valid for honitsu");
    }

    @Test
    public void testValidityOf_CompleteHandWithNonHonitsuTiles_ShouldBeFalse()
    {
        Yaku honitsu = new Honitsu(completeNonHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertFalse(honitsuIsValid, "123456m22345678p should not be valid for honitsu");
    }

    @Test
    public void testValidityOf_IncompleteHandWithNonHonitsuTiles_ShouldBeFalse()
    {
        Yaku honitsu = new Honitsu(incompleteNonHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertFalse(honitsuIsValid, "111p111s22z should not be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithChinitsuTiles_ShouldBeFalse()
    {
        Yaku honitsu = new Honitsu(chinitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertFalse(honitsuIsValid, "11123445678999m should not be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithTsuuiisouTiles_ShouldBeFalse()
    {
        Yaku honitsu = new Honitsu(tsuuiisouHand);

        var honitsuIsValid = honitsu.isValid();

        assertFalse(honitsuIsValid, "11122233344455z should not be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithCirclesHonitsuTiles_ShouldBeTrue()
    {
        Yaku honitsu = new Honitsu(circlesHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertTrue(honitsuIsValid, "11123456p111333z should be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithBamboosHonitsuTiles_ShouldBeTrue()
    {
        Yaku honitsu = new Honitsu(bamboosHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertTrue(honitsuIsValid, "44456789s111666z should be valid for honitsu");
    }

    @Test
    public void testValidityOf_HandWithTerminalLessHonitsuTiles_ShouldBeTrue()
    {
        Yaku honitsu = new Honitsu(terminalLessHonitsuHand);

        var honitsuIsValid = honitsu.isValid();

        assertTrue(honitsuIsValid, "34445678s111666z should be valid for honitsu");
    }

    @Test
    public void testValueOf_HonitsuWhileOpen_ShouldBeTwo()
    {
        Yaku honitsu = new Honitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(true);

        var honitsuValue = honitsu.getHanValue();

        assertEquals(2, honitsuValue, "Honitsu value should be 2");
    }

    @Test
    public void testValueOf_HonitsuWhileClosed_ShouldBeThree()
    {
        Yaku honitsu = new Honitsu(anyHand);
        when(anyHand.isOpen()).thenReturn(false);

        var honitsuValue = honitsu.getHanValue();

        assertEquals(3, honitsuValue, "Honitsu value should be 3");
    }
}
