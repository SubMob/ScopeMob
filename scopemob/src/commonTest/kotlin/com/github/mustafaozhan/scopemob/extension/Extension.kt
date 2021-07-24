package com.github.mustafaozhan.scopemob.extension

import kotlin.test.assertTrue
import kotlin.test.fail

fun failTest() {
    fail()
}

fun passTest() {
    assertTrue(true)
}
