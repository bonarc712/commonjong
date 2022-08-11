package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
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
        var terminalAndHonourCatalog = new ArrayList<>(MahjongTileKind.getAllTerminalsAndHonours());
        var pairFound = false;

        for (var group : groups)
        {
            for (var kind : group.getTileKinds())
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
        return terminalAndHonourCatalog.isEmpty() && pairFound;
    }

    @Override
    public int getHanValue()
    {
        var winningTile = hand.getWinningTile();
        var tileCount = (int) groups.stream().map(group -> group.getTileKinds()).flatMap(List::stream).filter(kind -> kind == winningTile).count();
        return tileCount == 2 ? 26 : 13;
    }
}