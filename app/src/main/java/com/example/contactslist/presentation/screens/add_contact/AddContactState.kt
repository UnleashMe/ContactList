package com.example.contactslist.presentation.screens.add_contact

data class AddContactState(
    val id: String = "",
    val number: String = "",
    val name: String = "",
    val address: String = "",
    val numberError: String = "",
    val nameError: String = "",
    val addressError: String = "",
    val isButtonEnabled: Boolean = false,
    val isAddMode: Boolean = true,
    val goBackEvent: Boolean = false
)