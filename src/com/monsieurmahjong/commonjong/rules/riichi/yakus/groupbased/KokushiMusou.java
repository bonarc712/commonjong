package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class KokushiMusou extends GroupBasedYaku
{
    public KokushiMusou(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        List<MahjongTileKind> terminalAndHonourCatalog = new ArrayList<>(TileKindUtils.getAllTerminalsAndHonours());
        boolean pairFound = false;

        for (TileGroup group : groups)
        {
            for (MahjongTileKind kind : group.getTileKinds())
            {
                if (kind.isNonTerminalNumeral())
                {
                    return false;
                }

                if (terminalAndHonourCatalog.contains(kind))
                {
                    terminalAndHonourCatalog.remove(kind);
                }
                else
                {
                    if (pairFound)
                    {
                        return false;
                    }
                    pairFound = true;
                }
            }
        }
        return terminalAndHonourCatalog.isEmpty();
    }

    @Override
    public int getHanValue()
    {
        MahjongTileKind winningTile = hand.getWinningTile();
        int tileCount = (int) groups.stream().map(group -> group.getTileKinds()).flatMap(List::stream).filter(kind -> kind == winningTile).count();
        return tileCount == 2 ? 26 : 13;
    }
}