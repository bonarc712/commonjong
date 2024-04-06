package com.monsieurmahjong.commonjong.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class MahjongGameOrchestratorTest
{
    @Test
    public void whenInvalidUUIDIsGiven_thenNoGameShouldBeFoundWithThisUUID()
    {
        var uuid = UUID.randomUUID();

        var hasMahjongGame = MahjongGameOrchestrator.hasMahjongGame(uuid);

        assertFalse(hasMahjongGame);
    }

    @Test
    public void whenCreatingMahjongGame_thenThatGameShouldBeFoundInTheOrchestrator()
    {
        var uuid = MahjongGameOrchestrator.createRiichiGame();

        var hasMahjongGame = MahjongGameOrchestrator.hasMahjongGame(uuid);

        assertTrue(hasMahjongGame);
    }
}
