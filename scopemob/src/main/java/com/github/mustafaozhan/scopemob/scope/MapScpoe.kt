package com.github.mustafaozhan.scopemob.scope

inline fun <reified T, reified R> T.mapTo(
    transform: T.(map: T) -> R?
): R? =
    transform(this)
