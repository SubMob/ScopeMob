/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("Filename")

package com.github.submob.scopemob

inline fun <reified T> T.inCase(
    condition: Boolean?,
    method: (T) -> Unit
): T {
    if (condition == true) {
        method(this)
    }
    return this
}

inline fun <reified T> T.inCaseNot(
    condition: Boolean?,
    method: (T) -> Unit
): T {
    if (condition == false) {
        method(this)
    }
    return this
}

inline fun <reified T> T.inCase(
    vararg condition: (T) -> Boolean?,
    method: (T) -> Unit
): T {
    if (condition.all { it(this) == true }) {
        method(this)
    }
    return this
}

inline fun <reified T> T.inCaseNot(
    vararg condition: (T) -> Boolean?,
    method: (T) -> Unit
): T {
    if (condition.all { it(this) == false }) {
        method(this)
    }
    return this
}
