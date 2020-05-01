/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import org.junit.Assert
import org.junit.Test

class SameAsScopeTest : MainScopeTest() {

    @Test
    fun same() {
        subjectFunction?.trueCondition
            ?.sameAs { true }
            ?.let { Assert.assertTrue(EXPECTED, true) }
            ?: run { Assert.fail(UN_EXPECTED) }

        subjectFunction?.falseCondition
            ?.sameAs { false }
            ?.let { Assert.assertTrue(EXPECTED, true) }
            ?: run { Assert.fail(UN_EXPECTED) }
    }

    @Test
    fun notSame() {
        subjectFunction?.falseCondition
            ?.notSameAs { true }
            ?.let { Assert.assertTrue(EXPECTED, true) }
            ?: run { Assert.fail(UN_EXPECTED) }

        subjectFunction?.trueCondition
            ?.notSameAs { false }
            ?.let { Assert.assertTrue(EXPECTED, true) }
            ?: run { Assert.fail(UN_EXPECTED) }
    }
}
