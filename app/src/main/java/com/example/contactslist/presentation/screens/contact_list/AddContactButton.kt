package com.example.contactslist.presentation.screens.contact_list

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.contactslist.ui.theme.AppTheme

@Composable
fun AddContactButton(
    onButtonClick: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onButtonClick()
        },
        containerColor = MaterialTheme.colorScheme.scrim,
        shape = CircleShape,
        modifier = Modifier.size(AppTheme.dimens.big)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "add contact",
            modifier = Modifier.size(AppTheme.dimens.mediumLarge)
        )
    }
}