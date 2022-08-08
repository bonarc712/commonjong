package com.monsieurmahjong.commonjong.rules.generic;

import com.monsieurmahjong.commonjong.game.Tileset;

public interface RuleSet
{
    public Tileset getTileSet();

    public int getUnusedTilesAmount();

    public Scoring getScoring();
}
