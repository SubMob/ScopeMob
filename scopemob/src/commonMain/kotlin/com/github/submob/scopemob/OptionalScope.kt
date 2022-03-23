/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.submob.scopemob

inline fun <reified T, R> ensure(
    vararg elements: T?,
    closureSafe: () -> R
): R? = if (elements.all { it != null }) {
    closureSafe()
} else {
    null
}

inline fun <reified T> T?.justInCase(block: () -> Unit) {
    if (this == null) block()
}
