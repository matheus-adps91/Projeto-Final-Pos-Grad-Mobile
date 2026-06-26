package com.neptum.todolistapp.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.ui.components.TaskCard
import com.neptum.todolistapp.ui.home.HomeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(
    state: HomeUiState,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onToggleStatus: (Task) -> Unit
) {
    LazyColumn {
        items(state.tasks, key = { it.id }) { task ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    if (it == SwipeToDismissBoxValue.EndToStart) {
                        onDelete(task)
                        true
                    } else {
                        false
                    }
                }
            )

            SwipeToDismissBox(
                state = dismissState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            SwipeToDismissBoxValue.EndToStart -> Color.Red.copy(alpha = 0.8f)
                            else -> Color.Transparent
                        }, label = "dismissBackground"
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .background(color, shape = MaterialTheme.shapes.medium),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Deletar",
                            modifier = Modifier.padding(end = 16.dp),
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier.animateItem()
            ) {
                TaskCard(
                    task,
                    onEdit = { onEdit(task) },
                    onDelete = { onDelete(task) },
                    onToggleStatus = { onToggleStatus(task) }
                )
            }
        }
    }
}
