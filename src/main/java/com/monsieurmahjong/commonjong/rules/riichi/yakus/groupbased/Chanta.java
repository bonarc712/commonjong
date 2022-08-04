package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Chanta extends GroupBasedYaku
{
    public Chanta(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var honourFound = false;
        var nonTerminalFound = false;

        for (TileGroup group : groups)
        {
            if (group.contains(MahjongTileKind::isHonour))
            {
                honourFound = true;
            }
            else
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
        }

        return honourFound && nonTerminalFound;
    }

    @Override
    public int getHanValue()
    {
        return hand.isClosed() ? 2 : 1;
    }
}
