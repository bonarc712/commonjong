package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileKindUtils
{
    /**
     * This method returns a hand made of the tiles in input. 
     * The strings in input must make use of the
     * {@link MahjongTileKind} abbreviation, which falls under
     * the Tenhou notation (1m, 2m, 3m, 1p, etc.)
     * 
     * @param tiles : all the tiles that make up the hand
     */
    public static List<Tile> asHand(String... tiles)
    {
        List<Tile> hand = new ArrayList<>();
        for (String tileName : tiles)
        {
            try
            {
                MahjongTileKind kind = MahjongTileKind.getMahjongTileByAbbreviation(tileName);
                Tile tile = new Tile(kind);
                hand.add(tile);
            }
            catch (IllegalArgumentException e)
            {
                continue;
            }
        }
        return hand;
    }

    /**
     * This method returns a hand made of the tiles in input. 
     * The strings in input must use the Tenhou notation in
     * one block, eg. 345m345p345s1155z.
     * 
     * @param tiles : all the tiles that make up the hand
     */
    public static List<Tile> asHand(String tileText)
    {
        List<Tile> hand = new ArrayList<>();
        String remainingText = new String(tileText);
        String currentSuitNumbers = "";

        while (!remainingText.isEmpty())
        {
            char firstCharacter = remainingText.charAt(0);
            if (Character.isDigit(firstCharacter))
            {
                currentSuitNumbers += firstCharacter;
            }
            else if (Character.isAlphabetic(firstCharacter))
            {
                for (int i = 0; i < currentSuitNumbers.length(); i++)
                {
                    StringBuilder builder = new StringBuilder(currentSuitNumbers.substring(i, i + 1));
                    builder.append(firstCharacter); // suit name

                    MahjongTileKind kind = MahjongTileKind.getMahjongTileByAbbreviation(builder.toString());
                    Tile tile = new Tile(kind);
                    hand.add(tile);
                }
                currentSuitNumbers = "";
            }
            else
            {
                throw new IllegalArgumentException("Invalid input");
            }

            remainingText = remainingText.substring(1);
        }

        return hand;
    }

    public static MahjongTileKind getKindFromIndex(int index)
    {
        return MahjongTileKind.values()[index];
    }

    public static boolean isWind(int index)
    {
        return getKindFromIndex(index).isWind();
    }

    public static boolean isDragon(int index)
    {
        return getKindFromIndex(index).isDragon();
    }

    public static boolean isHonor(int index)
    {
        return getKindFromIndex(index).isHonour();
    }

    /**
     * A terminal tile is any tile that is a 1 or a 9.
     */
    public static boolean isTerminal(int index)
    {
        return getKindFromIndex(index).isTerminal();
    }

    /**
     * A terminal or honour tile is any tile that is a 1, a 9,
     * a wind or a dragon.
     */
    public static boolean isTerminalOrHonour(int index)
    {
        MahjongTileKind tileKind = getKindFromIndex(index);
        return tileKind.isHonour() || tileKind.isTerminal();
    }

    /**
     * A numeral tile is any tile is part of
     * a family (characters, bamboos, dots)
     */
    public static boolean isNumeral(int index)
    {
        return getKindFromIndex(index).isNumeral();
    }

    /**
     * A simple tile is any tile that is between
     * 2 and 8, inclusive.
     */
    public static boolean isSimple(int index)
    {
        MahjongTileKind tileKind = getKindFromIndex(index);
        return tileKind.isNumeral() && !tileKind.isTerminal();
    }

    public static List<MahjongTileKind> getAllCharacters()
    {
        return Arrays.asList(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3, MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_5,
                MahjongTileKind.CHARACTERS_6, MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_8, MahjongTileKind.CHARACTERS_9);
    }

    public static List<MahjongTileKind> getAllCircles()
    {
        return Arrays.asList(MahjongTileKind.CIRCLES_1, MahjongTileKind.CIRCLES_2, MahjongTileKind.CIRCLES_3, MahjongTileKind.CIRCLES_4, MahjongTileKind.CIRCLES_5, MahjongTileKind.CIRCLES_6,
                MahjongTileKind.CIRCLES_7, MahjongTileKind.CIRCLES_8, MahjongTileKind.CIRCLES_9);
    }

    public static List<MahjongTileKind> getAllBamboos()
    {
        return Arrays.asList(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3, MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6,
                MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_9);
    }

    public static List<MahjongTileKind> getAllHonours()
    {
        return Arrays.asList(MahjongTileKind.EAST, MahjongTileKind.SOUTH, MahjongTileKind.WEST, MahjongTileKind.NORTH, MahjongTileKind.WHITE, MahjongTileKind.GREEN, MahjongTileKind.RED);
    }

    public static List<MahjongTileKind> getAllTerminalsAndHonours()
    {
        return Arrays.asList(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_9, MahjongTileKind.CIRCLES_1, MahjongTileKind.CIRCLES_9, MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_9,
                MahjongTileKind.EAST, MahjongTileKind.SOUTH, MahjongTileKind.WEST, MahjongTileKind.NORTH, MahjongTileKind.WHITE, MahjongTileKind.GREEN, MahjongTileKind.RED);
    }

    /**
     * Checks only for the same suit, within characters, circles and bamboos.
     */
    public static boolean areSameSuit(int first, int second)
    {
        MahjongTileKind firstTile = getKindFromIndex(first);
        MahjongTileKind secondTile = getKindFromIndex(second);

        return firstTile.isCharacters() && secondTile.isCharacters() || //
                firstTile.isCircles() && secondTile.isCircles() || //
                firstTile.isBamboos() && secondTile.isBamboos();
    }

    // ALIASES

    public static boolean isYaochuu(int index)
    {
        return isTerminalOrHonour(index);
    }

    public static boolean isRoutou(int index)
    {
        return isTerminal(index);
    }

    public static boolean isEnd(int index)
    {
        return isTerminal(index);
    }
}
