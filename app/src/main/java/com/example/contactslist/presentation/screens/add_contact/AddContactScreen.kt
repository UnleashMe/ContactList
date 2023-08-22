package com.example.contactslist.presentation.screens.add_contact

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactslist.ui.theme.AppTheme

@Composable
fun AddContactScreen(
    viewModel: AddContactViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val scrollableState = rememberScrollState()

    LaunchedEffect(key1 = state.goBackEvent) {
        if (state.goBackEvent) {
            navController.navigateUp()
        }
    }
    Card(
        modifier = Modifier
            .padding(AppTheme.dimens.medium)
            .fillMaxWidth()
            .verticalScroll(scrollableState)
            .border(
                AppTheme.dimens.tiny,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(AppTheme.dimens.smallMedium)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Добавить контакт",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(vertical = AppTheme.dimens.smallMedium)
            )
            TextFieldWithError(
                value = state.number,
                onValueChange = { viewModel.onEvent(AddContactEvent.PhoneNumberInput(it)) },
                label = "Номер телефона",
                error = state.numberError,
                prefix = { Text(text = "+") },
                modifier = Modifier.padding(bottom = AppTheme.dimens.smallMedium),
            )
            TextFieldWithError(
                value = state.name,
                onValueChange = {
                    viewModel.onEvent(AddContactEvent.NameInput(it))
                },
                label = "Имя",
                error = state.nameError,
                modifier = Modifier.padding(bottom = AppTheme.dimens.smallMedium)
            )
            TextFieldWithError(
                value = state.address,
                onValueChange = { viewModel.onEvent(AddContactEvent.AddressInput(it)) },
                label = "Адрес",
                error = state.addressError
            )
            Button(
                onClick = {
                    viewModel.onEvent(AddContactEvent.ButtonClick)
                },
                enabled = state.isButtonEnabled,
                modifier = Modifier.width(AppTheme.dimens.humongous)
            ) {
                Text(
                    text = if (state.isAddMode) "Добавить" else "Изменить",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}