/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test

internal class SameAsScopeTest : MainScopeTest() {

    @Test
    fun same() {
        subjectFunction?.trueCondition
            ?.sameAs { true }
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction?.falseCondition
            ?.sameAs { false }
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun notSame() {
        subjectFunction?.falseCondition
            ?.notSameAs { true }
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction?.trueCondition
            ?.notSameAs { false }
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
