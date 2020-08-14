package com.monsieurmahjong.commonjong.game;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.locale.TileLabels_fr;
import com.monsieurmahjong.commonjong.game.mahjong.MahjongTileKind;

public class Tile
{
	private MahjongTileKind tileKind;

	public Tile(MahjongTileKind tileKind)
	{
		this.tileKind = tileKind;
	}

	public MahjongTileKind getTileKind()
	{
		return tileKind;
	}

	public static List<Tile> asHand(String... tiles)
	{
		List<Tile> hand = new ArrayList<>();
		for (String tileName : tiles)
		{
			try
			{
				MahjongTileKind kind = MahjongTileKind.valueOf(tileName);
				Tile tile = new Tile(kind);
				hand.add(tile);
			}
			catch (IllegalArgumentException e)
			{
				continue;
			}
		}
		return hand;
	}

	public String toString()
	{
		return TileLabels_fr.getLabel(tileKind);
	}
}
