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
            ?.whether { it.trueCondition }
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
            ?.whether { it.trueCondition }
            ?.inCaseNot(false) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCaseNot(false) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun inCaseScope() {
        subjectFunction
            ?.inCase({ it.falseCondition }) {
                failTest()
            }

        subjectFunction
            ?.whether { it.trueCondition }
            ?.inCase({ it.trueCondition }) { passTest() }
            ?.inCase({ it.falseCondition }) { failTest() }
            ?.inCase({ it.trueCondition }) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun inCaseNotScope() {
        subjectFunction
            ?.inCaseNot({ it.trueCondition }) {
                failTest()
            }

        subjectFunction
            ?.whether { it.trueCondition }
            ?.inCaseNot({ it.falseCondition }) { passTest() }
            ?.inCaseNot({ it.trueCondition }) { failTest() }
            ?.inCaseNot({ it.falseCondition }) { passTest() }
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
