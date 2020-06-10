/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

class OptionalScopeTest : MainScopeTest() {

    private var nullString: String? = null
    private var notNullString: String? = SOME_STRING

    @Test
    fun ensure() {
        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition
        ) {
            assertTrue(EXPECTED, true)
        } ?: run {
            fail(UN_EXPECTED)
        }

        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition,
            subjectFunction?.nullAbleCondition
        ) {
            fail(UN_EXPECTED)
        } ?: run {
            assertTrue(EXPECTED, true)
        }
    }

    @Test
    fun justInCase() {
        nullString.justInCase {
            nullString = SOME_STRING
        }.apply {
            assertNotNull(nullString)
            assertEquals(SOME_STRING, nullString)
        }

        resetString()

        nullString.justInCase {
            nullString = SOME_STRING
        }.let {
            assertNotNull(nullString)
            assertEquals(SOME_STRING, nullString)
        }

        resetString()

        nullString.justInCase {
            // not initialized
        }.apply {
            assertNull(nullString)
        }

        resetString()

        notNullString.justInCase {
            fail(UN_EXPECTED)
        }.let {
            assertEquals(SOME_STRING, notNullString)
        }
    }

    private fun resetString() {
        nullString = null
        notNullString = SOME_STRING
    }
}
