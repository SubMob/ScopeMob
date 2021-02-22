/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class EitherScopeTest : MainScopeTest() {

    @Test
    fun eitherMixMix() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { falseCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { trueCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun eitherMixTrue() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun eitherMixFalse() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun eitherNotMix() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun eitherNotMixTrue() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun eitherNotMixFalse() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun eitherNotFalseFalse() {
        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }
}
