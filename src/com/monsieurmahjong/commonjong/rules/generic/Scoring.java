package com.monsieurmahjong.commonjong.rules.generic;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.mahjong.MahjongGame;

public interface Scoring
{
    public int getScore(MahjongGame game, Hand hand);
}
