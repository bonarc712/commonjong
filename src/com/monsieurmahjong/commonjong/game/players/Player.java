package com.monsieurmahjong.commonjong.game.players;

import java.util.List;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.discard.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileOrderingComparator;
import com.monsieurmahjong.commonjong.rules.generic.waits.WaitShapeEngine;

public class Player
{
    private String name;
    private Hand hand;
    private Seat seat; // still needed?

    private DiscardStrategy discardStrategy;

    public Player()
    {
        hand = new Hand();
        discardStrategy = new RightMostDiscardStrategy();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Seat getSeat()
    {
        return seat;
    }

    public void setSeat(Seat seat)
    {
        this.seat = seat;
    }

    public void setDiscardStrategy(DiscardStrategy strategy)
    {
        discardStrategy = strategy;
    }

    public void draw(Tile tile)
    {
        hand.getTiles().add(tile);
    }

    public Tile discard()
    {
        return discardStrategy.discard(hand);
    }

    public void call()
    {

    }

    public List<Tile> getHand()
    {
        return hand.getTiles();
    }

    public void showHand()
    {
        hand.getTiles().sort(new MahjongTileOrderingComparator());
        WaitShapeEngine engine = new WaitShapeEngine(hand);

        System.out.println("Je suis " + name);
        System.out.println("J'ai " + hand);
        System.out.println("Ma main a un shanten de " + engine.getShanten());
        System.out.println("Mes attentes sont " + engine.getWait());
        System.out.println();
    }
}
