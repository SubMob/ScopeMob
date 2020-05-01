/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.scopemob

inline fun <reified T, reified R> T.mapTo(
    transform: T.(map: T) -> R
) = transform(this)

inline fun <reified T> Any.castTo() =
    this as? T
