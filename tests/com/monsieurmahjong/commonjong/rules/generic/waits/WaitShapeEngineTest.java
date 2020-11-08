package com.monsieurmahjong.commonjong.rules.generic.waits;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class WaitShapeEngineTest
{
    @Test
    public void testGetWait()
    {
        List<Tile> hand1 = TileKindUtils.asHand("1112223334445z");
        WaitShapeEngine engine = new WaitShapeEngine(hand1);

        List<MahjongTileKind> hand1Expected = new ArrayList<>();
        hand1Expected.add(MahjongTileKind.WHITE);
        assertEquals(hand1Expected, engine.getWait());

        List<Tile> hand2 = TileKindUtils.asHand("1112223334567z");
        engine = new WaitShapeEngine(hand2);

        List<MahjongTileKind> hand2Expected = new ArrayList<>();
        hand2Expected.addAll(Arrays.asList(MahjongTileKind.NORTH, MahjongTileKind.WHITE, MahjongTileKind.GREEN, MahjongTileKind.RED));
        assertEquals(hand2Expected, engine.getWait());
    }
}
