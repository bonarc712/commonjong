package com.monsieurmahjong.commonjong.rules.generic.waits.parsing;

import java.util.List;

import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroupKindComparator;

import one.util.streamex.StreamEx;

public class TileCollisions
{
    public List<List<TileGroup>> createCollisionList(List<TileGroup> tileGroups)
    {
        return StreamEx.of(tileGroups) //
                .sorted(new TileGroupKindComparator()::compare) //
                .groupRuns((first, second) -> first.collidesWith(second)) //
                .filter(list -> list.size() >= 2) //
                .toList();
    }
}
