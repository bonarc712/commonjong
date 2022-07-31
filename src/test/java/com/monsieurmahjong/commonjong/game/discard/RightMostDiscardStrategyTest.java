package com.monsieurmahjong.commonjong.game.discard;

import com.monsieurmahjong.commonjong.game.discard.DiscardStrategy;
import com.monsieurmahjong.commonjong.game.discard.RightMostDiscardStrategy;
import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class RightMostDiscardStrategyTest
{
    @Test
    public void testDiscard()
    {
        DiscardStrategy strategy = new RightMostDiscardStrategy();

        Tile discardedTile = strategy.discard(new Hand(TileKindUtils.asHand("123456789s123p11m")));

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_9, discardedTile.getTileKind(), "Wrong discard for 123456789s123p11m, it should be 9s");

        discardedTile = strategy.discard(new Hand(TileKindUtils.asHand("77112233445566s")));

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_7, discardedTile.getTileKind(), "Wrong discard for 77112233445566s, it should be 7s");

        discardedTile = strategy.discard(new Hand(TileKindUtils.asHand("1234567z111m222p3s")));

        Assertions.assertEquals(MahjongTileKind.RED, discardedTile.getTileKind(), "Wrong discard for 1234567z111m222p3s, it should be 7z");

        discardedTile = strategy.discard(new Hand(TileKindUtils.asHand("1z1m1p1234567899s")));

        Assertions.assertEquals(MahjongTileKind.EAST, discardedTile.getTileKind(), "Wrong discard for 1z1m1p1234567899s, it should be 1z");

        discardedTile = strategy.discard(new Hand(TileKindUtils.asHand("9998887776665m")));

        Assertions.assertEquals(MahjongTileKind.CHARACTERS_9, discardedTile.getTileKind(), "Wrong discard for 9998887776665m, it should be 9m");
    }
}
