/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob.main

import com.github.mustafaozhan.scopemob.either
import com.github.mustafaozhan.scopemob.inCase
import com.github.mustafaozhan.scopemob.inCaseNot
import com.github.mustafaozhan.scopemob.mapTo
import com.github.mustafaozhan.scopemob.model.FunctionTestSubject
import com.github.mustafaozhan.scopemob.notSameAs
import com.github.mustafaozhan.scopemob.sameAs
import com.github.mustafaozhan.scopemob.whether
import com.github.mustafaozhan.scopemob.whetherNot
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

open class MainScopeTest {
    companion object {
        const val UN_EXPECTED = "Unexpected"
        const val EXPECTED = "Expected"

        @Suppress("MaybeConst")
        val SOME_STRING: String? = "Some String"
    }

    protected var subjectFunction: FunctionTestSubject? = FunctionTestSubject()

    @Test
    fun `is chain breaks`() {

        subjectFunction
            ?.whether { it.trueCondition }
            ?.whetherNot { falseCondition }
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.whetherNot { it.trueCondition } // exit chain
            ?.whether { true }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }

        subjectFunction
            ?.whether { it.trueCondition }
            ?.whetherNot { falseCondition }
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.either({ it.falseCondition }, { falseCondition }) // exit chain
            ?.whether { true }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(EXPECTED, true) }
    }

    @Test
    fun `is null passed through scope`() {
        subjectFunction = null
        subjectFunction
            ?.whether { it.trueCondition }
            ?.either({ it.falseCondition }, { trueCondition })
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.whetherNot { falseCondition }
            ?.mapTo { it.trueCondition }
            ?.sameAs { true }
            ?.notSameAs { false }
            .whether { true }
            .let {
                if (it == null) {
                    assertTrue(EXPECTED, true)
                } else {
                    fail(UN_EXPECTED)
                }
            }
        subjectFunction = null
        subjectFunction
            ?.whether { it.trueCondition }
            ?.either({ it.falseCondition }, { trueCondition })
            ?.inCase(true) { assertTrue(EXPECTED, true) }
            ?.inCaseNot(true) { fail(UN_EXPECTED) }
            ?.inCase({ trueCondition }) { assertTrue(EXPECTED, true) }
            ?.inCaseNot({ trueCondition }) { fail(UN_EXPECTED) }
            ?.whetherNot { falseCondition }
            ?.mapTo { it.trueCondition }
            ?.sameAs { true }
            ?.notSameAs { false }
            .whether { true }
            ?.let { fail(UN_EXPECTED) }
    }
}
