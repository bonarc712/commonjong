package game;

import java.util.ArrayList;
import java.util.List;

public abstract class Tileset
{
	protected List<Tile> tiles;
	protected List<Tile> drawnTiles;

	public Tileset()
	{
		tiles = new ArrayList<>();
		drawnTiles = new ArrayList<>();
		initTileset();
	}

	protected abstract void initTileset();

	public void reset()
	{
		tiles.addAll(drawnTiles);
		drawnTiles.clear();
	}

	public Tile draw()
	{
		int result = (int) (Math.random() * tiles.size());
		Tile drawnTile = tiles.remove(result);
		drawnTiles.add(drawnTile);
		return drawnTile;
	}
}
