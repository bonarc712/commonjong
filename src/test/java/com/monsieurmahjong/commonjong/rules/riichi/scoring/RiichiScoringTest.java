package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void testTsumoScoring()
    {
        var scoring = new RiichiScoring();

        // dealer
        assertEquals(makeScoreMap(Seat.SOUTH, 2000, Seat.WEST, 2000, Seat.NORTH, 2000), scoring.getTsumoScore(3, 30, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 4000, Seat.WEST, 4000, Seat.NORTH, 4000), scoring.getTsumoScore(4, 40, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 2300, Seat.WEST, 2300, Seat.NORTH, 2300), scoring.getTsumoScore(2, 70, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 700, Seat.WEST, 700, Seat.NORTH, 700), scoring.getTsumoScore(2, 20, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 6000, Seat.WEST, 6000, Seat.NORTH, 6000), scoring.getTsumoScore(6, 30, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 8000, Seat.WEST, 8000, Seat.NORTH, 8000), scoring.getTsumoScore(9, 30, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 12000, Seat.WEST, 12000, Seat.NORTH, 12000), scoring.getTsumoScore(12, 30, Seat.EAST));
        assertEquals(makeScoreMap(Seat.SOUTH, 16000, Seat.WEST, 16000, Seat.NORTH, 16000), scoring.getTsumoScore(14, 30, Seat.EAST));

        // non-dealer
        assertEquals(makeScoreMap(Seat.EAST, 2000, Seat.WEST, 1000, Seat.NORTH, 1000), scoring.getTsumoScore(3, 30, Seat.SOUTH));
        assertEquals(makeScoreMap(Seat.NORTH, 1000, Seat.WEST, 1000, Seat.EAST, 2000), scoring.getTsumoScore(3, 30, Seat.SOUTH));
        assertEquals(makeScoreMap(Seat.SOUTH, 2000, Seat.EAST, 4000, Seat.NORTH, 2000), scoring.getTsumoScore(4, 40, Seat.WEST));
        assertEquals(makeScoreMap(Seat.SOUTH, 1200, Seat.WEST, 1200, Seat.EAST, 2300), scoring.getTsumoScore(2, 70, Seat.NORTH));
        assertEquals(makeScoreMap(Seat.EAST, 700, Seat.WEST, 400, Seat.NORTH, 400), scoring.getTsumoScore(2, 20, Seat.SOUTH));
        assertEquals(makeScoreMap(Seat.SOUTH, 3000, Seat.EAST, 6000, Seat.NORTH, 3000), scoring.getTsumoScore(6, 30, Seat.WEST));
        assertEquals(makeScoreMap(Seat.SOUTH, 4000, Seat.WEST, 4000, Seat.EAST, 8000), scoring.getTsumoScore(9, 30, Seat.NORTH));
        assertEquals(makeScoreMap(Seat.EAST, 12000, Seat.WEST, 6000, Seat.NORTH, 6000), scoring.getTsumoScore(12, 30, Seat.SOUTH));
        assertEquals(makeScoreMap(Seat.SOUTH, 8000, Seat.EAST, 16000, Seat.NORTH, 8000), scoring.getTsumoScore(14, 30, Seat.WEST));
    }

    private Map<Seat, Integer> makeScoreMap(Seat firstSeat, int firstScore, Seat secondSeat, int secondScore, Seat thirdSeat, int thirdScore)
    {
        var scoreMap = new HashMap<Seat, Integer>();
        scoreMap.put(firstSeat, firstScore);
        scoreMap.put(secondSeat, secondScore);
        scoreMap.put(thirdSeat, thirdScore);

        return scoreMap;
    }

    @Test
    public void testRonScoring()
    {
        var scoring = new RiichiScoring();

        // dealer
        assertEquals(12000, scoring.getRonScore(4, 40, true));
        assertEquals(48000, scoring.getRonScore(17, 50, true));
        assertEquals(24000, scoring.getRonScore(9, 40, true));
        assertEquals(36000, scoring.getRonScore(12, 40, true));
        assertEquals(18000, scoring.getRonScore(7, 40, true));
        assertEquals(11600, scoring.getRonScore(4, 30, true));
        assertEquals(6800, scoring.getRonScore(2, 70, true));
        assertEquals(4400, scoring.getRonScore(1, 90, true));
        assertEquals(5300, scoring.getRonScore(1, 110, true));
        assertEquals(10600, scoring.getRonScore(2, 110, true));
        assertEquals(8700, scoring.getRonScore(2, 90, true));
        assertEquals(9600, scoring.getRonScore(4, 25, true));
        assertEquals(12000, scoring.getRonScore(3, 70, true));
        assertEquals(2000, scoring.getRonScore(2, 20, true));

        assertEquals(288000, scoring.getRonScore(0, 0, 6, true));

        // nondealer
        assertEquals(8000, scoring.getRonScore(4, 40, false));
        assertEquals(32000, scoring.getRonScore(17, 50, false));
        assertEquals(16000, scoring.getRonScore(9, 40, false));
        assertEquals(24000, scoring.getRonScore(12, 40, false));
        assertEquals(12000, scoring.getRonScore(7, 40, false));
        assertEquals(7700, scoring.getRonScore(4, 30, false));
        assertEquals(4500, scoring.getRonScore(2, 70, false));
        assertEquals(2900, scoring.getRonScore(1, 90, false));
        assertEquals(3600, scoring.getRonScore(1, 110, false));
        assertEquals(7100, scoring.getRonScore(2, 110, false));
        assertEquals(5800, scoring.getRonScore(2, 90, false));
        assertEquals(6400, scoring.getRonScore(4, 25, false));
        assertEquals(8000, scoring.getRonScore(3, 70, false));
        assertEquals(1300, scoring.getRonScore(2, 20, false));

        assertEquals(192000, scoring.getRonScore(0, 0, 6, false));
    }

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
        for (var yaku : yakuList)
        {
            if (yaku.getClass().equals(yakuClass))
            {
                return true;
            }
        }
        return false;
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
