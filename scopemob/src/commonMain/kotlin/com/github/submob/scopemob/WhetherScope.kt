/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

inline fun <reified T> T.whether(
    method: (condition: T) -> Boolean
): T? = if (this != null && method(this)) {
    this
} else {
    null
}

inline fun <reified T> T.whether(
    vararg method: (condition: T) -> Boolean
): T? = if (this != null) {
    if (method.all { it(this) }) {
        this
    } else {
        null
    }
} else {
    null
}

inline fun <reified T> T.whetherNot(
    method: (condition: T) -> Boolean
): T? = if (this != null && !method(this)) {
    this
} else {
    null
}

inline fun <reified T> T.whetherNot(
    vararg method: (condition: T) -> Boolean
): T? = if (this != null) {
    if (!method.all { it(this) }) {
        this
    } else {
        null
    }
} else {
    null
}
