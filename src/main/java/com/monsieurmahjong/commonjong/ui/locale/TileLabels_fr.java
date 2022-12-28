package com.monsieurmahjong.commonjong.ui.locale;

import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class TileLabels_fr
{
    private static String UN_DE_CARACTÈRES = "Un de caractères";
    private static String DEUX_DE_CARACTÈRES = "Deux de caractères";
    private static String TROIS_DE_CARACTÈRES = "Trois de caractères";
    private static String QUATRE_DE_CARACTÈRES = "Quatre de caractères";
    private static String CINQ_DE_CARACTÈRES = "Cinq de caractères";
    private static String SIX_DE_CARACTÈRES = "Six de caractères";
    private static String SEPT_DE_CARACTÈRES = "Sept de caractères";
    private static String HUIT_DE_CARACTÈRES = "Huit de caractères";
    private static String NEUF_DE_CARACTÈRES = "Neuf de caractères";
    private static String UN_DE_SAPÈQUES = "Un de sapèques";
    private static String DEUX_DE_SAPÈQUES = "Deux de sapèques";
    private static String TROIS_DE_SAPÈQUES = "Trois de sapèques";
    private static String QUATRE_DE_SAPÈQUES = "Quatre de sapèques";
    private static String CINQ_DE_SAPÈQUES = "Cinq de sapèques";
    private static String SIX_DE_SAPÈQUES = "Six de sapèques";
    private static String SEPT_DE_SAPÈQUES = "Sept de sapèques";
    private static String HUIT_DE_SAPÈQUES = "Huit de sapèques";
    private static String NEUF_DE_SAPÈQUES = "Neuf de sapèques";
    private static String UN_DE_BAMBOUS = "Un de bambous";
    private static String DEUX_DE_BAMBOUS = "Deux de bambous";
    private static String TROIS_DE_BAMBOUS = "Trois de bambous";
    private static String QUATRE_DE_BAMBOUS = "Quatre de bambous";
    private static String CINQ_DE_BAMBOUS = "Cinq de bambous";
    private static String SIX_DE_BAMBOUS = "Six de bambous";
    private static String SEPT_DE_BAMBOUS = "Sept de bambous";
    private static String HUIT_DE_BAMBOUS = "Huit de bambous";
    private static String NEUF_DE_BAMBOUS = "Neuf de bambous";
    private static String DRAGON_VERT = "Dragon vert";
    private static String DRAGON_BLANC = "Dragon blanc";
    private static String DRAGON_ROUGE = "Dragon rouge";
    private static String EST = "Est";
    private static String SUD = "Sud";
    private static String OUEST = "Ouest";
    private static String NORD = "Nord";

    public static String getLabel(MahjongTileKind tile)
    {
        switch (tile)
        {
        case CHARACTERS_1:
            return UN_DE_CARACTÈRES;
        case CHARACTERS_2:
            return DEUX_DE_CARACTÈRES;
        case CHARACTERS_3:
            return TROIS_DE_CARACTÈRES;
        case CHARACTERS_4:
            return QUATRE_DE_CARACTÈRES;
        case CHARACTERS_5:
            return CINQ_DE_CARACTÈRES;
        case CHARACTERS_6:
            return SIX_DE_CARACTÈRES;
        case CHARACTERS_7:
            return SEPT_DE_CARACTÈRES;
        case CHARACTERS_8:
            return HUIT_DE_CARACTÈRES;
        case CHARACTERS_9:
            return NEUF_DE_CARACTÈRES;
        case CIRCLES_1:
            return UN_DE_SAPÈQUES;
        case CIRCLES_2:
            return DEUX_DE_SAPÈQUES;
        case CIRCLES_3:
            return TROIS_DE_SAPÈQUES;
        case CIRCLES_4:
            return QUATRE_DE_SAPÈQUES;
        case CIRCLES_5:
            return CINQ_DE_SAPÈQUES;
        case CIRCLES_6:
            return SIX_DE_SAPÈQUES;
        case CIRCLES_7:
            return SEPT_DE_SAPÈQUES;
        case CIRCLES_8:
            return HUIT_DE_SAPÈQUES;
        case CIRCLES_9:
            return NEUF_DE_SAPÈQUES;
        case BAMBOOS_1:
            return UN_DE_BAMBOUS;
        case BAMBOOS_2:
            return DEUX_DE_BAMBOUS;
        case BAMBOOS_3:
            return TROIS_DE_BAMBOUS;
        case BAMBOOS_4:
            return QUATRE_DE_BAMBOUS;
        case BAMBOOS_5:
            return CINQ_DE_BAMBOUS;
        case BAMBOOS_6:
            return SIX_DE_BAMBOUS;
        case BAMBOOS_7:
            return SEPT_DE_BAMBOUS;
        case BAMBOOS_8:
            return HUIT_DE_BAMBOUS;
        case BAMBOOS_9:
            return NEUF_DE_BAMBOUS;
        case RED:
            return DRAGON_ROUGE;
        case GREEN:
            return DRAGON_VERT;
        case WHITE:
            return DRAGON_BLANC;
        case EAST:
            return EST;
        case SOUTH:
            return SUD;
        case WEST:
            return OUEST;
        case NORTH:
            return NORD;
        default:
            return "";
        }
    }
}
