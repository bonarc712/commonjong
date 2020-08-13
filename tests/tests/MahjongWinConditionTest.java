package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import game.Tile;
import game.mahjong.MahjongTileKind;
import game.mahjong.MahjongWinCondition;

public class MahjongWinConditionTest
{
	@Test
	public void mahjongWinConditionTest()
	{
		List<Tile> hand1 = Tile.asHand("C", "C", "C", "N", "N", "N", "W", "W", "W", "E", "E", "E", "S");
		List<Tile> hand2 = Tile.asHand("C", "C", "C", "N", "N", "N", "W", "W", "W", "E", "E", "E", "S", "H");
		List<Tile> sevenPairs = Tile.asHand("C", "C", "N", "N", "S", "S", "W", "W", "E", "E", "H", "H", "F", "F");
		List<Tile> thirteenOrphans = Tile.asHand("C", "C", "N", "S", "W", "E", "H", "F", "M1", "M9", "P1", "P9", "S1",
				"S9");
		assertFalse(MahjongWinCondition.isMahjong(hand1));
		hand1.add(new Tile(MahjongTileKind.S));
		assertTrue(MahjongWinCondition.isMahjong(hand1));
		assertTrue(MahjongWinCondition.isFourGroupsOnePair(hand1));
		assertFalse(MahjongWinCondition.isSevenPairs(hand1));
		assertFalse(MahjongWinCondition.isMahjong(hand2));
		assertTrue(MahjongWinCondition.isMahjong(sevenPairs));
		assertFalse(MahjongWinCondition.isFourGroupsOnePair(sevenPairs));
		assertTrue(MahjongWinCondition.isSevenPairs(sevenPairs));
		assertTrue(MahjongWinCondition.isMahjong(thirteenOrphans));
		assertFalse(MahjongWinCondition.isFourGroupsOnePair(thirteenOrphans));
		assertTrue(MahjongWinCondition.isThirteenOrphans(thirteenOrphans));
	}

}
