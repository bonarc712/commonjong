package com.monsieurmahjong.commonjong.rules.generic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import com.monsieurmahjong.commonjong.game.Tile;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKindComparator;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class MPSZNotation
{
    public TileGroup getTileGroupFrom(String tileText)
    {
        var tileGroup = new TileGroup();
        for (var tileKind : getTileKindsFrom(tileText))
        {
            tileGroup.add(tileKind.getIndex());
        }
        return tileGroup;
    }

    public List<TileGroup> getTileGroupsFrom(String... tileTexts)
    {
        var tileGroups = new ArrayList<TileGroup>();
        for (String tileText : tileTexts)
        {
            tileGroups.add(getTileGroupFrom(tileText));
        }
        return tileGroups;
    }

    /**
     * This method returns a hand made of the tiles in input. The strings in input
     * must use the Tenhou notation in one block, eg. 345m345p345s1155z.
     *
     * @param tileText : all the tiles that make up the hand
     */
    public List<Tile> getTilesFrom(String tileText)
    {
        List<Tile> hand = new ArrayList<>();

        for (var tileKind : getTileKindsFrom(tileText))
        {
            var tile = new Tile(tileKind);
            hand.add(tile);
        }

        return hand;
    }

    public String getHandAsMPSZNotation(List<Tile> hand)
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

    private List<MahjongTileKind> getTileKindsFrom(String tileText)
    {
        var mahjongTileKinds = new ArrayList<MahjongTileKind>();
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
                    mahjongTileKinds.add(kind);
                }
                currentSuitNumbers = "";
            }
            else
            {
                throw new IllegalArgumentException("Invalid input");
            }

            remainingText = remainingText.substring(1);
        }
        return mahjongTileKinds;
    }
}
