package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Pinfu extends GroupBasedYaku
{
    public Pinfu(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        var groupsThatContainTheWinningTile = new ArrayList<TileGroup>();
        var winningTile = hand.getWinningTile();
        TileGroup pair = null;

        for (var group : groups)
        {
            if (group.isPair())
            {
                if (pair != null)
                {
                    return false;
                }
                // else
                pair = group;
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

        var winningTileIsRyanmen = false;
        for (TileGroup candidateGroup : groupsThatContainTheWinningTile)
        {
            List<Integer> groupIndices = new ArrayList<>(candidateGroup.getIndices());
            groupIndices.remove(Integer.valueOf(winningTile.getIndex()));
            if (groupIndices.size() == 2)
            {
                winningTileIsRyanmen |= new TileGroup(groupIndices.get(0).intValue(), groupIndices.get(1).intValue()).isDoubleSidedBlock();
            }
        }
        if (!winningTileIsRyanmen)
        {
            return false;
        }

        // verify whether the pair gives minipoints
        if (pair == null)
        {
            return false;
        }
        var pairKind = TileKindUtils.getKindFromIndex(pair.getIndices().get(0));
        if (pairKind.isDragon())
        {
            return false;
        }
        if (pairKind.isWind())
        {
            var pairSeat = TileKindUtils.getSeatFromTileKind(pairKind);
            if (hand.isTableWind(pairSeat))
            {
                return false;
            }
            if (hand.isSeatWind(pairSeat))
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getHanValue()
    {
        return 1;
    }
}
