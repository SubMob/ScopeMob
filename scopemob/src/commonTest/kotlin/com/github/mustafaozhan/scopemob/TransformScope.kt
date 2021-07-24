/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.extension.failTest
import com.github.mustafaozhan.scopemob.extension.passTest
import com.github.mustafaozhan.scopemob.main.MainScopeTest
import com.github.mustafaozhan.scopemob.model.A
import com.github.mustafaozhan.scopemob.model.B
import com.github.mustafaozhan.scopemob.model.C
import kotlin.test.Test
import kotlin.test.assertEquals

class TransformScope : MainScopeTest() {

    @Test
    fun mapTo() {
        subjectFunction
            ?.mapTo { it.trueCondition }
            ?.whether { it }
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.mapTo { it.falseCondition }
            ?.whether { it }
            ?.let { failTest() }
            ?: run { passTest() }
        subjectFunction
            ?.mapTo { trueCondition }
            ?.whether { it }
            ?.let { passTest() }
            ?: run { failTest() }

        subjectFunction
            ?.mapTo { falseCondition }
            ?.whether { it }
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun extraordinaryMapTo() = subjectFunction
        .mapTo { someString }
        ?.mapTo { it -> it.length }
        ?.let { assertEquals(11, it) }
        ?: run { failTest() }

    @Test
    fun castTo() {
        B().castTo<A>()
            ?.let { passTest() }
            ?: run { failTest() }

        A().castTo<B>()
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun multiCastTo() {
        C().castTo<B>()
            ?.castTo<A>()
            ?.let { passTest() }
            ?: run { failTest() }

        A().castTo<B>()
            ?.castTo<C>()
            ?.let { failTest() }
            ?: run { passTest() }
    }

    @Test
    fun extraordinaryCastTo() = C().castTo<A>()
        ?.let { passTest() }
        ?: run { failTest() }
}
