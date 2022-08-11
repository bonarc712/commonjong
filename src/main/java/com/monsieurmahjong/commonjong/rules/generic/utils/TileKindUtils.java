package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKindComparator;

public class TileKindUtils
{
    /**
     * This method returns a hand made of the tiles in input. The strings in input
     * must make use of the {@link MahjongTileKind} abbreviation, which falls under
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
                var kind = MahjongTileKind.getMahjongTileByAbbreviation(tileName);
                var tile = new Tile(kind);
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
     * This method returns a hand made of the tiles in input. The strings in input
     * must use the Tenhou notation in one block, eg. 345m345p345s1155z.
     *
     * @param tileText : all the tiles that make up the hand
     */
    public static List<Tile> asHand(String tileText)
    {
        List<Tile> hand = new ArrayList<>();
        var remainingText = new String(tileText);
        var currentSuitNumbers = "";

        while (!remainingText.isEmpty())
        {
            var firstCharacter = remainingText.charAt(0);
            if (Character.isDigit(firstCharacter))
            {
                currentSuitNumbers += firstCharacter;
            }
            else if (Character.isAlphabetic(firstCharacter))
            {
                for (var i = 0; i < currentSuitNumbers.length(); i++)
                {
                    var builder = new StringBuilder(currentSuitNumbers.substring(i, i + 1));
                    builder.append(firstCharacter); // suit name

                    var kind = MahjongTileKind.getMahjongTileByAbbreviation(builder.toString());
                    var tile = new Tile(kind);
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

    public static String getHandAsMPSZNotation(List<Tile> hand)
    {
        var mpszStringBuilder = new StringBuilder();
        hand.stream().map(Tile::getTileKind).sorted(new MahjongTileKindComparator()).forEach(tileKind -> {
            mpszStringBuilder.append(tileKind.abbreviation);
        });

        var resultString = mpszStringBuilder.toString();

        BiFunction<String, String, String> replaceLettersInString = (originalString, letterToReplace) -> {
            while (originalString.indexOf(letterToReplace) != originalString.lastIndexOf(letterToReplace))
            {
                originalString = originalString.replaceFirst(letterToReplace, "");
            }
            return originalString;
        };

        resultString = replaceLettersInString.apply(resultString, "m");
        resultString = replaceLettersInString.apply(resultString, "p");
        resultString = replaceLettersInString.apply(resultString, "s");
        resultString = replaceLettersInString.apply(resultString, "z");

        return resultString;
    }

    /**
     * A terminal tile is any tile that is a 1 or a 9.
     */
    public static boolean isTerminal(int index)
    {
        return MahjongTileKind.getKindFromIndex(index).isTerminal();
    }

    /**
     * A terminal or honour tile is any tile that is a 1, a 9, a wind or a dragon.
     */
    public static boolean isTerminalOrHonour(int index)
    {
        var tileKind = MahjongTileKind.getKindFromIndex(index);
        return tileKind.isHonour() || tileKind.isTerminal();
    }

    /**
     * A numeral tile is any tile is part of a family (characters, bamboos, dots)
     */
    public static boolean isNumeral(int index)
    {
        return MahjongTileKind.getKindFromIndex(index).isNumeral();
    }

    /**
     * A simple tile is any tile that is between 2 and 8, inclusive.
     */
    public static boolean isSimple(int index)
    {
        var tileKind = MahjongTileKind.getKindFromIndex(index);
        return tileKind.isNumeral() && !tileKind.isTerminal();
    }

    /**
     * Checks only for the same suit, within characters, circles and bamboos.
     */
    public static boolean areSameSuit(int first, int second)
    {
        var firstTile = MahjongTileKind.getKindFromIndex(first);
        var secondTile = MahjongTileKind.getKindFromIndex(second);

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
