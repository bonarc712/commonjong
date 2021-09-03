package com.monsieurmahjong.commonjong.game.statelog;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Seat;

public class GameStateLog
{
    private List<String> logs;

    public GameStateLog()
    {
        logs = new ArrayList<>();
    }

    public void log(String log)
    {
        logs.add(log);
    }

    public void showWholeLog()
    {
        System.out.println("Game state log:");
        System.out.println();

        for (String log : logs)
        {
            System.out.println(log);
        }
    }

    public boolean hasPlayerDeclaredRiichi(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
