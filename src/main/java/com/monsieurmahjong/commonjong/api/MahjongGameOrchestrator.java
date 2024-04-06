package com.monsieurmahjong.commonjong.api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.monsieurmahjong.commonjong.game.mahjong.MahjongGame;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiStandardRuleset;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiTileset;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoring;

public class MahjongGameOrchestrator
{
    private static MahjongGameOrchestrator instance;
    private Map<UUID, MahjongGame> ongoingMahjongGames;

    private MahjongGameOrchestrator()
    {
        ongoingMahjongGames = new HashMap<>();
    }

    protected static MahjongGameOrchestrator getInstance()
    {
        if (instance == null)
        {
            instance = new MahjongGameOrchestrator();
        }
        return instance;
    }

    public static UUID createRiichiGame()
    {
        var mahjongGame = new MahjongGame(null, new RiichiStandardRuleset(new RiichiTileset(), new RiichiScoring()));
        var orchestrator = getInstance();
        var uuid = UUID.randomUUID();

        orchestrator.ongoingMahjongGames.put(uuid, mahjongGame);

        return uuid;
    }

    public static boolean hasMahjongGame(UUID uuid)
    {
        return getInstance().ongoingMahjongGames.get(uuid) != null;
    }
}
