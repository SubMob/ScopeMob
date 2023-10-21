/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob.main

import com.github.submob.scopemob.either
import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.inCase
import com.github.submob.scopemob.inCaseNot
import com.github.submob.scopemob.mapTo
import com.github.submob.scopemob.model.FunctionTestSubject
import com.github.submob.scopemob.notSameAs
import com.github.submob.scopemob.sameAs
import com.github.submob.scopemob.whether
import com.github.submob.scopemob.whetherNot
import kotlin.test.Test

internal open class MainScopeTest {
    protected var subjectFunction: FunctionTestSubject? = FunctionTestSubject()

    protected var someString: String? = "Some String"

    @Test
    fun isChainBreaks() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whetherNot { falseCondition }
            ?.inCase(true) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCase({ trueCondition }) { passTest() }
            ?.inCaseNot({ trueCondition }) { failTest() }
            ?.whetherNot { it.trueCondition } // exit chain
            ?.whether { true }
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.whether { it.trueCondition }
            ?.whetherNot { falseCondition }
            ?.inCase(true) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCase({ trueCondition }) { passTest() }
            ?.inCaseNot({ trueCondition }) { failTest() }
            ?.either({ it.falseCondition }, { falseCondition }) // exit chain
            ?.whether { true }
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun isNullPassedThroughScope() {
        subjectFunction = null
        subjectFunction
            ?.whether { it.trueCondition }
            ?.either({ it.falseCondition }, { trueCondition })
            ?.inCase(true) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCase({ trueCondition }) { passTest() }
            ?.inCaseNot({ trueCondition }) { failTest() }
            ?.whetherNot { falseCondition }
            ?.mapTo { it.trueCondition }
            ?.sameAs { true }
            ?.notSameAs { false }
            .whether { true }
            .let {
                if (it == null) {
                    passTest()
                } else {
                    failTest()
                }
            }
        subjectFunction = null
        subjectFunction
            ?.whether { it.trueCondition }
            ?.either({ it.falseCondition }, { trueCondition })
            ?.inCase(true) { passTest() }
            ?.inCaseNot(true) { failTest() }
            ?.inCase({ trueCondition }) { passTest() }
            ?.inCaseNot({ trueCondition }) { failTest() }
            ?.whetherNot { falseCondition }
            ?.mapTo { it.trueCondition }
            ?.sameAs { true }
            ?.notSameAs { false }
            .whether { true }
            ?.let { failTest() }
    }
}
