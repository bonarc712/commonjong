package com.monsieurmahjong.commonjong.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtilsTest
{
    @Test
    public void testUcFirst()
    {
        assertEquals("Bob", StringUtils.ucFirst("bob"));
        assertEquals("Bob", StringUtils.ucFirst("Bob"));
        assertEquals("1234", StringUtils.ucFirst("1234"));
        assertEquals("", StringUtils.ucFirst(""));
        assertEquals("H", StringUtils.ucFirst("h"));
        assertEquals("F", StringUtils.ucFirst("F"));
    }

    @Test
    public void testLcFirst()
    {
        assertEquals("bob", StringUtils.lcFirst("bob"));
        assertEquals("bob", StringUtils.lcFirst("Bob"));
        assertEquals("1234", StringUtils.lcFirst("1234"));
        assertEquals("", StringUtils.lcFirst(""));
        assertEquals("h", StringUtils.lcFirst("h"));
        assertEquals("f", StringUtils.lcFirst("F"));
    }

}
