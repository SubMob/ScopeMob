/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class OptionalScopeTest : MainScopeTest() {

    private var nullString: String? = null
    private var notNullString: String? = ""

    @Test
    fun ensure() {
        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition
        ) {
            passTest()
        } ?: run {
            failTest()
        }

        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition,
            subjectFunction?.nullAbleCondition
        ) {
            failTest()
        } ?: run {
            passTest()
        }
    }

    @Test
    fun justInCase() {
        nullString.justInCase {
            nullString = someString
        }.apply {
            assertNotNull(nullString)
            assertEquals(someString, nullString)
        }

        resetString()

        nullString.justInCase {
            nullString = someString
        }.let {
            assertNotNull(nullString)
            assertEquals(someString, nullString)
        }

        resetString()

        nullString.justInCase {
            // not initialized
        }.apply {
            assertNull(nullString)
        }

        resetString()

        notNullString.justInCase {
            failTest()
        }.let {
            assertEquals(someString, notNullString)
        }
    }

    private fun resetString() {
        nullString = null
        notNullString = someString
    }
}
