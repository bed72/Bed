package com.bed.hogwarts.framework.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object Utils {
    val dateNow get() = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
}
