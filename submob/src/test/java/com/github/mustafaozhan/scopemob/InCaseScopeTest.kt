/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class InCaseScopeTest : MainScopeTest() {

    @Test
    fun inCase() {
        subjectFunction
            ?.inCase(false) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.inCase(false) { fail(UN_EXPECTED) }
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun inCaseNot() {
        subjectFunction
            ?.inCaseNot(true) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCaseNot(false) { assertTrue(EXPECTED, true) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCaseNot(false) { assertTrue(EXPECTED, true) }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `inCase scope`() {
        subjectFunction
            ?.inCase({ falseCondition }) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.inCase({ falseCondition }) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun `inCaseNot scope`() {
        subjectFunction
            ?.inCaseNot({ trueCondition }) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCaseNot({ falseCondition }) { assertTrue(EXPECTED, true) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.inCaseNot({ falseCondition }) { assertTrue(EXPECTED, true) }
            ?.let { assertTrue(EXPECTED, true) }
            ?: run { fail(UN_EXPECTED) }
    }
}
