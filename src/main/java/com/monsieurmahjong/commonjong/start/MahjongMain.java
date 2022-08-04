package com.monsieurmahjong.commonjong.start;

import com.monsieurmahjong.commonjong.game.mahjong.MahjongGame;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiStandardRuleSet;

public class MahjongMain
{
    public static void main(String[] args)
    {
        System.out.println("Initiating mahjong...");
        System.out.println();

        var game = new MahjongGame(null, new RiichiStandardRuleSet());
        game.startGame();
    }
}
