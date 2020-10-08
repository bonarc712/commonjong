package com.monsieurmahjong.commonjong.game;

import java.util.List;

import com.monsieurmahjong.commonjong.game.mahjong.MahjongGame;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileOrderingComparator;

public class Player
{
    private String name;
    private Hand hand;
    private Seat seat;
    @Deprecated // player should not have direct access to the game. It should be passed to him via play method
    private MahjongGame game;

    public Player(MahjongGame game)
    {
        hand = new Hand();
        this.game = game;
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

    public void play()
    {
        hand.getTiles().add(game.getTileset().draw());
    }

    public void draw(MahjongGame game)
    {
        // TODO draw from here
        game.getTileset().draw();
    }

    public void discard()
    {

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
        System.out.println("Je suis " + name);
        System.out.println("J'ai " + hand);
        // System.out.println("Ma main a un shanten de " + MahjongShantenCounter.shantenCount(hand.getTiles()));
        System.out.println();
    }
}
