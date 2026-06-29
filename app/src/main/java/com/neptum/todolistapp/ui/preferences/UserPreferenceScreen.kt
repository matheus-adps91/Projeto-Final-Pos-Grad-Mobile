package com.neptum.todolistapp.ui.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neptum.todolistapp.data.datastore.AppTheme
import com.neptum.todolistapp.ui.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPreferenceScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    userPreferenceViewModel: UserPreferenceViewModel = koinViewModel(),
    themePreferenceViewModel: ThemePreferenceViewModel = koinViewModel()
) {
    val preferences by userPreferenceViewModel.preferences.collectAsState()
    var name by remember { mutableStateOf("") }

    LaunchedEffect(preferences) {
        preferences?.let {
            name = it.name
        }
    }

    LaunchedEffect(Unit) {
        userPreferenceViewModel.events.collect { event ->
            when (event) {
                is PreferencesEvent.LogoutSuccess -> {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            }
        }
    }

    if (preferences == null) {
        // Opcional: Mostrar um loading
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preferências") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(96.dp)
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = preferences?.name ?: "",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = {
                                //onChangePhoto
                            }
                        ) {
                            Text("Alterar foto")
                        }
                    }
                }
            }
            item {
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Perfil",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(16.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Nome")
                            }
                        )
                        Spacer(Modifier.height(16.dp))
                        Button(
                            modifier = Modifier.align(Alignment.End),
                            onClick = { userPreferenceViewModel.saveName(name) }
                        ) {
                            Text(text = "Salvar")
                        }
                    }
                }
            }
            item {
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Aparência",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        AppTheme.entries.forEach { theme ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = preferences?.theme == theme,
                                    onClick = { themePreferenceViewModel.saveTheme(theme) }
                                )
                                Text(theme.name)
                            }
                        }
                    }
                }
            }
            item {
                Button(
                    onClick = { userPreferenceViewModel.logOut() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sair da conta")
                }
            }
        }
    }
}