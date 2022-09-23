/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

inline fun <reified T> T?.either(
    vararg method: T.(condition: T) -> Boolean
): T? = if (this != null) {
    if (method.any { it(this) }) {
        this
    } else {
        null
    }
} else {
    null
}

inline fun <reified T> T?.eitherNot(
    vararg method: T.(condition: T) -> Boolean
): T? = if (this != null) {
    if (!method.any { it(this) }) {
        this
    } else {
        null
    }
} else {
    null
}
