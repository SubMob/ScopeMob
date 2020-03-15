package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.base.BaseCopeTest
import org.junit.Assert
import org.junit.Test

class OptionalLambdaTest : BaseCopeTest() {

    private var nullString: String? = null
    private var notNullString: String? = SOME_STRING

    @Test
    fun ensure() {
        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition
        ) {
            Assert.assertTrue(EXPECTED, true)
        } ?: run {
            Assert.fail(UN_EXPECTED)
        }

        ensure(
            subjectFunction?.falseCondition,
            subjectFunction?.trueCondition,
            subjectFunction?.nullAbleCondition
        ) {
            Assert.fail(UN_EXPECTED)
        } ?: run {
            Assert.assertTrue(EXPECTED, true)
        }
    }

    @Test
    fun inCase() {
        nullString.inCase {
            nullString = SOME_STRING
        }.apply {
            Assert.assertNotNull(nullString)
            Assert.assertEquals(SOME_STRING, nullString)
        }

        resetString()

        nullString.inCase {
            nullString = SOME_STRING
        }.let {
            Assert.assertNotNull(nullString)
            Assert.assertEquals(SOME_STRING, nullString)
        }

        resetString()

        nullString.inCase {
            // not initialized
        }.apply {
            Assert.assertNull(nullString)
        }

        resetString()

        notNullString.inCase {
            Assert.fail(UN_EXPECTED)
        }.let {
            Assert.assertEquals(SOME_STRING, notNullString)
        }
    }

    private fun resetString() {
        nullString = null
        notNullString = SOME_STRING
    }
}
