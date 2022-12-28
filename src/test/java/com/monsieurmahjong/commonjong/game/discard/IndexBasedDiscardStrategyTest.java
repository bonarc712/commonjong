package com.monsieurmahjong.commonjong.game.discard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.MPSZNotation;

public class IndexBasedDiscardStrategyTest
{
    @Test
    public void testDiscard()
    {
        DiscardStrategy strategy = new IndexBasedDiscardStrategy();
        var mpsz = new MPSZNotation();

        var hand = new Hand(mpsz.getTilesFrom("123456789s123p11m"));
        hand.setTileIndexToDiscard(10);
        var discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.CIRCLES_2, discardedTile.getTileKind(), "Wrong discard for 123456789s123p11m, it should be 2p");

        hand = new Hand(mpsz.getTilesFrom("77112233445566s"));
        hand.setTileIndexToDiscard(5);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_2, discardedTile.getTileKind(), "Wrong discard for 77112233445566s, it should be 2s");

        hand = new Hand(mpsz.getTilesFrom("1234567z111m222p3s"));
        hand.setTileIndexToDiscard(13);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.BAMBOOS_3, discardedTile.getTileKind(), "Wrong discard for 1234567z111m222p3s, it should be 3s");

        hand = new Hand(mpsz.getTilesFrom("1z1m1p1234567899s"));
        hand.setTileIndexToDiscard(0);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.EAST, discardedTile.getTileKind(), "Wrong discard for 1z1m1p1234567899s, it should be 1z");

        hand = new Hand(mpsz.getTilesFrom("9998887776665m"));
        hand.setTileIndexToDiscard(12);
        discardedTile = strategy.discard(hand);

        Assertions.assertEquals(MahjongTileKind.CHARACTERS_5, discardedTile.getTileKind(), "Wrong discard for 9998887776665m, it should be 5m");

        Assertions.assertThrows(IllegalStateException.class, () -> strategy.discard(new Hand(mpsz.getTilesFrom("9998887776665m"))));
    }
}
