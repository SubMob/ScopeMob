package com.github.mustafaozhan.scopemob

inline fun <reified T> T.same(
    method: T.(condition: T) -> T
) =
    if (this == method(this)) {
        this
    } else {
        null
    }

inline fun <reified T> T.notSame(
    method: T.(condition: T) -> T
) =
    if (this != method(this)) {
        this
    } else {
        null
    }
