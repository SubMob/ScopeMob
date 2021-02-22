/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class InCaseScopeTest : MainScopeTest() {

    @Test
    fun inCase() {
        subjectFunction
            ?.inCase(false) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase(true) { assertTrue(true, EXPECTED) }
            ?.inCase(false) { fail(UN_EXPECTED) }
            ?.inCase(true) { assertTrue(true, EXPECTED) }
            ?.let { assertTrue(true, EXPECTED) }
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
            ?.inCaseNot(false) { assertTrue(true, EXPECTED) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCaseNot(false) { assertTrue(true, EXPECTED) }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun inCaseScope() {
        subjectFunction
            ?.inCase({ falseCondition }) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase({ trueCondition }) { assertTrue(true, EXPECTED) }
            ?.inCase({ falseCondition }) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(true, EXPECTED) }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun inCaseNotScope() {
        subjectFunction
            ?.inCaseNot({ trueCondition }) {
                fail(UN_EXPECTED)
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCaseNot({ falseCondition }) { assertTrue(true, EXPECTED) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.inCaseNot({ falseCondition }) { assertTrue(true, EXPECTED) }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }
}
