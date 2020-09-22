package com.monsieurmahjong.commonjong.rules.generic;

import java.util.List;

import com.monsieurmahjong.commonjong.game.*;

public class WaitShapeEngine
{
    private Hand hand;

    public WaitShapeEngine(Hand hand)
    {
        this.hand = hand;
    }

    public WaitShapeEngine(List<Tile> tileList)
    {
        hand = new Hand();
        hand.setTiles(tileList);
    }

    public List<Tile> getWait()
    {
        // TODO implement this, consider tenpai hand
        return null;
    }
}
