package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
        var hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileTanki);
        var waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupTanki);

        var isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a tanki wait");
    }

    @Test
    public void withPenchanWait_WaitFu_ShouldBeCounted()
    {
        var hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTilePenchan);
        var waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupPenchan);

        var isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a penchan wait");
    }

    @Test
    public void withKanchanWait_WaitFu_ShouldBeCounted()
    {
        var hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileKanchan);
        var waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupKanchan);

        var isValid = waitFu.isValid();

        assertTrue(isValid, "Wait fu should be counted with a kanchan wait");
    }

    @Test
    public void withShanponWait_WaitFu_ShouldNotBeCounted()
    {
        var hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileShanpon);
        var waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupShanpon);

        var isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a shanpon wait");
    }

    @Test
    public void withRyanmenWait_WaitFu_ShouldNotBeCounted()
    {
        var hand = Hand.of(completeNonChiitoitsuHandGroups);
        hand.setWinningTile(winningTileRyanmen);
        var waitFu = new WaitFu(hand, completeNonChiitoitsuHandGroups, winningTileGroupRyanmen);

        var isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a ryanmen wait");
    }

    @Test
    public void withChiitoitsu_WaitFu_ShouldNotBeCounted()
    {
        var waitFu = new WaitFu(Hand.of(completeChiitoitsuHandGroups), completeChiitoitsuHandGroups, winningTileGroupTanki);

        var isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a chiitoi hand");
    }

    @Test
    public void withJuusanmenmachiKokushi_WaitFu_ShouldNotBeCounted()
    {
        var hand = Hand.of(completeKokushiHandGroups);
        hand.setWinningTile(winningTileKokushi);
        var waitFu = new WaitFu(hand, completeKokushiHandGroups, winningTileGroupKokushi);

        var isValid = waitFu.isValid();

        assertFalse(isValid, "Wait fu should not be counted with a juusanmenmachi (13-way wait) kokushi hand");
    }

    @Test
    public void fuForWait_ShouldAlwaysBeTwo()
    {
        var waitFu = new WaitFu(null, null, null);

        var fuValue = waitFu.getFuValue();

        assertEquals(2, fuValue, "Wait fu should be 2");
    }
}
