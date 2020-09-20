package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.*;
import java.util.stream.Collectors;

import com.monsieurmahjong.commonjong.game.*;
import com.monsieurmahjong.commonjong.rules.riichi.RiichiTileset;
import com.monsieurmahjong.commonjong.utils.NameGenerator;

public class MahjongGame
{
    private Tileset tileset;
    private List<Player> players;

    public MahjongGame()
    {
        tileset = new RiichiTileset(); // TODO this should not be hardcoded
        players = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
        {
            Player player = new Player(this);
            player.setName(NameGenerator.generateNameOtherThan(getPlayerNames()));
            players.add(player);
        }

        players.forEach(player -> {
            for (int i = 0; i < 13; i++)
            {
                player.play();
            }
        });

        players.get(0).play();
        players.forEach(Player::showHand);
    }

    public Tileset getTileset()
    {
        return tileset;
    }

    private List<String> getPlayerNames()
    {
        return players.stream() //
                .filter(player -> player.getName() != null && !player.getName().equals("")) //
                .map(Player::getName) //
                .collect(Collectors.toList());
    }

}
