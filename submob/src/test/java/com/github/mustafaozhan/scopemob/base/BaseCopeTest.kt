package com.github.mustafaozhan.scopemob.base

import com.github.mustafaozhan.scopemob.model.FunctionTestSubject

abstract class BaseCopeTest {

    companion object {
        const val UN_EXPECTED = "Unexpected"
        const val EXPECTED = "Expected"

        @Suppress("MaybeConst")
        val SOME_STRING: String? = "Some String"
    }

    protected var subjectFunction: FunctionTestSubject? = FunctionTestSubject()
}
