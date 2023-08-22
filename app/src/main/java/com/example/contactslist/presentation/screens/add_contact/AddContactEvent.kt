package com.example.contactslist.presentation.screens.add_contact

sealed class AddContactEvent {
    data class PhoneNumberInput(val text: String) : AddContactEvent()
    data class NameInput(val text: String) : AddContactEvent()
    data class AddressInput(val text: String) : AddContactEvent()
    object ButtonClick : AddContactEvent()
}
