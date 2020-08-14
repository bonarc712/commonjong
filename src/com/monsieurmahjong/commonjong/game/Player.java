package com.monsieurmahjong.commonjong.game;

public abstract class Player
{
	protected String name;

	public Player()
	{
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void play()
	{
	}

	public abstract void showHand();
}
