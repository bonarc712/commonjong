package com.monsieurmahjong.commonjong.rules.riichi.gamestate;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;

public class RiichiGameState implements RiichiScoringParameters
{
    private GameStateLog log;
    private String targetPlayer;

    public RiichiGameState(GameStateLog log, Seat targetPlayer)
    {
        this.log = log;
        this.targetPlayer = targetPlayer.getSeatNameLowercase();
    }

    @Override
    public boolean hasPlayerDeclaredRiichi()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnIppatsu()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnTenhou()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnChihou()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnRenhou()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnMenzenTsumo()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnRinshanKaihou()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnChankan()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnHaitei()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnHoutei()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnNagashiMangan()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean doesPlayerWinOnRon()
    {
        return log.getLogs().stream().anyMatch(ron -> ron.contains(targetPlayer + "-ron"));
    }

    @Override
    public boolean doesPlayerWinOnTsumo()
    {
        return log.getLogs().stream().anyMatch(tsumo -> tsumo.contains(targetPlayer + "-tsumo"));
    }
}
