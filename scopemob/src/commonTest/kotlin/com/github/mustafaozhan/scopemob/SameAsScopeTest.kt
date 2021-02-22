/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class SameAsScopeTest : MainScopeTest() {

    @Test
    fun same() {
        subjectFunction?.trueCondition
            ?.sameAs { true }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction?.falseCondition
            ?.sameAs { false }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }

    @Test
    fun notSame() {
        subjectFunction?.falseCondition
            ?.notSameAs { true }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction?.trueCondition
            ?.notSameAs { false }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }
    }
}
