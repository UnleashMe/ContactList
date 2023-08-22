package com.example.contactslist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactslist.presentation.screens.add_contact.AddContactScreen
import com.example.contactslist.presentation.screens.contact_list.ContactListScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.ContactListScreen.route) {
        composable(Screen.ContactListScreen.route) {
            ContactListScreen(navController = navController)
        }
        composable(Screen.AddContactScreen.route) {
            AddContactScreen(navController = navController)
        }
    }
}