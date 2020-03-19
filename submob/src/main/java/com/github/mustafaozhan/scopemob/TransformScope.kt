package com.github.mustafaozhan.scopemob

inline fun <reified T, reified R> T.mapTo(
    transform: T.(map: T) -> R?
): R? =
    transform(this)

inline fun <reified T> Any.castTo() =
    this as? T
