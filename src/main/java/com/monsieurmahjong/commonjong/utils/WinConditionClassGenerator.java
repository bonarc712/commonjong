package com.monsieurmahjong.commonjong.utils;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.utils.*;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.GroupBasedYaku;

public class WinConditionClassGenerator
{
    private static final String CONSTRUCTOR_PARAMETERS = "(Hand hand, List<TileGroup> groups)";
    private static final String CONSTRUCTOR_SUPER_CALL = "super(hand, groups)";

    public String createWinConditionClass(String className, Class<? extends Yaku> parentWinCondition, int score)
    {
        String classContents = "";

        classContents += writePackageName(parentWinCondition);
        classContents += writeLineBreak();

        classContents += writeClassImports(parentWinCondition);
        classContents += writeLineBreak();

        classContents += writeClass(className, parentWinCondition, score);

        return classContents;
    }

    public String createWinConditionTest(String className, Class<? extends Yaku> parentWinCondition, int score, List<GroupBasedWinConditionTestCase> testCases)
    {
        String testContents = "";

        testContents += writePackageName(parentWinCondition);
        testContents += writeLineBreak();

        testContents += writeTestImports(parentWinCondition);
        testContents += writeLineBreak();

        testContents += writeTest(className, parentWinCondition, score, testCases);

        return testContents;
    }

    private String writeTest(String className, Class<? extends Yaku> parentWinCondition, int score, List<GroupBasedWinConditionTestCase> testCases)
    {
        StringBuilder testStringBuilder = new StringBuilder();
        testStringBuilder.append("public class " + className + "Test" + writeLineBreak());
        testStringBuilder.append(writeOpeningBracket());
        testStringBuilder.append(writeFourSpaces() + "private Hand anyHand = mock(Hand.class)" + writeLineEnd());
        if (parentWinCondition == GroupBasedYaku.class)
        {
            testStringBuilder.append(writeFourSpaces() + "private List<TileGroup> anyGroups = new ArrayList<>()" + writeLineEnd());
        }
        testStringBuilder.append(writeLineBreak());

        testCases.forEach(testCase -> {
            testStringBuilder.append(writeFourSpaces() + "private List<TileGroup> " + testCase.getName() + "Groups = TileGroupUtils.tileGroupsOf(");
            testCase.getGroups().forEach(tileGroup -> {
                testStringBuilder.append("\"" + tileGroup.toMPSZNotation() + "\", ");
            });
            testStringBuilder.delete(testStringBuilder.toString().length() - 2, testStringBuilder.toString().length());
            testStringBuilder.append(")" + writeLineEnd());
        });
        testStringBuilder.append(writeLineBreak());

        testCases.forEach(testCase -> {
            testStringBuilder.append(writeTestCaseMethod(className, testCase));
            testStringBuilder.append(writeLineBreak());
        });

        testStringBuilder.append(writeValueTest(className, score));

        testStringBuilder.append(writeClosingBracket());

        return testStringBuilder.toString();
    }

    private Object writeValueTest(String className, int score)
    {
        String testCaseText = "";

        testCaseText += writeFourSpaces() + "@Test" + writeLineBreak();
        testCaseText += writeFourSpaces() + "public void testValueOf_" + className + "_ShouldBe";
        String scoreText = "";
        switch (score)
        {
            case 1:
                scoreText = "One";
                break;
            case 2:
                scoreText = "Two";
                break;
            case 3:
                scoreText = "Three";
                break;
            case 5:
                scoreText = "Five";
                break;
            case 6:
                scoreText = "Six";
                break;
            case 13:
                scoreText = "Thirteen";
                break;
            default:
                scoreText = "Unknown";
                break;
        }
        testCaseText += scoreText + "()" + writeLineBreak();
        testCaseText += writeFourSpaces() + writeOpeningBracket();

        String variableName = StringUtils.lcFirst(className);
        String valueVariableName = variableName + "Value";
        testCaseText += writeFourSpaces(2) + "Yaku " + variableName + " = new " + className + "(anyHand, anyGroups)" + writeLineEnd();
        testCaseText += writeLineBreak();
        testCaseText += writeFourSpaces(2) + "int " + valueVariableName + " = " + variableName + ".getHanValue()" + writeLineEnd();
        testCaseText += writeLineBreak();
        testCaseText += writeFourSpaces(2) + "assertEquals(" + score + ", " + valueVariableName + ", \"" + className + " value should be " + score + "\")" + writeLineEnd();

        testCaseText += writeFourSpaces() + writeClosingBracket();

        return testCaseText;
    }

