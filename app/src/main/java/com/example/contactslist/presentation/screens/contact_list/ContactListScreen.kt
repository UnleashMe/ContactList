package com.example.contactslist.presentation.screens.contact_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactslist.presentation.navigation.Screen
import com.example.contactslist.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    Scaffold(floatingActionButton = {
        AddContactButton {
            navController.navigate(route = Screen.AddContactScreen.route)
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isEmpty()) {
                Text(
                    text = "Как здесь пусто...",
                    modifier = Modifier.padding(top = AppTheme.dimens.mediumLarge),
                    style = MaterialTheme.typography.displayMedium
                )
            } else {
                LazyColumn {
                    item {
                        Text(
                            text = "Контакты",
                            modifier = Modifier
                                .padding(vertical = AppTheme.dimens.smallMedium)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                    items(state, key = { it.phoneNumber }) { contact ->
                        ContactItem(
                            contact = contact,
                            onDeleteClick = { viewModel.onEvent(ContactListEvent.DeleteContact(it)) },
                            onEditClick = {
                                navController.navigate(
                                    Screen.AddContactScreen.passNumber(
                                        it
                                    )
                                )
                            }, modifier = Modifier.animateItemPlacement()
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(AppTheme.dimens.big))
                    }
                }
            }
        }
    }
}