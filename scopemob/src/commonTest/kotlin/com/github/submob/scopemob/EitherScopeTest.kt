/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test

internal class EitherScopeTest : MainScopeTest() {

    @Test
    fun eitherMixMix() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun eitherMixTrue() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ it.trueCondition }, { it.trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ it.trueCondition }, { it.trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun eitherMixFalse() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ it.falseCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ it.falseCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMix() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMixTrue() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ it.trueCondition }, { it.trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ it.trueCondition }, { it.trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMixFalse() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotFalseFalse() {
        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
