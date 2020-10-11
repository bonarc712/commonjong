package com.monsieurmahjong.commonjong.rules.generic;

/**
 * The mahjong tile kinds are in the rules because it's a
 * definition of a game element.
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

    public static MahjongTileKind getMahjongTileByAbbreviation(String abbreviation)
    {
        for (MahjongTileKind tile : MahjongTileKind.values())
        {
            if (tile.abbreviation.equals(abbreviation))
            {
                return tile;
            }
        }

        System.out.println("No match found for tile :" + abbreviation);
        return null;
    }
}
