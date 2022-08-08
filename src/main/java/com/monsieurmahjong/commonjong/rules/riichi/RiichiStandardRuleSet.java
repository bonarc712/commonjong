package com.monsieurmahjong.commonjong.rules.riichi;

import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.rules.generic.RuleSet;
import com.monsieurmahjong.commonjong.rules.generic.Scoring;

/**
 * This is the base ruleset for riichi. All custom riichi rulesets will have
 * this one as default.
 */
public class RiichiStandardRuleSet implements RuleSet
{
    private Tileset tileset;
    private Scoring scoring;

    public RiichiStandardRuleSet(Tileset tileset, Scoring scoring)
    {
        this.tileset = tileset;
        this.scoring = scoring;
    }

    @Override
    public Tileset getTileSet()
    {
        return tileset;
    }

    /**
     * Dead wall always contains 14 tiles
     */
    @Override
    public int getUnusedTilesAmount()
    {
        return 14;
    }

    @Override
    public Scoring getScoring()
    {
        return scoring;
    }
}
