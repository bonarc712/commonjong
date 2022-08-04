package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        // using term triplets even though it can also apply to other complete exclusive
        // groups
        var tripletsCatalog = new ArrayList<TileGroup>();

        for (var group : groups)
        {
            if (group.isCompleteExclusiveGroup())
            {
                tripletsCatalog.add(group);
            }
        }

        while (tripletsCatalog.size() >= 3)
        {
            // pop first run
            var firstTriplet = tripletsCatalog.remove(0);
            var families = new HashSet<TileFamily>();
            families.add(firstTriplet.getTileKindAt(0).getFamily());

            // test against other runs
            for (var group : tripletsCatalog)
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
