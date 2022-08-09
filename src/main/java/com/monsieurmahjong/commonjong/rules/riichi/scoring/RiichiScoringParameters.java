package com.monsieurmahjong.commonjong.rules.riichi.scoring;

import com.monsieurmahjong.commonjong.game.Seat;

public interface RiichiScoringParameters
{
    public boolean hasPlayerDeclaredRiichi(Seat playerWind);

    public boolean doesPlayerWinOnIppatsu(Seat playerWind);

    public boolean doesPlayerWinOnTenhou(Seat playerWind);

    public boolean doesPlayerWinOnChihou(Seat playerWind);

    public boolean doesPlayerWinOnRenhou(Seat playerWind);

    public boolean doesPlayerWinOnMenzenTsumo(Seat playerWind);

    public boolean doesPlayerWinOnRinshanKaihou(Seat playerWind);

    public boolean doesPlayerWinOnChankan(Seat playerWind);

    public boolean doesPlayerWinOnHaitei(Seat playerWind);

    public boolean doesPlayerWinOnHoutei(Seat playerWind);

    public boolean doesPlayerWinOnNagashiMangan(Seat playerWind);

    public boolean doesPlayerWinOnRon(Seat playerWind);

    public boolean doesPlayerWinOnTsumo(Seat playerWind);
}
