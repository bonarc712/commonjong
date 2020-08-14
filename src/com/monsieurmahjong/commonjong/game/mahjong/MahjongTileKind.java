package com.monsieurmahjong.commonjong.game.mahjong;

public enum MahjongTileKind
{
	M1, // manzu --> characters
	M2, //
	M3, //
	M4, //
	M5, //
	M6, //
	M7, //
	M8, //
	M9, //
	P1, // pinzu --> circles
	P2, //
	P3, //
	P4, //
	P5, //
	P6, //
	P7, //
	P8, //
	P9, //
	S1, // souzu -->�bamboos
	S2, //
	S3, //
	S4, //
	S5, //
	S6, //
	S7, //
	S8, //
	S9, //
	C, // red dragon
	F, // green dragon
	H, // white dragon
	E, // east
	S, // south
	W, // west
	N; // north

	public boolean is(MahjongTileKind... choices)
	{
		for (MahjongTileKind choice : choices)
		{
			if (this == choice)
				return true;
		}
		return false;
	}

	public boolean isHonour()
	{
		return this.isDragon() || this.isWind();
	}

	public boolean isDragon()
	{
		return this.is(C, F, H);
	}

	public boolean isWind()
	{
		return this.is(E, S, W, N);
	}

	public boolean isCharacters()
	{
		return this.is(M1, M2, M3, M4, M5, M6, M7, M8, M9);
	}

	public boolean isCircles()
	{
		return this.is(P1, P2, P3, P4, P5, P6, P7, P8, P9);
	}

	public boolean isBamboos()
	{
		return this.is(S1, S2, S3, S4, S5, S6, S7, S8, S9);
	}

}
