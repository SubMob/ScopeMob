/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class EitherScopeTest : MainScopeTest() {

    @Test
    fun `either mix mix`() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { falseCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { trueCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `either mix true`() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `either mix false`() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `eitherNot mix`() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `eitherNot mix true`() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `eitherNot mix false`() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `eitherNot false false`() {
        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }
}
