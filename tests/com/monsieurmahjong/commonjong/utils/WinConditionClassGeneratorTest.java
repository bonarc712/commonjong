package com.monsieurmahjong.commonjong.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.GroupBasedYaku;

public class WinConditionClassGeneratorTest
{
    @Test
    public void creatingAWinConditionClass_UsingSanshokuDoujunAsAnExample_ShouldReturnABarrenSourceCodeClass()
    {
        WinConditionClassGenerator classGenerator = new WinConditionClassGenerator();

        String resultSourceCode = classGenerator.createWinConditionClass("SanshokuDoujun", GroupBasedYaku.class, 2);

        assertEquals(getSourceCodeForSanshokuDoujun(), resultSourceCode, "Sources don't match");
    }

    @Test
    public void creatingAWinConditionTest_UsingSanshokuDoujunAsAnExample_ShouldReturnACompleteTestCase()
    {
        WinConditionClassGenerator classGenerator = new WinConditionClassGenerator();
        List<GroupBasedWinConditionTestCase> testCases = new ArrayList<>();
        testCases.add(new GroupBasedWinConditionTestCase("completeSanshokuDoujunHand", TileGroupUtils.tileGroupsOf("456m", "456p", "456s", "999s", "11z"), true));
        testCases.add(new GroupBasedWinConditionTestCase("incompleteSanshokuDoujunHand", TileGroupUtils.tileGroupsOf("123m", "123p", "123s", "11z"), true));
        testCases.add(new GroupBasedWinConditionTestCase("completeNonSanshokuDoujunHand", TileGroupUtils.tileGroupsOf("123m", "345m", "22p", "345p", "678p"), false));
        testCases.add(new GroupBasedWinConditionTestCase("incompleteNonSanshokuDoujunHand", TileGroupUtils.tileGroupsOf("111m", "555p", "11s"), false));

        String resultTestCode = classGenerator.createWinConditionTest("SanshokuDoujun", GroupBasedYaku.class, 2, testCases);

        System.out.println(resultTestCode);
        System.out.println(getTestCodeForSanshokuDoujun());

        assertEquals(getTestCodeForSanshokuDoujun(), resultTestCode, "Sources don't match");
    }

    private String getSourceCodeForSanshokuDoujun()
    {
        return "package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;\n" + //
                "\n" + //
                "import java.util.*;\n" + //
                "\n" + //
                "import com.monsieurmahjong.commonjong.game.Hand;\n" + //
                "import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;\n" + //
                "\n" + //
                "public class SanshokuDoujun extends GroupBasedYaku\n" + //
                "{\n" + //
                "    public SanshokuDoujun(Hand hand, List<TileGroup> groups)\n" + //
                "    {\n" + //
                "        super(hand, groups);\n" + //
                "    }\n" + //
                "\n" + //
                "    @Override\n" + //
                "    public boolean isValid()\n" + //
                "    {\n" + //
                "        return false;\n" + //
                "    }\n" + //
                "\n" + //
                "    @Override\n" + //
                "    public int getHanValue()\n" + //
                "    {\n" + //
                "        return 2;\n" + //
                "    }\n" + //
                "}\n";
    }

    private String getTestCodeForSanshokuDoujun()
    {
        return "package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;\n" + //
                "\n" + //
                "import static org.junit.jupiter.api.Assertions.*;\n" + //
                "import static org.mockito.Mockito.*;\n" + //
                "\n" + //
                "import java.util.*;\n" + //
                "\n" + //
                "import org.junit.jupiter.api.Test;\n" + //
                "\n" + //
                "import com.monsieurmahjong.commonjong.game.Hand;\n" + //
                "import com.monsieurmahjong.commonjong.rules.generic.utils.TileGroupUtils;\n" + //
                "import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;\n" + //
                "import com.monsieurmahjong.commonjong.rules.riichi.yakus.Yaku;\n" + //
                "\n" + //
                "public class SanshokuDoujunTest\n" + //
                "{\n" + //
                "    private Hand anyHand = mock(Hand.class);\n" + //
                "    private List<TileGroup> anyGroups = new ArrayList<>();\n" + //
                "\n" + //
                "    private List<TileGroup> completeSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf(\"456m\", \"456p\", \"456s\", \"999s\", \"11z\");\n" + //
                "    private List<TileGroup> incompleteSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf(\"123m\", \"123p\", \"123s\", \"11z\");\n" + //
                "    private List<TileGroup> completeNonSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf(\"123m\", \"345m\", \"22p\", \"345p\", \"678p\");\n" + //
                "    private List<TileGroup> incompleteNonSanshokuDoujunHandGroups = TileGroupUtils.tileGroupsOf(\"111m\", \"555p\", \"11s\");\n" + //
                "\n" + //
                "    @Test\n" + //
                "    public void testValidityOf_CompleteSanshokuDoujunHand_ShouldBeTrue()\n" + //
                "    {\n" + //
                "        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(completeSanshokuDoujunHandGroups)), completeSanshokuDoujunHandGroups);\n" + //
                "\n" + //
                "        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();\n" + //
                "\n" + //
                "        assertTrue(sanshokuDoujunIsValid, \"456m456p456999s11z should be valid for SanshokuDoujun\");\n" + //
                "    }\n" + //
                "\n" + //
                "    @Test\n" + //
                "    public void testValidityOf_IncompleteSanshokuDoujunHand_ShouldBeTrue()\n" + //
                "    {\n" + //
                "        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteSanshokuDoujunHandGroups)), incompleteSanshokuDoujunHandGroups);\n" + //
                "\n" + //
                "        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();\n" + //
                "\n" + //
                "        assertTrue(sanshokuDoujunIsValid, \"123m123p123s11z should be valid for SanshokuDoujun\");\n" + //
                "    }\n" + //
                "\n" + //
                "    @Test\n" + //
                "    public void testValidityOf_CompleteNonSanshokuDoujunHand_ShouldBeFalse()\n" + //
                "    {\n" + //
                "        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(completeNonSanshokuDoujunHandGroups)), completeNonSanshokuDoujunHandGroups);\n" + //
                "\n" + //
                "        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();\n" + //
                "\n" + //
                "        assertFalse(sanshokuDoujunIsValid, \"123345m22345678p should not be valid for SanshokuDoujun\");\n" + //
                "    }\n" + //
                "\n" + //
                "    @Test\n" + //
                "    public void testValidityOf_IncompleteNonSanshokuDoujunHand_ShouldBeFalse()\n" + //
                "    {\n" + //
                "        Yaku sanshokuDoujun = new SanshokuDoujun(new Hand(TileGroupUtils.getTilesFromTileGroups(incompleteNonSanshokuDoujunHandGroups)), incompleteNonSanshokuDoujunHandGroups);\n" + //
                "\n" + //
                "        boolean sanshokuDoujunIsValid = sanshokuDoujun.isValid();\n" + //
                "\n" + //
                "        assertFalse(sanshokuDoujunIsValid, \"111m555p11s should not be valid for SanshokuDoujun\");\n" + //
                "    }\n" + //
                "\n" + //
                "    @Test\n" + //
                "    public void testValueOf_SanshokuDoujun_ShouldBeTwo()\n" + //
                "    {\n" + //
                "        Yaku sanshokuDoujun = new SanshokuDoujun(anyHand, anyGroups);\n" + //
                "\n" + //
                "        int sanshokuDoujunValue = sanshokuDoujun.getHanValue();\n" + //
                "\n" + //
                "        assertEquals(2, sanshokuDoujunValue, \"SanshokuDoujun value should be 2\");\n" + //
                "    }\n" + //
                "}\n";
    }
}
