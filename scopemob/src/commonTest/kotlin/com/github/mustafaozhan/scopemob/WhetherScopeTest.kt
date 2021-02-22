/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class WhetherScopeTest : MainScopeTest() {

    @Test
    fun whetherTrue() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { trueCondition }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { trueCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun whetherFalse() {
        subjectFunction
            ?.whether { it.falseCondition }
            ?.whether { falseCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        // vararg
        subjectFunction
            ?.whether({ it.falseCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun whetherMix() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { falseCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { falseCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun whetherNotFalse() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { falseCondition }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { falseCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun whetherNotTrue() {
        subjectFunction
            ?.whetherNot { it.trueCondition }
            ?.whetherNot { trueCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.trueCondition }, { trueCondition })
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun whetherNotMix() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { trueCondition }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { trueCondition })
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }
}
