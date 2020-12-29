package com.monsieurmahjong.commonjong.rules.riichi;

import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.rules.generic.RuleSet;

/**
 * This is the base ruleset for riichi. All custom riichi rulesets will have
 * this one as default.
 */
public class RiichiStandardRuleSet extends RuleSet
{
    @Override
    public Tileset getTileSet()
    {
        return new RiichiTileset();
    }

    /**
     * Dead wall always contains 14 tiles
     */
    @Override
    public int getUnusedTilesAmount()
    {
        return 14;
    }
}
