package com.monsieurmahjong.commonjong.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NameGenerator
{
	public static String generateName()
	{
		return generateNameOtherThan(Collections.EMPTY_LIST);
	}

	public static String generateNameOtherThan(List<String> names)
	{
		List<String> possibleNames = getPossibleNames();
		possibleNames.removeAll(names);
		return possibleNames.get((int) (Math.random() * possibleNames.size()));
	}

	private static List<String> getPossibleNames()
	{
		List<String> possibleNames = new ArrayList<>();
		possibleNames.add("A");
		possibleNames.add("B");
		possibleNames.add("C");
		possibleNames.add("D");
		possibleNames.add("E");
		possibleNames.add("F");
		possibleNames.add("G");
		possibleNames.add("H");
		return possibleNames;
	}
}
