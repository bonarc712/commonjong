package com.monsieurmahjong.commonjong.rules.generic;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.utils.*;

public class MahjongShantenCounter
{
    /*
     * Input may come in the following forms:
     * -> A hand of tiles.
     * -> A hand of tiles with called groups of tiles.
     * -> A partial hand of tiles with an integer group count.
     * -> A numeric representation of tiles in hand with an integer group count.
     * -> A partial amount of numerically represented tiles in hand with an integer group count.
     * 
     * Final form of input should be : hand, calledTiles, groupCount
     */

    public static int shantenCount(List<Tile> hand)
    {
        return shantenCount(hand, 0);
    }

    public static int shantenCount(List<Tile> hand, List<Tile> calledTiles)
    {
        // TODO: Edit according to future management of called tiles.
        // TODO: Edit primary constructor to account for said called tiles.
        return shantenCount(hand, 0);
    }

    public static int shantenCount(List<Tile> hand, int groupCount)
    {
        int[] digitalHand = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++)
        {
            digitalHand[i] = hand.get(i).getTileKind().ordinal();
        }
        return shantenCount(digitalHand, groupCount);
    }

    // INPUT: hand is a sorted array of tile values from 0-33 with repeating.
    // INPUT: g is the number of groups. The function works recursively by extracting a group and calling itself with shantenCount(smallerHand, g+1)

    public static int shantenCount(int[] hand, int groupCount)
    {
        // INIT
        int retVal = 8;
        int tCount = 0;
        int pCount = 0;
        // KOKUSHI-13 = Ignore 16-tile variants
        if (hand.length >= 13)
        {
            retVal = Math.min(retVal, 13 - countDiffTerminals(hand) - Math.max(Math.min(terminalPairs(hand), 1), 0));
        }
        // SUBHAND RECURSION: Sorted hands have relevant tiles spaced 4 apart or less. Order: O{4*4*(n-5)} instead of O{(n^3 / 6}
        if (hand.length > 3)
        {
            for (int i = 0; i < hand.length - 2; i++)
            {
                for (int j = i + 1; j < hand.length - 1 && (j - i < 5); j++)
                {
                    for (int k = j + 1; k < hand.length && (k - j < 5); k++)
                    {
                        // Max subhands:: 14 tiles: 6635520; 13 tiles: 1351680.
                        if (WaitShapeUtils.isGroup(hand[i], hand[j], hand[k]))
                        {
                            int[] subHand = new int[hand.length - 3];
                            subHand = extractHand(hand, i, j, k);
                            retVal = Math.min(retVal, shantenCount(subHand, groupCount + 1));
                        }
                        // Programmed triplet jump : AAABC implies after scanning AAA, 
                        // scanning AAB is useless. Jump to AAB exit condition to check ABC next iteration.
                        if (WaitShapeUtils.isTriplet(hand[i], hand[j], hand[k]))
                        {
                            i++;
                        }
                        j = 999;
                        k = 999;
                    }
                }
            }
        }
        // PROTOGROUP COUNT
        if (hand.length > 1)
        {
            // Priority: AB pair, AC pair, AB taatsu
            for (int i = 0; i < hand.length - 1; i++)
            {
                if (WaitShapeUtils.isPair(hand[i], hand[i + 1]))
                {
                    pCount++;
                    i++;
                }
                else if (i < hand.length - 2 && WaitShapeUtils.isPair(hand[i], hand[i + 2]))
                {
                    pCount++;
                    i = i + 2;
                }
                else if (WaitShapeUtils.isProtogroup(hand[i], hand[i + 1]))
                {
                    tCount++;
                    i++;
                }
            }
        }
        // 5 to 7 pairs, immediate return on 7
        if (pCount == 7)
        {
            return -1;
        }
        if (pCount > 4 && hand.length >= 13)
        {
            retVal = Math.min(retVal, 6 - pCount);
        }
        // Standard count: 8 - 2*g - max(4-g,p+t) - min(1,max(0,p+t-(4-g)))  
        if (pCount + tCount > Math.floor(hand.length / 3))
        {
            if (pCount > 0)
            {
                retVal = (int) Math.min(retVal, 8 - 2 * groupCount - Math.floor(hand.length / 3) - 1);
            }
            else
            {
                retVal = (int) Math.min(retVal, 8 - 2 * groupCount - Math.floor(hand.length / 3) - 0);
            }
        }
        else
        {
            retVal = Math.min(retVal, Math.min(8 - 2 * groupCount - pCount - tCount, 6 - pCount));
        }

        // Tenpai check for shanten == 0.
        if (retVal == 0 && hand.length == 13)
        {
            for (int i = 0; i < 34; i++)
            {
                int[] checkHand = new int[hand.length + 1];
                int checkResult = 0;
                checkHand = loadHand(hand, checkHand);
                checkHand = insertTile(checkHand, i);
                if (!hasQuint(checkHand, i))
                {
                    checkResult = shantenCount(checkHand, 0);
                    retVal = Math.max(retVal, checkResult + 1);
                }
            }
        }
        return retVal;
    }

    private static int countDiffTerminals(int[] hand)
    {
        int res = 0;
        // Check ones, nines, winds/dragons.
        if (TileKindUtils.isTerminalOrHonour(hand[0]))
        {
            res++;
        }
        // Check non-pairs ones/nines, winds/dragons
        for (int i = 1; i < hand.length; i++)
        {
            if (TileKindUtils.isTerminalOrHonour(hand[i]) && hand[i - 1] != hand[i])
            {
                res++;
            }
            if (TileKindUtils.isTerminalOrHonour(hand[i]) && hand[i - 1] != hand[i])
            {
                res++;
            }
        }
        return res;
    }

    private static int terminalPairs(int[] hand)
    {
        int res = 0;
        for (int i = 1; i < hand.length; i++)
        {
            // Count from second tile (index = 1)
            if (TileKindUtils.isTerminalOrHonour(hand[i]) && hand[i - 1] == hand[i])
            {
                res++;
            }
            // If three+ tiles, the line above adds, and the line below takes away. 
            // Distinct pair logic.
            if (TileKindUtils.isTerminalOrHonour(hand[i]) && hand[i - 2] == hand[i])
            {
                res--;
            }
        }
        return res;
    }

    private static int[] loadHand(int[] hand, int[] newHand)
    {
        for (int i = 0; i < hand.length; i++)
        {
            newHand[i] = hand[i];
        }
        return newHand;
    }

    // Hand insert
    private static int[] insertTile(int[] hand, int a)
    {
        int[] newHand = new int[hand.length + 1];
        int temp = 0;
        for (int i = 0; i < hand.length; i++)
        {
            if (hand[i] < a)
            {
                newHand[i] = hand[i];
                temp++;
            }
            if (hand[i] >= a)
            {
                newHand[i + 1] = hand[i];
            }
        }
        newHand[temp] = a;
        return newHand;
    }

    private static boolean hasQuint(int[] hand, int a)
    {
        int temp = 0;
        for (int i = 0; i < hand.length; i++)
        {
            if (hand[i] == a)
            {
                temp++;
            }
        }
        if (temp > 4)
        {
            return true;
        }
        return false;
    }

    // Hand splice
    private static int[] extractHand(int[] hand, int posX, int posY, int posZ)
    {
        int[] newHand = new int[hand.length - 3];
        int temp = 0;
        for (int i = 0; i < hand.length; i++)
        {
            if (i != posX && i != posY && i != posZ)
            {
                newHand[temp] = hand[i];
                temp++;
            }
        }
        return newHand;
    }

    private static int[] extractHand(int[] hand, int posX, int posY)
    {
        int[] newHand = new int[hand.length - 3];
        int temp = 0;
        for (int i = 0; i < hand.length; i++)
        {
            if (i != posX && i != posY)
            {
                newHand[temp] = hand[i];
                temp++;
            }
        }
        return newHand;
    }

    // TODO : move yaku logic out of here
    private static boolean flushHand(int[] hand)
    {
        int man = 0, pin = 0, so = 0;
        for (int i = 0; i < hand.length; i++)
        {
            if (hand[i] < 9)
            {
                man++;
            }
            else if (hand[i] < 18)
            {
                pin++;
            }
            else if (hand[i] < 27)
            {
                so++;
            }
        }
        // Reduce result to one or leave at zero.
        man = Math.min(1, man);
        pin = Math.min(1, pin);
        so = Math.min(1, so);
        if (man + pin + so > 1)
        {
            return false;
        }
        return true;
    }

    private static boolean pureFlushHand(int[] hand)
    {
        if (TileKindUtils.areSameSuit(hand[0], hand[hand.length - 1]))
        {
            return true;
        }
        return false;
    }

    private static boolean isTanyao(int[] hand)
    {
        for (int i = 0; i < hand.length; i++)
        {
            if (TileKindUtils.isHonor(hand[i]))
            {
                return false;
            }
            else if (TileKindUtils.isTerminal(hand[i]))
            {
                return false;
            }
        }
        return true;
    }

}
