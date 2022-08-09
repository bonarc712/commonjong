package com.monsieurmahjong.commonjong.start;

import com.monsieurmahjong.commonjong.game.mahjong.MahjongGame;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiStandardRuleset;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiTileset;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoring;

public class MahjongMain
{
    public static void main(String[] args)
    {
        System.out.println("Initiating mahjong...");
        System.out.println();

        var game = new MahjongGame(null, new RiichiStandardRuleset(new RiichiTileset(), new RiichiScoring()));
        game.startGame();
    }
}
