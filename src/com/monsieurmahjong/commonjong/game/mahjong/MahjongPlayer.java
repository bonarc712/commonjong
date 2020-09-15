package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.*;
import com.monsieurmahjong.commonjong.game.*;

public class MahjongPlayer extends Player
{
	private List<Tile> tiles;
	private MahjongGame game;

	public MahjongPlayer(MahjongGame game)
	{
		tiles = new ArrayList<>();
		this.game = game;
	}

	@Override
	public void play()
	{
		super.play();
		tiles.add(game.getTileset().draw());
	}

	public void showHand()
	{
		tiles.sort(new MahjongTileOrderingComparator());
		System.out.println("Je suis " + name);
		System.out.println("J'ai " + tiles);
		System.out.println();
	}
}