package com.monsieurmahjong.commonjong.game.discard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;

public class RightMostDiscardStrategyTest
{
    @Test
    public void testDiscard()
    {
        DiscardStrategy strategy = new RightMostDiscardStrategy();
        var mpsz = new MPSZNotation();

        var discardedTile = strategy.discard(new Hand(mpsz.getTilesFrom("123456789s123p11m")));

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_9, discardedTile.getTileKind(), "Wrong discard for 123456789s123p11m, it should be 9s");

        discardedTile = strategy.discard(new Hand(mpsz.getTilesFrom("77112233445566s")));

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_7, discardedTile.getTileKind(), "Wrong discard for 77112233445566s, it should be 7s");

        discardedTile = strategy.discard(new Hand(mpsz.getTilesFrom("1234567z111m222p3s")));

        Assertions.assertEquals(MahjongTileKind.RED, discardedTile.getTileKind(), "Wrong discard for 1234567z111m222p3s, it should be 7z");

        discardedTile = strategy.discard(new Hand(mpsz.getTilesFrom("1z1m1p1234567899s")));

        Assertions.assertEquals(MahjongTileKind.EAST, discardedTile.getTileKind(), "Wrong discard for 1z1m1p1234567899s, it should be 1z");

        discardedTile = strategy.discard(new Hand(mpsz.getTilesFrom("9998887776665m")));

        Assertions.assertEquals(MahjongTileKind.CHARACTERS_9, discardedTile.getTileKind(), "Wrong discard for 9998887776665m, it should be 9m");
    }
}
