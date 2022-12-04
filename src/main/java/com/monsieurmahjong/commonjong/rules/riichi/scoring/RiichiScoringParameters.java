package com.monsieurmahjong.commonjong.rules.riichi.scoring;

public interface RiichiScoringParameters
{
    public boolean hasPlayerDeclaredRiichi();

    public boolean isPlayerDealer();

    public boolean doesPlayerWinOnIppatsu();

    public boolean doesPlayerWinOnTenhou();

    public boolean doesPlayerWinOnChihou();

    public boolean doesPlayerWinOnRenhou();

    public boolean doesPlayerWinOnMenzenTsumo();

    public boolean doesPlayerWinOnRinshanKaihou();

    public boolean doesPlayerWinOnChankan();

    public boolean doesPlayerWinOnHaitei();

    public boolean doesPlayerWinOnHoutei();

    public boolean doesPlayerWinOnNagashiMangan();

    public boolean doesPlayerWinOnRon();

    public boolean doesPlayerWinOnTsumo();
}
