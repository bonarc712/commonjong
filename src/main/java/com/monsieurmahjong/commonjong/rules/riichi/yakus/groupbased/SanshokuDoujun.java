package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.TileFamily;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class SanshokuDoujun extends GroupBasedYaku
{
    public SanshokuDoujun(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var runsCatalog = new ArrayList<TileGroup>();

        for (var group : groups)
        {
            if (group.isRun())
            {
                runsCatalog.add(group);
            }
        }

        while (runsCatalog.size() >= 3)
        {
            // pop first run
            var firstRun = runsCatalog.remove(0);
            var families = new HashSet<TileFamily>();
            families.add(firstRun.getTileKindAt(0).getFamily());

            // test against other runs
            for (var group : runsCatalog)
            {
                if (group.getTileNumbers().equals(firstRun.getTileNumbers()))
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
        return hand.isClosed() ? 2 : 1;
    }
}
