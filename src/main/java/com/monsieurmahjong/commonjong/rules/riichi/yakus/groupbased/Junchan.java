package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Junchan extends GroupBasedYaku
{
    public Junchan(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var nonTerminalFound = false;

        for (var group : groups)
        {
            if (group.contains(MahjongTileKind::isTerminal))
            {
                if (group.isRun())
                {
                    nonTerminalFound = true;
                }
            }
            else
            {
                return false;
            }
        }

        return nonTerminalFound;
    }

    @Override
    public int getHanValue()
    {
        return hand.isClosed() ? 3 : 2;
    }
}