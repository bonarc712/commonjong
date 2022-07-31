package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.WaitShapeUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class WaitFu implements Fu
{
    private Hand hand;
    private List<TileGroup> tileGroups;
    private TileGroup winningGroup;

    public WaitFu(Hand hand, List<TileGroup> tileGroups, TileGroup winningGroup)
    {
        this.hand = hand;
        this.tileGroups = tileGroups;
        this.winningGroup = winningGroup;
    }

    @Override
    public boolean isValid()
    {
        if (isChiitoitsu() || isKokushi())
        {
            return false;
        }

        TileGroup tileGroupBeforeWinning = getWinningTileGroupWithoutWinningTile();

        if (tileGroupBeforeWinning.getSize() == 1) // tanki wait
        {
            return true;
        }

        if (WaitShapeUtils.isEndBlock(tileGroupBeforeWinning.getIndices().get(0), tileGroupBeforeWinning.getIndices().get(1)))
        {
            return true;
        }

        if (WaitShapeUtils.isInsideBlock(tileGroupBeforeWinning.getIndices().get(0), tileGroupBeforeWinning.getIndices().get(1)))
        {
            return true;
        }

        return false;
    }

    private boolean isKokushi()
    {
        return tileGroups.size() == 13;
    }

    private boolean isChiitoitsu()
    {
        return tileGroups.size() == 7;
    }

    private TileGroup getWinningTileGroupWithoutWinningTile()
    {
        MahjongTileKind winningTile = hand.getWinningTile();

        List<Integer> groupIndices = winningGroup.getIndices();
        groupIndices.remove(Integer.valueOf(winningTile.getIndex()));

        return new TileGroup(groupIndices);
    }

    @Override
    public int getFuValue()
    {
        return 2;
    }

}
