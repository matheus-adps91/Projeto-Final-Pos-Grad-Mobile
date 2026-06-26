package com.neptum.todolistapp.data.mapper

import com.neptum.todolistapp.data.dto.TaskFirebaseDto
import com.neptum.todolistapp.domain.model.Task

fun Task.toTaskFirebaseDto(): TaskFirebaseDto {
    return TaskFirebaseDto(
        id = id,
        title = title,
        description = description,
        finished = finished
    )
}

fun TaskFirebaseDto.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        finished = finished
    )
}