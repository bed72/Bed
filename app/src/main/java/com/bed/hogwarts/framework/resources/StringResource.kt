package com.bed.hogwarts.framework.resources

import androidx.annotation.StringRes

import com.bed.hogwarts.R

enum class StringResource(@StringRes val value: Int) {
    GET_OFFERS_FAILURE(R.string.failure_get_offers)
}
