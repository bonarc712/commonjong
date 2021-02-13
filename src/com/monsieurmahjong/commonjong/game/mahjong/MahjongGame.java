package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.game.players.Player;
import com.monsieurmahjong.commonjong.rules.generic.RuleSet;
import com.monsieurmahjong.commonjong.utils.NameGenerator;

public class MahjongGame
{
    private RuleSet ruleSet;
    private Tileset tileSet;
    private List<Player> players;

    public MahjongGame(List<Player> players, RuleSet ruleSet)
    {
        this.players = players;
        this.ruleSet = ruleSet;
    }

    public void startGame()
    {
        setup();
        playTurn(Seat.EAST);
    }

    private void setup()
    {
        tileSet = ruleSet.getTileSet();

        if (players == null || players.isEmpty())
        {
            players = new ArrayList<>(4);
            for (int i = 0; i < 4; i++)
            {
                Player player = new Player();
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
            for (int i = 0; i < 13; i++)
            {
                player.draw(tileSet.draw());
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
            players.get(playerIndex).setSeat(seat);
        }
    }

    private void playTurn(Seat activePlayer)
    {
        Player currentPlayer = getPlayerBySeat(activePlayer);

        // draw tile
        currentPlayer.draw(tileSet.draw());

        // do action with tile

        // discard tile
        waitForPlayerDiscard(currentPlayer);

        // let other players do action with tile
        // if a player makes an action other than winning, they become the current player

        // check if next turn is played
        if (canNextTurnBePlayed())
        {
            Seat nextPlayer = determineNextPlayer(currentPlayer);
            playTurn(nextPlayer);
        }
        else
        {
            finishGame();
        }
    }

    /**
     * Right now there is no finish hand method. There is just a finish
     * game, the two will be split later on in the game loop epic story.
     */
    private void finishGame()
    {
        // determine if tenpai-noten is needed

        // determine which player had which score

        // for now just show each hand
        players.forEach(player -> player.showHand());
    }

    /**
     * This method is package-private so it can be unit-tested.
     */
    Seat determineNextPlayer(Player currentPlayer)
    {
        int seatIndex = currentPlayer.getSeat().ordinal();
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
        return tileSet.getTiles().size() > ruleSet.getUnusedTilesAmount();
    }

    private void waitForPlayerDiscard(Player currentPlayer)
    {
        Tile discardedTile = currentPlayer.discard();
        //TODO add discardedTile to game log
    }

    private Player getPlayerBySeat(Seat seat)
    {
        return players.stream().filter(player -> player.getSeat() == seat).findFirst().orElse(null);
    }

    private List<String> getPlayerNames()
    {
        return players.stream() //
                .filter(player -> player.getName() != null && !player.getName().equals("")) //
                .map(Player::getName) //
                .collect(Collectors.toList());
    }

}
