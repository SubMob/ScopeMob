/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test

internal class InCaseScopeTest : MainScopeTest() {

    @Test
    fun inCase() {
        subjectFunction
            ?.inCase(false) {
                failTest()
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase(true) { passTest() }
            ?.inCase(false) { failTest() }
            ?.inCase(true) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun inCaseNot() {
        subjectFunction
            ?.inCaseNot(true) {
                failTest()
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCaseNot(false) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCaseNot(false) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun inCaseScope() {
        subjectFunction
            ?.inCase({ falseCondition }) {
                failTest()
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCase({ trueCondition }) { passTest() }
            ?.inCase({ falseCondition }) { failTest() }
            ?.inCase({ trueCondition }) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun inCaseNotScope() {
        subjectFunction
            ?.inCaseNot({ trueCondition }) {
                failTest()
            }

        subjectFunction
            ?.whether { trueCondition }
            ?.inCaseNot({ falseCondition }) { passTest() }
            ?.inCaseNot({ trueCondition }) { failTest() }
            ?.inCaseNot({ falseCondition }) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