    private String writeTestCaseMethod(String className, GroupBasedWinConditionTestCase testCase)
    {
        String testCaseText = "";

        testCaseText += writeFourSpaces() + "@Test" + writeLineBreak();
        testCaseText += writeFourSpaces() + "public void testValidityOf_" + StringUtils.ucFirst(testCase.getName()) + "_ShouldBe";
        if (testCase.isExpectsSuccess())
        {
            testCaseText += "True()" + writeLineBreak();
        }
        else
        {
            testCaseText += "False()" + writeLineBreak();
        }
        testCaseText += writeFourSpaces() + writeOpeningBracket();

        String variableName = StringUtils.lcFirst(className);
        String groupsVariableName = testCase.getName() + "Groups";
        String isValidVariableName = variableName + "IsValid";
        testCaseText += writeFourSpaces(2) + "Yaku " + variableName + " = new " + className + "(new Hand(TileGroupUtils.getTilesFromTileGroups(" + groupsVariableName + ")), " + groupsVariableName
                + ")" + writeLineEnd();
        testCaseText += writeLineBreak();
        testCaseText += writeFourSpaces(2) + "boolean " + isValidVariableName + " = " + variableName + ".isValid()" + writeLineEnd();
        testCaseText += writeLineBreak();
        testCaseText += writeFourSpaces(2) + "assert";
        if (testCase.isExpectsSuccess())
        {
            testCaseText += "True";
        }
        else
        {
            testCaseText += "False";
        }
        testCaseText += "(" + isValidVariableName + ", \"" + TileKindUtils.getHandAsMPSZNotation(TileGroupUtils.getTilesFromTileGroups(testCase.getGroups())) + " should ";
        if (!testCase.isExpectsSuccess())
        {
            testCaseText += "not ";
        }
        testCaseText += "be valid for " + className + "\")" + writeLineEnd();

        testCaseText += writeFourSpaces() + writeClosingBracket();

        return testCaseText;
    }

    private String writeClass(String className, Class<? extends Yaku> parentWinCondition, int score)
    {
        String classText = "";
        classText += "public class " + className + " extends " + parentWinCondition.getSimpleName() + writeLineBreak();
        classText += writeOpeningBracket();
        classText += writeFourSpaces() + "public " + className + CONSTRUCTOR_PARAMETERS + writeLineBreak();
        classText += writeFourSpaces() + writeOpeningBracket();
        classText += writeFourSpaces(2) + CONSTRUCTOR_SUPER_CALL + writeLineEnd();
        classText += writeFourSpaces() + writeClosingBracket();
        classText += writeLineBreak();
        classText += writeFourSpaces() + "@Override" + writeLineBreak();
        classText += writeFourSpaces() + "public boolean isValid()" + writeLineBreak();
        classText += writeFourSpaces() + writeOpeningBracket();
        classText += writeFourSpaces(2) + "return false" + writeLineEnd();
        classText += writeFourSpaces() + writeClosingBracket();
        classText += writeLineBreak();
        classText += writeFourSpaces() + "@Override" + writeLineBreak();
        classText += writeFourSpaces() + "public int getHanValue()" + writeLineBreak();
        classText += writeFourSpaces() + writeOpeningBracket();
        classText += writeFourSpaces(2) + "return " + score + writeLineEnd();
        classText += writeFourSpaces() + writeClosingBracket();
        classText += writeClosingBracket();

        return classText;
    }

    private String writeOpeningBracket()
    {
        return "{" + writeLineBreak();
    }

    private String writeClosingBracket()
    {
        return "}" + writeLineBreak();
    }

    private String writeTestImports(Class<? extends Yaku> parentWinCondition)
    {
        String imports = "";
        imports += "import static org.junit.jupiter.api.Assertions.*" + writeLineEnd();
        imports += "import static org.mockito.Mockito.*" + writeLineEnd();
        imports += writeLineBreak();
        imports += "import java.util.*" + writeLineEnd();
        imports += writeLineBreak();
        imports += "import org.junit.jupiter.api.Test" + writeLineEnd();
        imports += writeLineBreak();
        if (parentWinCondition.getName().endsWith("GroupBasedYaku"))
        {
            imports += writeSingleImportLine(Hand.class);
            imports += writeSingleImportLine(TileGroupUtils.class);
            imports += writeSingleImportLine(TileGroup.class);
            imports += writeSingleImportLine(Yaku.class);
        }
        return imports;
    }

    private String writeClassImports(Class<? extends Yaku> parentWinCondition)
    {
        String imports = "";
        imports += "import java.util.*" + writeLineEnd();
        imports += writeLineBreak();
        if (parentWinCondition.getName().endsWith("GroupBasedYaku"))
        {
            imports += writeSingleImportLine(Hand.class);
            imports += writeSingleImportLine(TileGroup.class);
        }
        return imports;
    }

    private String writeFourSpaces()
    {
        return writeFourSpaces(1);
    }

    private String writeFourSpaces(int tabAmount)
    {
        String spaces = "";
        for (int i = 0; i < tabAmount; i++)
        {
            spaces += "    ";
        }
        return spaces;
    }

    private String writeSingleImportLine(Class<?> importedClass)
    {
        return "import " + importedClass.getName() + writeLineEnd();
    }

    private String writePackageName(Class<? extends Yaku> parentWinCondition)
    {
        return "package " + parentWinCondition.getPackage().getName() + writeLineEnd();
    }

    private String writeLineEnd()
    {
        return ";" + writeLineBreak();
    }

    private String writeLineBreak()
    {
        return "\n";
    }
}
