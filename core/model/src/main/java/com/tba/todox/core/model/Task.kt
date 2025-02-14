package com.tba.todox.core.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Task(
    val title:String ="",
    val description:String="",
    val dateTime: LocalDateTime? = null,
    val priority:Int? = null,
    val tags: Tag? = null
)
