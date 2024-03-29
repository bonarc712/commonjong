package com.monsieurmahjong.commonjong.game.discard;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;

/**
 * "Dummy" discard strategy. The goal here is to cut the tile that is most at
 * the right of the hand. The tiles within the hand are sorted prior to
 * discarding.
 */
public class RightMostDiscardStrategy implements DiscardStrategy
{
    @Override
    public Tile discard(Hand hand)
    {
        hand.sortTiles();
        return hand.getTiles().remove(hand.getTiles().size() - 1);
    }
}
