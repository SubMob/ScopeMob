package com.github.mustafaozhan.scopemob

inline fun <reified T> T.sameAs(
    method: T.(condition: T) -> T
) =
    if (this == method(this)) {
        this
    } else {
        null
    }

inline fun <reified T> T.notSameAs(
    method: T.(condition: T) -> T
) =
    if (this != method(this)) {
        this
    } else {
        null
    }
