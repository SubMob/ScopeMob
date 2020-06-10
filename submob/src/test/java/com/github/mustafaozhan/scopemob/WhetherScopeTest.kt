/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class WhetherScopeTest : MainScopeTest() {

    @Test
    fun `whether true`() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { trueCondition }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { trueCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `whether  false`() {
        subjectFunction
            ?.whether { it.falseCondition }
            ?.whether { falseCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        // vararg
        subjectFunction
            ?.whether({ it.falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `whether mix`() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { falseCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `whetherNot false`() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { falseCondition }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { falseCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `whetherNot true`() {
        subjectFunction
            ?.whetherNot { it.trueCondition }
            ?.whetherNot { trueCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `whetherNot mix`() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { trueCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { trueCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }
}
