package com.monsieurmahjong.commonjong.game.statelog;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getLogs()
    {
        return logs;
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
}
