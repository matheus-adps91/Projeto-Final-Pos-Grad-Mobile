package com.neptum.todolistapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TaskFormCard(
    viewModel: HomeViewModel = koinViewModel(),
    task: Task? = null,
    onDismiss: () -> Unit
) {

    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var dateTime by remember { mutableStateOf(LocalDateTime.now()) }
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = if (task == null) "Nova Tarefa" else "Editar Tarefa",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                DateTimeField(
                    dateTime = dateTime.format(formatter),
                    onClick = {
                        showDatePicker = true
                    }
                )

                if (showDatePicker) {
                    DatePickerDialogComponent(
                        onDateSelected = { selected ->
                            dateTime = selected
                        },
                        onDismiss = {
                            showDatePicker = false
                            showTimePicker = true
                        })
                }

                if (showTimePicker) {
                    TimePickerDialogComponent(
                        onTimeSelected = { hour, minute ->
                            dateTime = dateTime.withHour(hour).withMinute(minute)
                        },
                        onDismiss = { showTimePicker = false }
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Button(
                    onClick = {
                        viewModel.saveTask(
                            id = task?.id,
                            title = title,
                            description = description,
                            finished = task?.finished ?: false,
                            finishDateTime = dateTime!!
                        )
                        onDismiss()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}