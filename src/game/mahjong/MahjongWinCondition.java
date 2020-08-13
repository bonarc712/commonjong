package game.mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.Tile;

public class MahjongWinCondition
{
	/*
	 * Hand has 4 groups and 1 pair OR 7 pairs OR 13 orphans
	 */
	public static boolean isMahjong(List<Tile> hand)
	{
		if (hand.size() < 14 && hand.size() > 18)
			return false;

		if (isThirteenOrphans(hand))
			return true;

		if (isSevenPairs(hand))
			return true;

		if (isFourGroupsOnePair(hand))
			return true;

		return false;
	}

	public static boolean isThirteenOrphans(List<Tile> hand)
	{
		List<Tile> handCopy = new ArrayList<>();
		handCopy.addAll(hand);

		List<List<Tile>> tileGroups = new ArrayList<>();

		while (!handCopy.isEmpty())
		{
			boolean tileWasAdded = false;
			Tile tile = handCopy.remove(0);
			for (List<Tile> group : tileGroups)
			{
				if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
				{
					group.add(tile);
					tileWasAdded = true;
				}
			}

			if (!tileWasAdded)
			{
				List<Tile> newGroup = new ArrayList<>();
				newGroup.add(tile);
				tileGroups.add(newGroup);
			}
		}

		if (tileGroups.size() == 13)
		{
			List<Integer> sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
			if (sizes.stream().filter(size -> size == 1).count() == 12
					&& sizes.stream().filter(size -> size == 2).count() == 1)
				return true;
		}

		return false;
	}

	public static boolean isSevenPairs(List<Tile> hand)
	{
		List<Tile> handCopy = new ArrayList<>();
		handCopy.addAll(hand);

		List<List<Tile>> tileGroups = new ArrayList<>();

		while (!handCopy.isEmpty())
		{
			boolean tileWasAdded = false;
			Tile tile = handCopy.remove(0);
			for (List<Tile> group : tileGroups)
			{
				if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
				{
					group.add(tile);
					tileWasAdded = true;
				}
			}

			if (!tileWasAdded)
			{
				List<Tile> newGroup = new ArrayList<>();
				newGroup.add(tile);
				tileGroups.add(newGroup);
			}
		}

		if (tileGroups.size() == 7)
		{
			List<Integer> sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
			if (sizes.stream().filter(size -> size == 2).count() == 7)
				return true;
		}

		return false;
	}

	public static boolean isFourGroupsOnePair(List<Tile> hand)
	{
		List<Tile> handCopy = new ArrayList<>();
		handCopy.addAll(hand);

		List<List<Tile>> tileGroups = new ArrayList<>();

		while (!handCopy.isEmpty())
		{
			boolean tileWasAdded = false;
			Tile tile = handCopy.remove(0);
			for (List<Tile> group : tileGroups)
			{
				if (group.stream().allMatch(otherTile -> otherTile.getTileKind() == tile.getTileKind()))
				{
					group.add(tile);
					tileWasAdded = true;
				}
			}

			if (!tileWasAdded)
			{
				List<Tile> newGroup = new ArrayList<>();
				newGroup.add(tile);
				tileGroups.add(newGroup);
			}
		}

		if (tileGroups.size() == 5)
		{
			List<Integer> sizes = tileGroups.stream().mapToInt(List::size).boxed().collect(Collectors.toList());
			if (sizes.stream().filter(size -> size == 3).count() == 4
					&& sizes.stream().filter(size -> size == 2).count() == 1)
				return true;
		}

		return false;
	}
}
