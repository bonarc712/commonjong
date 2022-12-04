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
        return log.getLogs().stream().anyMatch(aLog -> aLog.contains(targetPlayer + "-riichi"));
    }

    @Override
    public boolean doesPlayerWinOnIppatsu()
    {
        var playerDeclaredRiichi = false;
        var discardedAlready = false;

        for (String aLog : log.getLogs())
        {
            if (isRiichiCall(aLog))
            {
                playerDeclaredRiichi = true;
            }

            if (playerDeclaredRiichi)
            {
                if (isMahjongCall(aLog))
                {
                    return true;
                }
                else if (isGroupFinishingCall(aLog))
                {
                    return false;
                }
                else if (isDiscardCall(aLog))
                {
                    if (discardedAlready)
                    {
                        return false;
                    }
                    discardedAlready = true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isPlayerDealer()
    {
        return targetPlayer.equals(Seat.EAST.getSeatNameLowercase());
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
        return log.getLogs().stream().anyMatch(this::isRonCall);
    }

    @Override
    public boolean doesPlayerWinOnTsumo()
    {
        return log.getLogs().stream().anyMatch(this::isTsumoCall);
    }

    private boolean isMahjongCall(String log)
    {
        return isTsumoCall(log) || isRonCall(log);
    }

    private boolean isTsumoCall(String log)
    {
        return log.contains(targetPlayer + "-tsumo");
    }

    private boolean isRonCall(String log)
    {
        return log.contains(targetPlayer + "-ron");
    }

    private boolean isGroupFinishingCall(String log)
    {
        return log.contains(targetPlayer + "-pon") //
                || log.contains(targetPlayer + "-kan") //
                || log.contains(targetPlayer + "-chii");
    }

    private boolean isRiichiCall(String log)
    {
        return log.contains(targetPlayer + "-riichi");
    }

    private boolean isDiscardCall(String log)
    {
        return log.contains(targetPlayer + "-discard");
    }
}
