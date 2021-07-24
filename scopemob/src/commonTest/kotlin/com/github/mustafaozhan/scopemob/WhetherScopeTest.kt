/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.extension.failTest
import com.github.mustafaozhan.scopemob.extension.passTest
import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test

class WhetherScopeTest : MainScopeTest() {

    @Test
    fun whetherTrue() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { trueCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherFalse() {
        subjectFunction
            ?.whether { it.falseCondition }
            ?.whether { falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherMix() {
        subjectFunction
            ?.whether { it.trueCondition }
            ?.whether { falseCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whether({ it.trueCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotFalse() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { falseCondition }
            ?.let { passTest() }
            ?: run { failTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun whetherNotTrue() {
        subjectFunction
            ?.whetherNot { it.trueCondition }
            ?.whetherNot { trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.trueCondition }, { trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun whetherNotMix() {
        subjectFunction
            ?.whetherNot { it.falseCondition }
            ?.whetherNot { trueCondition }
            ?.let { failTest() }
            ?: run { passTest() }

        // vararg
        subjectFunction
            ?.whetherNot({ it.falseCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
