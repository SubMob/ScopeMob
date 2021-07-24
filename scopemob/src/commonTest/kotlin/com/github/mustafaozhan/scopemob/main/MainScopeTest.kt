/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob.main

import com.github.mustafaozhan.scopemob.either
import com.github.mustafaozhan.scopemob.extension.failTest
import com.github.mustafaozhan.scopemob.extension.passTest
import com.github.mustafaozhan.scopemob.inCase
import com.github.mustafaozhan.scopemob.inCaseNot
import com.github.mustafaozhan.scopemob.mapTo
import com.github.mustafaozhan.scopemob.model.FunctionTestSubject
import com.github.mustafaozhan.scopemob.notSameAs
import com.github.mustafaozhan.scopemob.sameAs
import com.github.mustafaozhan.scopemob.whether
import com.github.mustafaozhan.scopemob.whetherNot
import kotlin.test.Test

open class MainScopeTest {
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
