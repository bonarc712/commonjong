package game.locale;

import game.mahjong.MahjongTileKind;

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
		case M1:
			return UN_DE_CARACTÈRES;
		case M2:
			return DEUX_DE_CARACTÈRES;
		case M3:
			return TROIS_DE_CARACTÈRES;
		case M4:
			return QUATRE_DE_CARACTÈRES;
		case M5:
			return CINQ_DE_CARACTÈRES;
		case M6:
			return SIX_DE_CARACTÈRES;
		case M7:
			return SEPT_DE_CARACTÈRES;
		case M8:
			return HUIT_DE_CARACTÈRES;
		case M9:
			return NEUF_DE_CARACTÈRES;
		case P1:
			return UN_DE_SAPÈQUES;
		case P2:
			return DEUX_DE_SAPÈQUES;
		case P3:
			return TROIS_DE_SAPÈQUES;
		case P4:
			return QUATRE_DE_SAPÈQUES;
		case P5:
			return CINQ_DE_SAPÈQUES;
		case P6:
			return SIX_DE_SAPÈQUES;
		case P7:
			return SEPT_DE_SAPÈQUES;
		case P8:
			return HUIT_DE_SAPÈQUES;
		case P9:
			return NEUF_DE_SAPÈQUES;
		case S1:
			return UN_DE_BAMBOUS;
		case S2:
			return DEUX_DE_BAMBOUS;
		case S3:
			return TROIS_DE_BAMBOUS;
		case S4:
			return QUATRE_DE_BAMBOUS;
		case S5:
			return CINQ_DE_BAMBOUS;
		case S6:
			return SIX_DE_BAMBOUS;
		case S7:
			return SEPT_DE_BAMBOUS;
		case S8:
			return HUIT_DE_BAMBOUS;
		case S9:
			return NEUF_DE_BAMBOUS;
		case C:
			return DRAGON_ROUGE;
		case F:
			return DRAGON_VERT;
		case H:
			return DRAGON_BLANC;
		case E:
			return EST;
		case S:
			return SUD;
		case W:
			return OUEST;
		case N:
			return NORD;
		default:
			return "";
		}
	}
}
