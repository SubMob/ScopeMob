/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.extension.failTest
import com.github.mustafaozhan.scopemob.extension.passTest
import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test

class SameAsScopeTest : MainScopeTest() {

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
