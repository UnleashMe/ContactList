package com.example.contactslist.presentation.screens.contact_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.contactslist.data.database.entities.Contact
import com.example.contactslist.ui.theme.AppTheme

@Composable
fun ContactItem(
    contact: Contact,
    onEditClick: (String) -> Unit,
    onDeleteClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.small),
        border = BorderStroke(AppTheme.dimens.tiny, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(AppTheme.dimens.smallMedium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(AppTheme.dimens.small),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .padding(AppTheme.dimens.smallMedium),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "+${contact.phoneNumber}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(text = contact.name, style = MaterialTheme.typography.bodyMedium)
                Text(text = contact.address, style = MaterialTheme.typography.bodyMedium)
            }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    IconButton(
                        onClick = { onEditClick(contact.phoneNumber) },
                        modifier = Modifier.size(AppTheme.dimens.mediumLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit_contact",
                            modifier = Modifier.size(AppTheme.dimens.medium)
                        )
                    }
                    IconButton(
                        onClick = { onDeleteClick(contact) },
                        modifier = Modifier.size(AppTheme.dimens.mediumLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete_contact",
                            modifier = Modifier.size(AppTheme.dimens.medium)
                        )
                    }
                }
            }
        }
    }
}