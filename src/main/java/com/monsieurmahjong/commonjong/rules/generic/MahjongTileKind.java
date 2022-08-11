package com.monsieurmahjong.commonjong.rules.generic;

import java.util.Arrays;
import java.util.List;

/**
 * The mahjong tile kinds are in the rules because it's a definition of a game
 * element.
 */
public enum MahjongTileKind
{
    CHARACTERS_1("1m"), // characters
    CHARACTERS_2("2m"), //
    CHARACTERS_3("3m"), //
    CHARACTERS_4("4m"), //
    CHARACTERS_5("5m"), //
    CHARACTERS_6("6m"), //
    CHARACTERS_7("7m"), //
    CHARACTERS_8("8m"), //
    CHARACTERS_9("9m"), //
    CIRCLES_1("1p"), // circles
    CIRCLES_2("2p"), //
    CIRCLES_3("3p"), //
    CIRCLES_4("4p"), //
    CIRCLES_5("5p"), //
    CIRCLES_6("6p"), //
    CIRCLES_7("7p"), //
    CIRCLES_8("8p"), //
    CIRCLES_9("9p"), //
    BAMBOOS_1("1s"), // bamboos
    BAMBOOS_2("2s"), //
    BAMBOOS_3("3s"), //
    BAMBOOS_4("4s"), //
    BAMBOOS_5("5s"), //
    BAMBOOS_6("6s"), //
    BAMBOOS_7("7s"), //
    BAMBOOS_8("8s"), //
    BAMBOOS_9("9s"), //
    EAST("1z"), // east
    SOUTH("2z"), // south
    WEST("3z"), // west
    NORTH("4z"), // north
    WHITE("5z"), // white dragon
    GREEN("6z"), // green dragon
    RED("7z"); // red dragon

    // TODO add flowers, jokers, etc.

    public String abbreviation;

    private MahjongTileKind(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    public boolean is(MahjongTileKind... choices)
    {
        for (MahjongTileKind choice : choices)
        {
            if (this == choice)
            {
                return true;
            }
        }
        return false;
    }

    // UTIL METHODS

    public int getIndex()
    {
        return ordinal();
    }

    /**
     * Get the tile number of a specific tile, eg. a 4 of bamboos will return 4.
     *
     * @throws IllegalArgumentException when calling for a non-numbered tile (eg
     *                                  honours)
     */
    public int getTileNumber()
    {
        if (!isNumeral())
        {
            throw new IllegalArgumentException("Tile is not a numbered tile");
        }
        return Character.getNumericValue(abbreviation.charAt(0));
    }

    /**
     * This should be used only for MPSZ notation needs.
     *
     * Note that this is different from getTileNumber() as this can yield an number
     * for honours as well.
     */
    public int getMPSZNumber()
    {
        return Character.getNumericValue(abbreviation.charAt(0));
    }

    /**
     * This should be used only for MPSZ notation needs.
     */
    public char getMPSZFamily()
    {
        return abbreviation.charAt(1);
    }

    public boolean isHonour()
    {
        return this.isDragon() || this.isWind();
    }

    public boolean isDragon()
    {
        return this.is(RED, GREEN, WHITE);
    }

    public boolean isWind()
    {
        return this.is(EAST, SOUTH, WEST, NORTH);
    }

    public boolean isTerminal()
    {
        return this.is(CHARACTERS_1, CHARACTERS_9, CIRCLES_1, CIRCLES_9, BAMBOOS_1, BAMBOOS_9);
    }

    public boolean isNonTerminalNumeral()
    {
        return this.isNumeral() && !this.isTerminal();
    }

    public boolean isNumeral()
    {
        return this.isCharacters() || this.isBamboos() || this.isCircles();
    }

    public boolean isCharacters()
    {
        return this.is(CHARACTERS_1, CHARACTERS_2, CHARACTERS_3, CHARACTERS_4, CHARACTERS_5, CHARACTERS_6, CHARACTERS_7, CHARACTERS_8, CHARACTERS_9);
    }

    public boolean isCircles()
    {
        return this.is(CIRCLES_1, CIRCLES_2, CIRCLES_3, CIRCLES_4, CIRCLES_5, CIRCLES_6, CIRCLES_7, CIRCLES_8, CIRCLES_9);
    }

    public boolean isBamboos()
    {
        return this.is(BAMBOOS_1, BAMBOOS_2, BAMBOOS_3, BAMBOOS_4, BAMBOOS_5, BAMBOOS_6, BAMBOOS_7, BAMBOOS_8, BAMBOOS_9);
    }

    public TileFamily getFamily()
    {
        if (isCharacters())
        {
            return TileFamily.CHARACTERS;
        }
        if (isCircles())
        {
            return TileFamily.CIRCLES;
        }
        if (isBamboos())
        {
            return TileFamily.BAMBOOS;
        }
        if (isHonour())
        {
            return TileFamily.HONOURS;
        }
        return TileFamily.NONE;
    }

    public static MahjongTileKind getMahjongTileByAbbreviation(String abbreviation)
    {
        for (var tile : MahjongTileKind.values())
        {
            if (tile.abbreviation.equals(abbreviation))
            {
                return tile;
            }
        }

        System.out.println("No match found for tile :" + abbreviation);
        return null;
    }

    public static MahjongTileKind getKindFromIndex(int index)
    {
        return MahjongTileKind.values()[index];
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
}
