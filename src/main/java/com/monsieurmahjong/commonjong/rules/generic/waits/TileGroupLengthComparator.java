package com.monsieurmahjong.commonjong.rules.generic.waits;

import java.util.Comparator;

public class TileGroupLengthComparator implements Comparator<TileGroup>
{
    @Override
    public int compare(TileGroup first, TileGroup second)
    {
        if (first.getSize() > 2 || second.getSize() > 2)
        {
            // longest first
            if (first.getSize() != second.getSize())
            {
                if (first.getSize() > second.getSize())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }

            return new TileGroupKindComparator().compare(first, second);
        }

        // pair first
        if (first.isPair())
        {
            if (second.isPair())
            {
                return new TileGroupKindComparator().compare(first, second);
            }
            else
            {
                return -1;
            }
        }
        else if (second.isPair())
        {
            return 1;
        }

        // then return longest group
        if (first.getSize() != second.getSize())
        {
            if (first.getSize() > second.getSize())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }

        return new TileGroupKindComparator().compare(first, second);
    }

}
