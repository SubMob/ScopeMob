/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class WhetherScopeTest : MainScopeTest() {

    @Test
    fun whetherTrue() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { trueCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherFalse() {
        subjectFunction
            ?.whether { it.falseCondition }
            ?.whether { falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherMix() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotFalse() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { falseCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherNotTrue() {
        subjectFunction
            ?.whetherNot { it.trueCondition }
            ?.whetherNot { trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.trueCondition }, { trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotMix() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun null_with_expected_boolean_returns_null() {
        assertNull(null?.whether({ true }, { true }))
        assertNull(null?.whetherNot({ false }, { false }))
        assertNull(null?.whether { true })
        assertNull(null?.whetherNot { false })
    }

    @Test
    fun non_null_with_expected_boolean_returns_null() {
        assertEquals(subjectFunction, subjectFunction?.whether({ true }, { true }))
        assertEquals(subjectFunction, subjectFunction?.whetherNot({ false }, { false }))
        assertEquals(subjectFunction, subjectFunction?.whether { true })
        assertEquals(subjectFunction, subjectFunction?.whetherNot { false })
    }
}
