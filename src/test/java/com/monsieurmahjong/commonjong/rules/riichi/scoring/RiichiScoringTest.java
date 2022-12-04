package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Pinfu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Tanyao;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Ippatsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.MenzenTsumo;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Riichi;

public class RiichiScoringTest
{
    @Test
    public void whenHavingA1_30NonDealerHand_thenShouldBe1000()
    {
        var scoring = new RiichiScoring();
        var tileGroups = getTanyao1_30Hand();
        var scoringParameters = new RiichiScoringParametersImpl(Seat.SOUTH);
        scoringParameters.setWinOnRon(true);

        var score = scoring.getScore(tileGroups, scoringParameters);

        assertThat(score, is(1000));
    }

    @Test
    public void whenHavingA2_30NonDealerHand_thenShouldBe2000()
    {
        var scoring = new RiichiScoring();
        var tileGroups = getSanshokuDoujunHand();
        var scoringParameters = new RiichiScoringParametersImpl(Seat.SOUTH);
        scoringParameters.setWinOnRon(true);

        var score = scoring.getScore(tileGroups, scoringParameters);

        assertThat(score, is(2000));
    }

    @Test
    public void whenHavingAHanemanDealerHand_thenShouldBe18000()
    {
        var scoring = new RiichiScoring();
        var tileGroups = getChinitsuHand();
        var scoringParameters = new RiichiScoringParametersImpl(Seat.EAST);
        scoringParameters.setWinOnRon(true);

        var score = scoring.getScore(tileGroups, scoringParameters);

        assertThat(score, is(18000));
    }

    @Test
    public void whenHavingATanyaoHand_thenOnlyTanyaoIsValid()
    {
        var scoring = new RiichiScoring();
        var tileGroups = getTanyao1_30Hand();
        var scoringParameters = new RiichiScoringParametersImpl(Seat.NORTH);

        var yakus = scoring.getValidYakus(tileGroups, scoringParameters);

        assertThat(yakus, hasSize(1));
        assertTrue(yakuListContains(Tanyao.class, yakus));
    }

    private boolean yakuListContains(Class<? extends Yaku> yakuClass, List<Yaku> yakuList)
    {
        return yakuList.stream().anyMatch(yaku -> yaku.getClass().equals(yakuClass));
    }

    @Test
    public void whenHavingARiichiIppatsuTsumoTanyaoPinfuHand_thenAllTheseYakusAreValid()
    {
        var scoring = new RiichiScoring();
        var tileGroups = getTanyao1_30Hand();
        var scoringParameters = new RiichiScoringParametersImpl(Seat.NORTH);
        scoringParameters.setHasDeclaredRiichi(true);
        scoringParameters.setWinOnIppatsu(true);
        scoringParameters.setWinOnMenzenTsumo(true);
        var hand = new Hand(TileGroupUtils.getTilesFromTileGroups(tileGroups));
        hand.setWinningTile(MahjongTileKind.BAMBOOS_5);

        var yakus = scoring.getValidYakus(hand, tileGroups, scoringParameters);

        assertThat(yakus, hasSize(5));
        assertTrue(yakuListContains(Tanyao.class, yakus));
        assertTrue(yakuListContains(Pinfu.class, yakus));
        assertTrue(yakuListContains(Riichi.class, yakus));
        assertTrue(yakuListContains(Ippatsu.class, yakus));
        assertTrue(yakuListContains(MenzenTsumo.class, yakus));
    }

    private ArrayList<TileGroup> getChinitsuHand()
    {
        var tileGroups = new ArrayList<TileGroup>();
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3));
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_2));
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_3, MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_7));
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_8, MahjongTileKind.CHARACTERS_9));
        return tileGroups;
    }

    private ArrayList<TileGroup> getSanshokuDoujunHand()
    {
        var tileGroups = new ArrayList<TileGroup>();
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3, MahjongTileKind.CHARACTERS_4));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        tileGroups.add(TileGroup.of(MahjongTileKind.CIRCLES_2, MahjongTileKind.CIRCLES_3, MahjongTileKind.CIRCLES_4));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_8));
        tileGroups.add(TileGroup.of(MahjongTileKind.NORTH, MahjongTileKind.NORTH, MahjongTileKind.NORTH));
        return tileGroups;
    }

    private ArrayList<TileGroup> getTanyao1_30Hand()
    {
        var tileGroups = new ArrayList<TileGroup>();
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4));
        tileGroups.add(TileGroup.of(MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6, MahjongTileKind.BAMBOOS_7));
        tileGroups.add(TileGroup.of(MahjongTileKind.CIRCLES_3, MahjongTileKind.CIRCLES_4, MahjongTileKind.CIRCLES_5));
        tileGroups.add(TileGroup.of(MahjongTileKind.CIRCLES_4, MahjongTileKind.CIRCLES_4));
        tileGroups.add(TileGroup.of(MahjongTileKind.CHARACTERS_5, MahjongTileKind.CHARACTERS_6, MahjongTileKind.CHARACTERS_7));
        return tileGroups;
    }
}
