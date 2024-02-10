package com.example.sneakers

import com.example.sneakers.helpers.nonNullString
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilHelper {

    @Test
    fun `non null string helper for null string`() {
        TestCase.assertEquals(true, "null".nonNullString() == "")
    }

    @Test
    fun `non null string helper for empty string`() {
        TestCase.assertEquals(true, "".nonNullString() == "")
    }

    @Test
    fun `non null string helper for any null object`() {
        val string: Any? = null
        TestCase.assertEquals(true, string.nonNullString() == "")
    }

    @Test
    fun `non null string helper for any actual  object`() {
        val string: Any? = null
        TestCase.assertEquals(true, string.nonNullString() == "")
    }
}