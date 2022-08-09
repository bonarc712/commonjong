package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import com.monsieurmahjong.commonjong.game.Seat;

public class RiichiScoringParametersImpl implements RiichiScoringParameters
{
    private boolean hasDeclaredRiichi = false;
    private boolean winOnIppatsu = false;
    private boolean winOnTenhou = false;
    private boolean winOnChihou = false;
    private boolean winOnRenhou = false;
    private boolean winOnMenzenTsumo = false;
    private boolean winOnRinshanKaihou = false;
    private boolean winOnChankan = false;
    private boolean winOnHaitei = false;
    private boolean winOnHoutei = false;
    private boolean winOnNagashiMangan = false;
    private boolean winOnRon = false;
    private boolean winOnTsumo = false;

    private Seat winningPlayer;

    public RiichiScoringParametersImpl(Seat winningPlayer)
    {
        this.winningPlayer = winningPlayer;
    }

    public Seat getWinningPlayer()
    {
        return winningPlayer;
    }

    @Override
    public boolean hasPlayerDeclaredRiichi()
    {
        return hasDeclaredRiichi;
    }

    @Override
    public boolean doesPlayerWinOnIppatsu()
    {
        return winOnIppatsu;
    }

    @Override
    public boolean doesPlayerWinOnTenhou()
    {
        return winOnTenhou;
    }

    @Override
    public boolean doesPlayerWinOnChihou()
    {
        return winOnChihou;
    }

    @Override
    public boolean doesPlayerWinOnRenhou()
    {
        return winOnRenhou;
    }

    @Override
    public boolean doesPlayerWinOnMenzenTsumo()
    {
        return winOnMenzenTsumo;
    }

    @Override
    public boolean doesPlayerWinOnRinshanKaihou()
    {
        return winOnRinshanKaihou;
    }

    @Override
    public boolean doesPlayerWinOnChankan()
    {
        return winOnChankan;
    }

    @Override
    public boolean doesPlayerWinOnHaitei()
    {
        return winOnHaitei;
    }

    @Override
    public boolean doesPlayerWinOnHoutei()
    {
        return winOnHoutei;
    }

    @Override
    public boolean doesPlayerWinOnNagashiMangan()
    {
        return winOnNagashiMangan;
    }

    @Override
    public boolean doesPlayerWinOnRon()
    {
        return winOnRon;
    }

    @Override
    public boolean doesPlayerWinOnTsumo()
    {
        return winOnTsumo;
    }

    public void setHasDeclaredRiichi(boolean hasDeclaredRiichi)
    {
        this.hasDeclaredRiichi = hasDeclaredRiichi;
    }

    public void setWinOnIppatsu(boolean winOnIppatsu)
    {
        this.winOnIppatsu = winOnIppatsu;
    }

    public void setWinOnTenhou(boolean winOnTenhou)
    {
        this.winOnTenhou = winOnTenhou;
    }

    public void setWinOnChihou(boolean winOnChihou)
    {
        this.winOnChihou = winOnChihou;
    }

    public void setWinOnRenhou(boolean winOnRenhou)
    {
        this.winOnRenhou = winOnRenhou;
    }

    public void setWinOnMenzenTsumo(boolean winOnMenzenTsumo)
    {
        this.winOnMenzenTsumo = winOnMenzenTsumo;
    }

    public void setWinOnRinshanKaihou(boolean winOnRinshanKaihou)
    {
        this.winOnRinshanKaihou = winOnRinshanKaihou;
    }

    public void setWinOnChankan(boolean winOnChankan)
    {
        this.winOnChankan = winOnChankan;
    }

    public void setWinOnHaitei(boolean winOnHaitei)
    {
        this.winOnHaitei = winOnHaitei;
    }

    public void setWinOnHoutei(boolean winOnHoutei)
    {
        this.winOnHoutei = winOnHoutei;
    }

    public void setWinOnNagashiMangan(boolean winOnNagashiMangan)
    {
        this.winOnNagashiMangan = winOnNagashiMangan;
    }

    public void setWinOnRon(boolean winOnRon)
    {
        this.winOnRon = winOnRon;
    }

    public void setWinOnTsumo(boolean winOnTsumo)
    {
        this.winOnTsumo = winOnTsumo;
    }
}
