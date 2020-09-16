package com.monsieurmahjong.commonjong.game;

import java.util.*;

import com.monsieurmahjong.commonjong.game.mahjong.*;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileOrderingComparator;

public class Player
{
    private String name;
    private List<Tile> hand;
    @Deprecated // player should not have direct access to the game. It should be passed to him via play method
    private MahjongGame game;

    public Player(MahjongGame game)
    {
        hand = new ArrayList<>();
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

    public void play()
    {
        hand.add(game.getTileset().draw());
    }

    public void showHand()
    {
        hand.sort(new MahjongTileOrderingComparator());
        System.out.println("Je suis " + name);
        System.out.println("J'ai " + hand);
        System.out.println("Ma main a un shanten de " + MahjongShantenCounter.shantenCount(hand));
        System.out.println();
    }
}
