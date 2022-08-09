package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.Seat;
import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.game.players.Player;
import com.monsieurmahjong.commonjong.game.statelog.GameStateLog;
import com.monsieurmahjong.commonjong.rules.generic.Ruleset;
import com.monsieurmahjong.commonjong.utils.NameGenerator;

public class MahjongGame
{
    private Ruleset ruleset;
    private Tileset tileset;
    private GameStateLog gameStateLog;
    private List<Player> players;

    public MahjongGame(List<Player> players, Ruleset ruleset)
    {
        this.players = players;
        this.ruleset = ruleset;

        gameStateLog = new GameStateLog();
    }

    public void startGame()
    {
        setup();
        playTurn(Seat.EAST);
    }

    private void setup()
    {
        tileset = ruleset.getTileset();

        if (players == null || players.isEmpty())
        {
            players = new ArrayList<>(4);
            for (var i = 0; i < 4; i++)
            {
                var player = new Player();
                player.setName(NameGenerator.generateNameOtherThan(getPlayerNames()));
                players.add(player);
            }
        }

        assignSeats();
        drawStartingTiles();
    }

    private void drawStartingTiles()
    {
        players.forEach(player -> {
            for (var i = 0; i < 13; i++)
            {
                player.draw(tileset.draw());
            }
        });
    }

    private void assignSeats()
    {
        assignSeat(Seat.EAST, 0);
        assignSeat(Seat.SOUTH, 1);
        assignSeat(Seat.WEST, 2);
        assignSeat(Seat.NORTH, 3);
    }

    private void assignSeat(Seat seat, int playerIndex)
    {
        if (getAmountOfPlayers() > playerIndex)
        {
            var currentPlayer = players.get(playerIndex);
            currentPlayer.setSeat(seat);
            gameStateLog.log("Player " + currentPlayer.getName() + " is " + seat.getSeatName());
        }
    }

    private void playTurn(Seat activePlayer)
    {
        var currentPlayer = getPlayerBySeat(activePlayer);

        // draw tile
        var drawnTile = tileset.draw();
        currentPlayer.draw(drawnTile);
        gameStateLog.log(currentPlayer.getSeat().getSeatName() + " draws " + drawnTile.getTileKind().abbreviation);

        // do action with tile

        // discard tile
        waitForPlayerDiscard(currentPlayer);

        // let other players do action with tile
        // if a player makes an action other than winning, they become the current
        // player

        // check if next turn is played
        if (canNextTurnBePlayed())
        {
            var nextPlayer = determineNextPlayer(currentPlayer);
            playTurn(nextPlayer);
        }
        else
        {
            finishGame();
        }
    }

    /**
     * Right now there is no finish hand method. There is just a finish game, the
     * two will be split later on in the game loop epic story.
     */
    private void finishGame()
    {
        // determine if tenpai-noten is needed

        // determine which player had which score

        // for now just show each hand
        players.forEach(player -> player.showHand());

        // show log (but this is temporary)
        gameStateLog.showWholeLog();
    }

    /**
     * This method is package-private so it can be unit-tested.
     */
    public Seat determineNextPlayer(Player currentPlayer)
    {
        var seatIndex = currentPlayer.getSeat().ordinal();
        if (getAmountOfPlayers() - 1 == seatIndex)
        {
            return Seat.EAST;
        }
        return Seat.values()[currentPlayer.getSeat().ordinal() + 1];
    }

    public int getAmountOfPlayers()
    {
        return players.size();
    }

    private boolean canNextTurnBePlayed()
    {
        return tileset.getTiles().size() > ruleset.getUnusedTilesAmount();
    }

    private void waitForPlayerDiscard(Player currentPlayer)
    {
        var discardedTile = currentPlayer.discard();
        gameStateLog.log(currentPlayer.getSeat().getSeatName() + " discards " + discardedTile.getTileKind().abbreviation);
    }

    private Player getPlayerBySeat(Seat seat)
    {
        return players.stream().filter(player -> player.getSeat() == seat).findFirst().orElse(null);
    }

    private List<String> getPlayerNames()
    {
        return players.stream() //
                .filter(player -> player.getName() != null && !"".equals(player.getName())) //
                .map(Player::getName) //
                .collect(Collectors.toList());
    }

    private int calculatePoints()
    {
        // get hand of winning player
        var hand = players.get(0).getHand();
        var scoring = ruleset.getScoring();
        return scoring.getScore(this, hand);
    }
}
