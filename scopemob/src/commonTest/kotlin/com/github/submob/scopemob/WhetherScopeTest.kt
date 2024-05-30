/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test

internal class WhetherScopeTest : MainScopeTest() {

    @Test
    fun whetherTrue() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { it.trueCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { it.trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherFalse() {
        subjectFunction
            ?.whether { it.falseCondition }
            ?.whether { it.falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.falseCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherMix() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { it.falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotFalse() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { it.falseCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { it.falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherNotTrue() {
        subjectFunction
            ?.whetherNot { it.trueCondition }
            ?.whetherNot { it.trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.trueCondition }, { it.trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotMix() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { it.trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { it.trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
