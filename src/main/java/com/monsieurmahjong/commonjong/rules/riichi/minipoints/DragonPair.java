package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class DragonPair implements Fu
{
    private List<TileGroup> groups;

    public DragonPair(List<TileGroup> groups)
    {
        this.groups = groups;
    }

    @Override
    public boolean isValid()
    {
        if (groups.size() == 7)
        {
            return false;
        }

        for (TileGroup group : groups)
        {
            if (group.isPair() && group.getTileKindAt(0).isDragon())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getFuValue()
    {
        return 2;
    }
}
