/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

inline fun <reified T> T.sameAs(
    method: (condition: T) -> T
): T? = if (this == method(this)) {
    this
} else {
    null
}

inline fun <reified T> T.notSameAs(
    method: (condition: T) -> T
): T? = if (this != method(this)) {
    this
} else {
    null
}
