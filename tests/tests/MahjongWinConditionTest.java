package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.game.mahjong.*;

public class MahjongWinConditionTest
{
    @Test
    public void mahjongWinConditionTest()
    {
        List<Tile> hand1 = Tile.asHand("5z", "5z", "5z", "4z", "4z", "4z", "3z", "3z", "3z", "1z", "1z", "1z", "2z");
        assertFalse(MahjongWinCondition.isMahjong(hand1));
        hand1.add(new Tile(MahjongTileKind.SOUTH));
        assertTrue(MahjongWinCondition.isMahjong(hand1));
        assertTrue(MahjongWinCondition.isFourGroupsOnePair(hand1));
        assertFalse(MahjongWinCondition.isSevenPairs(hand1));

        List<Tile> hand2 = Tile.asHand("5z", "5z", "5z", "4z", "4z", "4z", "3z", "3z", "3z", "1z", "1z", "1z", "2z", "7z");
        assertFalse(MahjongWinCondition.isMahjong(hand2));

        List<Tile> sevenPairs = Tile.asHand("5z", "5z", "6z", "6z", "4z", "4z", "3z", "3z", "7z", "7z", "1z", "1z", "2z", "2z");
        assertTrue(MahjongWinCondition.isMahjong(sevenPairs));
        assertFalse(MahjongWinCondition.isFourGroupsOnePair(sevenPairs));
        assertTrue(MahjongWinCondition.isSevenPairs(sevenPairs));

        List<Tile> thirteenOrphans = Tile.asHand("1z", "1z", "2z", "3z", "4z", "5z", "6z", "7z", "1p", "9p", "1m", "9m", "1s", "9s");
        assertTrue(MahjongWinCondition.isMahjong(thirteenOrphans));
        assertFalse(MahjongWinCondition.isFourGroupsOnePair(thirteenOrphans));
        assertTrue(MahjongWinCondition.isThirteenOrphans(thirteenOrphans));
    }

}
