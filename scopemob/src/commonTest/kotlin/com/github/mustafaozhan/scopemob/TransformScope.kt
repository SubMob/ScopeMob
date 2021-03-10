/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

import com.github.mustafaozhan.scopemob.main.MainScopeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class TransformScope : MainScopeTest() {

    open class A
    open class B : A()
    class C : B()

    @Test
    fun mapTo() {
        subjectFunction
            ?.mapTo { it.trueCondition }
            ?.whether { it }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.mapTo { it.falseCondition }
            ?.whether { it }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
        subjectFunction
            ?.mapTo { trueCondition }
            ?.whether { it }
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        subjectFunction
            ?.mapTo { falseCondition }
            ?.whether { it }
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun extraordinaryMapTo() = subjectFunction
        .mapTo { SOME_STRING }
        ?.mapTo { it -> it.length }
        ?.let { assertEquals(11, it) }
        ?: run { fail(UN_EXPECTED) }

    @Test
    fun castTo() {
        B().castTo<A>()
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        A().castTo<B>()
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun multiCastTo() {
        C().castTo<B>()
            ?.castTo<A>()
            ?.let { assertTrue(true, EXPECTED) }
            ?: run { fail(UN_EXPECTED) }

        A().castTo<B>()
            ?.castTo<C>()
            ?.let { fail(UN_EXPECTED) }
            ?: run { assertTrue(true, EXPECTED) }
    }

    @Test
    fun extraordinaryCastTo() = C().castTo<A>()
        ?.let { assertTrue(true, EXPECTED) }
        ?: run { fail(UN_EXPECTED) }
}
