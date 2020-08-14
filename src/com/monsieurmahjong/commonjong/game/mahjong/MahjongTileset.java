package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.Arrays;
import java.util.List;

import com.monsieurmahjong.commonjong.*;
import com.monsieurmahjong.commonjong.game.*;

public class MahjongTileset extends Tileset
{
	@Override
	protected void initTileset()
	{
		List<MahjongTileKind> mahjongTiles = getTileList();
		mahjongTiles.forEach(tile -> {
			for (int i = 0; i < 4; ++i)
			{
				Tile physicalTile = new Tile(tile);
				tiles.add(physicalTile);
			}
		});
	}

	private static List<MahjongTileKind> getTileList()
	{
		return Arrays.asList(MahjongTileKind.values());
	}
}
