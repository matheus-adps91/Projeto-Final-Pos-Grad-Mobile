package com.neptum.todolistapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogComponent(
    onDateSelected: (LocalDateTime) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                state.selectedDateMillis?.let {
                    val selected = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(it),
                        ZoneId.systemDefault()
                    )
                    onDateSelected(selected)
                }
                onDismiss()
            }){
                Text("ok")
            }
        }
    ) {
        DatePicker(state = state)
    }
}