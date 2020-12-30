package com.monsieurmahjong.commonjong.game.discard;

import com.monsieurmahjong.commonjong.game.*;

/**
 * "Dummy" discard strategy. The goal here is to cut the tile that is most at the
 * right of the hand.
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
