package com.monsieurmahjong.commonjong.rules.generic;

import com.monsieurmahjong.commonjong.game.Tileset;

public abstract class RuleSet
{
    public abstract Tileset getTileSet();

    public int getUnusedTilesAmount()
    {
        return 0;
    }
}
