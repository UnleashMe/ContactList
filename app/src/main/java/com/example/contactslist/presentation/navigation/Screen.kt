package com.example.contactslist.presentation.navigation

sealed class Screen(val route: String) {

    object ContactListScreen: Screen("contact_list")
    object AddContactScreen: Screen("add_contact?number={number}") {
        fun passNumber(contactNumber: String?): String {
            return "add_contact?number=$contactNumber"
        }
    }
}