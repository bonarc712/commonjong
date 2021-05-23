package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.WaitShapeUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Pinfu extends GroupBasedYaku
{
    private MahjongTileKind winningTile;

    public Pinfu(Hand hand, List<TileGroup> groups, MahjongTileKind winningTile)
    {
        super(hand, groups);
        this.winningTile = winningTile;
    }

    @Override
    public boolean isValid()
    {
        List<TileGroup> groupsThatContainTheWinningTile = new ArrayList<>();
        boolean pairFound = false;

        for (TileGroup group : groups)
        {
            if (group.isPair())
            {
                if (pairFound)
                {
                    return false;
                }
                // else
                pairFound = true;
            }
            else if (group.isRun())
            {
                if (group.contains(tile -> tile.equals(winningTile)))
                {
                    groupsThatContainTheWinningTile.add(group);
                }
            }
            else
            {
                return false;
            }
        }

        boolean winningTileIsRyanmen = false;
        for (TileGroup candidateGroup : groupsThatContainTheWinningTile)
        {
            List<Integer> groupIndices = new ArrayList<>(candidateGroup.getIndices());
            groupIndices.remove(new Integer(winningTile.getIndex()));
            if (groupIndices.size() == 2)
            {
                winningTileIsRyanmen |= WaitShapeUtils.isDoubleSidedBlock(groupIndices.get(0).intValue(), groupIndices.get(1).intValue());
            }
        }
        if (!winningTileIsRyanmen)
        {
            return false;
        }

        // verify whether the pair gives minipoints
        return true;
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
