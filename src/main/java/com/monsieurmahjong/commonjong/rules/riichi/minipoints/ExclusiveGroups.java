package com.monsieurmahjong.commonjong.rules.riichi.minipoints;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

/**
 * By exclusive groups, we mean minkou (open pon), ankou (closed pon), minkan
 * (open kan) and ankan (closed kan).
 */
public class ExclusiveGroups implements Fu
{
    private Hand hand;
    private List<TileGroup> tileGroups;

    public ExclusiveGroups(Hand hand, List<TileGroup> tileGroups)
    {
        this.hand = hand;
        this.tileGroups = tileGroups;
    }

    @Override
    public int getFuValue()
    {
        var totalFuValue = 0;
        for (TileGroup tileGroup : tileGroups)
        {
            if (tileGroup.isCompleteExclusiveGroup())
            {
                var groupValue = 2;

                if (isClosed(tileGroup))
                {
                    groupValue *= 2;
                }

                if (isNonSimple(tileGroup))
                {
                    groupValue *= 2;
                }

                if (tileGroup.isQuad())
                {
                    groupValue *= 4;
                }

                totalFuValue += groupValue;
            }
        }
        return totalFuValue;
    }

    private boolean isNonSimple(TileGroup tileGroup)
    {
        return MahjongTileKind.isTerminalOrHonour(tileGroup.getIndices().get(0));
    }

    private boolean isClosed(TileGroup tileGroup)
    {
        var hasThisGroupOpen = hand.getMelds() //
                .stream() //
                .filter(tileList -> TileGroupUtils.getTilesFromTileGroup(tileGroup).equals(tileList)) //
                .findAny() //
                .isPresent();
        return !hasThisGroupOpen;
    }

    @Override
    public boolean isValid()
    {
        return true;
    }
}
