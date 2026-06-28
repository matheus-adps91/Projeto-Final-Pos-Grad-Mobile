package com.neptum.todolistapp.data.mapper

import com.google.firebase.Timestamp
import com.neptum.todolistapp.data.dto.TaskFirebaseDto
import com.neptum.todolistapp.domain.model.Task
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

fun Task.toTaskFirebaseDto(): TaskFirebaseDto {
    return TaskFirebaseDto(
        id = id,
        title = title,
        description = description,
        finished = finished,
        finishDateTime = Timestamp(
            Date.from(finishDateTime.atZone(ZoneId.systemDefault()).toInstant())
        )
    )
}

fun TaskFirebaseDto.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        finished = finished,
        finishDateTime = finishDateTime?.toDate()?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime() ?: LocalDateTime.now()
    )
}