package com.monsieurmahjong.commonjong.game.discard;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;

/**
 * The idea with this discard strategy is to provide a discard based on an index
 * and not a tile kind in particular. This way, it is possible to choose a tile
 * based on whether it is red or not, or to use strategies such as karagiri
 * (discarding a tile that is shares the same kind as the one which was drawn).
 * 
 * The hand's tile index to discard must be set before calling the discard
 * method.
 */
public class IndexBasedDiscardStrategy implements DiscardStrategy
{
    @Override
    public Tile discard(Hand hand)
    {
        var tileIndexToDiscard = hand.getTileIndexToDiscard();
        if (tileIndexToDiscard == -1)
        {
            throw new IllegalStateException("No tile has been selected for discard");
        }
        hand.setTileIndexToDiscard(-1);
        return hand.getTiles().remove(tileIndexToDiscard);
    }
}
