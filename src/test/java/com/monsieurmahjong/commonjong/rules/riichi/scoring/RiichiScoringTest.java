package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Seat;

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
}
