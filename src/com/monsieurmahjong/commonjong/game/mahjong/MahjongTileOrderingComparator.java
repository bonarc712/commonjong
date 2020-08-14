package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.Comparator;

import com.monsieurmahjong.commonjong.game.Tile;

public class MahjongTileOrderingComparator implements Comparator<Tile>
{
	@Override
	public int compare(Tile tile1, Tile tile2)
	{
		MahjongTileKind firstTile = tile1.getTileKind();
		MahjongTileKind secondTile = tile2.getTileKind();
		if (firstTile.equals(secondTile))
			return 0;
		return firstTile.ordinal() - secondTile.ordinal();
	}
}
