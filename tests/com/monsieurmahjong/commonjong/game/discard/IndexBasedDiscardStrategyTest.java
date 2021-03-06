package com.monsieurmahjong.commonjong.game.discard;

import org.junit.jupiter.api.*;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class IndexBasedDiscardStrategyTest
{
    @Test
    public void testDiscard()
    {
        DiscardStrategy strategy = new IndexBasedDiscardStrategy();

        Hand hand = new Hand(TileKindUtils.asHand("123456789s123p11m"));
        hand.setTileIndexToDiscard(10);
        Tile discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.CIRCLES_2, discardedTile.getTileKind(), "Wrong discard for 123456789s123p11m, it should be 2p");

        hand = new Hand(TileKindUtils.asHand("77112233445566s"));
        hand.setTileIndexToDiscard(5);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_2, discardedTile.getTileKind(), "Wrong discard for 77112233445566s, it should be 2s");

        hand = new Hand(TileKindUtils.asHand("1234567z111m222p3s"));
        hand.setTileIndexToDiscard(13);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_3, discardedTile.getTileKind(), "Wrong discard for 1234567z111m222p3s, it should be 3s");

        hand = new Hand(TileKindUtils.asHand("1z1m1p1234567899s"));
        hand.setTileIndexToDiscard(0);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.EAST, discardedTile.getTileKind(), "Wrong discard for 1z1m1p1234567899s, it should be 1z");

        hand = new Hand(TileKindUtils.asHand("9998887776665m"));
        hand.setTileIndexToDiscard(12);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.CHARACTERS_5, discardedTile.getTileKind(), "Wrong discard for 9998887776665m, it should be 5m");

        Assertions.assertThrows(IllegalStateException.class, () -> strategy.discard(new Hand(TileKindUtils.asHand("9998887776665m"))));
    }
}
