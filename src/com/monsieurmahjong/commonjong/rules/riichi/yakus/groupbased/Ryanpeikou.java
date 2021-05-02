package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Ryanpeikou extends GroupBasedYaku
{
    public Ryanpeikou(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        if (hand.isClosed())
        {
            List<TileGroup> sequencesFound = new ArrayList<>();
            List<TileGroup> matchesFound = new ArrayList<>(); // a match found is any sequence that is present at least twice

            for (TileGroup group : groups)
            {
                if (group.isRun())
                {
                    if (sequencesFound.contains(group) && !matchesFound.contains(group))
                    {
                        matchesFound.add(group);
                    }
                    else
                    {
                        sequencesFound.add(group);
                    }
                }
            }

            if (matchesFound.size() == 2)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getHanValue()
    {
        return 3;
    }
}
