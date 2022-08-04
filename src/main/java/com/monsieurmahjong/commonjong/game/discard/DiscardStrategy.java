package com.monsieurmahjong.commonjong.game.discard;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Tile;

public interface DiscardStrategy
{
    public Tile discard(Hand hand);
}
