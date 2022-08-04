package com.monsieurmahjong.commonjong.game.statelog;

import java.util.ArrayList;
import java.util.List;

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

    public boolean doesPlayerWinOnIppatsu(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnTenhou(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnChihou(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnRenhou(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnMenzenTsumo(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnRinshanKaihou(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnChankan(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnHaitei(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnHoutei(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnNagashiMangan(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnRon(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean doesPlayerWinOnTsumo(Seat playerWind)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
