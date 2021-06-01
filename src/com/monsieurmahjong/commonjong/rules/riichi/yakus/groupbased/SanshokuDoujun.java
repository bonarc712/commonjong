package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

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
        List<TileGroup> runsCatalog = new ArrayList<>();

        for (TileGroup group : groups)
        {
            if (group.isRun())
            {
                runsCatalog.add(group);
            }
        }

        while (runsCatalog.size() >= 3)
        {
            // pop first run
            TileGroup firstRun = runsCatalog.remove(0);
            Set<TileFamily> families = new HashSet<>();
            families.add(firstRun.getTileKindAt(0).getFamily());

            // test against other runs
            for (TileGroup group : runsCatalog)
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
