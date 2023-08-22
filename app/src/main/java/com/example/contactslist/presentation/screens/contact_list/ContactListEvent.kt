package com.example.contactslist.presentation.screens.contact_list

import com.example.contactslist.data.database.entities.Contact

sealed class ContactListEvent {
    data class DeleteContact(val contact: Contact) : ContactListEvent()
}
