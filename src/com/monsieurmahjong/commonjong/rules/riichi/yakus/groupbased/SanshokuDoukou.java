package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.TileFamily;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class SanshokuDoukou extends GroupBasedYaku
{
    public SanshokuDoukou(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        // using term triplets even though it can also apply to other complete exclusive groups
        List<TileGroup> tripletsCatalog = new ArrayList<>();

        for (TileGroup group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                tripletsCatalog.add(group);
            }
        }

        while (tripletsCatalog.size() >= 3)
        {
            // pop first run
            TileGroup firstTriplet = tripletsCatalog.remove(0);
            Set<TileFamily> families = new HashSet<>();
            families.add(firstTriplet.getTileKindAt(0).getFamily());

            // test against other runs
            for (TileGroup group : tripletsCatalog)
            {
                if (group.getTileKindAt(0).getTileNumber() == firstTriplet.getTileKindAt(0).getTileNumber())
                {
                    families.add(group.getTileKindAt(0).getFamily());
                }
            }

            if (families.size() == 3)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getHanValue()
    {
        return 2;
    }
}
