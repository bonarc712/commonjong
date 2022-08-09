package com.monsieurmahjong.commonjong.rules.generic;

import com.monsieurmahjong.commonjong.game.Tileset;

public interface Ruleset
{
    public Tileset getTileset();

    public int getUnusedTilesAmount();

    public Scoring getScoring();
}
