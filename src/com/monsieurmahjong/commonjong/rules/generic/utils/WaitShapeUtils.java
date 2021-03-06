package com.monsieurmahjong.commonjong.rules.generic.utils;

public class WaitShapeUtils
{
    // Element checks
    public static boolean isGroup(int first, int second, int third)
    {
        // Group identity checked in isolation. Looping checks left to bigger functions.
        return isTriplet(first, second, third) || isRun(first, second, third);
    }

    public static boolean isTriplet(int first, int second, int third)
    {
        return first == second && second == third;
    }

    public static boolean isRun(int first, int second, int third)
    {
        if (TileKindUtils.areSameSuit(first, second) && TileKindUtils.areSameSuit(second, third))
        {
            if (first != second && first != third && second != third)
            {
                int highestIndex = Math.max(first, Math.max(second, third));
                int lowestIndex = Math.min(first, Math.min(second, third));

                // difference must be exactly two
                return highestIndex - lowestIndex == 2;
            }
        }
        return false;
    }

    public static boolean isPair(int first, int second)
    {
        return first == second;
    }

    /**
     * This method checks for a protogroup. A protogroup is two tiles
     * that can form a group, for instance 1-2 in wait of a 3, 3-5 in
     * wait of a 4, or 6-7 in wait of a 5 or an 8.
     * 
     * By definition, a pair is not a protogroup.
     */
    public static boolean isProtogroup(int first, int second)
    {
        int highestIndex = Math.max(first, second);
        int lowestIndex = Math.min(first, second);

        // Same suit check required. 17 and 19 are two apart, but refer to 9-pin and 2-sô.
        // Protogroups (taatsu) do not exist for winds, all pairs are considered separate entities.
        return (lowestIndex + 1 == highestIndex || lowestIndex + 2 == highestIndex) && TileKindUtils.areSameSuit(first, second);
    }

    /**
     * This method checks for a double-sided block (that waits on a ryanmen). This is a protogroup characterized
     * by a wait on both sides, for instance 34 is waiting for 2 or 5, so it qualifies as a double
     * sided wait.
     */
    public static boolean isDoubleSidedBlock(int first, int second)
    {
        return Math.abs(first - second) == 1 && TileKindUtils.areSameSuit(first, second) && !TileKindUtils.isTerminal(first) && !TileKindUtils.isTerminal(second);
    }

    /**
     * This method checks for an end block (that waits on a penchan). This is a protogroup characterized
     * by a wait on a 3 or a 7 only, as it is either a 12(3) or (7)89.
     */
    public static boolean isEndBlock(int first, int second)
    {
        return Math.abs(first - second) == 1 && TileKindUtils.areSameSuit(first, second) && (TileKindUtils.isTerminal(first) || TileKindUtils.isTerminal(second));
    }

    /**
     * This method checks for an inside block (that waits on a kanchan). This is a protogroup characterized
     * by a wait on the middle tile. For instance, 35 waits on 4.
     */
    public static boolean isInsideBlock(int first, int second)
    {
        return Math.abs(first - second) == 2 && TileKindUtils.areSameSuit(first, second);
    }
}
