package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.Scoring;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yakus;

public class RiichiScoring implements Scoring
{
    public int getScore(List<TileGroup> tileGroups, RiichiScoringParameters parameters)
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(tileGroups));
        var yakus = Yakus.getStandardYakus(hand, tileGroups, parameters);
        var hanTotal = yakus.stream().filter(Yaku::isValid).mapToInt(Yaku::getHanValue).sum();

        var scoreCalculator = new RiichiScoreCalculator();
        return scoreCalculator.getRonScore(hanTotal, 30, parameters.isPlayerDealer());
    }

    public List<Yaku> getValidYakus(List<TileGroup> tileGroups, RiichiScoringParameters parameters)
    {
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(tileGroups));
        return getValidYakus(hand, tileGroups, parameters);
    }

    public List<Yaku> getValidYakus(Hand hand, List<TileGroup> tileGroups, RiichiScoringParameters parameters)
    {
        var yakus = Yakus.getStandardYakus(hand, tileGroups, parameters);
        return yakus.stream().filter(Yaku::isValid).collect(Collectors.toList());
    }
}
