/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

import com.github.submob.scopemob.extension.failTest
import com.github.submob.scopemob.extension.passTest
import com.github.submob.scopemob.main.MainScopeTest
import kotlin.test.Test

class EitherScopeTest : MainScopeTest() {

    @Test
    fun eitherMixMix() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun eitherMixTrue() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ trueCondition }, { trueCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }

    @Test
    fun eitherMixFalse() {
        subjectFunction
            ?.either({ it.trueCondition }, { it.falseCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.either({ it.falseCondition }, { it.trueCondition })
            ?.either({ falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMix() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMixTrue() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ trueCondition }, { trueCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotMixFalse() {
        subjectFunction
            ?.eitherNot({ it.trueCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }

        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.trueCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun eitherNotFalseFalse() {
        subjectFunction
            ?.eitherNot({ it.falseCondition }, { it.falseCondition })
            ?.eitherNot({ falseCondition }, { falseCondition })
            ?.let { passTest() }
            ?: run { failTest() }
    }
}
