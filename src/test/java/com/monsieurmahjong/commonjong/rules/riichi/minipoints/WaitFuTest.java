package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.monsieurmahjong.commonjong.rules.riichi.minipoints.WaitFu;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class WaitFuTest
{
    private List<TileGroup> completeChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("11m", "33m", "77m", "22p", "99p", "99s", "33z");
    private List<TileGroup> completeKokushiHandGroups = TileGroupUtils.tileGroupsOf("1m", "9m", "1p", "9p", "1s", "9s", "11z", "2z", "3z", "4z", "5z", "6z", "7z");
    private List<TileGroup> completeNonChiitoitsuHandGroups = TileGroupUtils.tileGroupsOf("123m", "444m", "22p", "345p", "678p");

    private TileGroup winningTileGroupTanki = TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.CIRCLES_2);
    private MahjongTileKind winningTileTanki = MahjongTileKind.CIRCLES_2;

    private TileGroup winningTileGroupPenchan = TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3);
    private MahjongTileKind winningTilePenchan = MahjongTileKind.CHARACTERS_3;

    private TileGroup winningTileGroupKanchan = TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3);
    private MahjongTileKind winningTileKanchan = MahjongTileKind.CHARACTERS_2;

    private TileGroup winningTileGroupShanpon = TileGroup.of(MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_4);
    private MahjongTileKind winningTileShanpon = MahjongTileKind.CHARACTERS_4;

    private TileGroup winningTileGroupRyanmen = TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3);
    private MahjongTileKind winningTileRyanmen = MahjongTileKind.CHARACTERS_1;

    private TileGroup winningTileGroupKokushi = TileGroup.of(MahjongTileKind.EAST, MahjongTileKind.EAST);
    private MahjongTileKind winningTileKokushi = MahjongTileKind.EAST;

    @Test
    public void withTankiWait_WaitFu_ShouldBeCounted()
    {
        Hand hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileTanki);
        WaitFu waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupTanki);

        boolean isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a tanki wait");
    }

    @Test
    public void withPenchanWait_WaitFu_ShouldBeCounted()
    {
        Hand hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTilePenchan);
        WaitFu waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupPenchan);

        boolean isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a penchan wait");
    }

    @Test
    public void withKanchanWait_WaitFu_ShouldBeCounted()
    {
        Hand hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileKanchan);
        WaitFu waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupKanchan);

        boolean isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a kanchan wait");
    }

    @Test
    public void withShanponWait_WaitFu_ShouldNotBeCounted()
    {
        Hand hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileShanpon);
        WaitFu waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupShanpon);

        boolean isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a shanpon wait");
    }

    @Test
    public void withRyanmenWait_WaitFu_ShouldNotBeCounted()
    {
        Hand hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileRyanmen);
        WaitFu waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupRyanmen);

        boolean isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a ryanmen wait");
    }

    @Test
    public void withChiitoitsu_WaitFu_ShouldNotBeCounted()
    {
        WaitFu waitFu = new WaitFu(Hand.of(completeChiitoitsuHandGroups), completeChiitoitsuHandGroups, winningTileGroupTanki);

        boolean isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a chiitoi hand");
    }

    @Test
    public void withJuusanmenmachiKokushi_WaitFu_ShouldNotBeCounted()
    {
        Hand hand = Hand.of(completeKokushiHandGroups);
        hand.setWinningTile(winningTileKokushi);
        WaitFu waitFu = new WaitFu(hand, completeKokushiHandGroups, winningTileGroupKokushi);

        boolean isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a juusanmenmachi (13-way wait) kokushi hand");
    }

    @Test
    public void fuForWait_ShouldAlwaysBeTwo()
    {
        WaitFu waitFu = new WaitFu(null, null, null);

        int fuValue = waitFu.getFuValue();

        assertEquals(2, fuValue, "Wait fu should be 2");
    }
}
