package game.locale;

import game.mahjong.MahjongTileKind;

public class TileLabels_fr
{
	private static String UN_DE_CARACT�RES = "Un de caract�res";
	private static String DEUX_DE_CARACT�RES = "Deux de caract�res";
	private static String TROIS_DE_CARACT�RES = "Trois de caract�res";
	private static String QUATRE_DE_CARACT�RES = "Quatre de caract�res";
	private static String CINQ_DE_CARACT�RES = "Cinq de caract�res";
	private static String SIX_DE_CARACT�RES = "Six de caract�res";
	private static String SEPT_DE_CARACT�RES = "Sept de caract�res";
	private static String HUIT_DE_CARACT�RES = "Huit de caract�res";
	private static String NEUF_DE_CARACT�RES = "Neuf de caract�res";
	private static String UN_DE_SAP�QUES = "Un de sap�ques";
	private static String DEUX_DE_SAP�QUES = "Deux de sap�ques";
	private static String TROIS_DE_SAP�QUES = "Trois de sap�ques";
	private static String QUATRE_DE_SAP�QUES = "Quatre de sap�ques";
	private static String CINQ_DE_SAP�QUES = "Cinq de sap�ques";
	private static String SIX_DE_SAP�QUES = "Six de sap�ques";
	private static String SEPT_DE_SAP�QUES = "Sept de sap�ques";
	private static String HUIT_DE_SAP�QUES = "Huit de sap�ques";
	private static String NEUF_DE_SAP�QUES = "Neuf de sap�ques";
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
			return UN_DE_CARACT�RES;
		case M2:
			return DEUX_DE_CARACT�RES;
		case M3:
			return TROIS_DE_CARACT�RES;
		case M4:
			return QUATRE_DE_CARACT�RES;
		case M5:
			return CINQ_DE_CARACT�RES;
		case M6:
			return SIX_DE_CARACT�RES;
		case M7:
			return SEPT_DE_CARACT�RES;
		case M8:
			return HUIT_DE_CARACT�RES;
		case M9:
			return NEUF_DE_CARACT�RES;
		case P1:
			return UN_DE_SAP�QUES;
		case P2:
			return DEUX_DE_SAP�QUES;
		case P3:
			return TROIS_DE_SAP�QUES;
		case P4:
			return QUATRE_DE_SAP�QUES;
		case P5:
			return CINQ_DE_SAP�QUES;
		case P6:
			return SIX_DE_SAP�QUES;
		case P7:
			return SEPT_DE_SAP�QUES;
		case P8:
			return HUIT_DE_SAP�QUES;
		case P9:
			return NEUF_DE_SAP�QUES;
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
